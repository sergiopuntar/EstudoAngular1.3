package br.com.anbima.angularjspoc.rest.valueobject.domain.meta;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Value object de uma entidade do sistema.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EntidadeVo implements Serializable {
	private static final long serialVersionUID = 1614491629698756227L;
	
	private String oid;
	private String titulo;
	private String nomeClasse;
	private String nomeQualificadoClasse;
	private Boolean visivelCadastro;
	
	public EntidadeVo() {
		super();
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
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

	public Boolean getVisivelCadastro() {
		return visivelCadastro;
	}

	public void setVisivelCadastro(Boolean visivelCadastro) {
		this.visivelCadastro = visivelCadastro;
	}
}
