package jsp.springboot.exception;

@SuppressWarnings("serial")
public class NoRecordAvailableException extends RuntimeException {
	
	public NoRecordAvailableException(String messege) {
		super(messege);
	}

}
