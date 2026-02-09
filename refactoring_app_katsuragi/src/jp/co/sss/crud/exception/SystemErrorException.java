package jp.co.sss.crud.exception;

public class SystemErrorException extends Exception {

	public SystemErrorException(String systemErrorMsg, Exception e) {
		super(systemErrorMsg, e);
	}

}
