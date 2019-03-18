package com.wxb.blog.common.generator;

import java.util.List;

/**
 * @author huadongnan
 * @title
 * @date 2018/3/20.
 * @since 1.0.0
 */
public interface MybatisGenerator {
	void generate(String message);

	String getMessage(GeneratorConfig config, List<String> tables) throws Exception;
}
