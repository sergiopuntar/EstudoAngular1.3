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
import br.com.anbima.angularjspoc.rest.valueobject.domain.meta.AtributoEntidadeVo;
import br.com.anbima.angularjspoc.service.MetadadosService;

/**
 * Controlador Rest de metadados de atributos de entidades.
 */
@RequestScoped
@Path("/metadados/entidades/{oidEntidade}/atributos")
public class AtributosEntidadesRestController extends BaseRestController {
	private static final long serialVersionUID = 6360127018535672914L;

	@Inject
	private MetadadosService metadadosService;
	
	/**
	 * Recupera todos os atributos de uma entidade.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AtributoEntidadeVo> getAtributosEntidade(@PathParam("oidEntidade") String oidEntidade) {
		return null;
	}

	/**
	 * Atualiza todos os atributos de uma entidade.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AtributoEntidadeVo> putAtributosEntidade(@PathParam("oidEntidade") String oidEntidade, List<AtributoEntidadeVo> atributosEntidade) {
		return null;
	}
	
	/**
	 * Adiciona um novo atributo de uma entidade.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AtributoEntidadeVo postAtributosEntidade(@PathParam("oidEntidade") String oidEntidade, AtributoEntidadeVo atributoEntidade) {
		return null;
	}
	
	/**
	 * Recupera um atributo de uma entidade.
	 */
	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public AtributoEntidadeVo getAtributoEntidade(@PathParam("oidEntidade") String oidEntidade, @PathParam("oid") String oid) {
		return null;
	}
	
	/**
	 * Atualiza um atributo de uma entidade.
	 */
	@PUT
	@Path("/{oid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AtributoEntidadeVo putAtributoEntidade(@PathParam("oidEntidade") String oidEntidade, @PathParam("oid") String oid, AtributoEntidadeVo atributoEntidade) {
		return null;
	}

	/**
	 * Remove um atributo de uma entidade.
	 */
	@DELETE
	@Path("/{oid}")
	public Response deleteAtributoEntidade(@PathParam("oidEntidade") String oidEntidade, @PathParam("oid") String oid) {
		return null;
	}
}
