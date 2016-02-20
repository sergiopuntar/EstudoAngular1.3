package br.com.anbima.commons.domain.entity.meta;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;
import br.com.anbima.commons.domain.valueobject.meta.ConjuntoTipoDado;
import br.com.anbima.commons.domain.valueobject.meta.TipoAlinhamento;
import br.com.anbima.commons.domain.valueobject.meta.TipoTemporal;

/**
 * Relacionamento entre os metadados de um atributo de uma entidade e os
 * metadados de uma consulta da mesma. Cada atributo de consulta � uma poss�vel
 * proje��o.
 */
@Audited
public class AtributoConsulta extends AbstractEntity {
	private static final long serialVersionUID = 1474999149597561334L;

	public static final String LINK_PARAM_NAME = "link_param";

	private static final String STR_SPACE = " ";

	private static final String SQL_OPERADOR_LIKE = "like";
	private static final String SQL_OPERADOR_IGUAL = "=";

	@Size(max = 100, message = "As classes CSS do atributo devem possuir no m�ximo 100 caracteres no total")
	private String classesCss;

	private TipoAlinhamento alinhamento;

	@NotNull(message = "Informe a numera��o de ordena��o do atributo")
	@Digits(integer = 3, fraction = 0, message = "A numera��o de ordena��o deve ser um inteiro de at� 3 d�gitos")
	private Integer ordenacao;

	@NotNull(message = "Informe se o atributo deve ser apresentado no relat�rio da consulta")
	private IndicadorSimNao apresentar;

	@NotNull(message = "Informe se o atributo deve ser apresentado por padr�o no relat�rio da consulta")
	private IndicadorSimNao apresentarPadrao;

	@NotNull(message = "Informe se o atributo pode ser utilizado como filtro no relat�rio da consulta")
	private IndicadorSimNao filtragem;

	@NotNull(message = "Informe se o atributo ser� um link no relat�rio da consulta")
	private IndicadorSimNao link;
	
	@Size(max = 500, message = "O nome da propriedade do link deve possuir no m�ximo 500 caracteres")
	private String nomePropriedadeLink;
	
	@Size(max = 200, message = "A legenda do link deve possuir no m�ximo 200 caracteres")
	private String labelLink;
	
	@Size(max = 200, message = "A a��o do link deve possuir no m�ximo 200 caracteres")
	private String acaoLink;

	@NotNull(message = "Informe a consulta de origem do atributo")
	private Consulta consulta;
	
	@NotNull(message = "Informe o atributo de entidade referenciado por este atributo de consulta")
	private AtributoEntidade atributoEntidade;

