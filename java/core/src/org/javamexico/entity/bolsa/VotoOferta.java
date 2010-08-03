package org.javamexico.entity.bolsa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.javamexico.entity.Usuario;

@Entity(name="chamba_voto_oferta")
public class VotoOferta {

	private int vid;
	private Oferta oferta;
	private Usuario usuario;

	@Id
	@SequenceGenerator(name="pk", sequenceName="chamba_voto_oferta_vid_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk")
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}

	@ManyToOne
	@JoinColumn(name="ofid")
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	@ManyToOne
	@JoinColumn(name="uid")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
