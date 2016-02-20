package br.com.anbima.commons.domain.entity;

import java.io.Serializable;

/**
 * Interface padrão para todas as entidades dos sistemas da Anbima.
 */
public interface EntityInterface extends Serializable, Cloneable {
	
	/**
	 * Recupera o identificador da entidade.
	 * 
	 * @return Identificador da entidade
	 */
	String getOid();

	/**
	 * Define o identificador da entidade.
	 * 
	 * @param id Novo identificador da entidade
	 */
	void setOid(String id);
}
