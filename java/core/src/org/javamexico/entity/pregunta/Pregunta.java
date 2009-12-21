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
package org.javamexico.entity.pregunta;

import org.javamexico.entity.Usuario;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

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
	private String titulo;
	private String pregunta;
	private Date fechaP; //la fecha en que se hizo la pregunta
	private Date fechaR; //la respuesta en que fue seleccionada la respuesta
	private Set<TagPregunta> tags;
	private Set<ComentPregunta> coments;
	private Set<Respuesta> resps;

	@Id
	@SequenceGenerator(name="pk", sequenceName="pregunta_pid_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk")
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

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String value) {
		titulo = value;
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

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tag_pregunta_join")
	public Set<TagPregunta> getTags() {
		return tags;
	}
	public void setTags(Set<TagPregunta> value) {
		tags = value;
	}

	@OneToMany(mappedBy="pid")
	@OrderBy("fecha")
	public Set<Respuesta> getRespuestas() {
		return resps;
	}
	public void setRespuestas(Set<Respuesta> value) {
		resps = value;
	}

	@OneToMany(mappedBy="pid")
	@OrderBy("fecha")
	public Set<ComentPregunta> getComentarios() {
		return coments;
	}
	public void setComentarios(Set<ComentPregunta> value) {
		coments = value;
	}

	public int compareTo(Pregunta o) {
		if (o == null) {
			return 1;
		}
		return o.getPid() > pid ? -1 : o.getPid() < pid ? 1 : 0;
	}

}
