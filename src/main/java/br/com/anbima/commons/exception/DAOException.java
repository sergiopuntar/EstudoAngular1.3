package br.com.anbima.commons.exception;

/**
 * Exceptions relacionadas a camada de Acesso ao Banco de Dados.
 */
public class DAOException extends AnbimaFatalException {
	private static final long serialVersionUID = -1555104124339514398L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
