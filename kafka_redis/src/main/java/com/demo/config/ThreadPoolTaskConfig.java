package com.demo.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author:
 * @date: created in 20:04 2020/8/12
 * @version:
 */
@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {
    private static final int corePoolSize = 5;            // 核心线程数（默认线程数）
    private static final int maxPoolSize = 20;                // 最大线程数
    private static final int keepAliveTime = 30;            // 允许线程空闲时间（单位：默认为秒）
    private static final int queueCapacity = 50;            // 缓冲队列数
    private static final String threadNamePrefix = "线程-"; // 线程池名前缀

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadNamePrefix(threadNamePrefix);

        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}