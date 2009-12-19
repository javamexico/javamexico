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
package org.javamexico.dao;

import org.javamexico.entity.foro.Foro;
import org.javamexico.entity.foro.ComentForo;
import org.javamexico.entity.Usuario;

import java.util.Set;
import java.util.Date;

/** Define la funcionalidad del DAO para la seccion de foros.
 * 
 * @author Enrique Zamudio
 */
public interface ForoDao {

	/** Devuelve los foros creados por el usuario especificado.
	 * @param user El creador de los foros
	 * @param published Indica si se deben devolver solamente sus foros publicados, o todos. */
	public Set<Foro> getForosByUser(Usuario user, boolean published);

	/** Devuelve los foros creados a partir de la fecha especificada, sin importar el autor. */
	public Set<Foro> getForosRecientes(Date desde);

	/** Devuelve los foros con mayor actividad (numero de comentarios). */
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

	/** Inserta nuevo foro en la base de datos. */
	public void insert(Foro foro);
	/** Actualiza los datos del foro en la base de datos. */
	public void update(Foro foro);
	/** Elimina de la base de datos el foro especificado. */
	public void delete(Foro foro);

	/** Agrega un comentario al foro especificado, como respuesta a otro comentario.
	 * @param coment El comentario a agregar.
	 * @param foro El foro donde se va a agregar el comentario.
	 * @param parent El comentario al cual se esta respondiendo (opcional). */
	public void addComment(ComentForo coment, Foro foro, ComentForo parent);

}
