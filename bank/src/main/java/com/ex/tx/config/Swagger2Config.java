package com.ex.tx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2    //启用swaggerr 注解解析器
public class Swagger2Config {
    // 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")  //通过 @Value  获取配置信息   复习   @Environement  @Value    @ConfigurationProperties
    private Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)//强制控制是否打开swagger功能
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 生成rest api的信息对象
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("银行存取操作接口")//设置文档的标题
                .description("银行存取操作 API 接口文档")//设置文档的描述
                .contact(new Contact("ex", "http://www.baidu.com", "1395382937@qq.com"))
                .version("1.0")//设置文档的版本信息 -> 1.0.0 Version information
                .termsOfServiceUrl("http://www.baidu.com")//设置文档的License信息 -> 1.3 License information
                .build();
    }
}
