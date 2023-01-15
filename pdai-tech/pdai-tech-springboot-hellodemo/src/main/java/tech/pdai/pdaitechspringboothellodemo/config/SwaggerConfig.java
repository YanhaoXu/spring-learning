package tech.pdai.pdaitechspringboothellodemo.config;

import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import tech.pdai.pdaitechspringboothellodemo.config.response.ResponseStatus;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

  @Bean
  public Docket openApi() {
    return new Docket(DocumentationType.OAS_30)
        .groupName("Test group")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build()
        .globalRequestParameters(getGlobalRequestParameters())
        .globalResponses(HttpMethod.GET, getGlobalResponse());
  }

  private List<Response> getGlobalResponse() {
    return ResponseStatus.HTTP_STATUS_ALL.stream().map(
            a -> new ResponseBuilder().code(a.getResponseCode()).description(a.getDescription())
                .build())
        .toList();
  }

  private List<RequestParameter> getGlobalRequestParameters() {
    List<RequestParameter> parameters = new ArrayList<>();
    parameters.add(new RequestParameterBuilder()
        .name("AppKey")
        .description("App Key")
        .required(false)
        .in(ParameterType.QUERY)
        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
        .required(false)
        .build());
    return parameters;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Swagger API")
        .description("test api")
        .contact(new Contact("pdai", "http://pdai.tech", "test@gmail.com"))
        .termsOfServiceUrl("http://xxxxxx.com/")
        .version("1.0")
        .build();
  }

}
