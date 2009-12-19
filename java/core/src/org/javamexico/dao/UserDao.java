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

import java.util.List;

public interface UserDao {

	public Usuario validaLogin(String username, String password);

	public List<Usuario> getAllUsers();

	public Usuario getUser(int id);

	public void insert(Usuario u);

	public void update(Usuario u);

	public void delete(Usuario u);

}
