package br.com.anbima.commons.domain.entity.meta;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.domain.entity.ca.PerfilAcesso;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;
import br.com.anbima.commons.domain.valueobject.meta.TipoTemporal;

/**
 * Metadados de um atributo de uma entidade do sistema.
 */
@Audited
public class AtributoEntidade extends AbstractEntity {
	private static final long serialVersionUID = -5129611254751476898L;

	@NotNull(message = "Informe o t�tulo")
	@Size(min = 1, max = 100, message = "Informe o t�tulo com 1 a 100 caracteres")
	private String titulo;
	
	@NotNull(message = "Informe o nome da propriedade")
	@Size(min = 1, max = 500, message = "Informe o nome da propriedade com 1 a 500 caracteres")
	private String nomePropriedade;
	
	@NotNull(message = "Informe o tipo de dado")
	@Size(min = 1, max = 500, message = "Informe o tipo de dado com 1 a 500 caracteres")
	private String tipoDado;
	
	private TipoTemporal tipoTemporal;
	
	@NotNull(message = "Informe a numera��o de ordena��o do atributo")
	@Digits(integer = 3, fraction = 0, message = "A numera��o de ordena��o deve ser um inteiro de at� 3 d�gitos")
	private Integer ordenacao;
	
	@Size(max = 100, message = "O nome do componente deve possuir no m�ximo 100 caracteres")
	private String nomeComponente;
	
	@Size(max = 200, message = "A dica de preenchimento deve possuir no m�ximo 200 caracteres")
	private String dicaPreenchimento;
	
	@Size(max = 50, message = "A m�scara de formata��o deve possuir no m�ximo 50 caracteres")
	private String mascaraFormatacao;
	
	@Size(max = 200, message = "A mensagem de erro de convers�o deve possuir no m�ximo 200 caracteres")
	private String mensagemErroConversao;
	
	@Digits(integer = 10, fraction = 0, message = "O tamanho do atributo deve ser um inteiro de at� 10 d�gitos")
	private Integer tamanho;
	
	@NotNull(message = "Informe se o atributo � identificador da entidade")
	private IndicadorSimNao identificador;
	
	@NotNull(message = "Informe se o atributo � descritivo da entidade")
	private IndicadorSimNao descritivo;
	
	@NotNull(message = "Informe se o atributo deve ser vis�vel no cadastro gen�rico da entidade")
	private IndicadorSimNao visivelCadastro;

	@NotNull(message = "Informe a entidade de origem do atributo")
	private Entidade entidadeOrigem;
	
	private Entidade entidadeRepresentada;
	
	private Set<PermissaoAtributoEntidade> permissoes;

	public AtributoEntidade() {
		super();
		this.permissoes = new HashSet<PermissaoAtributoEntidade>();
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

	public IndicadorSimNao getIdentificador() {
		return identificador;
	}

	public void setIdentificador(IndicadorSimNao identificador) {
		this.identificador = identificador;
	}

	public IndicadorSimNao getDescritivo() {
		return descritivo;
	}

	public void setDescritivo(IndicadorSimNao descritivo) {
		this.descritivo = descritivo;
	}

	public IndicadorSimNao getVisivelCadastro() {
		return visivelCadastro;
	}

	public void setVisivelCadastro(IndicadorSimNao visivelCadastro) {
		this.visivelCadastro = visivelCadastro;
	}

	public Entidade getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(Entidade entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}

	public Entidade getEntidadeRepresentada() {
		return entidadeRepresentada;
	}

	public void setEntidadeRepresentada(Entidade entidadeRepresentada) {
		this.entidadeRepresentada = entidadeRepresentada;
	}

	public Set<PermissaoAtributoEntidade> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<PermissaoAtributoEntidade> permissoes) {
		this.permissoes = permissoes;
	}
	
	public PermissaoAtributoEntidade addPermissao(PermissaoAtributoEntidade permissao) {
		permissao.setAtributoEntidade(this);
		getPermissoes().add(permissao);
		
		return permissao;
	}
	
	public PermissaoAtributoEntidade removePermissao(PermissaoAtributoEntidade permissao) {
		getPermissoes().remove(permissao);
		permissao.setAtributoEntidade(null);
		
		return permissao;
	}
	
	// M�todos de neg�cio
	
