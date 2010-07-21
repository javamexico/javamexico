package org.javamexico.site.pages.foros;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.ForoDao;
import org.javamexico.entity.foro.Foro;
import org.javamexico.entity.foro.TemaForo;

/** Muestra los foros de un mismo tema.
 * 
 * @author Enrique Zamudio
 */
public class Tema {

	@Inject @Service("foroDao")
	private ForoDao fdao;
	@Property private TemaForo tema;
	@Property private Foro foro;

	void onActivate(int tid) {
		tema = fdao.getTema(tid);
	}

	int onPassivate() {
		return tema == null ? 0 : tema.getTid();
	}

	public List<Foro> getForos() {
		//TODO paginacion...
		return fdao.getForosConTema(tema, 1, 20);
	}

}
