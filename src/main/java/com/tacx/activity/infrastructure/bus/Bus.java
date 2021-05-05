package com.tacx.activity.infrastructure.bus;


import lombok.SneakyThrows;

public class Bus {

    private final Registry registry;

    public Bus(Registry registry) {
        this.registry = registry;
    }

    @SneakyThrows
    public <R extends Request, T> T execute(R request) {
        RequestHandler<R, T> commandHandler = (RequestHandler<R, T>) registry.getHandler(request.getClass());
        return commandHandler.handle(request);
    }
}
