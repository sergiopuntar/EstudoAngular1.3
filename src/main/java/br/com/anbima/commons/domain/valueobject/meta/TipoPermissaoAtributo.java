package br.com.anbima.commons.domain.valueobject.meta;

/**
 * Tipos de permissão de um atributo.
 */
public enum TipoPermissaoAtributo {

	/**
	 * Permissão de leitura e escrita no atributo.
	 */
	LE ("Leitura e escrita"),
	
	/**
	 * Permissão somente de leitura no atributo.
	 */
	SL ("Somente leitura"),
	
	/**
	 * Nenhuma permissão no atributo.
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
