package br.com.anbima.commons.domain.valueobject.meta;

import javax.persistence.TemporalType;

/**
 * Tipos temporais de um atributo.
 */
public enum TipoTemporal {
	
	/**
	 * Tipo temporal de data.
	 */
	DATE ("Date", TemporalType.DATE),
	
	/**
	 * Tipo temporal de tempo.
	 */
	TIME ("Time", TemporalType.TIME),
	
	/**
	 * Tipo temporal de timestamp.
	 */
	TIMESTAMP ("Timestamp", TemporalType.TIMESTAMP);
	
	private String nome;
	
	private TemporalType temporalType;

	private TipoTemporal(String nome, TemporalType temporalType) {
		this.nome = nome;
		this.temporalType = temporalType;
	}

	public String getNome() {
		return nome;
	}

	public TemporalType getTemporalType() {
		return temporalType;
	}
}
