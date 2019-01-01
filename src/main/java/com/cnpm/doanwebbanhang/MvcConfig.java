package com.cnpm.doanwebbanhang;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("file:/C:/Users/SON NGUYEN/IdeaProjects/do-an-web-ban-hang/src/main/resources/static/", "file:/G:/images/");
    }
}
