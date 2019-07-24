/**
 * 
 */
package com.ekar.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Sathiyan
 *
 */
@Entity
@Table(name="threaddeatil")
@Component("ThreadDetails")
public class ThreadDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="ThreadName")
	private String threadname ;	
	@Column(name="Timestamp")
	private long timestamp;
	/**
	 * @return the threadname
	 */
	public String getThreadname() {
		return threadname;
	}
	/**
	 * @param threadname the threadname to set
	 */
	public void setThreadname(String threadname) {
		this.threadname = threadname;
	}
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
		
}
