package br.com.anbima.commons.exception;

/**
 * Exceção fatal básica dos sistemas da Anbima.
 */
public class AnbimaFatalException extends RuntimeException {
	private static final long serialVersionUID = 1000896224430502360L;

	public AnbimaFatalException() {
		super();
	}

	public AnbimaFatalException(String message) {
		super(message);
	}

	public AnbimaFatalException(Throwable cause) {
		super(cause);
	}

	public AnbimaFatalException(String message, Throwable cause) {
		super(message, cause);
	}
}
