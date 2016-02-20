package br.com.anbima.commons.domain.entity.meta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.domain.entity.EntityInterface;
import br.com.anbima.commons.domain.entity.ca.PerfilAcesso;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;
import br.com.anbima.commons.exception.MetadadosException;
import br.com.anbima.commons.util.ReflectionUtil;

import com.google.common.collect.Lists;

/**
 * Metadados de uma entidade do sistema.
 */
@Audited
public class Entidade extends AbstractEntity {
	private static final long serialVersionUID = 5227518142037699183L;

	/**
	 * Mensagem de erro de tipo de objeto da entidade.
	 */
	private static final String ERRO_TIPO_OBJETO_ENTIDADE = "O objeto %s não é o tipo da entidade %s";

	/**
	 * Mensagem de erro de classe da entidade inexistente
	 */
	private static final String ERRO_CLASSE_INEXISTENTE = "A classe %s da entidade %s não foi encontrada.";

	/**
	 * Mensagem de erro de recuperação de classe da entidade
	 */
	private static final String ERRO_RECUPERACAO_CLASSE = "Não foi possível recuperar a classe %s da entidade %s.";
	
	private static final String STR_DOT = ".";
	private static final String STR_COMMA = ", ";
	private static final String STR_SEPARADOR_DESCRITIVO = " - ";

	private static final String SQL_SELECT_ENTIDADE = "select e from %s e ";
	private static final String SQL_ENTIDADE_ALIAS = "e";
	private static final String SQL_ENTIDADE_OID = "e.oid";
	private static final String SQL_WHERE = "where ";
	private static final String SQL_ORDER_BY = "order by ";
	private static final String SQL_ORDER_BY_ASC = " asc";
	private static final String SQL_CONCAT = " || ";
	private static final String SQL_SEPARADOR_DESCRITIVO = "' - '";
	private static final String SQL_RESTRICAO_DESCRITIVO = "upper(%s) like upper('%%' || :descritivo || '%%') ";

	private static final String REGEX_PONTO = "\\.";

	/**
	 * Nome do parâmetro do descritivo na consulta de busca de entidades por
	 * descritivo.
	 */
	public static final String SQL_PARAM_DESCRITIVO = "descritivo";

	@NotNull(message = "Informe o título")
	@Size(min = 1, max = 100, message = "Informe o título com 1 a 100 caracteres")
	private String titulo;

	@NotNull(message = "Informe o nome de classe")
	@Size(min = 1, max = 100, message = "Informe o nome de classe com 1 a 100 caracteres")
	private String nomeClasse;

	@NotNull(message = "Informe o nome qualificado de classe")
	@Size(min = 1, max = 500, message = "Informe o nome qualificado de classe com 1 a 500 caracteres")
	private String nomeQualificadoClasse;

	@NotNull(message = "Informe se a entidade deve ser visível no cadastro genérico")
	private IndicadorSimNao visivelCadastro;

	private Consulta consulta;

	private Set<AtributoEntidade> atributos;

	private Set<Consulta> consultas;
	
	private Set<PermissaoEntidade> permissoes;

	public Entidade() {
		super();
		this.atributos = new HashSet<AtributoEntidade>();
		this.consultas = new HashSet<Consulta>();
		this.permissoes = new HashSet<PermissaoEntidade>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}

	public String getNomeQualificadoClasse() {
		return nomeQualificadoClasse;
	}

	public void setNomeQualificadoClasse(String nomeQualificadoClasse) {
		this.nomeQualificadoClasse = nomeQualificadoClasse;
	}

	public IndicadorSimNao getVisivelCadastro() {
		return visivelCadastro;
	}

	public void setVisivelCadastro(IndicadorSimNao visivelCadastro) {
		this.visivelCadastro = visivelCadastro;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Set<AtributoEntidade> getAtributos() {
		return atributos;
	}

	public void setAtributos(Set<AtributoEntidade> atributos) {
		this.atributos = atributos;
	}

	public AtributoEntidade addAtributo(AtributoEntidade atributo) {
		atributo.setEntidadeOrigem(this);
		getAtributos().add(atributo);

		return atributo;
	}

	public AtributoEntidade removeAtributo(AtributoEntidade atributo) {
		getAtributos().remove(atributo);
		atributo.setEntidadeOrigem(null);

		return atributo;
	}

	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		consulta.setEntidade(this);
		getConsultas().add(consulta);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setEntidade(null);

		return consulta;
	}

