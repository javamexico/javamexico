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

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.PreguntaDao;
import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.site.base.Pagina;

/** Esta pagina es para registrar una nueva pregunta, ponerle tags, etc.
 * 
 * @author Enrique Zamudio
 */
public class Nueva extends Pagina {

	@Property
	private String titulo;
	@Property
	private String pregunta;
	@Inject
	@Service("preguntaDao")
	private PreguntaDao qdao;
	@InjectPage
	private Editar editPage;

	Object onSuccessFromCrearPregunta() {
		Pregunta p = new Pregunta();
		p.setTitulo(titulo);
		p.setPregunta(pregunta);
		p.setAutor(getUser());
		p.setStatus(2);
		qdao.insert(p);
		//Con esto se muestra la siguiente pagina
		editPage.onActivate(Integer.toString(p.getPid()));
		return editPage;
	}

}
