/**
 * 
 */
package com.ekar.dao;

import com.ekar.entity.RequestDetail;
import com.ekar.entity.ThreadDetails;

/**
 * @author Sathiyan
 *
 */
public interface IEkarDAO {
	 

	void addlog(RequestDetail requestDetail);
	void addthreaddetails(ThreadDetails threadDetails);

}
