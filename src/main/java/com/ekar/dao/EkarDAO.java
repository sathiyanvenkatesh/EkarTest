/**
 * 
 */
package com.ekar.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ekar.entity.RequestDetail;



/**
 * @author Sathiyan
 *
 */
@Transactional
@Repository
public class EkarDAO implements IEkarDAO {

	@PersistenceContext	
	private EntityManager entityManager;//We will use JPA EntityManager to query database	

	@Override
	public void addlog(RequestDetail requestDetail) {
		// TODO Auto-generated method stub
		entityManager.persist(requestDetail);
	}

}
