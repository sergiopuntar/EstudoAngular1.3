package br.com.anbima.commons.domain.valueobject;

/**
 * Indicador para status booleanos de Sim e Não.
 */
public enum IndicadorSimNao {

	/**
	 * Representa um status boolean valido - Sim
	 */
	S ("Sim"),
	
	/**
	 * Representa um status boolean invalido - Não
	 */
	N ("Não");
	
	private String nome;
	
	private IndicadorSimNao(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * Recupera o idenficador a partir e um valor booleano. O indicador S
	 * representa true e o indicador N representa false ou null.
	 * 
	 * @param bool Valor booleano
	 * @return Indicador equivalente
	 */
	public static IndicadorSimNao fromBoolean(Boolean bool) {
		return bool != null ? bool ? S : N : N;
	}

	/**
	 * Recupera o valor booleano do identificador. O indicador S representa
	 * true e o indicador N representa false.
	 * 
	 * @return Valor booleano do identificador
	 */
	public Boolean booleanValue() {
		return this.equals(S);
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
