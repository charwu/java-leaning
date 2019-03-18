package com.wxb.blog.common.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.wxb.blog.common.utils.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * spring mvc converter
 * 
 * <pre>
 * content-type=application/json;charset=UTF-8 用于json的xss过滤 
 * 如果处理xss失败，会返回默认的未处理过xss的Object
 * ***************************************************
 * ***************************************************
 * NOTE:
 * 	1.默认会处理每一个请求路径的form
 *  2.只处理有@NeedXss注解的属性
 *  3.只处理String类型的属性
 * ***************************************************
 * ***************************************************
 * </pre>
 * 
 * * Implementation of {@link org.springframework.http.converter.HttpMessageConverter
 * HttpMessageConverter} that can read and write JSON using <a
 * href="http://jackson.codehaus.org/">Jackson 1.x's</a> {@link ObjectMapper}.
 * 
 * <p>
 * This converter can be used to bind to typed beans, or untyped {@link java.util.HashMap HashMap}
 * instances.
 * 
 * <p>
 * By default, this converter supports {@code application/json}. This can be overridden by setting
 * the {@link #setSupportedMediaTypes supportedMediaTy pes} property.
 * 
 * @author hujianjun
 * @date 2016年3月30日
 */
public class XssMappingJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter implements MessageConverterHandler<Object, Type> {

	/**
	 * 不需要xss过滤的路径
	 */
	protected final static List<String> urls;

	static {
		urls = new ArrayList<String>();
		// for example urls.add("/trade/noticeBuyerHost");
	}

	@Override
	public Object read( Type type, Class<?> contextClass, HttpInputMessage inputMessage ) throws IOException,
            HttpMessageNotReadableException {

		JavaType javaType = getJavaType( type, contextClass );

		Object obj = readJavaType( javaType, inputMessage );

		Object tempObj = this.process( obj, type, inputMessage );

		return tempObj;
	}

	private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage ) {
		try {
			return super.getObjectMapper().readValue( inputMessage.getBody(), javaType );
		} catch ( IOException ex ) {
			throw new HttpMessageNotReadableException( "Could not read JSON: " + ex.getMessage(), ex );
		}
	}

	protected Object process( Object obj, Type type, HttpInputMessage inputMessage ) {
		if ( this.isNeedProcess( inputMessage ) ) {
			return this.readAfter( obj, type );
		} else {
			return obj;
		}
	}

	protected boolean isNeedProcess( HttpInputMessage inputMessage ) {

		String url = "";

		try {
			ServletServerHttpRequest request = (ServletServerHttpRequest) inputMessage;

			url = request.getURI().getPath();

			if ( !StringUtils.isBlank( url ) ) {

				// 只处理以“/”开始的路径
				if ( !url.startsWith( "/" ) ) {
					return true;
				}

				int index = url.indexOf( "/" );

				int lastIndex = url.lastIndexOf( "/" );

				// 如果只有一个“/”，则不处理url
				if ( index != lastIndex ) {

					if ( index != 0 ) {
						return true;
					}

					url = url.substring( index + 1 );

					index = url.indexOf( "/" );

					url = url.substring( index );
				}

			}

			if ( urls != null && urls.size() > 0 ) {

				for ( int i = 0; i < urls.size(); i++ ) {
					if ( urls.get( i ).equals( url ) ) {
						return false;
					}
				}

			}

		} catch ( Exception e ) {
			logger.error( "BACK_ERROR," + this.getClass().getCanonicalName() + ",XSS处理-url处理失败,url=" + url + ",ERROR=", e );
			return true;
		}

		return true;

	}

	@Override
	public Object readAfter( Object obj, Type type ) {
		try {
			Class<?> clazz = Class.forName( JSON.toJSONString( type ).replace( "\"", "" ) );

			if ( clazz == null ) {
				return obj;
			}

			Field[] fields = clazz.getDeclaredFields();

			if ( fields != null && fields.length > 0 ) {
				// string类型字段名称列表
				List<String> strList = new ArrayList<String>( fields.length );

				// 1. 将需要xss处理的string类型的字段放入strlist
				for ( int i = 0; i < fields.length; i++ ) {

					// 1.1该属性是否有NotXss.class注解
					NotXss notXss = fields[ i ].getAnnotation( NotXss.class );
					// 1.2如果该属性拥有NotXss.class注解，则不处理该属性
					if ( notXss != null && notXss instanceof NotXss ) {
						continue;
					}

					NeedXss needXss = fields[ i ].getAnnotation( NeedXss.class );
					// 1.3如果该没有NeedXss.class注解，则不处理该属性
					if ( needXss == null || !( needXss instanceof NeedXss ) ) {
						continue;
					}

					String mod = Modifier.toString( fields[ i ].getModifiers() );
					if ( mod.indexOf( "static" ) != -1 )
						continue;
					// 得到属性的类名
					String className = fields[ i ].getType().getSimpleName();
					// 得到属性字段名
					if ( className.equalsIgnoreCase( "String" ) ) {

						strList.add( fields[ i ].getName() );
					}

				}

				// 2.将strlist中的字段进行xss处理
				if ( strList.size() > 0 ) {

					Object temp = JSON.toJavaObject( (JSON) JSON.toJSON( obj ), clazz );

					for ( int i = 0; i < strList.size(); i++ ) {
						Method set = clazz.getMethod( "set" + strList.get( i ).substring( 0, 1 ).toUpperCase()
								+ strList.get( i ).substring( 1 ), String.class );
						Method get = clazz.getMethod( "get" + strList.get( i ).substring( 0, 1 ).toUpperCase()
								+ strList.get( i ).substring( 1 ) );

						Object tempObj = get.invoke( temp );

						if ( tempObj == null ) {
							break;
						}

						String content = tempObj.toString();

						set.invoke( temp, StringUtils.cleanXss( content ) );
					}

					return temp;
				}

			}

		} catch ( Exception e ) {
			logger.error( "BACK_ERROR," + this.getClass().getCanonicalName() + ",XSS处理失败,obj=" + JSON.toJSONString( obj ) + ",javaType="
					+ JSON.toJSONString( type ) + ",ERROR=", e );
			return obj;
		}

		return obj;
	}
}
