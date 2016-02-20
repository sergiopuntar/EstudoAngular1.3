package br.com.anbima.angularjspoc.service.impl;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.anbima.angularjspoc.repository.meta.AtributoConsultaRepository;
import br.com.anbima.angularjspoc.repository.meta.AtributoEntidadeRepository;
import br.com.anbima.angularjspoc.repository.meta.ConsultaRepository;
import br.com.anbima.angularjspoc.repository.meta.EntidadeRepository;
import br.com.anbima.angularjspoc.service.MetadadosService;
import br.com.anbima.commons.domain.entity.meta.AtributoConsulta;
import br.com.anbima.commons.domain.entity.meta.AtributoEntidade;
import br.com.anbima.commons.domain.entity.meta.Consulta;
import br.com.anbima.commons.domain.entity.meta.Entidade;
import br.com.anbima.commons.valueobject.Ordenacao;
import br.com.anbima.commons.valueobject.Ordenacao.Ordem;

/**
 * Implementação do serviço de metadados do sistema que consome e produz
 * somente value objects.
 */
@Stateless
public class MetadadosServiceImpl implements MetadadosService {
	private static final long serialVersionUID = 3849826370043654029L;
	
	@Inject
	private EntidadeRepository entidadeRepository;
	
	@Inject
	private AtributoEntidadeRepository atributoEntidadeRepository;
	
	@Inject
	private ConsultaRepository consultaRepository;
	
	@Inject
	private AtributoConsultaRepository atributoConsultaRepository;

	@Override
	public List<Entidade> buscarEntidades() {
		return entidadeRepository.buscarTodos(new Ordenacao(Ordem.ASC, "titulo"));
	}

	@Override
	public Entidade buscarEntidadePorIdentificador(String oid) {
		return entidadeRepository.buscarPorId(oid);
	}

	@Override
	public Entidade buscarEntidadePorNomeQualificadoClasse(String nomeQualificadoClasse) {
		return entidadeRepository.buscarEntidadePorNomeQualificadoClasse(nomeQualificadoClasse);
	}

	@Override
	public Entidade salvarEntidade(Entidade entidade) {
		return entidadeRepository.gravar(entidade);
	}

	@Override
	public Entidade excluirEntidade(String oid) {
		Entidade entidade = entidadeRepository.buscarPorId(oid);
		entidadeRepository.excluir(entidade);
		return entidade;
	}

	@Override
	public List<AtributoEntidade> buscarAtributosEntidade(String oidEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtributoEntidade buscarAtributoEntidadePorIdentificador(String oidEntidade, String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtributoEntidade salvarAtributoEntidade(AtributoEntidade atributoEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtributoEntidade> salvarAtributosEntidade(Collection<AtributoEntidade> atributosEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtributoEntidade excluirAtributoEntidade(String oidEntidade, String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> buscarConsultasEntidade(String oidEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta buscarConsultaEntidadePorIdentificador(String oidEntidade, String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta buscarConsultaEntidadePorNome(String oidEntidade, String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta salvarConsultaEntidade(Consulta consulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> salvarConsultasEntidade(Collection<Consulta> consultas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta excluirConsultaEntidade(String oidEntidade, String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtributoConsulta> buscarAtributosConsulta(String oidEntidade, String oidConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtributoConsulta buscarAtributoConsultaPorIdentificador(String oidEntidade, String oidConsulta, String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtributoConsulta salvarAtributoConsulta(AtributoConsulta atributoConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtributoConsulta> salvarAtributosConsulta(Collection<AtributoConsulta> atributosConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtributoConsulta excluirAtributoConsulta(String oidEntidade, String oidConsulta, String oid) {
		// TODO Auto-generated method stub
		return null;
	}
}
