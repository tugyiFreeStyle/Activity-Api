package com.tacx.activity.infrastructure.bus.autoconfiguration;

import com.tacx.activity.infrastructure.bus.Bus;
import com.tacx.activity.infrastructure.bus.Registry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.tacx.activity.infrastructure.bus"})
public class BusAutoConfiguration {

    @Bean
    public Registry registry(ApplicationContext applicationContext) {
        return new Registry(applicationContext);
    }

    @Bean
    public Bus commandBus(Registry registry) {
        return new Bus(registry);
    }
}
