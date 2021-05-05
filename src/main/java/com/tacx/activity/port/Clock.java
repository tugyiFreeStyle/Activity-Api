package com.tacx.activity.port;


import java.time.LocalDateTime;

@FunctionalInterface
public interface Clock {
   LocalDateTime now();
}
