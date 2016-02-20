package br.com.anbima.commons.domain.entity.auditoria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.RevisionType;

/**
 * Entidade RevisionChange.
 * Persiste as alterações realizadas em cada Revision.
 */
@Entity
@Table(name = "REVISION_CHANGES", schema = "AUD")
public class RevisionChangesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REV_ID_REVISION_CHANGE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVISION_CHANGE")
	@SequenceGenerator(name = "SEQ_REVISION_CHANGE", sequenceName = "SEQ_REVISION_CHANGE", schema = "AUD", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "REV_ID_REVISION")
	private RevisionEntity revision;

	@Enumerated(EnumType.STRING)
	@Column(name = "REV_REVISION_TYPE")
	private RevisionType revisionType;

	@Column(name = "REV_NOME_ENTIDADE")
	private String nomeEntidade;

	@Column(name = "REV_ID_ENTIDADE")
	private String oidEntidade;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the revision
	 */
	public RevisionEntity getRevision() {
		return revision;
	}

	/**
	 * @param revision the revision to set
	 */
	public void setRevision(RevisionEntity revision) {
		this.revision = revision;
	}

	/**
	 * @return the revisionType
	 */
	public RevisionType getRevisionType() {
		return revisionType;
	}

	/**
	 * @param revisionType the revisionType to set
	 */
	public void setRevisionType(RevisionType revisionType) {
		this.revisionType = revisionType;
	}

	/**
	 * @return the nomeEntidade
	 */
	public String getNomeEntidade() {
		return nomeEntidade;
	}

	/**
	 * @param nomeEntidade the nomeEntidade to set
	 */
	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}

	/**
	 * @return the oidEntidade
	 */
	public String getOidEntidade() {
		return oidEntidade;
	}

	/**
	 * @param oidEntidade the oidEntidade to set
	 */
	public void setOidEntidade(String oidEntidade) {
		this.oidEntidade = oidEntidade;
	}
}