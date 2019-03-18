package com.wxb.blog.common.utils;



import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.common.constants.GeneralConstants;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2014/9/26.
 */
public class StringUtils {

	/**
	 * 
	 * @Description: 将String serviceId 转化 为 Integer
	 * @param serviceIds
	 * @return
	 * @return: List<Integer>
	 * @author: wujun
	 * @date: 2016年9月20日 下午12:32:12
	 */
	public static List<Integer> converStringList2IntegerList(List<String> serviceIds) {

		if (CollectionUtils.isEmpty(serviceIds)) {
			return null;
		}
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < serviceIds.size(); i++) {
			if (org.springframework.util.StringUtils.isEmpty(serviceIds.get(i))) {
				continue;
			}
			try {
				res.add(Integer.parseInt(serviceIds.get(i)));
			} catch (Exception e) {

			}
		}
		return res;
	}

	/**
	 * ojy 过滤掉文本中的html标签
	 * 
	 * @param str
	 * @return
	 */

	public static String getWithOutTag(String str) {
		@SuppressWarnings("unused")
		String result = "";
		String value = str.replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "");
		return value;
	}

	// 保留两位小数
	public static String getTwodecimalPlaces(double value) {
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal bigDecimal = new BigDecimal(value);
		BigDecimal result = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		return df.format(result.doubleValue());
	}

	// 获取特权名称
	public static String getPrivilege(int key) {
		String result = "";
		switch (key) {
		case 6:
			result = "八戒通";
			break;
		case 4:
			result = "皇冠";
			break;
		case 3:
			result = "钻石";
			break;
		case 2:
			result = "金牌";
			break;
		case 1:
			result = "银牌";
			break;
		default:
			result = "普通";
			break;
		}
		return result;
	}

	public static String getGoodCommentRatio(double goodCommentRatio) {
		return goodCommentRatio * 100 + "%";
	}

	/**
	 * 判断字符串是否为空
	 * */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空(空格也为空)
	 * */
	public static boolean isBlank(String str) {
		if (str == null || "".equals(str) || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是数字（限整型）
	 * */
	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 做了异常处理的trim方法
	 * 
	 * @param str
	 *            字符串
	 * @return 处理过的字符串
	 * */
	public static String toTrim(String str) {
		if (str != null) {
			return str.trim();
		}
		return str;
	}

	/**
	 * 做过异常处理的字符串转long型
	 * 
	 * @param
	 * @return 转换失败返回0
	 * */
	public static long parseLong(String str) {
		long result = 0;
		try {
			result = Long.parseLong(str);
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 做过异常处理的字符串转整型
	 * 
	 * @param
	 * @return 转换失败返回0
	 * */
	public static int parseInt(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 保留两位小数
	 * */
	public static double getDoubleF2(double in) {
		return (double) Math.round(in * 100) / 100;
	}

	/**
	 * 浮点型转sting去除科学计数
	 * */
	public static String doubleToString(double d) {
		BigDecimal big = new BigDecimal(d);
		big = big.setScale(2, BigDecimal.ROUND_HALF_UP);
		return big.toPlainString();

	}

	

	/**
	 * 转译xss攻击脚本
	 * */
	public static String cleanXss(String value) {
		if (value != null) {
			value = value.replaceAll("\\\"", "&quot;");
			value = value.replaceAll("&", "&amp;");
			value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
			value = value.replaceAll("eval\\((.*)\\)", "");
			value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
			value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		}
		return value;
	}

	public static void cleanXss(Object form, String[] args) throws BusinessException {
		if (form != null) {
			for (String name : args) {
				try {
					Method set = form.getClass().getMethod(
							"set" + name.substring(0, 1).toUpperCase() + name.substring(1),
							String.class);
					Method get = form.getClass().getMethod(
							"get" + name.substring(0, 1).toUpperCase() + name.substring(1));
					set.invoke(form, cleanXss(get.invoke(form).toString()));
				} catch (Exception e) {
					throw new BusinessException(ErrorConstants.SAFE_ERR_CODE, ErrorConstants.SAFE_ERR_MESSAGE);
				}
			}
		}
	}

	public static void cleanXss(Object form) {
		if (form != null) {
			Field[] field = form.getClass().getDeclaredFields();
			for (Field field2 : field) {
				try {
					if (field2.getGenericType().toString().equals("class java.lang.String")) {
						String name = field2.getName();
						Method set = form.getClass().getMethod(
								"set" + name.substring(0, 1).toUpperCase() + name.substring(1),
								String.class);
						Method get = form.getClass().getMethod(
								"get" + name.substring(0, 1).toUpperCase() + name.substring(1));
						set.invoke(form, cleanXss(get.invoke(form).toString()));
					}
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 取除HL
	 * 
	 * @param str
	 * @return
	 */
	public static String getStrHlReplace(String str) {
		String htmlStr = "";
		String regEx_hl = "<[^>]+>"; // html正则表达式
		Pattern p_hl = Pattern.compile(regEx_hl, Pattern.CASE_INSENSITIVE);
		if (str != null && !"".equals(str)) {
			Matcher m_style = p_hl.matcher(str);
			htmlStr = m_style.replaceAll(""); // 过滤hl标签
		} else {
			htmlStr = str;
		}
		return htmlStr.trim();
	}

	public static String getIp4NumStr(String ip) {
		String[] ips = ip.split("[.]");
		long num = 16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256
				* Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
		return String.valueOf(num);
	}

	public static String big2Str(BigDecimal num) {
		if (num != null) {
			num = num.setScale(2, BigDecimal.ROUND_HALF_UP);
			return num.toString();
		} else {
			return null;
		}
	}

	public static String obj2Str(Object num) {
		if (num != null) {
			return num.toString();
		} else {
			return null;
		}
	}

	public static boolean isEmptyByPhp(String str) {
		if (str == null || "".equals(str) || "null".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: Html2Text
	 * @Description: 过滤html标签
	 * @param inputString
	 * @return
	 * @return String
	 * @throws
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;

		Pattern p_html1;
		Matcher m_html1;

		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	public static boolean number2boolean(int num) {
		if (num == 0) {
			return false;
		} else if (num == 1) {
			return true;
		}
		return false;
	}

	public static Map<String, String> getMap(String url) {
		Map<String, String> result = new HashMap<String, String>();
		String[] pairs = url.split("&");
		for (String p : pairs) {
			String[] pair = p.split("=");
			result.put(pair[0], pair[1]);
		}
		return result;
	}

	/**
	 * 替换内容的前两位为*
	 * 
	 * @param val
	 * @return
	 */
	public static String replaceStrJionXin(String val) {

		if (val.length() >= 1 && val.length() <= 2) {
			val = "**";
		}
		if (val.length() > 2) {
			val = "**" + val.substring(2);
		}
		return val;
	}

	/**
	 * double格式化
	 * */
	public static String monyFormat(double mony, String formatStr) {
		if (Double.isNaN(mony)) {
			return "";
		}
		if (StringUtils.isBlank(formatStr)) {
			formatStr = "#.##";
		}
		DecimalFormat format = new DecimalFormat(formatStr);
		return format.format(mony);
	}

 	/**
     * @Description: 比较app版本号
     * @param softVersion1
     * @param softVersion2
     * @return
     * @return: int
     * @author: xueyuwei
     * @date: 2016年8月31日 上午10:46:07
     */
    public static int compareVersion(String softVersion1, String softVersion2) {
		int compare = 0;
		String[] str1 = softVersion1.split("\\.");
		String[] str2 = softVersion2.split("\\.");
		int len = str1.length;
		if (str2.length < len) {
			len = str2.length;
		}
		for (int i = 0; i < len; i++) {
			if (Integer.parseInt(str1[i]) < Integer.parseInt(str2[i])) {
				compare = -1;
				break;
			} else if (Integer.parseInt(str1[i]) > Integer.parseInt(str2[i])) {
				compare = 1;
				break;
			}
		}
		if (compare == 0) {
			if (str1.length < str2.length) {
				compare = -1;
			} else if (str1.length > str2.length) {
				compare = 1;
			}
		}
		return compare;
    }

    /**
     * @param appClientType app类型
     * @param clientVersion app版本号
     * @param version 指定版本号
     * @param clientType 指定app类型 1 buyer
     * @param type 判断方法 0等于 1大于等于 2小于等于 3大于 4小于  
     * @return boolean
     * @title 是否满足指定版本判断
     * @author xupu
     */
    public static boolean versionCheck(String appClientType,String clientVersion,String version,String clientType,int type) {
    	if(!appClientType.equals(clientType)){
    		return true;
    	}
    	switch (type) {
		case 0:
			if(compareVersion(clientVersion,version) == 0){
				return true;
			}
			break;
		case 1:
			if(compareVersion(clientVersion,version) >= 0){
				return true;
			}
			break;
		case 2:
			if(compareVersion(clientVersion,version) <= 0){
				return true;
			}
			break;
		case 3:
			if(compareVersion(clientVersion,version) > 0){
				return true;
			}
			break;
		case 4:
			if(compareVersion(clientVersion,version) < 0){
				return true;
			}
			break;
		default:
			return false;
		}
    	
    	return false;
    }

	/**
	 * 获取32位UUID字符串
	 * 
	 * @return
	 */
	public static String getUUID32() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
    
    /**
     * List Integer 转换List String
     * @param searchIds
     * @return
     *
     */
    public static List<String> toStringList(List<Integer> searchIds) {
    	if (CollectionUtils.isEmpty(searchIds)) {
			return null;
		}
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < searchIds.size(); i++) {
            stringList.add(searchIds.get(i) + "");
        }
        return stringList;
    }
    
    /** 
     * @Description: 将罗马数字转换成中文数字
     * @param n （n >= 0)
     * @return: String
     * @author: YinKailin
     * @date: 2017年8月29日 上午11:55:56 
     */
    public static String toChineseNumber(int n) {
    	final String[] weights = {
    			"",
    			"十",
    			"百",
    			"千",
    			"万",
    			"十", // "十万"
    			"百", // "百万"
    			"千", // "千万"
    			"亿",
    			"十", // "十亿"
    			"百", // "百亿"
    			"千", // "千亿"
    			"万"  // "万亿"
    	};
    	
    	final String[] numbers = {
    			"零",
    			"一",
    			"二",
    			"三",
    			"四",
    			"五",
    			"六",
    			"七",
    			"八",
    			"九",
    			"十"
    	};
    	
    	n = Math.abs(n);// n = |n|
    	if(n == 0) {
    		return numbers[0];
    	}
    	
    	// find the weight of each digit number
    	List<Tuple<Integer, Integer>> numberWeight = new ArrayList<>();
    	int rank = 0;
    	while(n > 0) {
    		numberWeight.add(new Tuple<Integer, Integer>(n % 10, rank++));
    		n /= 10;
    	}
    	
    	// traverse the number weights tuple list in reverse order to 
    	// generate the chinese representation of roman numerals
    	StringBuilder chineseNumber = new StringBuilder();
    	ListIterator<Tuple<Integer, Integer>> it = numberWeight.listIterator(numberWeight.size());
    	while(it.hasPrevious()) {
    		Tuple<Integer, Integer> tuple = it.previous();
    		
    		if (tuple.value1 > 0) {
				chineseNumber.append(numbers[tuple.value1]).append(weights[tuple.value2]);
			
			// ignore the weight of number 0 
			} else {
				chineseNumber.append(numbers[tuple.value1]);
			}
    	}
    	
    	// replace multiple consecutive "零" with only one 
    	String result = chineseNumber.toString().replaceAll("零+", "零");
    	
    	// remove the trailing "零"
    	if("零".equals(result.substring(result.length() - 1))) {
    		result = result.substring(0, result.length() - 1);
    	}
    	
    	return result;
    }
    
    static class Tuple<K, T> {
    	K value1;
    	T value2;
    	
    	public Tuple(K value1, T value2) {
    		this.value1 = value1;
    		this.value2 = value2;
    	}
    }

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
    
    /**
     * 判断字符串是否为null
     * @param st
     * @return boolean
     */
    public static boolean isNullStr(String st){
    	if(null == st || "".equals(st) || "".equals(st.trim()) || "null".equals(st) || "(null)".equals(st) || "&#40;null&#41;".equals(st)){
    		return true;
    	}
    	return false;
    }
    /** 
     * unicode 转字符串 
     */  
    public static String unicodeToString(String str) {
    	
    	if (str == null) {
    		return null;
    	}
    	
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch+"" );

        }

        return str;

    }
    
	/**
	 * 获取数字列表，入参形式为 123,123
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> toIntList(String str) {
		List<Integer> nums = new ArrayList<>();
		if (StringUtils.isBlank(str)) {
			return null;
		}
		for (String num : str.split(",")) {
			// 数字加入列表，非数字忽略
			try {
				nums.add(Integer.valueOf(num));
			} catch (NumberFormatException e) {

			}
		}
		if (nums.size() == 0) {
			return null;
		} else {
			return nums;
		}
	}
    
}
