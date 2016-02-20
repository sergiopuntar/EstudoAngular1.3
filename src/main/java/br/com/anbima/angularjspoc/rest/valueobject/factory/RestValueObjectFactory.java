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
 * Fábrica para de Value Objects para serviços REST.
 */
@ApplicationScoped
public class RestValueObjectFactory implements Serializable {
	private static final long serialVersionUID = -1013466177833987404L;
	
	@Inject @Any
	private Instance<RestValueObjectBuilder<?, ?>> builders;
	
	/**
	 * Constrói o value object de um objeto de domínio.
	 * 
	 * @param entity Objeto de domínio
	 * @param valueObjectClass Classe do value object
	 * @return Value object gerado
	 */
	@SuppressWarnings("unchecked")
	public <V> V buildVo(Object entity, Class<V> valueObjectClass) {
		Validate.notNull(entity, "O parâmetro entity não pode ser nulo");
		Validate.notNull(valueObjectClass, "O parâmetro valueObjectClass não pode ser nulo");
		
		return (V) getBuilder(entity.getClass(), valueObjectClass).buildVo(entity);
	}
	
	/**
	 * Constrói uma lista de value objects a partir de uma coleção de objetos
	 * de domínio.
	 * 
	 * @param entities Coleção de Objetos de domínio
	 * @param valueObjectClass Classe do value object
	 * @return Lista de value objects gerados
	 */
	@SuppressWarnings("unchecked")
	public <V> List<V> buildVos(Collection<? extends Object> entities, Class<V> valueObjectClass) {
		Validate.notNull(entities, "O parâmetro entities não pode ser nulo");
		Validate.notNull(valueObjectClass, "O parâmetro valueObjectClass não pode ser nulo");
		if (entities.isEmpty()) return Lists.newArrayList();
		
		return (List<V>) getBuilder(entities.iterator().next().getClass(), valueObjectClass).buildVos(entities);
	}
	
	/**
	 * Constrói o objeto de domínio representado por um value object.
	 * 
	 * @param valueObject Value object
	 * @param entityClass Classe do objeto de domínio
	 * @return Objeto de domínio gerado
	 */
	@SuppressWarnings("unchecked")
	public <E> E buildEntity(Object valueObject, Class<E> entityClass) {
		Validate.notNull(valueObject, "O parâmetro valueObject não pode ser nulo");
		Validate.notNull(entityClass, "O parâmetro entityClass não pode ser nulo");
		
		return  (E) getBuilder(valueObject.getClass(), entityClass).buildEntity(valueObject);
	}
	
	/**
	 * Constrói uma lista de objetos de domínio representados por uma coleção
	 * de value objects.
	 * 
	 * @param valueObjects Coleção de value objects
	 * @param entityClass Classe do objeto de domínio
	 * @return Lista de objetos de domínio gerados
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> buildEntities(Collection<? extends Object> valueObjects, Class<E> entityClass) {
		Validate.notNull(valueObjects, "O parâmetro valueObjects não pode ser nulo");
		Validate.notNull(entityClass, "O parâmetro entityClass não pode ser nulo");
		if (valueObjects.isEmpty()) return Lists.newArrayList();
		
		return (List<E>) getBuilder(valueObjects.iterator().next().getClass(), entityClass).buildVos(valueObjects);
	}
	
	/**
	 * Recupera o builder de Value Object.
	 * 
	 * @param entityClass Classe do objeto de domínio tratado pelo builder
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
				String.format("Não existe Value Object Builder para Objeto de Domínio do tipo %s e Value Object do tipo %s",
						entityClass.getName(), valueObjectClass.getName()));
	}
}
