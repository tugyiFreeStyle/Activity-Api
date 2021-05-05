package com.tacx.activity.infrastructure.bus;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Registry {

    private Map<Class<? extends Request>, RequestHandlerProvider> providerMap = new HashMap<>();

    public Registry(ApplicationContext applicationContext) {
        registerHandlers(applicationContext);
    }

    private void registerHandlers(ApplicationContext applicationContext) {
        String[] handlerBeanNames = applicationContext.getBeanNamesForType(RequestHandler.class);
        Arrays.stream(handlerBeanNames)
                .forEach(beanName -> register(applicationContext, beanName));
    }

    private void register(ApplicationContext applicationContext, String beanName) {
        Class<RequestHandler<?,?>> handlerClass = (Class<RequestHandler<?,?>>) applicationContext.getType(beanName);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, RequestHandler.class);
        Class<? extends Request> requestClass = (Class<? extends Request>) generics[0];
        providerMap.put(requestClass, new RequestHandlerProvider(applicationContext, handlerClass));
    }

    <R extends Request, T> RequestHandler<R,T> getHandler(Class<R> requestClass) {
        return providerMap.get(requestClass).provide();
    }
}