	/**
	 * Verifica se o atributo � identificador da entidade.
	 * 
	 * @return True se o atributo � identificador, False caso contr�rio.
	 */
	public boolean ehIdentificador() {
		return getIdentificador() != null && getIdentificador().booleanValue();
	}
	
	/**
	 * Define o atributo como identificador da entidade.
	 */
	public void definirComoIdentificador() {
		setIdentificador(IndicadorSimNao.S);
	}
	
	/**
	 * Define o atributo como n�o identificador da entidade.
	 */
	public void definirComoNaoIdentificador() {
		setIdentificador(IndicadorSimNao.N);
	}
	
	/**
	 * Verifica se o atributo � descritivo da entidade.
	 * 
	 * @return True se o atributo � descritivo, False caso contr�rio.
	 */
	public boolean ehDescritivo() {
		return getDescritivo() != null && getDescritivo().booleanValue();
	}
	
	/**
	 * Define o atributo como descritivo da entidade.
	 */
	public void definirComoDescritivo() {
		setDescritivo(IndicadorSimNao.S);
	}
	
	/**
	 * Define o atributo como n�o descritivo da entidade.
	 */
	public void definirComoNaoDescritivo() {
		setDescritivo(IndicadorSimNao.N);
	}

	/**
	 * Verifica se o atributo � vis�vel para o cadastro gen�rico.
	 * 
	 * @return True se o atributo � vis�vel, False caso contr�rio.
	 */
	public boolean ehVisivelCadastro() {
		return getVisivelCadastro() != null && getVisivelCadastro().booleanValue();
	}
	
	/**
	 * Ativa a visibilidade do atributo no cadastro gen�rico.
	 */
	public void ativarVisibilidadeCadastro() {
		setVisivelCadastro(IndicadorSimNao.S);
	}
	
	/**
	 * Desativa a visibilidade do atributo no cadastro gen�rico.
	 */
	public void desativarVisibilidadeCadastro() {
		setVisivelCadastro(IndicadorSimNao.N);
	}
	
	/**
	 * Recupera a permiss�o que o atributo possui para um determinado perfil.
	 * 
	 * @param perfil Perfil de acesso
	 * @return Permiss�o do atributo para o perfil, null se n�o possui
	 * permiss�o para o perfil
	 */
	public PermissaoAtributoEntidade getPemissaoPerfil(PerfilAcesso perfil) {
		if (perfil == null) {
			return null;
		}
		
		for (PermissaoAtributoEntidade permissao : getPermissoes()) {
			if (permissao.getPerfil().getOid().equals(perfil.getOid())) {
				return permissao;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return entidadeOrigem + "." + nomePropriedade;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11)
			.appendSuper(super.hashCode())
			.append(titulo)
			.append(nomePropriedade)
			.append(tipoDado)
			.append(tipoTemporal)
			.append(ordenacao)
			.append(nomeComponente)
			.append(dicaPreenchimento)
			.append(mascaraFormatacao)
			.append(mensagemErroConversao)
			.append(tamanho)
			.append(identificador)
			.append(descritivo)
			.append(visivelCadastro)
			.append(entidadeOrigem)
			.append(entidadeRepresentada)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof AtributoEntidade) {
			AtributoEntidade a = (AtributoEntidade) obj;
			
			return a.canEqual(this) && new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(titulo, a.getTitulo())
				.append(nomePropriedade, a.getNomePropriedade())
				.append(tipoDado, a.getTipoDado())
				.append(tipoTemporal, a.getTipoTemporal())
				.append(ordenacao, a.getOrdenacao())
				.append(nomeComponente, a.getNomeComponente())
				.append(dicaPreenchimento, a.getDicaPreenchimento())
				.append(mascaraFormatacao, a.getMascaraFormatacao())
				.append(mensagemErroConversao, a.getMensagemErroConversao())
				.append(tamanho, a.getTamanho())
				.append(identificador, a.getIdentificador())
				.append(descritivo, a.getDescritivo())
				.append(visivelCadastro, a.getVisivelCadastro())
				.append(entidadeOrigem, a.getEntidadeOrigem())
				.append(entidadeRepresentada, a.getEntidadeRepresentada())
				.isEquals();
		}
		
		return result;
	}
	
	@Override
	public boolean canEqual(Object obj) {
        return obj instanceof AtributoEntidade;
    }
}
