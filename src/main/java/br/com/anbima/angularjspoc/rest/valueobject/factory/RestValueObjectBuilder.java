package br.com.anbima.angularjspoc.rest.valueobject.factory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Interface de construtores de Value Objects para servi�os REST.
 */
public interface RestValueObjectBuilder<E, V> extends Serializable {

	/**
	 * Constr�i o value object de um objeto de dom�nio.
	 * 
	 * @param entity Objeto de dom�nio
	 * @return Value object gerado
	 */
	public V buildVo(Object entity);
	
	/**
	 * Constr�i uma lista de value objects a partir de uma cole��o de objetos
	 * de dom�nio.
	 * 
	 * @param entities Cole��o de Objetos de dom�nio
	 * @return Lista de value objects gerados
	 */
	public List<V> buildVos(Collection<? extends Object> entities);
	
	/**
	 * Constr�i o objeto de dom�nio representado por um value object.
	 * 
	 * @param valueObject Value object
	 * @return Objeto de dom�nio gerado
	 */
	public E buildEntity(Object valueObject);
	
	/**
	 * Constr�i uma lista de objetos de dom�nio representados por uma cole��o
	 * de value objects.
	 * 
	 * @param valueObjects Cole��o de value objects
	 * @return Lista de objetos de dom�nio gerados
	 */
	public List<E> buildEntities(Collection<? extends Object> valueObjects);
	
	/**
	 * Recupera a classe dos objetos de dom�nio tratados pelo builder.
	 * 
	 * @return Classe do objeto de dom�nio tratado pelo builder
	 */
	public Class<E> getEntityClass();
	
	/**
	 * Recupera a classe dos value objects tratados pelo builder.
	 * 
	 * @return Classe do value object tratado pelo builder
	 */
	public Class<V> getValueObjectClass();
}
