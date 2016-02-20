package br.com.anbima.commons.domain.valueobject.meta;

/**
 * Componentes de um atributo.
 */
public enum Componente {

	LABEL ("CmpLabel", "Label", false),
	
	TEXT ("CmpText", "Texto", false),
	
	TEXTAREA ("CmpTextArea", "Área de texto", false),
	
	CHECK ("CmpCheck", "Checkbox", false),
	
	SELECT ("CmpSelect", "Seleção simples", true),
	
	SELECT_ONE_LIST ("CmpSelectOneList", "Seleção em lista", true),
	
	AUTOCOMPLETE ("CmpAutocomplete", "Autocomplete", false),
	
	CALENDAR ("CmpCalendar", "Calendário", false),
	
	DATA_POR_EXTENSO ("CmpDataPorExtenso", "Data por extenso", false);
	
	private String nomeComponente;
	
	private String nomeApresentacao;

	private Boolean hasSelectItens;
	
	private Componente(String nomeComponente, String nomeApresentacao, Boolean hasSelectItens) {
		this.nomeComponente = nomeComponente;
		this.nomeApresentacao = nomeApresentacao;
		this.hasSelectItens = hasSelectItens;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public String getNomeApresentacao() {
		return nomeApresentacao;
	}
	
	public Boolean getHasSelectItens() {
		return hasSelectItens;
	}

	/**
	 * Recupera o componente a partir do seu nome.
	 * 
	 * @param nomeComponente Nome do componente
	 * @return Componente recuperado
	 */
	public static Componente getTipoComponentePorNome(String nomeComponente) {
		for (Componente tipoComponente : values()) {
			if (tipoComponente.getNomeComponente().equals(nomeComponente)) {
				return tipoComponente;
			}
		}
		
		return null;
	}
}
