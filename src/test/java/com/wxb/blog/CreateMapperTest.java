package com.wxb.blog;




import com.wxb.blog.common.generator.DefaultGenerator;
import com.wxb.blog.common.generator.GeneratorConfig;
import com.wxb.blog.common.generator.MybatisGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @author huadongnan
 * @date 2018-03-20
 */
public class CreateMapperTest {


    public static void main(String[] args) throws Throwable {
        //通过表生成通用生成
        GeneratorConfig config = new GeneratorConfig();
		config.setDriver("/Users/wxb/Maven/repository/mysql/mysql-connector-java/5.1.46/mysql-connector-java-5.1.46.jar");
        config.setDbUrl("jdbc:mysql://47.106.208.164:3306/blog?useSSL=false");
        config.setUser("admin");
        config.setPassword("p@ssw0rd");

        MybatisGenerator generator = new DefaultGenerator();
        //必须修改的
		config.setPack(userPack);
		config.setKey(userKey);
		generator.generate(generator.getMessage(config, userTable));

        //通过文件定制生成
//        MybatisGenerator fileGenerator = new FileGenerator();
//        List<String> paths = Arrays.asList(
//                "tprc_approval_record_detail.xml"
//        );
//        for(String path : paths){
//        	GeneratorConfig config = new GeneratorConfig();
//        	config.setTemplate(path);
//            fileGenerator.generate(fileGenerator.getMessage(config, null));
//        }
    }

    /**
     * user
     */
    private static String userPack = "user";
    private static List<String> userKey = Arrays.asList("uid");
    private static List<String> userTable = Arrays.asList( "blog_user");


    /**
     * user
     */
    private static String contentPack = "content";
    private static List<String> contentKey = Arrays.asList("id");
    private static List<String> contentTable = Arrays.asList( "blog_content");


}
