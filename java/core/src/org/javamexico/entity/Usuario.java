package org.javamexico.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/** Representa un usuario del sistema.
 * 
 * @author Enrique Zamudio
 */
@Entity
public class Usuario implements Comparable<Usuario> {

	private int uid;
	private String uname;
	private String nombre;
	private String passwd;
	private Date alta;
	private int status;
	private int rep;
	private boolean verif;

	@Id
	public int getUid() {
		return uid;
	}
	public void setUid(int value) {
		uid = value;
	}

	public String getUsername() {
		return uname;
	}
	public void setUsername(String value) {
		uname = value;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String value) {
		nombre = value;
	}

	public String getPassword() {
		return passwd;
	}
	public void setPassword(String value) {
		passwd = value;
	}

	public Date getFechaAlta() {
		return alta;
	}
	public void setFechaAlta(Date value) {
		alta = value;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int value) {
		status = value;
	}

	public int getReputacion() {
		return rep;
	}
	public void setReputacion(int value) {
		rep = value;
	}

	public boolean isVerificado() {
		return verif;
	}
	public void setVerificado(boolean value) {
		verif = value;
	}

	public int compareTo(Usuario o) {
		if (o == null) {
			return 1;
		}
		return o.getUid() > uid ? -1 : o.getUid() < uid ? 1 : 0;
	}

}
