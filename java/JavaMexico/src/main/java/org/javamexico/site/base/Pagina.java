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
package org.javamexico.site.base;

import org.apache.tapestry5.annotations.SessionState;
import org.javamexico.entity.Usuario;

/** Nuestra clase base para las paginas que necesitan obtener el usuario registrado. No todas las paginas
 * lo necesitan, pero la mayoria si (donde hay opcion de que el usuario agregue contenido).
 * 
 * @author Enrique Zamudio
 */
public class Pagina {

	@SessionState
	private Usuario user;
	/** T5 actualiza esta variable cuando se asigna un usuario por primera vez. Hasta entonces
	 * valdra "false" y es mejor asi porque no se crea una sesion hasta que sea necesario. */
	private boolean userExists;

	public void setUser(Usuario value) {
		user = value;
	}
	public Usuario getUser() {
		return user;
	}

	/** Este es el metodo que se debe utilizar para ver si hay usuario registrado. */
	public boolean getUserExists() {
		if (userExists) {
			return user != null;
		}
		return false;
	}

}
