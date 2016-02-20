package br.com.anbima.angularjspoc.rest.valueobject.factory;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Implementação base de construtores de Value Objects para serviços REST.
 */
public abstract class BaseRestValueObjectBuilder<E, V> implements RestValueObjectBuilder<E, V> {
	private static final long serialVersionUID = -4061383021846181569L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<V> buildVos(Collection<? extends Object> entities) {
		List<V> vos = Lists.newArrayList();
		
		if (entities != null) {
			for (Object entity : entities) {
				vos.add(buildVo(entity));
			}
		}
		
		return vos;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> buildEntities(Collection<? extends Object> valueObjects) {
		List<E> entities = Lists.newArrayList();
		
		if (valueObjects != null) {
			for (Object valueObject : valueObjects) {
				entities.add(buildEntity(valueObject));
			}
		}
		
		return entities;
	}
}
