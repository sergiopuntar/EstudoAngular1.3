package br.com.anbima.commons.exception;

/**
 * Exceção básica dos sistemas da Anbima.
 */
public class AnbimaException extends Exception {
	private static final long serialVersionUID = 1417205324573148696L;

	public AnbimaException() {
		super();
	}

	public AnbimaException(String message) {
		super(message);
	}

	public AnbimaException(Throwable cause) {
		super(cause);
	}

	public AnbimaException(String message, Throwable cause) {
		super(message, cause);
	}
}
