package org.javamexico.entity.pregunta;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.javamexico.entity.Usuario;

/** Las preguntas pueden tener votos de los usuarios; una pregunta interesante tendra votos positivos
 * mientras que una pregunta muy basica o redundante puede tener votos negativos.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="voto_pregunta")
public class VotoPregunta {

	//TODO llave primaria compuesta a pregunta y usuario
	private Pregunta pregunta;
	private Usuario user;
	private Date fecha;
	private boolean up;

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
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario value) {
		user = value;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date value) {
		fecha = value;
	}

	public boolean isUp() {
		return up;
	}
	public void setUp(boolean flag) {
		up = flag;
	}

}
