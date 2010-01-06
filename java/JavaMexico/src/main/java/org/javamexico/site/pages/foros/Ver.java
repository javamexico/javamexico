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
package org.javamexico.site.pages.foros;

import java.util.List;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.ForoDao;
import org.javamexico.entity.foro.ComentForo;
import org.javamexico.entity.foro.Foro;
import org.javamexico.entity.foro.TagForo;
import org.javamexico.site.base.Pagina;
import org.slf4j.Logger;

/** La pagina para mostrar un foro, ver y agregar comentarios, etc.
 * 
 * @author Enrique Zamudio
 */
public class Ver extends Pagina {

	@Inject
	@Service("foroDao")
	private ForoDao fdao;
	@Inject private Logger log;
	@Property private Foro foro;
	@Property private Foro otrof;
	@Property private TagForo tag;
	@Property private ComentForo coment;
	@InjectComponent private Zone rzone;
	/** Aqui vamos a guardar el ID de un comentario especifico, para AJAX */
	private int cid;

	Object onActivate(String ids) {
		int fid = 0;
		if (ids.indexOf('-') > 0) {
			fid = Integer.parseInt(ids.substring(0, ids.indexOf('-')));
			cid = Integer.parseInt(ids.substring(ids.indexOf('-') + 1));
			log.warn("Me activan con fid {} cid {}", fid, cid);
		} else {
			fid = Integer.parseInt(ids);
		}
		if (fid > 0) {
			foro = fdao.getForo(fid);
		}
		if (foro == null) {
			return Index.class;
		} else if (cid > 0) {
			//TODO buscar el comentario especifico
		}
		return null;
	}

	Object onPassivate() {
		if (foro == null) {
			return null;
		}
		return coment == null ? foro.getFid() : String.format("%d-%d", foro.getFid(), coment.getCfid());
	}

	public List<ComentForo> getComents() {
		return fdao.getComentarios(foro, 1, 30, false);
	}

	public List<Foro> getMisForos() {
		return getUserExists() ? fdao.getForosByUser(getUser(), true) : null;
	}

	public boolean isForoVotado() {
		return foro != null && getUserExists() && fdao.findVoto(getUser(), foro) != null;
	}

	public boolean isComentVotado() {
		return coment != null && getUserExists() && fdao.findVoto(getUser(), coment) != null;
	}

	public String getComentZoneId() {
		return String.format("czone%d", coment.getCfid());
	}

	/** Este metodo lo invoca la liga para ver mas respuestas a un comentario. */
	Object onActionFromShowResps() {
		return rzone.getBody();
	}

}
