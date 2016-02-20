package br.com.anbima.angularjspoc.rest.application;

import java.io.Serializable;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import br.com.anbima.angularjspoc.rest.valueobject.domain.Errors;

/**
 * Controlador abstrato base para todos os controladores REST do sistema.
 */
public class BaseRestController implements Serializable {
	private static final long serialVersionUID = -5563871172012324633L;

	@Inject
	private Validator validator;

	/**
	 * Executa a valida��o de um bean.
	 * 
	 * @param bean Bean a ser validado
	 */
	protected <T> Errors validateBean(T bean) {
		return createViolationsErrors(validator.validate(bean));
	}

	/**
	 * Cria os erros de viola��es de restri��es de valida��o.
	 * 
	 * @param violations Conjunto de viola��es de restri��es de valida��o
	 * @return Mapa de viola��es de restri��es de valida��o
	 */
	protected <T> Errors createViolationsErrors(Set<ConstraintViolation<T>> violations) {
		Errors errors = new Errors();
		populateViolationsErrors(errors, violations);
		
		return errors;
	}

	/**
	 * Popula um objeto de erros com um conjunto de erros de viola��o de
	 * restri��es de valida��o.
	 *  
	 * @param errors Objeto de erros a ser populado
	 * @param violations Conjunto de viola��es de restri��es de valida��o
	 */
	protected <T> void populateViolationsErrors(Errors errors, Set<ConstraintViolation<T>> violations) {
		for (ConstraintViolation<?> violation : violations) {
			errors.addFieldError(violation.getPropertyPath().toString(), violation.getMessage());
		}
	}

	/**
	 * Cria uma resposta padr�o de erros de valida��o.
	 * 
	 * @param violations Conjunto de Viola��es de restri��es de valida��o
	 * @return Resposta de erro de valida��o
	 */
	protected <T> Response.ResponseBuilder createViolationsResponse(Set<ConstraintViolation<T>> violations) {
		return createErrorResponse(createViolationsErrors(violations));
	}
	
	/**
	 * Cria uma resposta padr�o de erro.
	 * 
	 * @param error Objeto de erro
	 * @return Resposta de erro
	 */
	protected Response.ResponseBuilder createErrorResponse(Object error) {
		return Response.status(Response.Status.BAD_REQUEST).entity(error);
	}
}
