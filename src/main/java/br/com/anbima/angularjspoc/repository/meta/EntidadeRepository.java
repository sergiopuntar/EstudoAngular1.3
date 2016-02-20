package br.com.anbima.angularjspoc.repository.meta;

import br.com.anbima.commons.domain.entity.meta.Entidade;
import br.com.anbima.commons.repository.Repository;

/**
 * Interface do repositório de metadados de entidades do sistema.
 */
public interface EntidadeRepository extends Repository<Entidade> {

	/**
	 * Recupera uma entidade a partir do nome qualificado da sua classe.
	 * 
	 * @param nomeQualificadoClasse Nome qualificado da classe da entidade
	 * @return Entidade recuperada
	 */
	Entidade buscarEntidadePorNomeQualificadoClasse(String nomeQualificadoClasse);
}
