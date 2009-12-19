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
package org.javamexico.mockup.dao;

import org.javamexico.dao.UserDao;
import org.javamexico.entity.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

/** Un DAO falso de usuarios, para pruebas de GUI y otras cosas.
 * 
 * @author Enrique Zamudio
 */
public class UserDAO implements UserDao {

	private List<Usuario> users;

	@PostConstruct
	public void init() {
		users = new ArrayList<Usuario>();
		Usuario u = new Usuario();
		u.setUid(1);
		u.setUsername("admin");
		u.setNombre("Administrador");
		u.setPassword("pass");
		u.setFechaAlta(new Date(System.currentTimeMillis() - (9999990000l)));
		u.setReputacion(0);
		u.setStatus(1);
		u.setVerificado(true);
		users.add(u);

		u = new Usuario();
		u.setUid(2);
		u.setUsername("ezl");
		u.setNombre("Enrique Zamudio");
		u.setPassword("pass");
		u.setFechaAlta(new Date(System.currentTimeMillis() - (86400500l*90)));
		u.setReputacion(100);
		u.setStatus(1);
		u.setVerificado(true);
		users.add(u);

		u = new Usuario();
		u.setUid(3);
		u.setUsername("jb");
		u.setNombre("Javier Ramirez");
		u.setPassword("pass");
		u.setFechaAlta(new Date(System.currentTimeMillis() - (86400900l*80)));
		u.setReputacion(120);
		u.setStatus(1);
		u.setVerificado(true);
		users.add(u);

		u = new Usuario();
		u.setUid(4);
		u.setUsername("ecamacho");
		u.setNombre("Eric Camacho");
		u.setPassword("pass");
		u.setFechaAlta(new Date(System.currentTimeMillis() - (86412345l*30)));
		u.setReputacion(50);
		u.setStatus(1);
		u.setVerificado(true);
		users.add(u);

		u = new Usuario();
		u.setUid(5);
		u.setUsername("domix");
		u.setNombre("Domingo Suarez");
		u.setPassword("pass");
		u.setFechaAlta(new Date(System.currentTimeMillis() - (86498765l*22)));
		u.setReputacion(50);
		u.setStatus(1);
		u.setVerificado(true);
		users.add(u);

		u = new Usuario();
		u.setUid(6);
		u.setUsername("test");
		u.setNombre("Usuario de prueba");
		u.setPassword("pass");
		u.setFechaAlta(new Date(System.currentTimeMillis() - (86460203l*3)));
		u.setReputacion(5);
		u.setStatus(1);
		users.add(u);
	}

	public Usuario validaLogin(String username, String password) {
		if (username == null) {
			return null;
		}
		for (Usuario x : users) {
			if (username.equals(x.getUsername())) {
				return x;
			}
		}
		return null;
	}

	public List<Usuario> getAllUsers() {
		return users;
	}

	public Usuario getUser(int id) {
		for (Usuario x : users) {
			if (id == x.getUid()) {
				return x;
			}
		}
		return null;
	}

	public void delete(Usuario u) {
		users.remove(u);
	}

	public void insert(Usuario u) {
		users.add(u);
	}

	public void update(Usuario u) {
		//nada...
	}

}
