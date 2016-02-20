package br.com.anbima.commons.domain.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * Super classe abstrata para todas as entidades dos sistemas da Anbima.
 */
@MappedSuperclass
public abstract class AbstractEntity implements EntityInterface {
	private static final long serialVersionUID = -722405405688105038L;
	
	@Id
	@Size(min = 36, max = 36)
	@GenericGenerator(name = "anbima-uuid", strategy = "br.com.anbima.commons.domain.entity.util.AnbimaIdGenerator")
	@GeneratedValue(generator = "anbima-uuid")
	protected String oid;

	@Override
	public String getOid() {
		return oid;
	}

	@Override
	public void setOid(String oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return oid;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11).append(oid).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof AbstractEntity) {
			AbstractEntity ae = (AbstractEntity) obj;

			result = ae.canEqual(this) && new EqualsBuilder().append(oid, ae.getOid()).isEquals();
		}

		return result;
	}

	/**
	 * EqualsVerifier.
	 * 
	 * @param obj
	 * @return canEqual
	 */
	public boolean canEqual(Object obj) {
		return obj instanceof AbstractEntity;
	}
	
}
