package br.com.anbima.angularjspoc.repository.meta;

import java.util.List;

import br.com.anbima.commons.domain.entity.EntityInterface;
import br.com.anbima.commons.domain.entity.meta.AtributoConsulta;
import br.com.anbima.commons.domain.entity.meta.Entidade;

/**
 * Interface do reposit�rio de entidades gen�ricas do sistema.<br>
 * Pode ser utilizado para recuperar qualquer entidade a partir dos seus metadados.
 */
public interface EntidadeGenericaRepository {

	/**
	 * Busca uma inst�ncia de uma entidade a partir do seu identificador.
	 * 
	 * @param entidade Metadados da entidade
	 * @param oid Identificador da entidade
	 * @return Inst�ncia encontrada
	 */
	EntityInterface buscarInstanciaEntidadePorIdentificador(Entidade entidade, String oid);

	/**
	 * Busca todas as inst�ncias de uma determinada entidade
	 * 
	 * @param entidade Metadados da entidade
	 * @return Todas as inst�ncias da entidade encontardas
	 */
	List<EntityInterface> buscarTodasInstanciasEntidade(Entidade entidade);
	
	/**
	 * Busca todas as inst�ncias de uma determinada entidade que possuam um
	 * determinado descritivo.
	 * 
	 * @param entidade Metadados da entidade
	 * @param descritivo Descritivo a ser buscado
	 * @return Entidades encontardas que possuem o descritivo
	 */
	List<EntityInterface> buscarInstanciasEntidadePorDescritivo(Entidade entidade, String descritivo);
	
	/**
	 * Recupera os descritivos de um atributo de consulta que representa outra
	 * entidade, a partir do identificador de uma inst�ncia da entidade base da
	 * consulta.
	 * 
	 * @param atributoConsulta Atributo de consulta que representa outra
	 * entidade
	 * @param oidInstancia Identificador de uma inst�ncia da entidade base da
	 * consulta
	 * @return Descritivos do atributo da consulta
	 */
	List<?> buscarDescritivosInstanciaAtributoConsulta(AtributoConsulta atributoConsulta, String oidInstancia);
	
	/**
	 * Salva na base uma inst�ncia de uma entidade.
	 * 
	 * @param instancia Inst�ncia a ser salva
	 * @return Inst�ncia salva
	 */
	EntityInterface salvarInstanciaEntidade(EntityInterface instancia);
	
	/**
	 * Remove da base uma inst�ncia de uma entidade.
	 * 
	 * @param instancia Inst�ncia a ser removida
	 */
	void excluirInstanciaEntidade(EntityInterface instancia);
}
