/**
 * 
 */
package com.ekar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author Sathiyan
 *
 */
@Entity
@Table(name="requestdetail")
public class RequestDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int requestid;
	@Column(name="producerCount")
	private int producerCount ;	
	@Column(name="consumerCount")
	private int consumerCount;	
    /**
	 * @return the requestid
	 */
	public int getRequestid() {
		return requestid;
	}
	/**
	 * @param requestid the requestid to set
	 */
	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}
	/**
	 * @return the producerCount
	 */
	public int getProducerCount() {
		return producerCount;
	}
	/**
	 * @param producerCount the producerCount to set
	 */
	public void setProducerCount(int producerCount) {
		this.producerCount = producerCount;
	}
	/**
	 * @return the consumerCount
	 */
	public int getConsumerCount() {
		return consumerCount;
	}
	/**
	 * @param consumerCount the consumerCount to set
	 */
	public void setConsumerCount(int consumerCount) {
		this.consumerCount = consumerCount;
	}
	
	


	
	
	

}
