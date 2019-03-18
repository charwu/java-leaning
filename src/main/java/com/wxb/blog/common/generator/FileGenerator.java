package com.wxb.blog.common.generator;

import java.util.List;

/**
 * @author huadongnan
 * @title
 * @date 2018/3/20.
 * @since 1.0.0
 */
public class FileGenerator extends AbstractGenerator {
	public String getMessage(GeneratorConfig config, List<String> tables) throws Exception {
		return this.getTemplate(config.getTemplate());
	}
}
