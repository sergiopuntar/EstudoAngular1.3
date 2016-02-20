package br.com.anbima.commons.domain.entity.util;

import java.io.Serializable;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Gerador de identificador de entidades dos sistemas da Anbima.
 */
public class AnbimaIdGenerator implements IdentifierGenerator {

	public AnbimaIdGenerator() {
		super();
	}

	@Override
	public Serializable generate(SessionImplementor session, Object object) {
		return EntityUtil.gerarOidRandomico();
	}
}
