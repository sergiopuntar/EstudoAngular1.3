package br.com.anbima.angularjspoc.dao.meta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.anbima.angularjspoc.repository.meta.EntidadeGenericaRepository;
import br.com.anbima.commons.domain.entity.EntityInterface;
import br.com.anbima.commons.domain.entity.meta.AtributoConsulta;
import br.com.anbima.commons.domain.entity.meta.Consulta;
import br.com.anbima.commons.domain.entity.meta.Entidade;
import br.com.anbima.commons.exception.DAOException;
import br.com.anbima.commons.exception.MetadadosException;

/**
 * Implementação JPA do repositório genérico baseado em metadados de entidades.
 */
public class EntidadeGenericaDAO implements EntidadeGenericaRepository {
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	public EntityInterface buscarInstanciaEntidadePorIdentificador(Entidade entidade, String oid) {
		return (EntityInterface) em.find(recuperarClasseEntidade(entidade), oid);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EntityInterface> buscarTodasInstanciasEntidade(Entidade entidade) {
		Query query = em.createQuery(entidade.getQueryTodas(), recuperarClasseEntidade(entidade));
		
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EntityInterface> buscarInstanciasEntidadePorDescritivo(Entidade entidade, String descritivo) {
		Query query = em.createQuery(entidade.getQueryPorDescritivo(), recuperarClasseEntidade(entidade));
		query.setParameter(Entidade.SQL_PARAM_DESCRITIVO, descritivo);
		
		return query.getResultList();
	}

	@Override
	public List<?> buscarDescritivosInstanciaAtributoConsulta(AtributoConsulta atributoConsulta, String oidInstancia) {
		Query query = em.createQuery(atributoConsulta.getConsulta().getQueryDescritivoAtributo(atributoConsulta));
		query.setParameter(Consulta.SQL_PARAM_OID, oidInstancia);
		
		return query.getResultList();
	}

	@Override
	public EntityInterface salvarInstanciaEntidade(EntityInterface instancia) {
		EntityInterface instanciaPersistida = instancia;
		
		try {
			if (instancia.getOid() == null || instancia.getOid().isEmpty()) {
				em.persist(instancia);
			} else {
				instanciaPersistida = em.merge(instancia);
			}
			
			em.flush();
		} catch (PersistenceException exception) {
			throw new DAOException(exception.getMessage(), exception);
		}
		
		return instanciaPersistida;
	}

	@Override
	public void excluirInstanciaEntidade(EntityInterface instancia) {
		try {
			em.remove(instancia);
			em.flush();
		} catch (PersistenceException exception) {
			throw new DAOException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Recupera a classe de uma entidade a partir dos seus metadados.
	 * 
	 * @param entidade Metadados da entidade
	 * @return Classe da entidade
	 */
	private Class<?> recuperarClasseEntidade(Entidade entidade) {
		try {
			return entidade.getClasse();
		} catch (MetadadosException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
}
