package br.com.anbima.commons.repository;

import java.io.Serializable;
import java.util.List;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.valueobject.Ordenacao;

/**
 * Interface genérica de repositórios de entidades.
 *
 * @param <E> Tipo da entidade do repositório
 */
public interface Repository<E extends AbstractEntity> extends Serializable {

	/**
	 * Recupera uma entidade a partir do seu identificador.
	 *
	 * @param oid Identificador da entidade
	 * @return Entidade recuperada
	 */
	E buscarPorId(String oid);

	/**
	 * Recupera uma entidade a partir do seu identificador, realizando fetch com as propriedades.
	 *
	 * @param oid Identificador da entidade
	 * @param fetchProperties
	 * @return Entidade recuperada
	 */
	E buscarPorIdComFetch(String oid, String... fetchProperties);

	/**
	 * Recupera todas as entidade.
	 *
	 * @return List<E> Entidades recuperadas
	 */
	List<E> buscarTodos();

	/**
	 * Buscar Todos, com Ordenacao.
	 *
	 * @param ordenacao
	 * @return List<E>
	 */
	List<E> buscarTodos(Ordenacao... ordenacao);

	/**
	 * Persiste uma entidade na base de dados através do persit.
	 *
	 * @param entidade Entidade a ser persistida.
	 * @return Entidade gerenciada.
	 */
	E persist(E entidade);

	/**
	 * Persiste uma entidade na base de dados através do merge.
	 *
	 * @param entidade Entidade a ser persistida.
	 * @return Entidade gerenciada.
	 */
	E merge(E entidade);

	/**
	 * Persiste várias entidades na base de dados através do merge.
	 *
	 * @param entidades Entidades a serem persistidas
	 * @return Entidades gerenciadas
	 */
	List<E> merge(List<E> entidades);

	/**
	 * Atualiza o estado na entidade entidade, sincronizando com a base de
	 * dados.
	 *
	 * @param entidade Entidade a ser atualizada.
	 */
	void refresh(E entidade);

	/**
	 * Grava a entidade no repositório. Se a entidade ainda não possui um
	 * identificador será executado o persist, caso contrário, será executado o
	 * merge.
	 *
	 * @param entidade Entidade a ser persistida.
	 * @return Entidade gerenciada.
	 */
	E gravar(E entidade);

	/**
	 * Remove a entidade do repositório.
	 *
	 * @param entidade Entidade a ser removida
	 */
	void excluir(E entidade);
}
