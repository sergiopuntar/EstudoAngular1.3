package br.com.anbima.angularjspoc.rest.valueobject.domain.meta;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.anbima.commons.domain.entity.meta.AtributoConsulta;
import br.com.anbima.commons.domain.entity.meta.AtributoEntidade;
import br.com.anbima.commons.domain.entity.meta.Consulta;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;
import br.com.anbima.commons.domain.valueobject.meta.TipoAlinhamento;

/**
 * Value object de um atributo de consulta de entidade do sistema.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AtributoConsultaVo implements Serializable {
	private static final long serialVersionUID = 2803707360193449195L;

	@XmlTransient
	private AtributoConsulta origem;

	private String oid;
	private String classesCss;
	private TipoAlinhamento alinhamento;
	private Integer ordenacao;
	private Boolean apresentar;
	private Boolean apresentarPadrao;
	private Boolean filtragem;
	private Boolean link;
	private String labelLink;
	private String nomePropriedadeLink;
	private String acaoLink;
	private String oidConsulta;
	private String oidAtributoEntidade;
	
	public AtributoConsultaVo() {
		super();
	}

	/**
	 * Constrói o value object a partir do objeto de domínio.
	 * 
	 * @param atributoConsulta Objeto de domínio
	 */
	public AtributoConsultaVo(AtributoConsulta atributoConsulta) {
		super();
		this.origem = atributoConsulta;
		this.oid = atributoConsulta.getOid();
		this.classesCss = atributoConsulta.getClassesCss();
		this.alinhamento = atributoConsulta.getAlinhamento();
		this.ordenacao = atributoConsulta.getOrdenacao();
		this.apresentar = atributoConsulta.getApresentar().booleanValue();
		this.apresentarPadrao = atributoConsulta.getApresentarPadrao().booleanValue();
		this.filtragem = atributoConsulta.getFiltragem().booleanValue();
		this.link = atributoConsulta.getLink().booleanValue();
		this.labelLink = atributoConsulta.getLabelLink();
		this.nomePropriedadeLink = atributoConsulta.getNomePropriedadeLink();
		this.acaoLink = atributoConsulta.getAcaoLink();
		this.oidConsulta = atributoConsulta.getConsulta() != null
				? atributoConsulta.getConsulta().getOid() : null;
		this.oidAtributoEntidade = atributoConsulta.getAtributoEntidade() != null
				? atributoConsulta.getAtributoEntidade().getOid() : null;
	}

	public AtributoConsulta getOrigem() {
		return origem;
	}

	public void setOrigem(AtributoConsulta origem) {
		this.origem = origem;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getClassesCss() {
		return classesCss;
	}

	public void setClassesCss(String classesCss) {
		this.classesCss = classesCss;
	}

	public TipoAlinhamento getAlinhamento() {
		return alinhamento;
	}

	public void setAlinhamento(TipoAlinhamento alinhamento) {
		this.alinhamento = alinhamento;
	}

	public Integer getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}

	public Boolean getApresentar() {
		return apresentar;
	}

	public void setApresentar(Boolean apresentar) {
		this.apresentar = apresentar;
	}

	public Boolean getApresentarPadrao() {
		return apresentarPadrao;
	}

	public void setApresentarPadrao(Boolean apresentarPadrao) {
		this.apresentarPadrao = apresentarPadrao;
	}

	public Boolean getFiltragem() {
		return filtragem;
	}

	public void setFiltragem(Boolean filtragem) {
		this.filtragem = filtragem;
	}

	public Boolean getLink() {
		return link;
	}

	public void setLink(Boolean link) {
		this.link = link;
	}

	public String getLabelLink() {
		return labelLink;
	}

	public void setLabelLink(String labelLink) {
		this.labelLink = labelLink;
	}

	public String getNomePropriedadeLink() {
		return nomePropriedadeLink;
	}

	public void setNomePropriedadeLink(String nomePropriedadeLink) {
		this.nomePropriedadeLink = nomePropriedadeLink;
	}

	public String getAcaoLink() {
		return acaoLink;
	}

	public void setAcaoLink(String acaoLink) {
		this.acaoLink = acaoLink;
	}

	public String getOidConsulta() {
		return oidConsulta;
	}

	public void setOidConsulta(String oidConsulta) {
		this.oidConsulta = oidConsulta;
	}

	public String getOidAtributoEntidade() {
		return oidAtributoEntidade;
	}

	public void setOidAtributoEntidade(String oidAtributoEntidade) {
		this.oidAtributoEntidade = oidAtributoEntidade;
	}
	
	/**
	 * Gera um objeto de domínio representado por este value object.
	 * 
	 * @return Objeto do domínio
	 */
	public AtributoConsulta gerarEntidadeDominio() {
		AtributoConsulta atributoConsulta = new AtributoConsulta();
		atributoConsulta.setOid(oid);
		atributoConsulta.setClassesCss(classesCss);
		atributoConsulta.setAlinhamento(alinhamento);
		atributoConsulta.setOrdenacao(ordenacao);
		atributoConsulta.setApresentar(IndicadorSimNao.fromBoolean(apresentar));
		atributoConsulta.setApresentarPadrao(IndicadorSimNao.fromBoolean(apresentarPadrao));
		atributoConsulta.setFiltragem(IndicadorSimNao.fromBoolean(filtragem));
		atributoConsulta.setLink(IndicadorSimNao.fromBoolean(link));
		atributoConsulta.setLabelLink(labelLink);
		atributoConsulta.setNomePropriedadeLink(nomePropriedadeLink);
		atributoConsulta.setAcaoLink(acaoLink);
		
		Consulta consulta = null;
		if (oidConsulta != null) {
			consulta = new Consulta();
			consulta.setOid(oidConsulta);
		}
		atributoConsulta.setConsulta(consulta);
		
		AtributoEntidade atributoEntidade = null;
		if (oidAtributoEntidade != null) {
			atributoEntidade = new AtributoEntidade();
			atributoEntidade.setOid(oid);
		}
		atributoConsulta.setAtributoEntidade(atributoEntidade);
		
		return atributoConsulta;
	}
}
