/**
 * 
 */
package com.ekar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekar.dao.IEkarDAO;
import com.ekar.entity.RequestDetail;

/**
 * @author Sathiyan
 *
 */
@Service
public class EkarService implements IEkarService {

	@Autowired
	private IEkarDAO articleDAO;
	@Override
	public synchronized boolean createLog(RequestDetail requestDetail) {
		articleDAO.addlog(requestDetail);
		
		return false;
	}

}
