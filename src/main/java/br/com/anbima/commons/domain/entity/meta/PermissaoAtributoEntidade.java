package br.com.anbima.commons.domain.entity.meta;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.anbima.commons.domain.entity.AbstractEntity;
import br.com.anbima.commons.domain.entity.ca.PerfilAcesso;
import br.com.anbima.commons.domain.valueobject.meta.TipoPermissaoAtributo;

/**
 * Permissões de um perfil sobre um atributo de entidade do sistema.
 */
@Audited
public class PermissaoAtributoEntidade extends AbstractEntity {
	private static final long serialVersionUID = 1089792866991028898L;

	private PerfilAcesso perfil;
	
	private AtributoEntidade atributoEntidade;
	
	private TipoPermissaoAtributo tipo;
	
	public PermissaoAtributoEntidade() {
		super();
	}

	public PerfilAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAcesso perfil) {
		this.perfil = perfil;
	}

	public AtributoEntidade getAtributoEntidade() {
		return atributoEntidade;
	}

	public void setAtributoEntidade(AtributoEntidade atributoEntidade) {
		this.atributoEntidade = atributoEntidade;
	}
	
	// Métodos de negócio
	
	public TipoPermissaoAtributo getTipo() {
		return tipo;
	}

	public void setTipo(TipoPermissaoAtributo tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Verifica se permite a leitura e escrita do conteúdo do atributo da
	 * entidade.
	 * 
	 * @return True se permite, False caso contrário
	 */
	public Boolean permiteLeituraEscrita() {
		return TipoPermissaoAtributo.LE.equals(getTipo());
	}
	
	/**
	 * Verifica se permite somente a leitura do conteúdo do atributo da
	 * entidade.
	 * 
	 * @return True se permite, False caso contrário
	 */
	public Boolean permiteSomenteLeitura() {
		return TipoPermissaoAtributo.SL.equals(getTipo());
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 11)
			.appendSuper(super.hashCode())
			.append(perfil)
			.append(atributoEntidade)
			.append(tipo)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof PermissaoAtributoEntidade) {
			PermissaoAtributoEntidade pae = (PermissaoAtributoEntidade) obj;

			return pae.canEqual(this) && new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(perfil, pae.getPerfil())
				.append(atributoEntidade, pae.getAtributoEntidade())
				.append(tipo, pae.getTipo())
				.isEquals();
		}
		
		return result;
	}
	
	@Override
	public boolean canEqual(Object obj) {
        return obj instanceof PermissaoAtributoEntidade;
    }
}
