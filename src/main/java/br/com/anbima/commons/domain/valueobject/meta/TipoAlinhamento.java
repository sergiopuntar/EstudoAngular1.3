package br.com.anbima.commons.domain.valueobject.meta;

/**
 * Tipos de alinhamento de um atributo.
 */
public enum TipoAlinhamento {
	
	/**
	 * Alinhamento do texto à esqueda.
	 */
	LEFT ("Esquerda", "left"),
	
	/**
	 * Alinhamento do texto à direita.
	 */
	RIGHT ("Direita", "right"),
	
	/**
	 * Alinhamento do texto ao centro.
	 */
	CENTER ("Centro", "center");
	
	private String nome;
	
	private String classeCss;

	private TipoAlinhamento(String nome, String classeCss) {
		this.nome = nome;
		this.classeCss = classeCss;
	}

	public String getNome() {
		return nome;
	}

	public String getClasseCss() {
		return classeCss;
	}
}
