package br.com.anbima.commons.domain.entity.meta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.domain.entity.util.EntityUtil;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;
import br.com.anbima.commons.domain.valueobject.meta.ConjuntoTipoDado;
import br.com.anbima.commons.exception.FatalMetadadosException;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Metadados de uma consulta que pode gerar relatórios no sistema.
 */
@Audited
public class Consulta extends AbstractEntity {
	private static final long serialVersionUID = -3203223287522217647L;

	private static final String ERRO_ENTIDADE_SEM_ATRIBUTO_IDENTIFICADOR = "A entidade %s não possui um atributo identificador cadastrado.";

	private static final String ERRO_ENTIDADE_SEM_ATRIBUTO_DESCRITIVO = "A entidade %s não possui nenhum atributo descritivo cadastrado.";

	private static final String ERRO_ATRIBUTO_CONSULTA_NAO_PERTENCENTE = "O atributo informado não pertence à consulta";

	private static final String ERRO_ATRIBUTO_NAO_REPRESENTA_ENTIDADE = "O atributo informado não representa uma entidade";

	private static final String STR_SPACE = " ";
	private static final String STR_COMMA = ", ";
	private static final String STR_DOT = ".";
	private static final String STR_UNDERLINE = "_";

	private static final String SQL_SELECT = "select ";
	private static final String SQL_SELECT_DISTINCT = "select distinct ";
	private static final String SQL_SELECT_COUNT = "select count(*) ";

	private static final String SQL_FROM = "from ";
	private static final String SQL_JOIN = "join ";
	private static final String SQL_WHERE = "where ";

	private static final String SQL_ORDER_BY = "order by ";
	private static final String SQL_ORDER_BY_ASC = " asc";

	private static final String SQL_RESTRICAO_FILTRO = " and %s.%s %s :%s";
	private static final String SQL_RESTRICAO_FILTRO_TEXTO = " and upper(%s.%s) %s upper('%%' || :%s || '%%')";
	private static final String SQL_RESTRICAO_OID = "%s.oid = :oid";

	public static final String SQL_PARAM_OID = "oid";

	private static final String REGEX_DOT = "\\.";

	private static final Pattern ORDER_BY_PATTERN = Pattern.compile("\\s(order)(\\s)+by\\s", Pattern.CASE_INSENSITIVE);

	@NotNull(message = "Informe o nome")
	@Size(min = 1, max = 100, message = "Informe o nome com 1 a 100 caracteres")
	private String nome;

	@NotNull(message = "Informe a base da consulta")
	@Size(min = 1, max = 1000, message = "Informe a base da consulta com 1 a 1000 caracteres")
	private String queryBase;

	@NotNull(message = "Informe o alias da entidade utilizado na base da consulta")
	@Size(min = 1, max = 20, message = "Informe o alias com 1 a 20 caracteres")
	private String aliasEntidade;

	@NotNull(message = "Informe se a consulta deve retornar somente resultados distintos")
	private IndicadorSimNao distinct;

	@NotNull(message = "Informe a entidade de origem da consulta")
	private Entidade entidade;

	private Set<AtributoConsulta> atributos;

	private List<AtributoConsulta> atributosProjetados;

	private Map<String, Object> valoresFiltragem;

