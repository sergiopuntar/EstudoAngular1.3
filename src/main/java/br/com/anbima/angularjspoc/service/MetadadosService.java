package br.com.anbima.angularjspoc.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.com.anbima.commons.domain.entity.meta.AtributoConsulta;
import br.com.anbima.commons.domain.entity.meta.AtributoEntidade;
import br.com.anbima.commons.domain.entity.meta.Consulta;
import br.com.anbima.commons.domain.entity.meta.Entidade;

/**
 * Serviço de metadados do sistema que consome e produz somente value objects.
 */
public interface MetadadosService extends Serializable {

	/**
	 * Busca todas as entidades.
	 * 
	 * @return Lista das entidades recuperadas
	 */
	List<Entidade> buscarEntidades();

	/**
	 * Busca uma entidade a partir do seu identificador.
	 * 
	 * @param oid Identificador da entidade
	 * @return Entidade recuperada
	 */
	Entidade buscarEntidadePorIdentificador(String oid);
	
	/**
	 * Busca uma entidade a partir do nome qualificado da sua classe.
	 * 
	 * @param nomeQualificadoClasse Nome qualificado da classe da entidade
	 * @return Entidade encontrada
	 */
	Entidade buscarEntidadePorNomeQualificadoClasse(String nomeQualificadoClasse);

	/**
	 * Salva uma entidade.
	 * 
	 * @param entidade Entidade a ser salva
	 * @return Entidade salva
	 */
	Entidade salvarEntidade(Entidade entidade);

	/**
	 * Exclui uma entidade.
	 * 
	 * @param oid Identificador da entidade a ser excluída
	 * @return Entidade excluida
	 */
	Entidade excluirEntidade(String oid);
	
	/**
	 * Busca todos os atributos de uma entidade.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @return Lista de atributos de entidade recuperados
	 */
	List<AtributoEntidade> buscarAtributosEntidade(String oidEntidade);
	
	/**
	 * Busca um atributo de uma entidade a partir do seu identificador.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param oid Identificador do atributo de entidade
	 * @return Atributo de entidade recuperado
	 */
	AtributoEntidade buscarAtributoEntidadePorIdentificador(String oidEntidade, String oid);
	
	/**
	 * Salva um atributo de uma entidade.
	 * 
	 * @param atributoEntidade Atributo de entidade a ser salvo
	 * @return Atributo de entidade salvo
	 */
	AtributoEntidade salvarAtributoEntidade(AtributoEntidade atributoEntidade);
	
	/**
	 * Salva uma coleção de atributos de entidade.
	 * 
	 * @param atributosEntidade Atributos de entidade a serem salvos
	 * @return Atributos de entidade salvos
	 */
	List<AtributoEntidade> salvarAtributosEntidade(Collection<AtributoEntidade> atributosEntidade);
	
	/**
	 * Exclui um atributo de uma entidade.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param oid Identificador do atributo de entidade
	 * @return Atributo de entidade excluído
	 */
	AtributoEntidade excluirAtributoEntidade(String oidEntidade, String oid);
	
	/**
	 * Busca todas as consultas de uma entidade.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @return Lista de consultas de entidade recuperadas
	 */
	List<Consulta> buscarConsultasEntidade(String oidEntidade);
	
	/**
	 * Busca uma consulta de uma entidade a partir do seu identificador.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param oid Identificador da consulta de entidade
	 * @return Consulta de entidade recuperada
	 */
	Consulta buscarConsultaEntidadePorIdentificador(String oidEntidade, String oid);
	
	/**
	 * Busca uma consulta de uma entidade a partir do seu nome.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param nome Nome da consulta de entidade
	 * @return Consulta de entidade recuperada
	 */
	Consulta buscarConsultaEntidadePorNome(String oidEntidade, String nome);
	
	/**
	 * Salva uma consulta de uma entidade.
	 * 
	 * @param consulta Consulta de entidade a ser salva
	 * @return Consulta de entidade salva
	 */
	Consulta salvarConsultaEntidade(Consulta consulta);
	
	/**
	 * Salva uma coleção de consultas de entidade.
	 * 
	 * @param consultas Consultas de entidade a serem salvas
	 * @return Consultas de entidade salvas
	 */
	List<Consulta> salvarConsultasEntidade(Collection<Consulta> consultas);
	
	/**
	 * Exclui uma consulta de uma entidade.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param oid Identificador da consulta de entidade
	 * @return Consulta de entidade excluída
	 */
	Consulta excluirConsultaEntidade(String oidEntidade, String oid);
	
	/**
	 * Busca todos os atributos de uma consulta de uma entidade.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param oidConsulta Identificador da consulta de entidade
	 * @return Lista de atributos de consulta de entidade recuperados
	 */
	List<AtributoConsulta> buscarAtributosConsulta(String oidEntidade, String oidConsulta);
	
	/**
	 * Busca um atributo de uma consulta de de uma entidade a partir do seu
	 * identificador.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param oidConsulta  Identificador da consulta de entidade
	 * @param oid Identificador do atributo de consulta de entidade
	 * @return Atributo de consulta de entidade recuperado
	 */
	AtributoConsulta buscarAtributoConsultaPorIdentificador(String oidEntidade, String oidConsulta, String oid);
	
	/**
	 * Salva um atributo de uma consulta de uma entidade.
	 * 
	 * @param atributoConsulta Atributo de consulta de entidade a ser salvo
	 * @return Atributo de consulta de entidade salvo
	 */
	AtributoConsulta salvarAtributoConsulta(AtributoConsulta atributoConsulta);
	
	/**
	 * Salva uma coleção de atributos de consulta de entidade.
	 * 
	 * @param atributosConsulta Atributos de consulta de entidade a serem salvos
	 * @return Atributos de consulta de entidade salvos
	 */
	List<AtributoConsulta> salvarAtributosConsulta(Collection<AtributoConsulta> atributosConsulta);
	
	/**
	 * Exclui um atributo de uma consulta de uma entidade.
	 * 
	 * @param oidEntidade Identificador da entidade
	 * @param oidConsulta Identificador da consulta de entidade
	 * @param oid Identificador do atributo de consulta de entidade
	 * @return Atributo de consulta de entidade excluído
	 */
	AtributoConsulta excluirAtributoConsulta(String oidEntidade, String oidConsulta, String oid);
}
