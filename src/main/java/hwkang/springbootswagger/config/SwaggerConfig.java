package hwkang.springbootswagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final String TITLE = "SpringBoot-Swagger Demo";
    private final String VERSION = "1.0.0";
    private final String DESCRIPTION = "SpringBoot-Swagger Demo Project";

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)  //UI 상에서 디폴트 HttpStatus 메시지 안보이게 하기
                .select()
                .apis(RequestHandlerSelectors.basePackage("hwkang.springbootswagger"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public UiConfiguration ui() {

        return UiConfigurationBuilder.builder()
                .defaultModelsExpandDepth(-1)   //화면 하단에 Models Section 숨기기
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .version(VERSION)
                .description(DESCRIPTION)
                .build();
    }
}
