package com.popcorn.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.time.Clock;

@Slf4j
public class CustomApplicationReadyEventListener extends ApplicationEvent implements ApplicationListener<CustomApplicationReadyEventListener> {

    public CustomApplicationReadyEventListener(Object source) {
        super(source);
    }

    public CustomApplicationReadyEventListener(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public void onApplicationEvent(CustomApplicationReadyEventListener event) {
        log.info("onApplicationEvent");
    }

    @Override
    public boolean supportsAsyncExecution() {
        log.info("supportsAsyncExecution");
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
