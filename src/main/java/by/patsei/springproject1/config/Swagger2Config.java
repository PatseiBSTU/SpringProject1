package by.patsei.springproject1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//
//@Configuration
//@EnableSwagger2
//public class Swagger2Config {
//
////        @Bean
////        public Docket api() {
////            return new Docket(DocumentationType.SWAGGER_2).select()
////                    .apis(RequestHandlerSelectors
////                            .basePackage("by.patsei.springproject1.controller"))
////                    .paths(PathSelectors.regex("/.*"))
////                    .build().apiInfo(apiEndPointsInfo());
////        }
////можно так
//    @Bean
//    public Docket apiDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//        private ApiInfo apiEndPointsInfo() {
//            return new ApiInfoBuilder().title("Spring REST API")
//                    .description("Person List REST API")
//                    .contact(new Contact("Patsei N.V.", "www.iba.by", "n.patsei@gmail.com"))
//                    .version("1.0.0")
//                    .build();
//        }
  //  }