	/**
	 * Construtor.
	 */
	public AtributoConsulta() {
		super();
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

	public IndicadorSimNao getApresentar() {
		return apresentar;
	}

	public void setApresentar(IndicadorSimNao apresentar) {
		this.apresentar = apresentar;
	}

	public IndicadorSimNao getApresentarPadrao() {
		return apresentarPadrao;
	}

	public void setApresentarPadrao(IndicadorSimNao apresentarPadrao) {
		this.apresentarPadrao = apresentarPadrao;
	}

	public IndicadorSimNao getFiltragem() {
		return filtragem;
	}

	public void setFiltragem(IndicadorSimNao filtragem) {
		this.filtragem = filtragem;
	}

	public IndicadorSimNao getLink() {
		return link;
	}

	public void setLink(IndicadorSimNao link) {
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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public AtributoEntidade getAtributoEntidade() {
		return atributoEntidade;
	}

	public void setAtributoEntidade(AtributoEntidade atributoEntidade) {
		this.atributoEntidade = atributoEntidade;
	}

	// M�todos de neg�cio

	/**
	 * Verifica se o atributo pode ser apresentado em relat�rios.
	 *
	 * @return True se o atributo pode ser apresentado em relat�rios, False caso
	 *         contr�rio.
	 */
	public boolean possuiApresentacaoAtiva() {
		return getApresentar().equals(IndicadorSimNao.S);
	}

	/**
	 * Define o atributo como apresent�vel em relat�rios.
	 */
	public void ativarApresentacao() {
		setApresentar(IndicadorSimNao.S);
	}

	/**
	 * Define o atributo como n�o apresent�vel em relat�rios.
	 */
	public void desativarApresentacao() {
		setApresentar(IndicadorSimNao.N);
	}

	/**
	 * Verifica se o atributo deve ser apresentado em relat�rios por padr�o.
	 *
	 * @return True se o atributo deve ser apresentado em relat�rios por padr�o,
	 *         False caso contr�rio.
	 */
	public boolean possuiApresentacaoPadrao() {
		return getApresentarPadrao().equals(IndicadorSimNao.S);
	}

	/**
	 * Define o atributo como apresent�vel por padr�o em relat�rios.
	 */
	public void ativarApresentacaoPadrao() {
		setApresentarPadrao(IndicadorSimNao.S);
	}

	/**
	 * Define o atributo como n�o apresent�vel por padr�o em relat�rios.
	 */
	public void desativarApresentacaoPadrao() {
		setApresentarPadrao(IndicadorSimNao.N);
	}

	/**
	 * Verifica se o atributo � de filtragem da entidade.
	 *
	 * @return True se o atributo � de filtragem, False caso contr�rio.
	 */
	public boolean ehFiltragem() {
		return getFiltragem().equals(IndicadorSimNao.S);
	}

	/**
	 * Define o atributo como de filtragem da entidade.
	 */
	public void definirComoFiltragem() {
		setFiltragem(IndicadorSimNao.S);
	}

	/**
	 * Define o atributo como n�o de filtragem da entidade.
	 */
	public void definirComoNaoFiltragem() {
		setFiltragem(IndicadorSimNao.N);
	}

	/**
	 * Verifica se o atributo deve ser apresentado como link.
	 *
	 * @return True se o atributo deve ser apresentado como link, False caso
	 *         contr�rio.
	 */
	public boolean ehLink() {
		return getLink().equals(IndicadorSimNao.S);
	}

	/**
	 * Define apresenta��o como link para o atributo.
	 */
	public void definirComoLink() {
		setLink(IndicadorSimNao.S);
	}

	/**
	 * Define apresenta��o como n�o link para o atributo.
	 */
	public void definirComoNaoLink() {
		setLink(IndicadorSimNao.N);
	}

	public String getTitulo() {
		return getAtributoEntidade().getTitulo();
	}

	public String getNomePropriedade() {
		return getAtributoEntidade().getNomePropriedade();
	}

	public String getTipoDado() {
		return getAtributoEntidade().getTipoDado();
	}

	public TipoTemporal getTipoTemporal() {
		return getAtributoEntidade().getTipoTemporal();
	}

	public String getNomeComponente() {
		return getAtributoEntidade().getNomeComponente();
	}

	public String getDicaPreenchimento() {
		return getAtributoEntidade().getDicaPreenchimento();
	}

	public String getMascaraFormatacao() {
		return getAtributoEntidade().getMascaraFormatacao();
	}

	public Integer getTamanho() {
		return getAtributoEntidade().getTamanho();
	}

	public Entidade getEntidadeOrigem() {
		return getAtributoEntidade().getEntidadeOrigem();
	}

	public Entidade getEntidadeRepresentada() {
		return getAtributoEntidade().getEntidadeRepresentada();
	}

	/**
	 * Recupera o tipo de operador para restri��o de filtragem do atributo.
	 *
	 * @return Operador para filtragem
	 */
	public String getOperadorFiltragem() {
		return ConjuntoTipoDado.ehTextual(getTipoDado()) ? SQL_OPERADOR_LIKE
				: SQL_OPERADOR_IGUAL;
	}

	/**
	 * Recupera as classes de estilo a serem aplicadas � coluna que apresentar�
	 * o atributo.
	 *
	 * @return Classes de estilo do atributo
	 */
	public String getStyleClass() {
		StringBuilder styleClass = new StringBuilder();

		if (getClassesCss() != null && getClassesCss().length() > 0) {
			styleClass.append(getClassesCss()).append(STR_SPACE);
		}

		if (getAlinhamento() != null) {
			styleClass.append(getAlinhamento().getClasseCss());
		}

		return styleClass.toString();
	}

	@Override
	public String toString() {
		return atributoEntidade.getNomePropriedade();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11).appendSuper(super.hashCode())
				.append(classesCss).append(alinhamento).append(ordenacao)
				.append(apresentar).append(apresentarPadrao).append(filtragem)
				.append(link).append(labelLink).append(nomePropriedadeLink)
				.append(acaoLink).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof AtributoConsulta) {
			AtributoConsulta ac = (AtributoConsulta) obj;

			return ac.canEqual(this)
					&& new EqualsBuilder()
							.appendSuper(super.equals(obj))
							.append(classesCss, ac.getClassesCss())
							.append(alinhamento, ac.getAlinhamento())
							.append(ordenacao, ac.getOrdenacao())
							.append(apresentar, ac.getApresentar())
							.append(apresentarPadrao, ac.getApresentarPadrao())
							.append(filtragem, ac.getFiltragem())
							.append(nomePropriedadeLink,
									ac.getNomePropriedadeLink())
							.append(link, ac.getLink())
							.append(labelLink, ac.labelLink)
							.append(acaoLink, ac.getAcaoLink()).isEquals();
		}

		return result;
	}

	@Override
	public boolean canEqual(Object obj) {
		return obj instanceof AtributoConsulta;
	}
}
