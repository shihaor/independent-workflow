package com.sdt.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 前后端分离 跨域配置
 *
 * @author shihaoran
 * @date 2020/2/2
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        // 1.添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 指定url 或者可以用* 放行全部
        config.addAllowedOrigin("http://localhost:8080");
        // 设置是否发送cookie信息
        config.setAllowCredentials(true);
        // 设置允许请求的法式
        config.addAllowedMethod("*");
        // 设置允许的header
        config.addAllowedHeader("*");

        // 2.为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        // 把配置适用于全部路径 /**
        corsSource.registerCorsConfiguration("/**", config);

        // 3.返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
