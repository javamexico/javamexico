package org.javamexico.site.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.PreguntaDao;
import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.entity.pregunta.Respuesta;
import org.javamexico.entity.pregunta.TagPregunta;

/** Esta pagina muestra una sola pregunta, con sus respuestas. Permite agregar una respuesta
 * 
 * @author Enrique Zamudio
 */
public class VerPregunta {

	@Inject
	@Service("preguntaDao")
	private PreguntaDao pdao;
	@Property
	private Pregunta pregunta;
	@Property
	private Respuesta resp;
	@Property
	private TagPregunta tag;

	void onActivate(int pid) {
		pregunta = pdao.getPregunta(pid);
		
	}

	public List<Respuesta> getRespuestas() {
		return pdao.getRespuestas(pregunta, 8, 1, false);
	}

}
