package com.wxb.blog.common.generator;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author huadongnan
 * @title
 * @date 2018/3/20.
 * @since 1.0.0
 */
public abstract class AbstractGenerator implements MybatisGenerator {

	private static final String xmlPath = "./src/main/resources/generator";

	private static final String mapperPath = "./src/main/resources/com/wxb/blog/dao/mapper";

	protected String pack;

	public void generate(String message) {
		try {
//			this.dealOldFile();
			ArrayList warnings = new ArrayList();
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration configuration = cp.parseConfiguration(new StringReader(message));
			DefaultShellCallback callback = new DefaultShellCallback(true);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
			myBatisGenerator.generate((ProgressCallback) null);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void dealOldFile() {
		if(StringUtils.isBlank(pack)) {
			return;
		}
		String path = new File(mapperPath + File.separatorChar + pack).getAbsolutePath();
		File file = new File(path);
		if(!file.exists()) {
			return;
		}
		for (String str : file.list()) {
			File f = new File(path + File.separatorChar + str);
			if (f.isDirectory()) {
				continue;
			}
			f.delete();
		}
	}

	public String getTemplate(String path) throws Exception {
		if(StringUtils.isBlank(path)){
			path = "template.xml";
		}
		path = (new File(xmlPath)).getAbsolutePath() + File.separatorChar + path;
		return new Scanner( new File(path) ).useDelimiter("\\Z").next();
	}

	public String getTableTemplate(String path, String key) throws Exception {
		if(StringUtils.isBlank(path)){
			if(StringUtils.isBlank(key)){
				path = "tableTemplate.xml";
			}else {
				path = "tableTemplateWithKey.xml";
			}
		}
		path = (new File(xmlPath)).getAbsolutePath() + File.separatorChar + path;
		return new Scanner( new File(path) ).useDelimiter("\\Z").next();
	}
}
