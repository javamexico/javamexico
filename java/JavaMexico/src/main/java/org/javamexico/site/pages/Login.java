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
package org.javamexico.site.pages;

import org.apache.tapestry5.annotations.Property;

/** Pagina para que un usuario pueda registrarse.
 * 
 * @author Enrique Zamudio
 */
public class Login {

	@Property private String page;

	void onActivate(String pname) {
		page = pname;
	}
	String onPassivate() {
		return page;
	}

}
