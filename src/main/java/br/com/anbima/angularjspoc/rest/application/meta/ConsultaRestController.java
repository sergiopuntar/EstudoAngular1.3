package br.com.anbima.angularjspoc.rest.application.meta;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.anbima.angularjspoc.rest.application.BaseRestController;
import br.com.anbima.angularjspoc.rest.valueobject.domain.meta.ConsultaVo;
import br.com.anbima.angularjspoc.service.MetadadosService;

/**
 * Controlador Rest de metadados de consultas de entidades.
 */
@RequestScoped
@Path("/metadados/entidades/{oidEntidade}/consultas")
public class ConsultaRestController extends BaseRestController {
	private static final long serialVersionUID = -3850474590285248550L;
	
	@Inject
	private MetadadosService metadadosService;
	
	/**
	 * Recupera todos as consultas de uma entidade.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConsultaVo> getConsultas(@PathParam("oidEntidade") String oidEntidade) {
		return null;
	}

	/**
	 * Atualiza todos as consultas de uma entidade.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConsultaVo> putConsultas(@PathParam("oidEntidade") String oidEntidade, List<ConsultaVo> consultas) {
		return null;
	}
	
	/**
	 * Adiciona uma nova consulta de uma entidade.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaVo postConsultas(@PathParam("oidEntidade") String oidEntidade, ConsultaVo consulta) {
		return null;
	}
	
	/**
	 * Recupera uma consulta de uma entidade.
	 */
	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaVo getConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oid") String oid) {
		return null;
	}
	
	/**
	 * Atualiza uma consulta de uma entidade.
	 */
	@PUT
	@Path("/{oid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaVo putConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oid") String oid, ConsultaVo consulta) {
		return null;
	}

	/**
	 * Remove uma consulta de uma entidade.
	 */
	@DELETE
	@Path("/{oid}")
	public Response deleteConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oid") String oid) {
		return null;
	}
}
