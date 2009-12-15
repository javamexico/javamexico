package org.javamexico.entity.pregunta;

import org.javamexico.entity.Usuario;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/** Representa una pregunta que un usuario hace en el sistema.
 * 
 * @author Enrique Zamudio
 */
@Entity
public class Pregunta implements Comparable<Pregunta> {

	private int pid;
	private int status; //abierta, contestada, etc
	private Usuario autor;
	private Respuesta resp; //cuando ya fue seleccionada una respuesta por el usuario que hizo la pregunta original
	private String pregunta;
	private Date fechaP; //la fecha en que se hizo la pregunta
	private Date fechaR; //la respuesta en que fue seleccionada la respuesta
	private Set<TagPregunta> tags;

	@Id
	public int getPid() {
		return pid;
	}
	public void setPid(int value) {
		pid = value;
	}

	@ManyToOne
	@JoinColumn(name="uid", referencedColumnName="uid")
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario value) {
		autor = value;
	}

	public Date getFechaPregunta() {
		return fechaP;
	}
	public void setFechaPregunta(Date value) {
		fechaP = value;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int value) {
		status = value;
	}

	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String value) {
		pregunta = value;
	}

	public Date getFechaRespuesta() {
		return fechaR;
	}
	public void setFechaRespuesta(Date value) {
		fechaR = value;
	}

	@OneToOne
	@JoinColumn(name="resp_sel", referencedColumnName="rid")
	public Respuesta getRespuestaElegida() {
		return resp;
	}
	public void setRespuestaElegida(Respuesta value) {
		resp = value;
	}

	@OneToMany
	public Set<TagPregunta> getTags() {
		return tags;
	}
	public void setTags(Set<TagPregunta> value) {
		tags = value;
	}

	public int compareTo(Pregunta o) {
		if (o == null) {
			return 1;
		}
		return o.getPid() > pid ? -1 : o.getPid() < pid ? 1 : 0;
	}

}
