package br.com.anbima.commons.exception;

/**
 * Exceção relacionada aos metadados do sistema.
 */
public class MetadadosException extends AnbimaException {
	private static final long serialVersionUID = -5134874815318774495L;

	public MetadadosException(String message) {
		super(message);
	}

	public MetadadosException(String message, Throwable cause) {
		super(message, cause);
	}
}
