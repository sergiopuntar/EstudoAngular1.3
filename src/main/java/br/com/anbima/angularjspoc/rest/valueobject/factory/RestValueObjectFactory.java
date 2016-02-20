package br.com.anbima.angularjspoc.rest.valueobject.factory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import br.com.anbima.angularjspoc.exception.AngularJsPocFatalException;

import com.google.common.collect.Lists;

/**
 * F�brica para de Value Objects para servi�os REST.
 */
@ApplicationScoped
public class RestValueObjectFactory implements Serializable {
	private static final long serialVersionUID = -1013466177833987404L;
	
	@Inject @Any
	private Instance<RestValueObjectBuilder<?, ?>> builders;
	
	/**
	 * Constr�i o value object de um objeto de dom�nio.
	 * 
	 * @param entity Objeto de dom�nio
	 * @param valueObjectClass Classe do value object
	 * @return Value object gerado
	 */
	@SuppressWarnings("unchecked")
	public <V> V buildVo(Object entity, Class<V> valueObjectClass) {
		Validate.notNull(entity, "O par�metro entity n�o pode ser nulo");
		Validate.notNull(valueObjectClass, "O par�metro valueObjectClass n�o pode ser nulo");
		
		return (V) getBuilder(entity.getClass(), valueObjectClass).buildVo(entity);
	}
	
	/**
	 * Constr�i uma lista de value objects a partir de uma cole��o de objetos
	 * de dom�nio.
	 * 
	 * @param entities Cole��o de Objetos de dom�nio
	 * @param valueObjectClass Classe do value object
	 * @return Lista de value objects gerados
	 */
	@SuppressWarnings("unchecked")
	public <V> List<V> buildVos(Collection<? extends Object> entities, Class<V> valueObjectClass) {
		Validate.notNull(entities, "O par�metro entities n�o pode ser nulo");
		Validate.notNull(valueObjectClass, "O par�metro valueObjectClass n�o pode ser nulo");
		if (entities.isEmpty()) return Lists.newArrayList();
		
		return (List<V>) getBuilder(entities.iterator().next().getClass(), valueObjectClass).buildVos(entities);
	}
	
	/**
	 * Constr�i o objeto de dom�nio representado por um value object.
	 * 
	 * @param valueObject Value object
	 * @param entityClass Classe do objeto de dom�nio
	 * @return Objeto de dom�nio gerado
	 */
	@SuppressWarnings("unchecked")
	public <E> E buildEntity(Object valueObject, Class<E> entityClass) {
		Validate.notNull(valueObject, "O par�metro valueObject n�o pode ser nulo");
		Validate.notNull(entityClass, "O par�metro entityClass n�o pode ser nulo");
		
		return  (E) getBuilder(valueObject.getClass(), entityClass).buildEntity(valueObject);
	}
	
	/**
	 * Constr�i uma lista de objetos de dom�nio representados por uma cole��o
	 * de value objects.
	 * 
	 * @param valueObjects Cole��o de value objects
	 * @param entityClass Classe do objeto de dom�nio
	 * @return Lista de objetos de dom�nio gerados
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> buildEntities(Collection<? extends Object> valueObjects, Class<E> entityClass) {
		Validate.notNull(valueObjects, "O par�metro valueObjects n�o pode ser nulo");
		Validate.notNull(entityClass, "O par�metro entityClass n�o pode ser nulo");
		if (valueObjects.isEmpty()) return Lists.newArrayList();
		
		return (List<E>) getBuilder(valueObjects.iterator().next().getClass(), entityClass).buildVos(valueObjects);
	}
	
	/**
	 * Recupera o builder de Value Object.
	 * 
	 * @param entityClass Classe do objeto de dom�nio tratado pelo builder
	 * @param valueObjectClass Classe do value object tratado pelo builder
	 * @return Construtor encontrado
	 */
	private RestValueObjectBuilder<?, ?> getBuilder(Class<?> entityClass, Class<?> valueObjectClass) {
		for (RestValueObjectBuilder<?, ?> builder : builders) {
			if (builder.getEntityClass().equals(entityClass) && builder.getValueObjectClass().equals(valueObjectClass)) {
				return builder;
			}
		}
		
		throw new AngularJsPocFatalException(
				String.format("N�o existe Value Object Builder para Objeto de Dom�nio do tipo %s e Value Object do tipo %s",
						entityClass.getName(), valueObjectClass.getName()));
	}
}
