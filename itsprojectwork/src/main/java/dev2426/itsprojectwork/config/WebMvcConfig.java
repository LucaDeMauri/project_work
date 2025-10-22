// dev2426.itsprojectwork.config.WebConfig.java
package dev2426.itsprojectwork.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${app.upload.base-dir}")
    private String baseDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // es. baseDir=/var/app/uploads  ->  /media/** serve file:/var/app/uploads/
        registry.addResourceHandler("/media/**")
                .addResourceLocations("file:" + baseDir + "/");
    }
}
