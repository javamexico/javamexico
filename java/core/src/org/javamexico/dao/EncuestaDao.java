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

import org.javamexico.entity.Usuario;
import org.javamexico.entity.encuesta.Encuesta;
import org.javamexico.entity.encuesta.ComentEncuesta;
import org.javamexico.entity.encuesta.OpcionEncuesta;
import org.javamexico.entity.encuesta.VotoEncuesta;

import java.util.Date;
import java.util.Set;

public interface EncuestaDao {

	public Set<Encuesta> getEncuestasRecientes(Date desde);

	public Encuesta getEncuesta(Encuesta poll);

	public Set<ComentEncuesta> getComentarios(Encuesta poll, int pageSize, int page, boolean crono);

	public void insert(Encuesta nueva);
	public void delete(Encuesta poll);
	public void update(Encuesta poll);

	public OpcionEncuesta addOpcion(String opcion, Encuesta poll);
	public void deleteOpcion(OpcionEncuesta opt);

	public ComentEncuesta addComentario(String texto, Usuario autor, Encuesta poll);

	public VotoEncuesta vota(Usuario autor, OpcionEncuesta opt);

}
