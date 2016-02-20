package br.com.anbima.angularjspoc.rest.valueobject.factory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Interface de construtores de Value Objects para serviços REST.
 */
public interface RestValueObjectBuilder<E, V> extends Serializable {

	/**
	 * Constrói o value object de um objeto de domínio.
	 * 
	 * @param entity Objeto de domínio
	 * @return Value object gerado
	 */
	public V buildVo(Object entity);
	
	/**
	 * Constrói uma lista de value objects a partir de uma coleção de objetos
	 * de domínio.
	 * 
	 * @param entities Coleção de Objetos de domínio
	 * @return Lista de value objects gerados
	 */
	public List<V> buildVos(Collection<? extends Object> entities);
	
	/**
	 * Constrói o objeto de domínio representado por um value object.
	 * 
	 * @param valueObject Value object
	 * @return Objeto de domínio gerado
	 */
	public E buildEntity(Object valueObject);
	
	/**
	 * Constrói uma lista de objetos de domínio representados por uma coleção
	 * de value objects.
	 * 
	 * @param valueObjects Coleção de value objects
	 * @return Lista de objetos de domínio gerados
	 */
	public List<E> buildEntities(Collection<? extends Object> valueObjects);
	
	/**
	 * Recupera a classe dos objetos de domínio tratados pelo builder.
	 * 
	 * @return Classe do objeto de domínio tratado pelo builder
	 */
	public Class<E> getEntityClass();
	
	/**
	 * Recupera a classe dos value objects tratados pelo builder.
	 * 
	 * @return Classe do value object tratado pelo builder
	 */
	public Class<V> getValueObjectClass();
}
