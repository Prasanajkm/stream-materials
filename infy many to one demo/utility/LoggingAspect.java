/*package com.infy.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingAspect {

	private Logger logger=LogManager.getLogger(this.getClass());
	
	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	public void logDaoException(Exception exception) throws Exception {
		
	}

	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	public void logServiceException(Exception exception) throws Exception {
		
	}

}
*/

package com.infy.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private Logger logger=LogManager.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "execution(* com.infy.dao.InfySolutionDAOImpl.*(..))", throwing = "exception")
	public void logDAOException(Exception exception) throws Exception {

		logger.error(exception.getMessage(),exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infy.service.InfySolutionServiceImpl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) throws Exception {

		logger.error(exception.getMessage(),exception);

	}


}
