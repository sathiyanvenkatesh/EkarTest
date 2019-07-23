/**
 * 
 */
package com.ekar.util;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



/**
 * @author Sathiyan
 *
 */
@Component
@Scope("prototype")
public class ProducerThread implements Runnable{
	Logger logger = LoggerFactory.getLogger(ProducerThread.class);
	@SuppressWarnings("static-access")
	int counter=EkarCounter.getInstance().counter.intValue();	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (EkarCounter.getInstance().counter.get() < 100) {
			synchronized (this) {
				
				this.counter=EkarCounter.getInstance().increment();
				if(EkarCounter.getInstance().counter.get()==100) {
				System.out.println("Producer produced counter max value" + this.counter+"Time in mill sec"+ZonedDateTime.now().toInstant().toEpochMilli());
				logger.info("Producer produced counter max value" + this.counter+"Time in mill sec"+ZonedDateTime.now().toInstant().toEpochMilli());
				}
				notify();

			}
		}
		
	}

}
