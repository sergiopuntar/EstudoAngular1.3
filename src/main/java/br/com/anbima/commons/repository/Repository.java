package br.com.anbima.commons.repository;

import java.io.Serializable;
import java.util.List;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.valueobject.Ordenacao;

/**
 * Interface gen�rica de reposit�rios de entidades.
 *
 * @param <E> Tipo da entidade do reposit�rio
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
	 * Persiste uma entidade na base de dados atrav�s do persit.
	 *
	 * @param entidade Entidade a ser persistida.
	 * @return Entidade gerenciada.
	 */
	E persist(E entidade);

	/**
	 * Persiste uma entidade na base de dados atrav�s do merge.
	 *
	 * @param entidade Entidade a ser persistida.
	 * @return Entidade gerenciada.
	 */
	E merge(E entidade);

	/**
	 * Persiste v�rias entidades na base de dados atrav�s do merge.
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
	 * Grava a entidade no reposit�rio. Se a entidade ainda n�o possui um
	 * identificador ser� executado o persist, caso contr�rio, ser� executado o
	 * merge.
	 *
	 * @param entidade Entidade a ser persistida.
	 * @return Entidade gerenciada.
	 */
	E gravar(E entidade);

	/**
	 * Remove a entidade do reposit�rio.
	 *
	 * @param entidade Entidade a ser removida
	 */
	void excluir(E entidade);
}
