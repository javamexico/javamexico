package org.javamexico.entity.pregunta;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.javamexico.entity.Usuario;

/** Una respuesta puede tener votos de los demas usuarios. Es la manera en que la gente puede
 * decir si considera util la respuesta o no.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="voto_respuesta")
public class VotoRespuesta {

	//TODO pkey compuesta a respuesta y usuario
	private Respuesta resp;
	private Usuario user;
	private Date fecha;
	private boolean up;

	@ManyToOne
	@JoinColumn(name="rid", referencedColumnName="rid")
	public Respuesta getRespuesta() {
		return resp;
	}
	public void setRespuesta(Respuesta value) {
		resp = value;
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
