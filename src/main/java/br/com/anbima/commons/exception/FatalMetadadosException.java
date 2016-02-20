package br.com.anbima.commons.exception;

/**
 * Exceção fatal relacionada aos metadados do sistema.
 */
public class FatalMetadadosException extends AnbimaFatalException {
	private static final long serialVersionUID = 7012725365151462069L;

	public FatalMetadadosException(String message) {
		super(message);
	}

	public FatalMetadadosException(String message, Throwable cause) {
		super(message, cause);
	}
}
