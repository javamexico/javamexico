package org.javamexico.entity.blog;

import java.util.Date;
import org.javamexico.entity.Usuario;

public class BlogComent {

	private int cid;
	private BlogPost blog;
	private Usuario user;
	private BlogComent rt;
	private Date fecha;
	private String coment;

}
