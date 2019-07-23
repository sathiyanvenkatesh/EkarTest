/**
 * 
 */
package com.ekar.util;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Sathiyan
 *
 */
@Component
public class Ekarproduceconsumer {
	Logger logger = LoggerFactory.getLogger(Ekarproduceconsumer.class);
	@SuppressWarnings("static-access")
	int counter=EkarCounter.getInstance().counter.intValue();	
	// Function called by producer thread
	@SuppressWarnings("static-access")
	public void produce() throws InterruptedException {
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

	// Function called by consumer thread
	@SuppressWarnings("static-access")
	public void consume() throws InterruptedException {
		//System.out.println("Counter Value in consumer get instance" + this.counter);
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
