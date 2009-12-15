package org.javamexico.dao;

import org.javamexico.entity.Usuario;

import java.util.Set;

public interface UserDao {

	public Usuario validaLogin(String username, String password);

	public Set<Usuario> getAllUsers();

	public Usuario getUser(int id);

}
