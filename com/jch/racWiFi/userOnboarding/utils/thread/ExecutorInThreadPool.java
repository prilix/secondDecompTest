package com.jch.racWiFi.userOnboarding.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecutorInThreadPool {
    private static final ExecutorInThreadPool executorInstance = new ExecutorInThreadPool();
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    static ExecutorInThreadPool getInstance() {
        return executorInstance;
    }

    private ExecutorInThreadPool() {
    }
}
