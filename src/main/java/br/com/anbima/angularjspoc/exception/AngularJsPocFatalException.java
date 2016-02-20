package br.com.anbima.angularjspoc.exception;

import br.com.anbima.commons.exception.AnbimaFatalException;

/**
 * Exceção fatal do showcase AngularJS.
 */
public class AngularJsPocFatalException extends AnbimaFatalException {
	private static final long serialVersionUID = -2448559407838930665L;

	public AngularJsPocFatalException() {
		super();
	}

	public AngularJsPocFatalException(String message) {
		super(message);
	}

	public AngularJsPocFatalException(Throwable cause) {
		super(cause);
	}

	public AngularJsPocFatalException(String message, Throwable cause) {
		super(message, cause);
	}
}
