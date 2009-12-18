package org.javamexico.entity.pregunta;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.javamexico.entity.Usuario;

/** Una respuesta puede tener comentarios hechos por otros usuarios, que no son directamente
 * respuestas a la pregunta pero pueden ser indicaciones a una respuesta especifica.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="coment_respuesta")
public class ComentRespuesta {

	private int cid;
	private Respuesta resp;
	private Usuario user;
	private Date fecha;
	private String coment;

	@Id
	public int getCid() {
		return cid;
	}
	public void setCid(int value) {
		cid = value;
	}

	@ManyToOne
	@JoinColumn(name="rid")
	public Respuesta getRespuesta() {
		return resp;
	}
	public void setRespuesta(Respuesta value) {
		resp = value;
	}

	@ManyToOne
	@JoinColumn(name="uid")
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

	public String getComentario() {
		return coment;
	}
	public void setComentario(String value) {
		coment = value;
	}

}
