package com.tacx.activity.infrastructure.bus.autoconfiguration;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableAspectJAutoProxy(proxyTargetClass = false)
@Import(BusAutoConfiguration.class)
@Documented
public @interface EnableBus {
}
