package org.javamexico.entity.pregunta;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.javamexico.entity.Usuario;

/** Una pregunta puede tener comentarios hechos por varios usuarios, que pueden ser solicitudes de
 * aclaracion y otro tipo de contenido que no es directamente una respuesta.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="coment_pregunta")
public class ComentPregunta {

	private int cid;
	private Pregunta pregunta;
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

	public String getComentario() {
		return coment;
	}
	public void setComentario(String value) {
		coment = value;
	}

}
