package org.javamexico.site.pages;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.PreguntaDao;
import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.entity.pregunta.TagPregunta;

/** La pagina principal del modulo de preguntas; muestra una lista de las preguntas mas recientes.
 * 
 * @author Enrique Zamudio
 */
public class Preguntas {

	@Inject
	@Service("preguntaDao")
	private PreguntaDao pdao;
	@Property
	private Pregunta pregunta;
	@Property
	private TagPregunta tag;

	/** Devuelve las preguntas mas recientes para mostrarlas en la pagina. */
	public List<Pregunta> getPreguntas() {
		return pdao.getPreguntasRecientes(new Date(System.currentTimeMillis()-864000000l));
	}

	/** Devuelve los 5 tags mas utilizados en el modulo. */
	public Set<TagPregunta> getPopTags() {
		return pdao.getTagsPopulares(5);
	}

}
