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
public class ConsumerThread implements Runnable {
	Logger logger = LoggerFactory.getLogger(ProducerThread.class);
	@SuppressWarnings("static-access")
	int counter=EkarCounter.getInstance().counter.intValue();	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (EkarCounter.getInstance().counter.get()>0) {
			synchronized (this) {
				this.counter=EkarCounter.getInstance().decrement();			
				if(EkarCounter.getInstance().counter.get()==0) {
					logger.info("Consumer consumed all values" + this.counter+"Time stamp in mill sec"+ZonedDateTime.now().toInstant().toEpochMilli());
					}
				// Wake up producer thread
				notify();

			
			}
		}
		
	}

}
