package com.restaurantApp.restaurantBack.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configure le gestionnaire de ressources pour les images
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/Users/KINSM/Desktop/menuItemsPhotos/");
    }
}
