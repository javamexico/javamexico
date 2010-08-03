package org.javamexico.entity.bolsa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/** Los tags que se pueden poner a las ofertas, anuncios, etc.
 * 
 * @author Enrique Zamudio
 */
@Entity(name="chamba_tag")
public class Tag {

	private int tid;
	private String tag;

	@Id
	@SequenceGenerator(name="pk", sequenceName="chamba_tag_tid_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk")
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
