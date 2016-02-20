package br.com.anbima.commons.domain.valueobject.meta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Conjuntos de tipos de dados de um atributo.
 */
public enum ConjuntoTipoDado {

	/**
	 * Conjunto de tipos de dados numéricos.
	 */
	NUMERICO(ImmutableList.of("byte", "short", "int", "long", "float", "double"), ImmutableList.of("java.lang.Byte",
			"java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double",
			"java.math.BigDecimal", "java.math.BigInteger")),

	/**
	 * Conjunto de tipos de dados textuais.
	 */
	TEXTUAL(ImmutableList.of("char"), ImmutableList.of("java.lang.Character", "java.lang.String")),

	/**
	 * Conjunto de tipos de dados temporais.
	 */
	TEMPORAL(Collections.unmodifiableList(new ArrayList<String>(0)), ImmutableList.of("java.util.Date",
			"java.util.Calendar")),

	/**
	 * Conjunto de tipos de dados booleanos.
	 */
	BOOLEANO(ImmutableList.of("boolean"), ImmutableList.of("java.lang.Boolean")),

	/**
	 * Conjunto de tipos de dados enumerados.
	 */
	ENUMERADO(ImmutableList.of("enum"), ImmutableList.of("java.lang.Enum")),

	/**
	 * Conjunto de tipos de coleção de dados.
	 */
	COLECAO(ImmutableList.of("collection", "set", "list"), ImmutableList.of("java.util.Collection", "java.util.Set",
			"java.util.List"));

	private List<String> primitivos;

	private List<String> complexos;

	private ConjuntoTipoDado(List<String> primitivos, List<String> complexos) {
		this.primitivos = primitivos;
		this.complexos = complexos;
	}

	public List<String> getPrimitivos() {
		return primitivos;
	}

	public List<String> getComplexos() {
		return complexos;
	}

	/**
	 * Verifica se um tipo de dado pertence ao conjunto de dados numéricos.
	 * 
	 * @param tipoDado Tipo de dado a ser verificado
	 * @return True se pretence ao conjunto, False caso contrário
	 */
	public static Boolean ehNumerico(String tipoDado) {
		return pertenceConjuntoTipoDado(tipoDado, NUMERICO);
	}

	/**
	 * Verifica se um tipo de dado pertence ao conjunto de dados textuais.
	 * 
	 * @param tipoDado Tipo de dado a ser verificado
	 * @return True se pretence ao conjunto, False caso contrário
	 */
	public static Boolean ehTextual(String tipoDado) {
		return pertenceConjuntoTipoDado(tipoDado, TEXTUAL);
	}

	/**
	 * Verifica se um tipo de dado pertence ao conjunto de dados temporais.
	 * 
	 * @param tipoDado Tipo de dado a ser verificado
	 * @return True se pretence ao conjunto, False caso contrário
	 */
	public static Boolean ehTemporal(String tipoDado) {
		return pertenceConjuntoTipoDado(tipoDado, TEMPORAL);
	}

	/**
	 * Verifica se um tipo de dado pertence ao conjunto de dados booleanos.
	 * 
	 * @param tipoDado Tipo de dado a ser verificado
	 * @return True se pretence ao conjunto, False caso contrário
	 */
	public static Boolean ehBooleano(String tipoDado) {
		return pertenceConjuntoTipoDado(tipoDado, BOOLEANO);
	}

	/**
	 * Verifica se um tipo de dado pertence ao conjunto de dados enumerados.
	 * 
	 * @param tipoDado Tipo de dado a ser verificado
	 * @return True se pretence ao conjunto, False caso contrário
	 */
	public static Boolean ehEnumerado(String tipoDado) {
		return pertenceConjuntoTipoDado(tipoDado, ENUMERADO);
	}

	/**
	 * Verifica se um tipo de dado pertence ao conjunto de coleção de dados.
	 * 
	 * @param tipoDado Tipo de dado a ser verificado
	 * @return True se pretence ao conjunto, False caso contrário
	 */
	public static Boolean ehColecao(String tipoDado) {
		return pertenceConjuntoTipoDado(tipoDado, COLECAO);
	}

	/**
	 * Verifica se um tipo de dado pertence a um determinado conjunto de tipos.
	 * 
	 * @param tipoDado Tipo de dado a ser verificado
	 * @param conjuntoTipoDado Conjunto onnde o tipo deve ser verificado
	 * @return True se o tipo de dado pertence ao conjunto, False caso
	 *         contrário
	 */
	private static Boolean pertenceConjuntoTipoDado(String tipoDado, ConjuntoTipoDado conjuntoTipoDado) {
		return conjuntoTipoDado.getPrimitivos().contains(tipoDado)
				|| conjuntoTipoDado.getComplexos().contains(tipoDado);
	}
}
