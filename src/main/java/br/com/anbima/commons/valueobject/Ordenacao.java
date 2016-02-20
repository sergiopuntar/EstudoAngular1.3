package br.com.anbima.commons.valueobject;

import java.io.Serializable;

import org.hibernate.criterion.Order;

/**
 * Ordenação.
 *
 */
public class Ordenacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Ordem ordem;
	private String nomePropriedade;

	/**
	 * Construtor.
	 * 
	 * @param ordem
	 * @param nomePropriedade
	 */
	public Ordenacao(Ordem ordem, String nomePropriedade) {
		this.ordem = ordem;
		this.nomePropriedade = nomePropriedade;
	}

	/**
	 * @return the ordem
	 */
	public Ordem getOrdem() {
		return ordem;
	}

	/**
	 * @param ordem the ordem to set
	 */
	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	/**
	 * @return the nomePropriedade
	 */
	public String getNomePropriedade() {
		return nomePropriedade;
	}

	/**
	 * @param nomePropriedade the nomePropriedade to set
	 */
	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}

	/**
	 * Get Criteria Order.
	 * 
	 * @return Order.
	 */
	public Order getCriteriaOrder() {
		return Ordem.ASC.equals(ordem) ? Order.asc(nomePropriedade) : Order.desc(nomePropriedade);
	}

	/**
	 * Enum Ordem.
	 *
	 */
	public static enum Ordem {
		ASC("Ascendente"),
		DESC("Descendente");

		private String descricao;

		Ordem(String descricao) {
			this.descricao = descricao;
		}

		/**
		 * @return the descricao
		 */
		public String getDescricao() {
			return descricao;
		}
	}
}