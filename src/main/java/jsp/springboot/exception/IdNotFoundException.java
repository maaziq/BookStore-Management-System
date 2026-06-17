package jsp.springboot.exception;

@SuppressWarnings("serial")
public class IdNotFoundException extends RuntimeException {
	
	public IdNotFoundException(String messege) {
		super(messege);
	}
}
