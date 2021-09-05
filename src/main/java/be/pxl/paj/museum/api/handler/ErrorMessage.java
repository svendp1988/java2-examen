package be.pxl.paj.museum.api.handler;

public class ErrorMessage {

	private final String message;

	public ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
