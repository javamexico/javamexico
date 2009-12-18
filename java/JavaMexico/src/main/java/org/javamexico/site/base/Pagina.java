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
		return userExists;
	}

}
