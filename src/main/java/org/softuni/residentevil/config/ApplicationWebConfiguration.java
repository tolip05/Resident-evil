package org.softuni.residentevil.config;

import org.softuni.residentevil.interceptors.PreAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {
    private final PreAuthenticationInterceptor preAuthenticationInterceptor;

    @Autowired
    public ApplicationWebConfiguration(PreAuthenticationInterceptor preAuthenticationInterceptor) {
        this.preAuthenticationInterceptor = preAuthenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.preAuthenticationInterceptor);
    }
}
