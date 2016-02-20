package br.com.anbima.commons.domain.entity.meta;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.domain.entity.ca.PerfilAcesso;
import br.com.anbima.commons.domain.valueobject.IndicadorSimNao;

/**
 * Permissões de um perfil sobre uma entidade do sistema.
 */
@Audited
public class PermissaoEntidade extends AbstractEntity {
	private static final long serialVersionUID = 7951042035320914362L;

	private PerfilAcesso perfil;
	
	private Entidade entidade;
	
	private IndicadorSimNao inclusao;
	
	private IndicadorSimNao consulta;
	
	private IndicadorSimNao alteracao;
	
	private IndicadorSimNao exclusao;

	public PermissaoEntidade() {
		super();
	}

	public PerfilAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAcesso perfil) {
		this.perfil = perfil;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public IndicadorSimNao getInclusao() {
		return inclusao;
	}

	public void setInclusao(IndicadorSimNao inclusao) {
		this.inclusao = inclusao;
	}

	public IndicadorSimNao getConsulta() {
		return consulta;
	}

	public void setConsulta(IndicadorSimNao consulta) {
		this.consulta = consulta;
	}

	public IndicadorSimNao getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(IndicadorSimNao alteracao) {
		this.alteracao = alteracao;
	}

	public IndicadorSimNao getExclusao() {
		return exclusao;
	}

	public void setExclusao(IndicadorSimNao exclusao) {
		this.exclusao = exclusao;
	}
	
	// Métodos de negócio
	
	/**
	 * Verifica se permite a inclusão de instâncias da entidade.
	 * 
	 * @return True se permite, False caso contrário
	 */
	public Boolean permiteInclusao() {
		return getInclusao() != null && getInclusao().booleanValue();
	}
	
	/**
	 * Verifica se permite a consulta de instâncias da entidade.
	 * 
	 * @return True se permite, False caso contrário
	 */
	public Boolean permiteConsulta() {
		return getConsulta() != null && getConsulta().booleanValue();
	}

	/**
	 * Verifica se permite a alteração de instâncias da entidade.
	 * 
	 * @return True se permite, False caso contrário
	 */
	public Boolean permiteAlteracao() {
		return getAlteracao() != null && getAlteracao().booleanValue();
	}

	/**
	 * Verifica se permite a exclusão de instâncias da entidade.
	 * 
	 * @return True se permite, False caso contrário
	 */
	public Boolean permiteExclusao() {
		return getExclusao() != null && getExclusao().booleanValue();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11)
			.appendSuper(super.hashCode())
			.append(perfil)
			.append(entidade)
			.append(inclusao)
			.append(consulta)
			.append(alteracao)
			.append(exclusao)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof PermissaoEntidade) {
			PermissaoEntidade pe = (PermissaoEntidade) obj;

			return pe.canEqual(this) && new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(perfil, pe.getPerfil())
				.append(entidade, pe.getEntidade())
				.append(inclusao, pe.getInclusao())
				.append(consulta, pe.getConsulta())
				.append(alteracao, pe.getAlteracao())
				.append(exclusao, pe.getExclusao())
				.isEquals();
		}
		
		return result;
	}
	
	@Override
	public boolean canEqual(Object obj) {
        return obj instanceof PermissaoEntidade;
    }
}
