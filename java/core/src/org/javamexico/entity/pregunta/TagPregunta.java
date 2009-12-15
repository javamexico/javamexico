package org.javamexico.entity.pregunta;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/** Representa un tag que el autor de una pregunta le pone, para indicar los temas
 * relacionados.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="tag_pregunta")
public class TagPregunta implements Comparable<TagPregunta> {

	private int tid;
	private Pregunta pregunta;
	private String tag;

	@Id
	public int getTid() {
		return tid;
	}
	public void setTid(int value) {
		tid = value;
	}

	@ManyToOne
	@JoinColumn(name="pid", referencedColumnName="pid")
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta value) {
		pregunta = value;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String value) {
		tag = value;
	}

	public int compareTo(TagPregunta o) {
		if (o == null) {
			return 1;
		}
		if (tag == null) {
			return o.getTag() == null ? 0 : -1;
		}
		return tag.compareTo(o.getTag());
	}

}
