/**
 * 
 */
package com.ekar.util;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;



/**
 * @author Sathiyan
 *
 */
@Configuration
@EnableAsync
public class Ekarconfiguration {

	@Primary
	@Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("Ekarthread:-");
        executor.initialize();
        return executor;
    }
	@Bean(name = "specificTaskExecutor")
    public TaskExecutor specificTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        return executor;
    }
	
}
