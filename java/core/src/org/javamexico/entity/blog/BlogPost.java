package org.javamexico.entity.blog;

import java.util.Date;
import org.javamexico.entity.Usuario;

public class BlogPost {

	private int bid;
	private Usuario user;
	private Date fecha;
	private String titulo;
	private String texto;
	private boolean coments; //permite comentarios o no

}
