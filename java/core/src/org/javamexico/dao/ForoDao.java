package org.javamexico.dao;

import org.javamexico.entity.foro.Foro;
import org.javamexico.entity.foro.ComentForo;
import org.javamexico.entity.Usuario;

import java.util.Set;
import java.util.Date;

public interface ForoDao {

	public Set<Foro> getForosByUser(Usuario user);

	public Set<Foro> getForosRecientes(Date desde);

	public Set<Foro> getForosMasActivos(int limit);

	/** Devuelve el foro con el ID especificado. */
	public Foro getForo(int id);

	/** Devuelve los comentarios del foro indicado, paginado segun se pida.
	 *  @param foro El foro cuyos comentarios se quieren obtener.
	 *  @param pageSize El numero de comentarios por pagina.
	 *  @param page El numero de pagina a obtener (la primera es 1)
	 *  @param crono Indica si los comentarios se deben mostrar en orden cronologico
	 *         (el mas viejo primero).
	 */
	public Set<ComentForo> getComentarios(Foro foro, int pageSize, int page, boolean crono);

}
