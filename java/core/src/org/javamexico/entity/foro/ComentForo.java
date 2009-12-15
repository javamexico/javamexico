package org.javamexico.entity.foro;

import java.util.Date;
import org.javamexico.entity.Usuario;

public class ComentForo {

	private int cfid;
	private Foro foro;
	private Usuario autor;
	private ComentForo rt; //para manejar threads de comentarios
	private Date fecha;
	private String coment;

}
