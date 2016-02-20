package br.com.anbima.commons.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.exception.DAOException;
import br.com.anbima.commons.repository.Repository;
import br.com.anbima.commons.valueobject.Ordenacao;

/**
 * Classe abstrata genérica para classes de Acesso ao Banco de Dados.
 *
 * @param <E> Tipo da entidade gerenciada pelo DAO
 */
@SuppressWarnings("unchecked")
public abstract class BaseDAO<E extends AbstractEntity> implements Repository<E> {
	private static final long serialVersionUID = -8074376041013278584L;

	private static final Logger LOG = LoggerFactory.getLogger(BaseDAO.class);

	@PersistenceContext
	protected EntityManager em;

	protected Class<E> clazz;

	@PostConstruct
	public void postConstruct() {
		clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public E buscarPorId(String oid) {
		return em.find(clazz, oid);
	}

	@Override
	public E buscarPorIdComFetch(String oid, String... fetchProperties) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(clazz);
		Root<E> root = cq.from(clazz);
		
		for (String propriedade : fetchProperties) {
			root.join(propriedade);
		}
		
		cq.select(root);
		cq.where(cb.equal(root.get("oid"), oid));
		
		TypedQuery<E> q = em.createQuery(cq);
		
		return q.getSingleResult();
	}

	@Override
	public List<E> buscarTodos() {
		return em.createQuery("from " + clazz.getName()).getResultList();
	}

	@Override
	public List<E> buscarTodos(Ordenacao... ordenacao) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(clazz);
		Root<E> root = cq.from(clazz);
		
		cq.select(root);
		
		for (Ordenacao ordem : ordenacao) {
			Path<Object> exp = root.get(ordem.getNomePropriedade());
			
			switch (ordem.getOrdem()) {
			case ASC:
				cq.orderBy(cb.asc(exp));
				break;

			case DESC:
				cq.orderBy(cb.desc(exp));
				break;
			}
		}
		
		
		TypedQuery<E> q = em.createQuery(cq);
		
		return q.getResultList();
	}

	@Override
	public E persist(E entidade) {
		try {
			em.persist(entidade);
			em.flush();

			return entidade;
		} catch (PersistenceException exception) {
			LOG.error(exception.getMessage(), exception);
			throw new DAOException(exception.getMessage(), exception);
		}
	}

	@Override
	public E merge(E entidade) {
		try {
			E entidadePersistida = em.merge(entidade);
			em.flush();

			return entidadePersistida;
		} catch (PersistenceException exception) {
			LOG.error(exception.getMessage(), exception);
			throw new DAOException(exception.getMessage(), exception);
		}
	}

	@Override
	public List<E> merge(List<E> entidades) {
		try {
			List<E> entidadesAtualizadas = new ArrayList<E>();
			for (E entidade : entidades) {
				em.merge(entidade);
				entidadesAtualizadas.add(entidade);
			}

			return entidadesAtualizadas;

		} catch (PersistenceException exception) {
			LOG.error(exception.getMessage(), exception);
			throw new DAOException(exception.getMessage(), exception);
		}
	}

	@Override
	public void refresh(E entidade) {
		em.refresh(entidade);
	}

	@Override
	public E gravar(E entidade) {
		try {
			E entidadePersistida = entidade;

			if (entidade.getOid() == null || entidade.getOid().isEmpty()) {
				em.persist(entidade);
			} else {
				entidadePersistida = em.merge(entidade);
			}

			em.flush();

			return entidadePersistida;
		} catch (PersistenceException exception) {
			LOG.error(exception.getMessage(), exception);
			throw new DAOException(exception.getMessage(), exception);
		}
	}

	@Override
	public void excluir(E entidade) {
		try {
			em.remove(entidade);
			em.flush();
		} catch (PersistenceException exception) {
			LOG.error(exception.getMessage(), exception);
			throw new DAOException(exception.getMessage(), exception);
		}
	}
}
