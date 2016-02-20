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
import br.com.anbima.angularjspoc.rest.valueobject.domain.meta.AtributoConsultaVo;
import br.com.anbima.angularjspoc.service.MetadadosService;

/**
 * Controlador Rest de metadados de atributos de consultas de entidades.
 */
@RequestScoped
@Path("/metadados/entidades/{oidEntidade}/consultas/{oidConsulta}")
public class AtributosConsultaRestController extends BaseRestController {
	private static final long serialVersionUID = -2915897205728266603L;
	
	@Inject
	private MetadadosService metadadosService;
	
	/**
	 * Recupera todos as consultas de uma entidade.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AtributoConsultaVo> getAtributosConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oidConsulta") String oidConsulta) {
		return null;
	}

	/**
	 * Atualiza todos as consultas de uma entidade.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AtributoConsultaVo> putAtributosConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oidConsulta") String oidConsulta, List<AtributoConsultaVo> atributosConsulta) {
		return null;
	}
	
	/**
	 * Adiciona uma nova consulta de uma entidade.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AtributoConsultaVo postAtributosConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oidConsulta") String oidConsulta, AtributoConsultaVo atributoConsulta) {
		return null;
	}
	
	/**
	 * Recupera uma consulta de uma entidade.
	 */
	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public AtributoConsultaVo getAtributoConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oidConsulta") String oidConsulta, @PathParam("oid") String oid) {
		return null;
	}
	
	/**
	 * Atualiza uma consulta de uma entidade.
	 */
	@PUT
	@Path("/{oid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AtributoConsultaVo putAtributoConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oidConsulta") String oidConsulta, @PathParam("oid") String oid, AtributoConsultaVo atributoConsulta) {
		return null;
	}

	/**
	 * Remove uma consulta de uma entidade.
	 */
	@DELETE
	@Path("/{oid}")
	public Response deleteAtributoConsulta(@PathParam("oidEntidade") String oidEntidade, @PathParam("oidConsulta") String oidConsulta, @PathParam("oid") String oid) {
		return null;
	}
}
