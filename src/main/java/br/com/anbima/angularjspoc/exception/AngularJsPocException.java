package br.com.anbima.angularjspoc.exception;

import br.com.anbima.commons.exception.AnbimaException;

/**
 * Exceção básica do showcase AngularJS.
 */
public class AngularJsPocException extends AnbimaException {
	private static final long serialVersionUID = 1119166104002429037L;

	public AngularJsPocException() {
		super();
	}

	public AngularJsPocException(String message) {
		super(message);
	}

	public AngularJsPocException(Throwable cause) {
		super(cause);
	}

	public AngularJsPocException(String message, Throwable cause) {
		super(message, cause);
	}
}
