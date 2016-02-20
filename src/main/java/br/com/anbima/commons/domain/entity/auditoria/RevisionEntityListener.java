package br.com.anbima.commons.domain.entity.auditoria;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener responsável por montar o Objeto RevisionEntity de Auditoria do Hibernate Envers.
 *
 */
public class RevisionEntityListener implements EntityTrackingRevisionListener {
	private static final Logger LOG = LoggerFactory.getLogger(RevisionEntityListener.class);
	private static final String USUARIO_EXTERNO = "Externo";

	@Override
	public void newRevision(Object obj) {
		RevisionEntity revision = (RevisionEntity) obj;
		revision.setData(new Date());

		try {
			revision.setNomeUsuario("angularjspoc");
			revision.setOidUsuario("0");
			revision.setIp("0.0.0.0");

		} catch (Exception exception) {
			LOG.error("Erro ao recuperar usuário Logado. Considerando como Usuário Externo.");
			LOG.debug("Erro:", exception);

			revision.setNomeUsuario(USUARIO_EXTERNO);
			revision.setOidUsuario(USUARIO_EXTERNO);
			revision.setIp(USUARIO_EXTERNO);
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void entityChanged(Class entityClass, String entityName, Serializable entityId, RevisionType revisionType,
			Object revisionEntity) {
		RevisionChangesEntity revisionChange = new RevisionChangesEntity();
		revisionChange.setNomeEntidade(entityName);
		revisionChange.setRevisionType(revisionType);
		revisionChange.setOidEntidade(String.valueOf(entityId));

		((RevisionEntity) revisionEntity).addRevisionChanges(revisionChange);
	}
}
