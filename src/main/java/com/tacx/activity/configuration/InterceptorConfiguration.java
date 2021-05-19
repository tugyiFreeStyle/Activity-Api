package com.tacx.activity.configuration;

import com.tacx.activity.infrastructure.interceptor.IncomingRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final IncomingRequestInterceptor incomingRequestInterceptor;

    public InterceptorConfiguration(IncomingRequestInterceptor incomingRequestInterceptor) {
        this.incomingRequestInterceptor = incomingRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(incomingRequestInterceptor)
                .addPathPatterns("/api/v1/**");
    }
}
