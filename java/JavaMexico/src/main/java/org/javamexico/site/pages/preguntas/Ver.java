/*
This file is part of JavaMexico.

JavaMexico is free software: you can redistribute it and/or modify it under the terms of the
GNU General Public License as published by the Free Software Foundation, either version 3
of the License, or (at your option) any later version.

JavaMexico is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with JavaMexico.
If not, see <http://www.gnu.org/licenses/>.
*/
package org.javamexico.site.pages.preguntas;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.PreguntaDao;
import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.entity.pregunta.Respuesta;
import org.javamexico.entity.pregunta.TagPregunta;
import org.javamexico.site.base.Pagina;

/** Esta pagina muestra una sola pregunta, con sus respuestas. Permite agregar una respuesta
 * 
 * @author Enrique Zamudio
 */
@IncludeStylesheet("context:layout/preguntas.css")
public class Ver extends Pagina {

	@Inject
	@Service("preguntaDao")
	private PreguntaDao pdao;
	@Property
	private Pregunta pregunta;
	@Property
	private Respuesta resp;
	@Property
	private TagPregunta tag;
	@Property
	private String resptext;

	/** Con esto obtenemos la pregunta con la clave indicada en el URL */
	void onActivate(int pid) {
		pregunta = pdao.getPregunta(pid);
		
	}
	/** Con esto volvemos a poner la clave de la pregunta en el URL */
	int onPassivate() {
		return pregunta.getPid();
	}

	/** Devuelve las respuestas a la pregunta mostrada. */
	public List<Respuesta> getRespuestas() {
		return pdao.getRespuestas(pregunta, 8, 1, false);
	}

	void onSuccessFromRespform(int pid) {
		pregunta = pdao.getPregunta(pid);
		resp = new Respuesta();
		resp.setAutor(getUser());
		resp.setFecha(new Date());
		resp.setPregunta(pregunta);
		resp.setRespuesta(resptext);
		pregunta.getRespuestas().add(resp);
		//TODO falta salvar en base de datos este cambio
		//seguramente con el DAO se agregara directamente la respuesta en vez de ese add
	}

}
