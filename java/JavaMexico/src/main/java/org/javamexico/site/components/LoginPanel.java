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
package org.javamexico.site.components;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.UserDao;
import org.javamexico.entity.Usuario;
import org.javamexico.site.base.Pagina;

/** Presenta un panel de login para que un usuario se registre.
 * 
 * @author Enrique Zamudio
 */
public class LoginPanel extends Pagina {

	@Property
	private String uname;
	@Property
	private String passwd;
	@Inject
	@Service("usuarioDao")
	private UserDao udao;
	@InjectComponent
	private Form login;
	@Parameter
	private String page;

	Object onSuccessFromLogin() {
		Usuario u = udao.validaLogin(uname, passwd);
		if (u == null) {
			login.recordError("Usuario inexistente, o password invalido.");
		} else {
			//Con esto ya se queda el usuario en la sesion
			setUser(u);
			if (page == null) {
				return org.javamexico.site.pages.Index.class;
			} else {
				return page;
			}
		}
		return null;
	}

}
