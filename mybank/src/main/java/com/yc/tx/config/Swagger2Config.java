package com.yc.tx.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: testSpring
 * @description: Swagger2配置
 * @author: King
 * @create: 2021-04-26 18:52
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    /**
     * .title("用户管理")//api标题
     * .description("用户管理相关接口描述")//api描述
     * .version("1.0.0")//版本号
     * .contact("sabre")//本API负责人的联系信息
     * .build();
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("银行存取操作接口")
                .description("银行存取操作 API 文档")
                .version("1.0")
                .contact("sabre")
                .build();
    }

    @Bean
    public Docket getUserDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                //文档类型（swagger2）
                .apiInfo(apiInfo())
                //设置包含在json ResourceListing响应中的api元信息
                .select()
                //启动用于api选择的构建器
                .apis(RequestHandlerSelectors.basePackage("com.yc"))
                //扫描接口的包
                .paths(PathSelectors.any())
                //路径过滤器（扫描所有路径）
                .build();
    }


}
