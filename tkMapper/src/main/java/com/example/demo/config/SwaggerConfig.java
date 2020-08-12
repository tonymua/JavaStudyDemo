/**
 *
 * copyright © 2010-2017浙江邦盛科技有限公司 版权所有
 *
 */

package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置. <br>
 * 时间:2018年9月29日 下午1:54:33 <br>
 * 
 * @author HH
 * @version 6.5.0
 * @since 6.5.0
 */
//http://localhost:8080/swagger-ui.html
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
            .select()
             .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
            .paths(PathSelectors.any())
            .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("tkMapper")
            .description("tkMapper Demo")
            .build();
    }
    
}
