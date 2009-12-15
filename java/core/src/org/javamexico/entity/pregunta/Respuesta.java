package org.javamexico.entity.pregunta;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.javamexico.entity.Usuario;

/** Representa una respuesta que un usuario da a una pregunta hecha por otro usuario.
 * 
 * @author Enrique Zamudio
 */
@Entity
public class Respuesta {

	private int rid;
	private Pregunta pregunta;
	private Usuario user;
	private Date fecha;
	private String respuesta;

	@Id
	public int getRid() {
		return rid;
	}
	public void setRid(int value) {
		rid = value;
	}

	@ManyToOne
	@JoinColumn(name="pid", referencedColumnName="pid")
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta value) {
		pregunta = value;
	}

	@ManyToOne
	@JoinColumn(name="uid", referencedColumnName="uid")
	public Usuario getAutor() {
		return user;
	}
	public void setAutor(Usuario value) {
		user = value;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date value) {
		fecha = value;
	}

	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String value) {
		respuesta = value;
	}

}
