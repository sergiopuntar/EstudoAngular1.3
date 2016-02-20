package br.com.anbima.commons.domain.entity.ca;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;

/**
 * Sistemas da ANBIMA que utilizam o controle de acesso centralizado.
 */
@Audited
public class Sistema extends AbstractEntity {
	private static final long serialVersionUID = 4169815169685309953L;

	private String nome;

	private String sigla;

	private String descricao;

	private Set<PerfilAcesso> perfisAcesso;

	public Sistema() {
		super();
		this.perfisAcesso = new HashSet<PerfilAcesso>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<PerfilAcesso> getPerfisAcesso() {
		return this.perfisAcesso;
	}

	public void setPerfisAcesso(Set<PerfilAcesso> perfisAcesso) {
		this.perfisAcesso = perfisAcesso;
	}

	public PerfilAcesso addPerfilAcesso(PerfilAcesso perfilAcesso) {
		perfilAcesso.setSistema(this);
		getPerfisAcesso().add(perfilAcesso);
		
		return perfilAcesso;
	}

	public PerfilAcesso removePerfilAcesso(PerfilAcesso perfilAcesso) {
		getPerfisAcesso().remove(perfilAcesso);
		perfilAcesso.setSistema(null);

		return perfilAcesso;
	}

	@Override
	public String toString() {
		return oid + " - " + nome;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11)
			.appendSuper(super.hashCode())
			.append(nome)
			.append(sigla)
			.append(descricao)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof Sistema) {
			Sistema s = (Sistema) obj;
			
			return s.canEqual(this) && new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(nome, s.getNome())
				.append(sigla, s.getSigla())
				.append(descricao, s.getDescricao())
				.isEquals();
		}
		
		return result;
	}
	
	@Override
	public boolean canEqual(Object obj) {
        return obj instanceof Sistema;
    }
}
