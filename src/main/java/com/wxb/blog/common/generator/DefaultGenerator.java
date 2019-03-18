package com.wxb.blog.common.generator;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author huadongnan
 * @title
 * @date 2018/3/20.
 * @since 1.0.0
 */
public class DefaultGenerator extends AbstractGenerator {

	public String getMessage(GeneratorConfig config, List<String> tables) throws Exception {
		if(StringUtils.isBlank(config.getDriver())){
			throw new Exception("jar包地址不能为null");
		}
		if(StringUtils.isBlank(config.getDbUrl())){
			throw new Exception("数据库地址不能为null");
		}
		if(StringUtils.isBlank(config.getUser())){
			throw new Exception("用户不能为null");
		}
		if(StringUtils.isBlank(config.getPassword())){
			throw new Exception("密码不能为null");
		}
		if(StringUtils.isBlank(config.getPack())){
			throw new Exception("生成包地址不能为null");
		}
		this.pack = config.getPack();
		return MessageFormat.format(this.getTemplate(null), new Object[]{
				config.getDriver(),
				config.getDbUrl(),
				config.getUser(),
				config.getPassword(),
				config.getPack(),
				this.getTable(null, config.getKey(), tables)
		});
	}

	public String getTable(String path, List<String> key, List<String> tables) throws Exception {
		StringBuffer table = new StringBuffer();
		for(int i = 0; i < tables.size(); ++i) {
			String[] strs = tables.get(i).split("_");
			StringBuffer po = new StringBuffer();
			for(int j = 1; j < strs.length; j ++){
				if(StringUtils.isBlank(strs[j])){
					continue;
				}
				po.append(String.valueOf(strs[j].charAt(0)).toUpperCase());
				po.append(strs[j].substring(1, strs[j].length()));
			}
			po.append("PO");
			String tableTemplate = this.getTableTemplate(path, key.get(i));
			table.append(MessageFormat.format(tableTemplate, new Object[]{
					tables.get(i),
					po.toString(),
					key.get(i)
			}));
			table.append("\n");
		}
		return table.toString();
	}
}
