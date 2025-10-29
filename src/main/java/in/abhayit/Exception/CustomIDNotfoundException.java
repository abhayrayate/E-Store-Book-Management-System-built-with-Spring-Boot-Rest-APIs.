package in.abhayit.Exception;

public class CustomIDNotfoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;//use for serilization(verrification) tells to jvm that this clss unique version  is 1 

	public CustomIDNotfoundException (String msg) {
		
		super(msg);
	}

}
