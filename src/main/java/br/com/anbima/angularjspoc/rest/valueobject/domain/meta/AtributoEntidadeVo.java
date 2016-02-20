package br.com.anbima.angularjspoc.rest.valueobject.domain.meta;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.anbima.commons.domain.entity.meta.AtributoEntidade;
import br.com.anbima.commons.domain.entity.meta.Entidade;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;
import br.com.anbima.commons.domain.valueobject.meta.TipoTemporal;

/**
 * Value object de um atributo de entidade do sistema.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AtributoEntidadeVo implements Serializable {
	private static final long serialVersionUID = -7933593938682204527L;

	@XmlTransient
	private AtributoEntidade origem;
	
	private String oid;
	private String titulo;
	private String nomePropriedade;
	private String tipoDado;
	private TipoTemporal tipoTemporal;
	private Integer ordenacao;
	private String nomeComponente;
	private String dicaPreenchimento;
	private String mascaraFormatacao;
	private String mensagemErroConversao;
	private Integer tamanho;
	private Boolean identificador;
	private Boolean descritivo;
	private Boolean visivelCadastro;
	private String oidEntidadeOrigem;
	private String oidEntidadeRepresentada;
	
	public AtributoEntidadeVo() {
		super();
	}

	/**
	 * Constrói o value object a partir do objeto de domínio.
	 * 
	 * @param atributoEntidade Objeto de domínio
	 */
	public AtributoEntidadeVo(AtributoEntidade atributoEntidade) {
		this();
		this.origem = atributoEntidade;
		this.oid = atributoEntidade.getOid();
		this.titulo = atributoEntidade.getTitulo();
		this.nomePropriedade = atributoEntidade.getNomePropriedade();
		this.tipoDado = atributoEntidade.getTipoDado();
		this.tipoTemporal = atributoEntidade.getTipoTemporal();
		this.ordenacao = atributoEntidade.getOrdenacao();
		this.nomeComponente = atributoEntidade.getNomeComponente();
		this.dicaPreenchimento = atributoEntidade.getDicaPreenchimento();
		this.mascaraFormatacao = atributoEntidade.getMascaraFormatacao();
		this.mensagemErroConversao = atributoEntidade.getMensagemErroConversao();
		this.tamanho = atributoEntidade.getTamanho();
		this.identificador = atributoEntidade.getIdentificador().booleanValue();
		this.descritivo = atributoEntidade.getDescritivo().booleanValue();
		this.visivelCadastro = atributoEntidade.getVisivelCadastro().booleanValue();
		this.oidEntidadeOrigem = atributoEntidade.getEntidadeOrigem() != null ?
				atributoEntidade.getEntidadeOrigem().getOid() : null;
		this.oidEntidadeRepresentada = atributoEntidade.getEntidadeRepresentada() != null ?
				atributoEntidade.getEntidadeRepresentada().getOid() : null;
	}

	public AtributoEntidade getOrigem() {
		return origem;
	}

	public void setOrigem(AtributoEntidade origem) {
		this.origem = origem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}

	public String getTipoDado() {
		return tipoDado;
	}

	public void setTipoDado(String tipoDado) {
		this.tipoDado = tipoDado;
	}

	public TipoTemporal getTipoTemporal() {
		return tipoTemporal;
	}

	public void setTipoTemporal(TipoTemporal tipoTemporal) {
		this.tipoTemporal = tipoTemporal;
	}

	public Integer getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public void setNomeComponente(String nomeComponente) {
		this.nomeComponente = nomeComponente;
	}

	public String getDicaPreenchimento() {
		return dicaPreenchimento;
	}

	public void setDicaPreenchimento(String dicaPreenchimento) {
		this.dicaPreenchimento = dicaPreenchimento;
	}

	public String getMascaraFormatacao() {
		return mascaraFormatacao;
	}

	public void setMascaraFormatacao(String mascaraFormatacao) {
		this.mascaraFormatacao = mascaraFormatacao;
	}

	public String getMensagemErroConversao() {
		return mensagemErroConversao;
	}

	public void setMensagemErroConversao(String mensagemErroConversao) {
		this.mensagemErroConversao = mensagemErroConversao;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Boolean getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Boolean identificador) {
		this.identificador = identificador;
	}

	public Boolean getDescritivo() {
		return descritivo;
	}

	public void setDescritivo(Boolean descritivo) {
		this.descritivo = descritivo;
	}

	public Boolean getVisivelCadastro() {
		return visivelCadastro;
	}

	public void setVisivelCadastro(Boolean visivelCadastro) {
		this.visivelCadastro = visivelCadastro;
	}

	public String getOidEntidadeOrigem() {
		return oidEntidadeOrigem;
	}

	public void setOidEntidadeOrigem(String oidEntidadeOrigem) {
		this.oidEntidadeOrigem = oidEntidadeOrigem;
	}

	public String getOidEntidadeRepresentada() {
		return oidEntidadeRepresentada;
	}

	public void setOidEntidadeRepresentada(String oidEntidadeRepresentada) {
		this.oidEntidadeRepresentada = oidEntidadeRepresentada;
	}
	
	/**
	 * Gera um objeto de domínio representado por este value object.
	 * 
	 * @return Objeto do domínio
	 */
	public AtributoEntidade gerarEntidadeDominio() {
		AtributoEntidade atributoEntidade = new AtributoEntidade();
		atributoEntidade.setOid(oid);
		atributoEntidade.setTitulo(titulo);
		atributoEntidade.setNomePropriedade(nomePropriedade);
		atributoEntidade.setTipoDado(tipoDado);
		atributoEntidade.setTipoTemporal(tipoTemporal);
		atributoEntidade.setOrdenacao(ordenacao);
		atributoEntidade.setNomeComponente(nomeComponente);
		atributoEntidade.setDicaPreenchimento(dicaPreenchimento);
		atributoEntidade.setMascaraFormatacao(mascaraFormatacao);
		atributoEntidade.setMensagemErroConversao(mensagemErroConversao);
		atributoEntidade.setTamanho(tamanho);
		atributoEntidade.setIdentificador(IndicadorSimNao.fromBoolean(identificador));
		atributoEntidade.setDescritivo(IndicadorSimNao.fromBoolean(descritivo));
		atributoEntidade.setVisivelCadastro(IndicadorSimNao.fromBoolean(visivelCadastro));
		
		Entidade entidadeOrigem = null;
		if (oidEntidadeOrigem != null) {
			entidadeOrigem = new Entidade();
			entidadeOrigem.setOid(oidEntidadeOrigem);
		}
		atributoEntidade.setEntidadeOrigem(entidadeOrigem);
		
		Entidade entidadeRepresentada = null;
		if (oidEntidadeRepresentada != null) {
			entidadeRepresentada = new Entidade();
			entidadeRepresentada.setOid(oidEntidadeRepresentada);
		}
		atributoEntidade.setEntidadeRepresentada(entidadeRepresentada);
		
		return atributoEntidade;
	}
}
