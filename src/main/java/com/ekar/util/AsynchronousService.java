/**
 * 
 */
package com.ekar.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.*;
import org.springframework.stereotype.Service;

/**
 * @author Sathiyan
 *
 */
@Service
public class AsynchronousService {
	@Autowired
	private TaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext applicationContext;
    public void executeAsynchronously() {
        ProducerThread pt = applicationContext.getBean(ProducerThread.class);
        
        taskExecutor.execute(pt);
        
    }


}
