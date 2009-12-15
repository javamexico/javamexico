package org.javamexico.dao;

import org.javamexico.entity.blog.BlogPost;
import org.javamexico.entity.blog.BlogComent;
import org.javamexico.entity.Usuario;

import java.util.Date;
import java.util.Set;

public interface BlogDao {

	public Set<BlogPost> getUserBlog(Usuario user);

	public Set<BlogPost> getBlogsRecientes(Date desde);

	public BlogPost getBlog(int id);

	public Set<BlogComent> getComentarios(BlogPost blog, int pageSize, int page, boolean crono);

}