	public Set<PermissaoEntidade> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<PermissaoEntidade> permissoes) {
		this.permissoes = permissoes;
	}
	
	public PermissaoEntidade addPermissao(PermissaoEntidade permissao) {
		permissao.setEntidade(this);
		getPermissoes().add(permissao);
		
		return permissao;
	}
	
	public PermissaoEntidade removePermissao(PermissaoEntidade permissao) {
		getPermissoes().remove(permissao);
		permissao.setEntidade(null);
		
		return permissao;
	}

	// Métodos de negócio

	/**
	 * Verifica se a entidade é visível para o cadastro genérico.
	 * 
	 * @return True se a entidade é visível, False caso contrário.
	 */
	public boolean ehVisivelCadastro() {
		return getVisivelCadastro() != null && getVisivelCadastro().booleanValue();
	}

	/**
	 * Ativa a visibilidade da entidade no cadastro genérico.
	 */
	public void ativarVisibilidadeCadastro() {
		setVisivelCadastro(IndicadorSimNao.S);
	}

	/**
	 * Desativa a visibilidade da entidade no cadastro genérico.
	 */
	public void desativarVisibilidadeCadastro() {
		setVisivelCadastro(IndicadorSimNao.N);
	}

	/**
	 * Recupera a permissão que a entidade possui para um determinado perfil.
	 * 
	 * @param perfil Perfil de acesso
	 * @return Permissão da entidade para o perfil, null se não possui
	 * permissão para o perfil
	 */
	public PermissaoEntidade getPemissaoPerfil(PerfilAcesso perfil) {
		if (perfil == null) {
			return null;
		}
		
		for (PermissaoEntidade permissao : getPermissoes()) {
			if (permissao.getPerfil().getOid().equals(perfil.getOid())) {
				return permissao;
			}
		}
		
		return null;
	}

	/**
	 * Retorna a lista ordenada de atributos da entidade.
	 * 
	 * @return Lista ordenada de atributos da entidade.
	 */
	public List<AtributoEntidade> getAtributosOrdenados() {
		List<AtributoEntidade> atributosOrdenados = Lists.newArrayList(getAtributos());
		Collections.sort(atributosOrdenados, getComparatorOrdem());

		return Collections.unmodifiableList(atributosOrdenados);
	}

	/**
	 * Gera um comparator de atributos baseado na ordem dos atributos.
	 * 
	 * @return Comparator gerado
	 */
	private Comparator<AtributoEntidade> getComparatorOrdem() {
		return new Comparator<AtributoEntidade>() {
			public int compare(AtributoEntidade a1, AtributoEntidade a2) {
				return a1.getOrdenacao().compareTo(a2.getOrdenacao());
			}
		};
	}

	/**
	 * Recupera o atributo identificador da entidade.
	 * 
	 * @return Atributo identificador da entidade
	 */
	public AtributoEntidade getAtributoIdentificador() {
		for (AtributoEntidade atributo : getAtributosOrdenados()) {
			if (atributo.ehIdentificador()) {
				return atributo;
			}
		}
		
		return null;
	}

	/**
	 * Retorna a lista ordenada de atributos descritivos da entidade.
	 * 
	 * @return Lista ordenada de atributos descritivos da entidade.
	 */
	public List<AtributoEntidade> getAtributosDescritivos() {
		List<AtributoEntidade> atributosDescritivos = new ArrayList<AtributoEntidade>();

		for (AtributoEntidade atributo : getAtributosOrdenados()) {
			if (atributo.ehDescritivo()) {
				atributosDescritivos.add(atributo);
			}
		}

		return Collections.unmodifiableList(atributosDescritivos);
	}

	/**
	 * Retorna a lista ordenada de nomes de propriedades dos atributos
	 * descritivos da entidade.
	 * 
	 * @return Lista ordenada de nomes de propriedades dos atributos
	 *         descritivos da entidade.
	 */
	public List<String> getNomesPropriedadesAtributosDescritivos() {
		List<String> nomesAtributosDescritivos = new ArrayList<String>();

		for (AtributoEntidade atributo : getAtributosOrdenados()) {
			if (atributo.ehDescritivo()) {
				nomesAtributosDescritivos.add(atributo.getNomePropriedade());
			}
		}

		return Collections.unmodifiableList(nomesAtributosDescritivos);
	}

	/**
	 * Recupera a classe da entidade
	 * 
	 * @return Classe da entidade
	 * @throws MetadadosException Se a classe não for encontrada
	 */
	@SuppressWarnings("unchecked")
	public Class<? extends EntityInterface> getClasse() throws MetadadosException {
		try {
			return (Class<? extends EntityInterface>) Class.forName(getNomeQualificadoClasse());
		} catch (ClassNotFoundException e) {
			throw new MetadadosException(
					String.format(ERRO_CLASSE_INEXISTENTE, getNomeQualificadoClasse(), getTitulo()), e);
		} catch (Exception e) {
			throw new MetadadosException(
					String.format(ERRO_RECUPERACAO_CLASSE, getNomeQualificadoClasse(), getTitulo()), e);
		}
	}

	/**
	 * Cria o texto descritivo de um objeto que é uma instância desta entidade.
	 * 
	 * @param obj Objeto cujo descritivo deve ser criado
	 * @return Texto descritivo do objeto
	 * @throws MetadadosException Se o objeto não for do tipo da entidade
	 */
	public String criarDescritivo(EntityInterface obj) throws MetadadosException {
		if (!obj.getClass().getName().equals(nomeQualificadoClasse)) {
			throw new MetadadosException(
					String.format(ERRO_TIPO_OBJETO_ENTIDADE, obj.getClass(), nomeQualificadoClasse));
		}

		List<AtributoEntidade> atributosDescritivos = getAtributosDescritivos();
		StringBuilder descricao = new StringBuilder();

		for (int i = 0; i < atributosDescritivos.size(); i++) {
			AtributoEntidade atributo = atributosDescritivos.get(i);

			if (atributo.getEntidadeRepresentada() != null) {
				continue;
			}

			Object objAtual = obj;

			for (String propriedade : atributo.getNomePropriedade().split(REGEX_PONTO)) {
				objAtual = ReflectionUtil.getPropriedadeObjeto(propriedade, objAtual);
			}

			if (i != 0) {
				descricao.append(STR_SEPARADOR_DESCRITIVO);
			}

			if (objAtual != null) {
				descricao.append(objAtual.toString());
			}
		}

		return descricao.toString();
	}

	/**
	 * Recupera a Query de busca de todas as entidades.
	 * 
	 * @return Query de busca todas as entidades
	 */
	public String getQueryTodas() {
		StringBuilder query = new StringBuilder();
		query.append(String.format(SQL_SELECT_ENTIDADE, nomeClasse)).append(SQL_ORDER_BY).append(SQL_ENTIDADE_OID)
				.append(SQL_ORDER_BY_ASC);

		return query.toString();
	}

	/**
	 * Recupera a Query de busca da entidade por descritivo.
	 * 
	 * @return Query de busca da entidade por descritivo
	 */
	public String getQueryPorDescritivo() {
		StringBuilder descritivo = new StringBuilder();
		StringBuilder ordenacao = new StringBuilder(SQL_ORDER_BY);
		Iterator<AtributoEntidade> it = getAtributosDescritivos().iterator();

		while (it.hasNext()) {
			AtributoEntidade atributo = it.next();
			descritivo.append(SQL_ENTIDADE_ALIAS).append(STR_DOT).append(atributo.getNomePropriedade());

			ordenacao.append(SQL_ENTIDADE_ALIAS).append(STR_DOT).append(atributo.getNomePropriedade())
					.append(SQL_ORDER_BY_ASC);

			if (it.hasNext()) {
				descritivo.append(SQL_CONCAT).append(SQL_SEPARADOR_DESCRITIVO).append(SQL_CONCAT);
				ordenacao.append(STR_COMMA);
			}
		}

		StringBuilder query = new StringBuilder();
		query.append(String.format(SQL_SELECT_ENTIDADE, nomeClasse)).append(SQL_WHERE)
				.append(String.format(SQL_RESTRICAO_DESCRITIVO, descritivo.toString())).append(ordenacao.toString());

		return query.toString();
	}

	@Override
	public String toString() {
		return nomeQualificadoClasse;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11)
			.appendSuper(super.hashCode())
			.append(titulo)
			.append(nomeClasse)
			.append(nomeQualificadoClasse)
			.append(visivelCadastro)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof Entidade) {
			Entidade e = (Entidade) obj;

			return e.canEqual(this) && new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(titulo, e.getTitulo())
				.append(nomeClasse, e.getNomeClasse())
				.append(nomeQualificadoClasse, e.getNomeQualificadoClasse())
				.append(visivelCadastro, e.getVisivelCadastro())
				.isEquals();
		}
		
		return result;
	}
	
	@Override
	public boolean canEqual(Object obj) {
        return obj instanceof Entidade;
    }
}
