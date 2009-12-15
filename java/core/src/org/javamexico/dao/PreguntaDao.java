package org.javamexico.dao;

import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.entity.pregunta.Respuesta;
import org.javamexico.entity.pregunta.TagPregunta;
import org.javamexico.entity.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Set;

/** Esta interfaz define los metodos que debe implementar un DAO para el modulo de preguntas y respuestas.
 * 
 * @author Enrique Zamudio
 */
public interface PreguntaDao {

	/** Devuelve las preguntas mas recientes, ordenadas desde la mas reciente hacia la mas antigua.
	 * @param desde El limite inferior de fecha que deben tener las preguntas. */
	public List<Pregunta> getPreguntasRecientes(Date desde);

	/** Devuelve las preguntas hechas por el usuario indicado. */
	public List<Pregunta> getPreguntasUsuario(Usuario user);

	/** Devuelve las preguntas que contienen el tag especificado. */
	public List<Pregunta> getPreguntasConTag(TagPregunta tag);

	/** Devuelve todas las preguntas que tengan al menos uno de los tags especificados. */
	public List<Pregunta> getPreguntasConTags(Set<TagPregunta> tags);

	/** Devuelve los tags mas utilizados en preguntas.
	 * @param limit El numero maximo de tags a devolver. */
	public Set<TagPregunta> getTagsPopulares(int limit);

	/** Devuelve la pregunta con el id especificado. */
	public Pregunta getPregunta(int id);

	/** Devuelve las respuestas a la pregunta especificada.
	 * @param pageSize El numero maximo de preguntas a devolver.
	 * @param page El numero de pagina a devolver (la primera pagina es 1)
	 * @param crono Indica si se deben ordenar las respuestas en orden cronologico, en vez de por votos
	 * (default es por votos). */
	public List<Respuesta> getRespuestas(Pregunta q, int pageSize, int page, boolean crono);

	/** Devuelve la lista de preguntas con mas votos positivos.
	 * @param limit El numero maximo de preguntas a devolver. */
	public List<Pregunta> getPreguntasMasVotadas(int limit);

	/** Devuelve la lista de preguntas que no tienen ni una sola respuesta. */
	public List<Pregunta> getPreguntasSinResponder(int limit);

	/** Devuelve la lista de preguntas cuyo autor no ha elegido una respuesta. */
	public List<Pregunta> getPreguntasSinResolver(int limit);

}
