/**
 * 
 */
package com.ekar.controller;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekar.service.IEkarService;
import com.ekar.util.AsynchronousService;
import com.ekar.util.ConsumerThread;
import com.ekar.util.Ekarconfiguration;
import com.ekar.util.Ekarproduceconsumer;
import com.ekar.util.ProducerThread;
import com.ekar.entity.RequestDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Sathiyan
 *
 */
@RestController
@RequestMapping("/")
@Api(value="Ekarcounter" )
public class EkarController {
	Logger logger = LoggerFactory.getLogger(EkarController.class);

	@Autowired
	private IEkarService ekarService;
	
	@Autowired
	private ConsumerThread ct;
	
	@Autowired
	private ProducerThread pt;
	
	//@Autowired
	//private AsynchronousService asyserv;//using Async method 
	

	@GetMapping("Ping")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<>("Pong!", HttpStatus.OK);
	}


	@ApiOperation(value = "Add Thredcount param")
	@PostMapping("increasethread")
	public ResponseEntity<Void> createlog(@RequestBody RequestDetail  reqdetails){
		
		boolean flag =ekarService.createLog(reqdetails);
		
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		//asyserv.executeAsynchronously();
		logger.info("Producerthreadcoun from input"+reqdetails.getProducerCount());
		//ApplicationContext ap =new ApplicationContext(Ekarconfiguration.class);
		Thread[] poducerthread = new Thread [reqdetails.getProducerCount()];//amount of threads
        for(int b =0; b < poducerthread.length; b++){
       	 poducerthread[b] = new Thread(pt);
       	 poducerthread[b].start();
        }

        
        Thread[] consumerthread = new Thread [reqdetails.getConsumerCount()];//amount of threads
        for(int c =0; c< consumerthread.length; c++){
       	 consumerthread[c] = new Thread(ct);
       	 consumerthread[c].start();
        }
		
		
		HttpHeaders headers = new HttpHeaders();			
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
