package br.com.anbima.commons.domain.entity.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.com.anbima.commons.domain.entity.EntityInterface;

/**
 * Classe de utilitários para entidades do sistema.
 */
public class EntityUtil {

	private EntityUtil() {
		super();
	}

	/**
	 * Gera um identificador de entidade randômico.
	 * 
	 * @return Identificador gerado
	 */
	public static String gerarOidRandomico() {
		return UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
	 * Recupera uma entidade de uma lista a partir do seu identificador.
	 * 
	 * @param entities Lista de entidades
	 * @param oid Identificador da entidade
	 * @return Entidade recuperada, null se ela não estiver presente na lista
	 */
	public static <T extends EntityInterface> T recuperarEntity(Collection<T> entities, String oid) {
		for (T entity : entities) {
			if(entity.getOid()!=null && entity.getOid().equals(oid)) {
				return entity;
			}
		}		
		return null;
	}
	
	/**
	 * Identifica em uma coleção de entidades, quais identificadores informados
	 * não estão presentes. 
	 * 
	 * @param entities Coleção de entidades
	 * @param oids Identificadores a serem buscados
	 * @return Lista de identificadores não presentes na lista de entidades
	 */
	public static List<String> identificarOidsNaoPresentes(Collection<? extends EntityInterface> entities, Set<String> oids) {
		List<String> oidsInexistentes = new ArrayList<String>();
		boolean presente;
		
		for (String oid : oids) {
			presente = false;
			
			for (EntityInterface area : entities) {
				if (area.getOid().equals(oid)) {
					presente = true;
					break;
				}
			}
			
			if (!presente) {
				oidsInexistentes.add(oid);
			}
		}
		
		return oidsInexistentes;
	}
}
