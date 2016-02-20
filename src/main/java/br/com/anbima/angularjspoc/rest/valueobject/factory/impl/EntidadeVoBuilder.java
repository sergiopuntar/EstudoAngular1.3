package br.com.anbima.angularjspoc.rest.valueobject.factory.impl;

import javax.enterprise.context.ApplicationScoped;

import br.com.anbima.angularjspoc.rest.valueobject.domain.meta.EntidadeVo;
import br.com.anbima.angularjspoc.rest.valueobject.factory.BaseRestValueObjectBuilder;
import br.com.anbima.commons.domain.entity.meta.Entidade;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;

/**
 * Construtor de Value Object de entidade.
 */
@ApplicationScoped
public class EntidadeVoBuilder extends BaseRestValueObjectBuilder<Entidade, EntidadeVo> {
	private static final long serialVersionUID = -8791610000648961647L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntidadeVo buildVo(Object entity) {
		Entidade entidade = (Entidade) entity;
		
		EntidadeVo entidadeVo = new EntidadeVo();
		entidadeVo.setOid(entidade.getOid());
		entidadeVo.setTitulo(entidade.getTitulo());
		entidadeVo.setNomeClasse(entidade.getNomeClasse());
		entidadeVo.setNomeQualificadoClasse(entidade.getNomeQualificadoClasse());
		entidadeVo.setVisivelCadastro(entidade.getVisivelCadastro().booleanValue());
		
		return entidadeVo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Entidade buildEntity(Object valueObject) {
		EntidadeVo entidadeVo = (EntidadeVo) valueObject;
		
		Entidade entidade = new Entidade();
		entidade.setOid(entidadeVo.getOid());
		entidade.setTitulo(entidadeVo.getTitulo());
		entidade.setNomeClasse(entidadeVo.getNomeClasse());
		entidade.setNomeQualificadoClasse(entidadeVo.getNomeQualificadoClasse());
		entidade.setVisivelCadastro(IndicadorSimNao.fromBoolean(entidadeVo.getVisivelCadastro()));
		
		return entidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<Entidade> getEntityClass() {
		return Entidade.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<EntidadeVo> getValueObjectClass() {
		return EntidadeVo.class;
	}
}
