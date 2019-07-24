/**
 * 
 */
package com.ekar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekar.dao.IEkarDAO;
import com.ekar.entity.RequestDetail;
import com.ekar.util.ConsumerThread;
import com.ekar.util.ProducerThread;

/**
 * @author Sathiyan
 *
 */
@Service
public class EkarService implements IEkarService {

	@Autowired
	private IEkarDAO articleDAO;
	@Autowired
	private ConsumerThread ct;
	
	@Autowired
	private ProducerThread pt;
	@Override
	public synchronized boolean createLog(RequestDetail requestDetail) {
		articleDAO.addlog(requestDetail);
		Thread[] poducerthread = new Thread [requestDetail.getProducerCount()];//amount of threads
        for(int b =0; b < poducerthread.length; b++){
       	 poducerthread[b] = new Thread(pt);
       	 poducerthread[b].start();
        }

        
        Thread[] consumerthread = new Thread [requestDetail.getConsumerCount()];//amount of threads
        for(int c =0; c< consumerthread.length; c++){
       	 consumerthread[c] = new Thread(ct);
       	 consumerthread[c].start();
        }
		
		return true;
	}

}