	public Consulta() {
		super();
		atributos = new HashSet<AtributoConsulta>();

		// Transientes
		atributosProjetados = null;
		valoresFiltragem = new HashMap<String, Object>();

		limparValoresFiltragem();
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

	public IndicadorSimNao getDistinct() {
		return distinct;
	}

	public void setDistinct(IndicadorSimNao distinct) {
		this.distinct = distinct;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public Set<AtributoConsulta> getAtributos() {
		return atributos;
	}

	public void setAtributos(Set<AtributoConsulta> atributos) {
		this.atributos = atributos;
	}

	public AtributoConsulta addAtributoConsulta(AtributoConsulta atributo) {
		atributo.setConsulta(this);
		getAtributos().add(atributo);

		return atributo;
	}

	public AtributoConsulta removeAtributoConsulta(AtributoConsulta atributo) {
		getAtributos().remove(atributo);
		atributo.setConsulta(null);

		return atributo;
	}

	public Map<String, Object> getValoresFiltragem() {
		return valoresFiltragem;
	}

	public void setValoresFiltragem(Map<String, Object> valoresFiltragem) {
		this.valoresFiltragem = valoresFiltragem;
	}

	public Object putValorFiltragem(String key, Object value) {
		return valoresFiltragem.put(key, value);
	}

	public Object removeValorFiltragem(String key) {
		return valoresFiltragem.remove(key);
	}

	// Métodos de negócio

	/**
	 * Verifica se a consulta é distinta.
	 *
	 * @return True se a consulta é distinta, false caso contrário.
	 */
	public boolean ehDistinct() {
		return getDistinct().equals(IndicadorSimNao.S);
	}

	/**
	 * Define a consulta como distinta.
	 */
	public void definirComoDistinct() {
		setDistinct(IndicadorSimNao.S);
	}

	/**
	 * Define a consulta como não distinta.
	 */
	public void definirComoNaoDistinct() {
		setDistinct(IndicadorSimNao.N);
	}

	/**
	 * Remove todos os valores de filtragem aplicados à consulta.
	 */
	public void limparValoresFiltragem() {
		valoresFiltragem.clear();

		for (AtributoConsulta ac : atributos) {
			if (ac.ehFiltragem()) {
				valoresFiltragem.put(ac.getOid(), null);
			}
		}
	}

	/**
	 * Retorna a lista ordenada de atributos da consulta.
	 *
	 * @return Lista ordenada de atributos da consulta.
	 */
	public List<AtributoConsulta> getAtributosOrdenados() {
		List<AtributoConsulta> atributosOrdenados = Lists.newArrayList(getAtributos());
		Collections.sort(atributosOrdenados, getComparatorOrdem());

		return Collections.unmodifiableList(atributosOrdenados);
	}

	/**
	 * Gera um comparator de atributos consulta baseado na ordem dos atributos.
	 *
	 * @return Comparator gerado
	 */
	private Comparator<AtributoConsulta> getComparatorOrdem() {
		return new Comparator<AtributoConsulta>() {
			@Override
			public int compare(AtributoConsulta a1, AtributoConsulta a2) {
				return a1.getOrdenacao().compareTo(a2.getOrdenacao());
			}
		};
	}

	/**
	 * Recupera os atributos atualmente projetados pela consulta.
	 *
	 * @return Atributos atualmente projetados pela consulta
	 */
	public List<AtributoConsulta> getAtributosProjetados() {
		if (atributosProjetados == null) {
			atributosProjetados = getAtributosProjetadosPadrao();
		}

		return atributosProjetados;
	}

	/**
	 * Atualiza os atributos projetados pela consulta. Os atributos atualmente
	 * projetados serão totalmente substituídos pelos novos atributos. Somente
	 * os atributos associados à consulta serão considerados.
	 *
	 * @param novosAtributosProjetados
	 *            Novos atributos a serem projetados pela consulta
	 */
	public void atualizarAtributosProjetados(List<AtributoConsulta> novosAtributosProjetados) {
		List<AtributoConsulta> novosAtributosValidos = new ArrayList<AtributoConsulta>();

		for (AtributoConsulta ac : novosAtributosProjetados) {
			if (ac.possuiApresentacaoAtiva() && EntityUtil.recuperarEntity(atributos, ac.getOid()) != null) {
				novosAtributosValidos.add(ac);
			}
		}

		atributosProjetados = Collections.unmodifiableList(novosAtributosValidos);
	}

	/**
	 * Get Atributo Identificador da Consulta.
	 *
	 * @return atributoConsulta
	 */
	public AtributoConsulta getAtributoIdentificador() {
		AtributoConsulta atributo = null;

		for (AtributoConsulta atributoConsulta : atributos) {
			if (atributoConsulta.getAtributoEntidade().ehIdentificador()) {
				atributo = atributoConsulta;
			}
		}
		return atributo;
	}

	/**
	 * Recupera os atributos projetados por padrão pela consulta.
	 *
	 * @return Atributos projetados por padrão pela consulta
	 */
	public List<AtributoConsulta> getAtributosProjetadosPadrao() {
		List<AtributoConsulta> atributosProjetadosPadrao = new ArrayList<AtributoConsulta>();

		for (AtributoConsulta ac : getAtributosOrdenados()) {
			if (ac.possuiApresentacaoAtiva() && ac.possuiApresentacaoPadrao()) {
				atributosProjetadosPadrao.add(ac);
			}
		}

		return Collections.unmodifiableList(atributosProjetadosPadrao);
	}

	/**
	 * Recupera os atributos projetáveis pela consulta.
	 *
	 * @return Atributos projetáveis pela consulta
	 */
	public List<AtributoConsulta> getAtributosProjetaveis() {
		List<AtributoConsulta> atributosProjetaveis = new ArrayList<AtributoConsulta>();

		for (AtributoConsulta ac : getAtributosOrdenados()) {
			if (ac.possuiApresentacaoAtiva()) {
				atributosProjetaveis.add(ac);
			}
		}

		return Collections.unmodifiableList(atributosProjetaveis);
	}

	/**
	 * Recupera todos os atributos que podem filtrar a consulta
	 *
	 * @return Todos os atributos que podem filtrar a consulta
	 */
	public List<AtributoConsulta> getAtributosFiltragem() {
		List<AtributoConsulta> atributosFiltragem = new ArrayList<AtributoConsulta>();

		for (AtributoConsulta atributo : getAtributosOrdenados()) {
			if (atributo.ehFiltragem()) {
				atributosFiltragem.add(atributo);
			}
		}

		return atributosFiltragem;
	}

	/**
	 * Recupera a Query que representa a consulta atual.
	 *
	 * @return Query que representa a consulta atual
	 */
	public String getQuery() {
		StringBuilder query = new StringBuilder();

		if (ehDistinct()) {
			query.append(SQL_SELECT_DISTINCT);
		} else {
			query.append(SQL_SELECT);
		}

		query.append(criarProjecoesQuery(Boolean.TRUE)).append(STR_SPACE).append(queryBase);

		if (possuiOrdenacaoPropria()) {
			String[] splitOrder = query.toString().split(SQL_ORDER_BY);

			query.replace(0, query.length(), splitOrder[0]);
			query.append(criarRestricoesQuery());
			query.append(SQL_ORDER_BY).append(splitOrder[1]);

		} else {
			query.append(criarRestricoesQuery());
			query.append(STR_SPACE).append(criarOrdenacaoQuery());
		}

		return query.toString();
	}

	/**
	 * Recupera a Query que faz um Count(*) da consulta atual.
	 *
	 * @return Count Query da consulta atual
	 */
	public String getCountQuery() {
		StringBuilder query = new StringBuilder(SQL_SELECT_COUNT);
		query.append(queryBase);

		if (possuiOrdenacaoPropria()) {
			String string = query.toString();
			query.replace(0, query.length(), string.substring(0, string.lastIndexOf(SQL_ORDER_BY)));
		}

		query.append(criarRestricoesQuery());

		return query.toString();
	}

	/**
	 * Cria as projeções da consulta.
	 *
	 * @param addId Flag que indica se o identificador da entidade deve ser
	 * adicionado ao fim da lista de projeções
	 * @return Projeções da consulta
	 */
	private String criarProjecoesQuery(Boolean addId) {
		String alias = aliasEntidade.concat(STR_DOT);
		StringBuilder projecoes = new StringBuilder();

		for (AtributoConsulta ac : getAtributosProjetados()) {
			if (ConjuntoTipoDado.ehColecao(ac.getTipoDado())
					|| (ac.ehLink() && StringUtils.isBlank(ac.getNomePropriedadeLink()))) {
				continue;
			}

			if (projecoes.length() > 0) {
				projecoes.append(STR_COMMA);
			}

			if (ac.ehLink()) {
				projecoes.append(alias).append(ac.getNomePropriedadeLink());
			} else {
				projecoes.append(alias).append(ac.getNomePropriedade());
			}
		}

		if (addId) {
			AtributoEntidade aeId = getEntidade().getAtributoIdentificador();

			if (aeId == null) {
				throw new FatalMetadadosException(String.format(ERRO_ENTIDADE_SEM_ATRIBUTO_IDENTIFICADOR,
						entidade.getNomeQualificadoClasse()));
			}

			if (projecoes.length() > 0) {
				projecoes.append(STR_COMMA);
			}

			projecoes.append(alias).append(aeId.getNomePropriedade());
		}

		return projecoes.toString();
	}

	/**
	 * Cria as restrições da consulta.
	 *
	 * @return Restrições da consulta
	 */
	private String criarRestricoesQuery() {
		StringBuilder restricoes = new StringBuilder();

		for (Entry<String, Object> valorFiltragem : valoresFiltragem.entrySet()) {
			Object value = valorFiltragem.getValue();

			if (value == null || value instanceof String && Strings.isNullOrEmpty((String) value)) {
				continue;
			}

			AtributoConsulta atributo = EntityUtil.recuperarEntity(getAtributos(), valorFiltragem.getKey());

			restricoes.append(String.format(getFormatoRestricaoAtributo(atributo), aliasEntidade,
					atributo.getNomePropriedade(), atributo.getOperadorFiltragem(), atributo.getNomePropriedade()));
		}

		return restricoes.toString();
	}

	/**
	 * Retornar o formato de restrição do atributo dado o seu tipo.
	 *
	 * @param atributo
	 *            Atributo da restrição
	 * @return Formato de restrição do atributo
	 */
	private String getFormatoRestricaoAtributo(AtributoConsulta atributo) {
		return ConjuntoTipoDado.ehTextual(atributo.getTipoDado()) ? SQL_RESTRICAO_FILTRO_TEXTO : SQL_RESTRICAO_FILTRO;
	}

	/**
	 * Verifica se a Query Base da consulta possui cláusula Order by.
	 *
	 * @return True se possui cláusula Order by, false caso contrário.
	 */
	private boolean possuiOrdenacaoPropria() {
		return ORDER_BY_PATTERN.matcher(queryBase).find();
	}

	/**
	 * Cria a ordenação da consulta, incluíndo todas as projeções.
	 *
	 * @return Ordenação da consulta
	 */
	private String criarOrdenacaoQuery() {
		StringBuilder ordenacao = new StringBuilder(SQL_ORDER_BY);
		ordenacao.append(criarProjecoesQuery(Boolean.FALSE)).append(SQL_ORDER_BY_ASC);
		
		return ordenacao.toString().replace(STR_COMMA, SQL_ORDER_BY_ASC + STR_COMMA);
	}

	/**
	 * Recupera a consulta de descritivos de um atributo da consulta que
	 * representa outra entidade.
	 * 
	 * @param ac Atributo da consulta
	 * @return Consulta de descritivos do atributo
	 */
	public String getQueryDescritivoAtributo(AtributoConsulta ac) {
		if (!getAtributos().contains(ac)) {
			throw new FatalMetadadosException(ERRO_ATRIBUTO_CONSULTA_NAO_PERTENCENTE);
		} else if (ac.getEntidadeRepresentada() == null) {
			throw new FatalMetadadosException(ERRO_ATRIBUTO_NAO_REPRESENTA_ENTIDADE);
		}

		StringBuilder query = new StringBuilder();

		query.append(SQL_FROM).append(getEntidade().getNomeClasse()).append(STR_SPACE).append(getAliasEntidade());

		String alias = getAliasEntidade();
		String baseAlias = getAliasEntidade().concat(STR_UNDERLINE);
		Integer aliasCount = 0;

		String[] joins = ac.getNomePropriedade().split(REGEX_DOT);

		for (String join : joins) {
			query.append(STR_SPACE).append(SQL_JOIN).append(alias).append(STR_DOT).append(join);

			alias = baseAlias.concat(aliasCount.toString());
			aliasCount++;

			query.append(STR_SPACE).append(alias).append(STR_SPACE);
		}

		query.append(SQL_WHERE).append(String.format(SQL_RESTRICAO_OID, getAliasEntidade()));

		StringBuilder projecao = new StringBuilder(SQL_SELECT);

		Iterator<String> itProjecoes = ac.getEntidadeRepresentada().getNomesPropriedadesAtributosDescritivos()
				.iterator();

		if (!itProjecoes.hasNext()) {
			throw new FatalMetadadosException(String.format(ERRO_ENTIDADE_SEM_ATRIBUTO_DESCRITIVO, ac
					.getEntidadeRepresentada().getNomeQualificadoClasse()));
		}

		while (itProjecoes.hasNext()) {
			projecao.append(alias).append(STR_DOT).append(itProjecoes.next());

			if (itProjecoes.hasNext()) {
				projecao.append(STR_COMMA);
			}
		}

		return projecao.append(STR_SPACE).append(query).toString();
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11).appendSuper(super.hashCode()).append(nome).append(queryBase)
				.append(aliasEntidade).append(distinct).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof Consulta) {
			Consulta c = (Consulta) obj;

			return c.canEqual(this)
					&& new EqualsBuilder().appendSuper(super.equals(obj)).append(nome, c.getNome())
							.append(queryBase, c.getQueryBase()).append(aliasEntidade, c.getAliasEntidade())
							.append(distinct, c.getDistinct()).isEquals();
		}

		return result;
	}

	@Override
	public boolean canEqual(Object obj) {
		return obj instanceof Consulta;
	}
}
