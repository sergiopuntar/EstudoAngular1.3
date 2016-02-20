package br.com.anbima.commons.domain.valueobject.meta;

/**
 * Tipos de permiss�o de um atributo.
 */
public enum TipoPermissaoAtributo {

	/**
	 * Permiss�o de leitura e escrita no atributo.
	 */
	LE ("Leitura e escrita"),
	
	/**
	 * Permiss�o somente de leitura no atributo.
	 */
	SL ("Somente leitura"),
	
	/**
	 * Nenhuma permiss�o no atributo.
	 */
	NE ("Nenhuma");
	
	private String nome;

	private TipoPermissaoAtributo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
