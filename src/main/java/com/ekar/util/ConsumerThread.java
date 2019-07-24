/**
 * 
 */
package com.ekar.util;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ekar.dao.IEkarDAO;
import com.ekar.entity.ThreadDetails;

/**
 * @author Sathiyan
 *
 */
@Component
@Scope("prototype")
public class ConsumerThread implements Runnable {
	@Autowired
	private IEkarDAO articleDAO;
	Logger logger = LoggerFactory.getLogger(ProducerThread.class);
	@SuppressWarnings("static-access")
	int counter=EkarCounter.getInstance().counter.intValue();	
	@SuppressWarnings("static-access")
	@Autowired
	private ThreadDetails thdet;
	@Override
	public void run() {
		while (EkarCounter.getInstance().counter.get()>0) {
			synchronized (this) {
				this.counter=EkarCounter.getInstance().decrement();			
				if(EkarCounter.getInstance().counter.get()==0) {
					logger.info("Consumer thread"+Thread.currentThread().getName()+" consumed all values" + this.counter+"Time stamp in mill sec"+ZonedDateTime.now().toInstant().toEpochMilli());
					thdet.setThreadname(Thread.currentThread().getName());
					thdet.setTimestamp(ZonedDateTime.now().toInstant().toEpochMilli());
					//articleDAO.addthreaddetails(thdet);
					}
				// Wake up producer thread
				notify();

			
			}
		}
		
	}

}
