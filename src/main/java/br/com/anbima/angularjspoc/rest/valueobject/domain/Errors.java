package br.com.anbima.angularjspoc.rest.valueobject.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe de erros para serviços REST. 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Errors implements Serializable {
	private static final long serialVersionUID = 1769541944927743171L;
	
	private Map<String, Set<String>> fields;
	private Set<String> general;
	
	public Errors() {
		super();
	}
	
	/**
	 * Adiciona um erro específico de um campo.
	 * 
	 * @param fieldName Nome do campo
	 * @param message Mensagem de erro
	 */
	public void addFieldError(String fieldName, String message) {
		if (fields == null) {
			fields = new HashMap<String, Set<String>>();
		}
		
		Set<String> messages = fields.get(fieldName);
		
		if (messages == null) {
			messages = new LinkedHashSet<String>();
			fields.put(fieldName, messages);
		}
		
		messages.add(message);
	}
	
	/**
	 * Adiciona um erro geral.
	 * 
	 * @param message Mensagem de erro.
	 */
	public void addGeneralError(String message) {
		if (general == null) {
			general = new LinkedHashSet<String>();
		}
		
		general.add(message);
	}
	
	/**
	 * Verifica se existem erros registrados no objeto.
	 * 
	 * @return True se existem erros, False caso contrário.
	 */
	public boolean hasErros() {
		return fields != null || general != null;
	}
}
