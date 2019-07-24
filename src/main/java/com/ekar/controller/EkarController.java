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
	@GetMapping("Ping")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<>("Pong!", HttpStatus.OK);
	}


	@ApiOperation(value = "Add Thredcount param")
	@PostMapping("increasethread")
	public ResponseEntity<String> createlog(@RequestBody RequestDetail  reqdetails){
		logger.info("Producerthreadcoun from input-----"+reqdetails.getProducerCount());
		logger.info("Consumer Threadcount from input------"+reqdetails.getConsumerCount());
		boolean flag =ekarService.createLog(reqdetails);
		
		if (flag == false) {
			return new ResponseEntity<>("Not Added to DB ",HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}
}
