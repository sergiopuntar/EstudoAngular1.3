package br.com.anbima.angularjspoc.dao.meta;

import java.util.List;

import javax.persistence.Query;

import br.com.anbima.angularjspoc.repository.meta.EntidadeRepository;
import br.com.anbima.commons.dao.BaseDAO;
import br.com.anbima.commons.domain.entity.meta.Entidade;

/**
 * Implementação JPA do repositório de Entidades do sistema.
 */
public class EntidadeDAO extends BaseDAO<Entidade> implements EntidadeRepository {
	private static final long serialVersionUID = -3784887683713103520L;

	@Override
	@SuppressWarnings("rawtypes")
	public Entidade buscarEntidadePorNomeQualificadoClasse(String nomeQualificadoClasse) {
		Query query = em.createNamedQuery("Entidade.buscarPorNomeQualificadoClasse");
		query.setParameter("nomeQualificadoClasseEntidade", nomeQualificadoClasse);
		List result = query.getResultList();

		return result.size() > 0 ? (Entidade) result.get(0) : null;
	}
}
