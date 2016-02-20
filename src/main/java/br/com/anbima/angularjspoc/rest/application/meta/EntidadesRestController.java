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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.anbima.angularjspoc.rest.application.BaseRestController;
import br.com.anbima.angularjspoc.rest.valueobject.domain.Errors;
import br.com.anbima.angularjspoc.rest.valueobject.domain.meta.EntidadeVo;
import br.com.anbima.angularjspoc.rest.valueobject.factory.RestValueObjectFactory;
import br.com.anbima.angularjspoc.service.MetadadosService;
import br.com.anbima.commons.domain.entity.meta.Entidade;

/**
 * Controlador Rest de metadados de entidades.
 */
@RequestScoped
@Path("/metadados/entidades")
public class EntidadesRestController extends BaseRestController {
	private static final long serialVersionUID = -1663685963351451343L;
	
	@Inject
	private MetadadosService metadadosService;
	
	@Inject
	private RestValueObjectFactory restValueObjectFactory;

	/**
	 * Recupera todas as entidades.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntidadeVo> getEntidades() {
		return restValueObjectFactory.buildVos(metadadosService.buscarEntidades(), EntidadeVo.class);
	}
	
	/**
	 * Adiciona uma nova entidade.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EntidadeVo postEntidades(EntidadeVo entidadeVo) {
		Entidade entidade = restValueObjectFactory.buildEntity(entidadeVo, Entidade.class);
		validarEntidade(entidade);
		
		return restValueObjectFactory.buildVo(metadadosService.salvarEntidade(entidade), EntidadeVo.class);
	}
	
	/**
	 * Recupera uma entidade.
	 */
	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public EntidadeVo getEntidade(@PathParam("oid") String oid) {
		Entidade entidade = metadadosService.buscarEntidadePorIdentificador(oid);
		
		if (entidade == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return restValueObjectFactory.buildVo(metadadosService.salvarEntidade(entidade), EntidadeVo.class);
	}
	
	/**
	 * Atualiza uma entidade.
	 */
	@PUT
	@Path("/{oid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EntidadeVo putEntidade(@PathParam("oid") String oid, EntidadeVo entidadeVo) {
		Entidade entidade = restValueObjectFactory.buildEntity(entidadeVo, Entidade.class);
		validarEntidade(entidade);
		
		return restValueObjectFactory.buildVo(metadadosService.salvarEntidade(entidade), EntidadeVo.class);
	}
	
	/**
	 * Remove uma entidade.
	 */
	@DELETE
	@Path("/{oid}")
	public Response deleteEntidade(@PathParam("oid") String oid) {
		metadadosService.excluirEntidade(oid);
		return Response.ok().build();
	}
	
	/**
	 * Valida os dados de uma entidade. 
	 * 
	 * @param entidade
	 */
	private void validarEntidade(Entidade entidade) {
		Errors errors = validateBean(entidade);
		
		if (!validarNomeQualificadoClasse(entidade)) {
			errors.addFieldError("nomeQualificadoClasse", "Já existe uma entidade com essa classe");
		}
		
		if (errors.hasErros()) {
			throw new WebApplicationException(createErrorResponse(errors).build());
		}
	}

	/**
	 * Valida o nome qualificado da classe da entidade.
	 * 
	 * @param entidade Entidade cuja classe deve ser validada
	 * @return True se a classe é válida, False caso contrário
	 */
	private boolean validarNomeQualificadoClasse(Entidade entidade) {
		Entidade entidadeExistente = metadadosService.buscarEntidadePorNomeQualificadoClasse(entidade.getNomeQualificadoClasse());
		return entidadeExistente == null || entidadeExistente.getOid().equals(entidade.getOid());
	}
}
