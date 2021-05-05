package com.tacx.activity.infrastructure.bus;

import org.springframework.context.ApplicationContext;

public class RequestHandlerProvider<H extends RequestHandler<?, ?>> {

    private final ApplicationContext applicationContext;
    private final Class<H> handlerClass;

    RequestHandlerProvider(ApplicationContext applicationContext, Class<H> handlerClass) {
        this.applicationContext = applicationContext;
        this.handlerClass = handlerClass;
    }

    public H provide() {
        return applicationContext.getBean(handlerClass);
    }
}
