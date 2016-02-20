package br.com.anbima.commons.domain.entity.ca;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;

/**
 * Perfis de acesso aos sistemas da ANBIMA que utilizam o controle de acesso
 * centralizado.
 */
@Audited
public class PerfilAcesso extends AbstractEntity {
	private static final long serialVersionUID = -8352646339703975580L;

	private String nome;

	private String descricao;

	private Sistema sistema;
	
	public PerfilAcesso() {
		super();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) { 
		this.sistema = sistema;
	}

	// Métodos de negócio
	
	@Override
	public String toString() {
		return oid + " - " + nome;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11)
			.appendSuper(super.hashCode())
			.append(nome)
			.append(descricao)
			.append(sistema)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof PerfilAcesso) {
			PerfilAcesso pa = (PerfilAcesso) obj;

			return pa.canEqual(this) && new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(nome, pa.getNome())
				.append(descricao, pa.getDescricao())
				.append(sistema, pa.getSistema())
				.isEquals();
		}
		
		return result;
	}
	
	@Override
	public boolean canEqual(Object obj) {
        return obj instanceof PerfilAcesso;
    }
}
