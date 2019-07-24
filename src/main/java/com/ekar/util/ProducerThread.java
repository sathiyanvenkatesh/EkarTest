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
public class ProducerThread implements Runnable{
	Logger logger = LoggerFactory.getLogger(ProducerThread.class);
	@Autowired
	private IEkarDAO articleDAO;
	@SuppressWarnings("static-access")
	int counter=EkarCounter.getInstance().counter.intValue();	
	@SuppressWarnings("static-access")
	@Autowired
	private ThreadDetails thdet;
	@Override
	public void run() {
		while (EkarCounter.getInstance().counter.get() < 101) {
			synchronized (this) {
				
				this.counter=EkarCounter.getInstance().increment();
				if(EkarCounter.getInstance().counter.get()==100) {
				//System.out.println("Producer produced counter max value" + this.counter+"Time in mill sec"+ZonedDateTime.now().toInstant().toEpochMilli());
				logger.info("Producerthread"+ Thread.currentThread().getName()+" produced counter max value" + this.counter+"Time in mill sec"+ZonedDateTime.now().toInstant().toEpochMilli());
				thdet.setThreadname(Thread.currentThread().getName());
				thdet.setTimestamp(ZonedDateTime.now().toInstant().toEpochMilli());
				//articleDAO.addthreaddetails(thdet);
				}
				notify();

			}
		}
		
	}

}
