/**
 * 
 */
package co.edu.udea.iw.exception;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author dcamilo.bedoya
 * @version 1.0
 * Creamos nuestra propia excepción. 
 */
public class MyException extends Exception {

	Logger log = Logger.getLogger(this.getClass());
	
	public MyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	
	public MyException(String message, Throwable cause) {
		super(message, cause);
		//PropertyConfigurator.configure("log4j.properties");
		log.error(message,cause);
		// TODO Auto-generated constructor stub
	}

	public MyException(String message) {
		super(message);
		
	}

	public MyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}