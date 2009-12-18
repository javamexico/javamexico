package org.javamexico.entity.pregunta;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.javamexico.entity.Usuario;

/** Representa una respuesta que un usuario da a una pregunta hecha por otro usuario.
 * 
 * @author Enrique Zamudio
 */
@Entity
public class Respuesta implements Comparable<Respuesta> {

	private int rid;
	private Pregunta pregunta;
	private Usuario user;
	private Date fecha;
	private String respuesta;
	private Set<ComentRespuesta> coments;

	@Id
	public int getRid() {
		return rid;
	}
	public void setRid(int value) {
		rid = value;
	}

	@ManyToOne
	@JoinColumn(name="pid")
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta value) {
		pregunta = value;
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

	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String value) {
		respuesta = value;
	}

	@OneToMany(mappedBy="rid")
	@OrderBy("fecha")
	public Set<ComentRespuesta> getComentarios() {
		return coments;
	}
	public void setComentarios(Set<ComentRespuesta> value) {
		coments = value;
	}

	public int compareTo(Respuesta o) {
		if (o == null) {
			return 1;
		}
		if (fecha == null) {
			return o.getFecha() == null ? 0 : 1;
		}
		return fecha.compareTo(o.getFecha());
	}

}
