package org.javamexico.site.pages.preguntas;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.PreguntaDao;
import org.javamexico.entity.pregunta.Pregunta;

/** Lista las preguntas por tag.
 * 
 * @author Enrique Zamudio
 */
public class PorTag {

	@Property
	private String tag;
	@Inject
	@Service("preguntaDao")
	private PreguntaDao pdao;
	@Property
	private Pregunta pregunta;

	void onActivate(String value) {
		tag = value;
	}
	String onPassivate() {
		return tag;
	}

	/** Devuelve las preguntas mas recientes para mostrarlas en la pagina. */
	public List<Pregunta> getPreguntas() {
		return pdao.getPreguntasConTag(tag);
	}

}
