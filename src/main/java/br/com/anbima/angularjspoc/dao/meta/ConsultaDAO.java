package br.com.anbima.angularjspoc.dao.meta;

import br.com.anbima.angularjspoc.repository.meta.ConsultaRepository;
import br.com.anbima.commons.dao.BaseDAO;
import br.com.anbima.commons.domain.entity.meta.Consulta;

/**
 * Implementa��o JPA do reposit�rio de Consultas de entidades do sistema.
 */
public class ConsultaDAO extends BaseDAO<Consulta> implements ConsultaRepository {
	private static final long serialVersionUID = 3021536888829918175L;

}
