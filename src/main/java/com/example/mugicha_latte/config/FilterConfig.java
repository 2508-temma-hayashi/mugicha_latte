package com.example.mugicha_latte.config;

import com.example.mugicha_latte.filter.AdministratorFilter;
import com.example.mugicha_latte.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new LoginFilter());
        //ログイン情報が必要なURL
        bean.addUrlPatterns("/user/*", "/comment/*","/message/*", "/", "/logout", "/post", "/signup");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public  FilterRegistrationBean<AdministratorFilter> administratorFilter() {
        FilterRegistrationBean<AdministratorFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new AdministratorFilter());
        //管理者権限が必要なURL
        bean.addUrlPatterns("/user/*", "/signup");
        bean.setOrder(2);
        return bean;
    }
}
