package com.jerryyang.esunfinancialfavorites;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS 設定，允許前端跨域存取後端 API
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")          // 允許所有 API 路徑
                        .allowedOrigins("*")        // 允許所有來源（開發環境用）
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允許的 HTTP 方法
                        .allowedHeaders("*");       // 允許所有 Header
            }
        };
    }
}
