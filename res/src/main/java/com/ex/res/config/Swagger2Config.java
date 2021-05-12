package com.ex.res.config;

import com.ex.res.vo.JsonModel;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@EnableSwagger2    //启用swaggerr 注解解析器
public class Swagger2Config {
    // 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(HttpSession.class, HttpServletRequest.class, HttpServletResponse.class, JsonModel.class)
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ex"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 生成的rest api的信息对象
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档的标题
                .title("小萌神订单系统")
                // 设置文档的描述
                .description("小萌神订单系统 API 接口文档")
                // 设置文档的版本信息-> 1.0.0 Version information
                .version("1.0")
                .contact(new Contact("ErFeng", "https://blog.csdn.net/Er_fengV", "3208027182@qq.com"))
                .termsOfServiceUrl("")
                .build();
    }
}
