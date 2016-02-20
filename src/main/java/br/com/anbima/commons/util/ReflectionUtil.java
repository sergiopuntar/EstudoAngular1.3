package br.com.anbima.commons.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * Classe de utilitários para reflexão.
 */
public class ReflectionUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

	/**
	 * Mensagem de erro de leitura de propriedade de um objeto através de
	 * reflexão.
	 */
	private static final String ERRO_LEITURA_PROPRIEDADE = "Não foi possível acessar a propriedade %s do objeto do tipo %s.";

	/**
	 * Mensagem de erro de recuperação de tipo de propriedade de uma classe
	 * através de reflexão.
	 */
	private static final String ERRO_RECUPERACAO_TIPO_PROPRIEDADE = "Não foi possível recuperar o tipo da propriedade %s da classe %s.";

	/**
	 * Mensagem de erro de construtor inexistente de uma classe.
	 */
	private static final String ERRO_CONSTRUTOR_INEXISTENTE = "Não existe construtor para a classe %s com os parâmetros %s.";

	/**
	 * Mensagem de erro de construção de um objeto de uma de uma classe através
	 * de reflexão.
	 */
	private static final String ERRO_CONSTRUCAO_OBJETO = "Não foi possível construir o objeto do tipo %s.";

	private ReflectionUtil() {
		super();
	}

	/**
	 * Verifica se um objeto é de uma determinada classe, através do nome
	 * qualificado da classe.<br>
	 * O método utiliza o nome qualificado para garantir que as classes são
	 * iguais mesmo que sejam de class loaders diferentes.
	 *
	 * @param obj Objeto a ser verificado
	 * @param clazz Classe a ser verificada
	 * @return True se o objeto é da classe definida, False caso contrário.
	 */
	public static Boolean verificarTipoObjeto(Object obj, Class<?> clazz) {
		return clazz.getName().equals(obj.getClass().getName());
	}

	/**
	 * Cria a instância de um objeto a partir de sua classe e seus de
	 * parâmetros.
	 * Se ocorrer algum erro será feito log e retornado null.
	 *
	 * @param clazz Classe do objeto a ser criado
	 * @param paramsTypes typos dos parâmetros de construção do objeto
	 * @param params Parâmetros de construção do objeto
	 * @return Objeto construído
	 */
	public static <T> T construirObjeto(Class<T> clazz, List<Class<?>> paramsTypes, List<Object> params) {
		Class<?>[] paramsClazz = new Class<?>[paramsTypes.size()];

		for (int i = 0; i < paramsTypes.size(); i++) {
			paramsClazz[i] = paramsTypes.get(i);
		}

		T object = null;

		try {
			Constructor<T> ct = clazz.getConstructor(paramsClazz);
			object = ct.newInstance(params.toArray());
		} catch (NoSuchMethodException e) {
			LOGGER.error(String.format(ERRO_CONSTRUTOR_INEXISTENTE, clazz.getName(), params.toString()), e);
		} catch (InstantiationException e) {
			LOGGER.error(String.format(ERRO_CONSTRUCAO_OBJETO, clazz.getName()), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(String.format(ERRO_CONSTRUCAO_OBJETO, clazz.getName()), e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(String.format(ERRO_CONSTRUCAO_OBJETO, clazz.getName()), e);
		} catch (InvocationTargetException e) {
			LOGGER.error(String.format(ERRO_CONSTRUCAO_OBJETO, clazz.getName()), e);
		}

		return object;
	}

	/**
	 * Recupera o tipo de uma propriedade de uma classe.
	 * Se ocorrer algum erro será feito log e retornado null.
	 *
	 * @param propriedade Nome da propriedade da classe
	 * @param clazz Classe cujo tipo da propriedade deve ser recuperado
	 * @return Tipo da propriedade da classe
	 */
	public static Class<?> getTipoPropriedadeClasse(String propriedade, Class<?> clazz) {
		Class<?> tipoPropriedade = null;

		try {
			if (propriedade.contains(".")) {
				Iterator<String> iterator = Lists.newArrayList(propriedade.split("\\.")).iterator();

				Class<?> classe = clazz;
				while (iterator.hasNext()) {
					String nome = iterator.next();

					if (iterator.hasNext()) {
						classe = new PropertyDescriptor(nome, classe).getPropertyType();

					} else {
						tipoPropriedade = new PropertyDescriptor(nome, classe).getPropertyType();
					}
				}

			} else {
				tipoPropriedade = new PropertyDescriptor(propriedade, clazz).getPropertyType();
			}
		} catch (IntrospectionException e) {
			LOGGER.error(String.format(ERRO_RECUPERACAO_TIPO_PROPRIEDADE, propriedade, clazz.getName()), e);
		}

		return tipoPropriedade;
	}

	/**
	 * Recupera o valor de uma propriedade de um objeto.
	 * Se ocorrer algum erro será feito log e retornado null.
	 *
	 * @param propriedade Nome da propriedade do objeto
	 * @param objeto Objeto cuja propriedade deve ser recuperada
	 * @return Valor da propriedade do objeto
	 */
	public static Object getPropriedadeObjeto(String propriedade, Object objeto) {
		Object valor = null;

		try {
			valor = new PropertyDescriptor(propriedade, objeto.getClass()).getReadMethod().invoke(objeto);
		} catch (IntrospectionException e) {
			LOGGER.error(String.format(ERRO_LEITURA_PROPRIEDADE, propriedade, objeto.getClass().getName()), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(String.format(ERRO_LEITURA_PROPRIEDADE, propriedade, objeto.getClass().getName()), e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(String.format(ERRO_LEITURA_PROPRIEDADE, propriedade, objeto.getClass().getName()), e);
		} catch (InvocationTargetException e) {
			LOGGER.error(String.format(ERRO_LEITURA_PROPRIEDADE, propriedade, objeto.getClass().getName()), e);
		}

		return valor;
	}
}
