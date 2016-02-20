package br.com.anbima.angularjspoc.rest.valueobject.domain.meta;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.anbima.commons.domain.entity.meta.Consulta;
import br.com.anbima.commons.domain.entity.meta.Entidade;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;

/**
 * Value object de uma consulta de entidade do sistema.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaVo implements Serializable {
	private static final long serialVersionUID = -2095891447590905653L;

	@XmlTransient
	private Consulta origem;
	
	private String oid;
	private String nome;
	private String queryBase;
	private String aliasEntidade;
	private Boolean distinct;
	private String oidEntidade;
	
	public ConsultaVo() {
		super();
	}

	/**
	 * Constrói o value object a partir do objeto de domínio.
	 * 
	 * @param consulta Objeto de domínio
	 */
	public ConsultaVo(Consulta consulta) {
		this();
		this.origem = consulta;
		this.oid = consulta.getOid();
		this.nome = consulta.getNome();
		this.queryBase = consulta.getQueryBase();
		this.aliasEntidade = consulta.getAliasEntidade();
		this.distinct = consulta.getDistinct().booleanValue();
		this.oidEntidade = consulta.getEntidade() != null
				? consulta.getEntidade().getOid() : null;
	}

	public Consulta getOrigem() {
		return origem;
	}

	public void setOrigem(Consulta origem) {
		this.origem = origem;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQueryBase() {
		return queryBase;
	}

	public void setQueryBase(String queryBase) {
		this.queryBase = queryBase;
	}

	public String getAliasEntidade() {
		return aliasEntidade;
	}

	public void setAliasEntidade(String aliasEntidade) {
		this.aliasEntidade = aliasEntidade;
	}

	public Boolean getDistinct() {
		return distinct;
	}

	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
	}

	public String getOidEntidade() {
		return oidEntidade;
	}

	public void setOidEntidade(String oidEntidade) {
		this.oidEntidade = oidEntidade;
	}
	
	/**
	 * Gera um objeto de domínio representado por este value object.
	 * 
	 * @return Objeto do domínio
	 */
	public Consulta gerarEntidadeDominio() {
		Consulta consulta = new Consulta();
		consulta.setOid(oid);
		consulta.setNome(nome);
		consulta.setQueryBase(queryBase);
		consulta.setAliasEntidade(aliasEntidade);
		consulta.setDistinct(IndicadorSimNao.fromBoolean(distinct));
		
		Entidade entidade = null;
		if (oidEntidade != null) {
			entidade = new Entidade();
			entidade.setOid(oidEntidade);
		}
		consulta.setEntidade(entidade);
		
		return consulta;
	}
}
