package br.com.anbima.commons.domain.entity.auditoria;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;

/**
 * Entidade Revision.
 * Auditoria das Transacoes realizadas no sistema.
 */
@Entity
@Table(name = "REVISION", schema = "AUD")
@org.hibernate.envers.RevisionEntity(RevisionEntityListener.class)
public class RevisionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@RevisionNumber
	@Column(name = "REV_ID_REVISION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVISION")
	@SequenceGenerator(name = "SEQ_REVISION", sequenceName = "SEQ_REVISION", schema = "AUD", allocationSize = 1)
	private Long id;

	@RevisionTimestamp
	@Column(name = "REV_DT_REVISION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@OneToMany(mappedBy = "revision", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private Set<RevisionChangesEntity> revisionChanges = Sets.newHashSet();

	@Column(name = "REV_OID_USUARIO")
	private String oidUsuario;

	@Column(name = "REV_NOME_USUARIO")
	private String nomeUsuario;

	@Column(name = "REV_IP_USUARIO")
	private String ip;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data == null ? null : new Date(data.getTime());
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data == null ? null : new Date(data.getTime());
	}

	/**
	 * @return the revisionChanges
	 */
	public Set<RevisionChangesEntity> getRevisionChanges() {
		return revisionChanges;
	}

	/**
	 * @param revisionChanges the revisionChanges to set
	 */
	public void setRevisionChanges(Set<RevisionChangesEntity> revisionChanges) {
		this.revisionChanges = revisionChanges;
	}

	/**
	 * @return the oidUsuario
	 */
	public String getOidUsuario() {
		return oidUsuario;
	}

	/**
	 * @param oidUsuario the oidUsuario to set
	 */
	public void setOidUsuario(String oidUsuario) {
		this.oidUsuario = oidUsuario;
	}

	/**
	 * @return the nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario the nomeUsuario to set
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @param revisionChangesEntity
	 */
	public void addRevisionChanges(RevisionChangesEntity revisionChangesEntity) {
		revisionChangesEntity.setRevision(this);
		revisionChanges.add(revisionChangesEntity);
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RevisionEntity)) {
			return false;
		}

		RevisionEntity that = (RevisionEntity) o;

		return Objects.equal(id, that.id) && Objects.equal(data, that.data);
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(id, data);
	}

	@Override
	public String toString() {
		return "DefaultRevisionEntity(id = " + id + ", revisionDate = "
				+ DateFormat.getDateTimeInstance().format(getData()) + ")";
	}
}
