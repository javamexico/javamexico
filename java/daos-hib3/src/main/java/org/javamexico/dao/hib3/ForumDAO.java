package org.javamexico.dao.hib3;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javamexico.dao.ForoDao;
import org.javamexico.entity.Usuario;
import org.javamexico.entity.foro.ComentForo;
import org.javamexico.entity.foro.Foro;
import org.javamexico.entity.foro.TagForo;
import org.javamexico.entity.foro.VotoComentForo;
import org.javamexico.entity.foro.VotoForo;
import org.javamexico.util.PrivilegioInsuficienteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/** Implementacion del DAO para el modulo de foros, usando Hibernate 3.
 * 
 * @author Enrique Zamudio
 */
public class ForumDAO implements ForoDao {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	private SessionFactory sfact;
	private int minRepVotaF;
	private int minRepVotaC;

	/** Establece la reputacion minima que debe tener un usuario para dar un voto negativo a un foro. */
	public void setMinRepVotoForo(int value) {
		minRepVotaF = value;
	}
	/** Establece la reputacion minima que debe tener un usuario para dar un voto negativo a un comentario de foro. */
	public void setMinRepVotoComent(int value) {
		minRepVotaC = value;
	}

	@Required
	public void setSessionFactory(SessionFactory value) {
		sfact = value;
	}
	public SessionFactory getSessionFactory() {
		return sfact;
	}

	public ComentForo addComment(String coment, ComentForo parent, Usuario autor) {
		Session sess = sfact.getCurrentSession();
		ComentForo cf = new ComentForo();
		cf.setForo(parent.getForo());
		cf.setInReplyTo(parent);
		cf.setAutor(autor);
		cf.setFecha(new Date());
		cf.setComentario(coment);
		sess.save(cf);
		sess.flush();
		sess.refresh(parent);
		parent.getRespuestas().size();
		return cf;
	}

	public ComentForo addComment(String coment, Foro foro, Usuario autor) {
		Session sess = sfact.getCurrentSession();
		ComentForo cf = new ComentForo();
		cf.setForo(foro);
		cf.setAutor(autor);
		cf.setFecha(new Date());
		cf.setComentario(coment);
		sess.save(cf);
		return cf;
	}

	@Transactional
	public void addTag(String tag, Foro foro) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TagForo> tags = sess.createCriteria(TagForo.class).add(Restrictions.ilike("tag", tag)).setMaxResults(1).list();
		TagForo elTag = null;
		if (tags.size() == 0) {
			elTag = new TagForo();
			elTag.setTag(tag);
			sess.save(elTag);
			sess.flush();
		} else {
			elTag = tags.get(0);
		}
		if (foro.getTags() == null) {
			sess.refresh(foro);
		}
		foro.getTags().add(elTag);
		sess.update(foro);
		foro.getTags().size();
	}

	public void delete(Foro foro) {
		Session sess = sfact.getCurrentSession();
		sess.delete(foro);
	}

	public List<TagForo> findMatchingTags(String parcial) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TagForo> tags = sess.createCriteria(TagForo.class).add(
				Restrictions.ilike("tag", parcial, MatchMode.ANYWHERE)).list();
		return tags;
	}

	public VotoForo findVoto(Usuario user, Foro foro) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<VotoForo> v = sess.createCriteria(VotoForo.class).add(Restrictions.eq("user", user)).add(
				Restrictions.eq("foro", foro)).setMaxResults(1).list();
		return v.size() > 0 ? v.get(0) : null;
	}

	public VotoComentForo findVoto(Usuario user, ComentForo coment) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<VotoComentForo> v = sess.createCriteria(VotoComentForo.class).add(Restrictions.eq("user", user)).add(
				Restrictions.eq("comentario", coment)).setMaxResults(1).list();
		return v.size() > 0 ? v.get(0) : null;
	}

	public List<ComentForo> getComentarios(Foro foro, int pageSize, int page,
			boolean crono) {
		Session sess = sfact.getCurrentSession();
		Order order = crono ? Order.desc("fecha") : Order.desc("votos");
		@SuppressWarnings("unchecked")
		List<ComentForo> coms = sess.createCriteria(ComentForo.class).add(
				Restrictions.eq("foro", foro)).add(Restrictions.isNull("inReplyTo")).setFirstResult(
						(page-1)*pageSize).setMaxResults(page).addOrder(order).list();
		for (ComentForo cf : coms) {
			cf.getRespuestas().size();
		}
		return coms;
	}

	public Set<ComentForo> getRespuestas(ComentForo coment) {
		Session sess = sfact.getCurrentSession();
		sess.refresh(coment);
		coment.getRespuestas().size();
		return coment.getRespuestas();
	}

	public Foro getForo(int id) {
		Session sess = sfact.getCurrentSession();
		Foro f = (Foro)sess.get(Foro.class, id);
		if (f != null) {
			f.getTags().size();
		}
		return f;
	}

	public List<Foro> getForosByUser(Usuario user, boolean published) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Foro> qus = sess.createCriteria(Foro.class).add(
				Restrictions.eq("autor", user)).addOrder(Order.desc("fecha")).list();
		return qus;
	}

	public List<Foro> getForosMasActivos(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Foro> getForosRecientes(int page, int pageSize) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Foro> qus = sess.createCriteria(Foro.class).add(
				Restrictions.gt("status", 0)).addOrder(Order.desc("fecha")
						).setMaxResults(pageSize).setFirstResult((page-1)*pageSize).list();
		return qus;
	}

	public void insert(Foro foro) {
		Session sess = sfact.getCurrentSession();
		if (foro.getFecha() == null) {
			foro.setFecha(new Date());
		}
		sess.save(foro);
	}

	public void update(Foro foro) {
		Session sess = sfact.getCurrentSession();
		if (foro.getFecha() == null) {
			foro.setFecha(new Date());
		}
		sess.update(foro);
	}

	public VotoForo vota(Usuario user, Foro foro, boolean up)
			throws PrivilegioInsuficienteException {
		if (!up && user.getReputacion() < minRepVotaF) {
			throw new PrivilegioInsuficienteException();
		}
		Session sess = sfact.getCurrentSession();
		//Buscamos el voto a ver si ya se hizo
		VotoForo voto = findVoto(user, foro);
		if (voto == null) {
			//Si no existe lo creamos
			voto = new VotoForo();
			voto.setFecha(new Date());
			voto.setForo(foro);
			voto.setUp(up);
			voto.setUser(user);
			sess.save(voto);
		} else if (voto.isUp() != up) {
			//Si ya existe pero quieren cambio, se actualiza
			voto.setFecha(new Date());
			voto.setUp(up);
			sess.update(voto);
		}
		return voto;
	}

	public VotoComentForo vota(Usuario user, ComentForo coment, boolean up)
			throws PrivilegioInsuficienteException {
		if (!up && user.getReputacion() < minRepVotaC) {
			throw new PrivilegioInsuficienteException();
		}
		Session sess = sfact.getCurrentSession();
		VotoComentForo voto = findVoto(user, coment);
		if (voto == null) {
			voto = new VotoComentForo();
			voto.setFecha(new Date());
			voto.setComentario(coment);
			voto.setUp(up);
			voto.setUser(user);
			sess.save(voto);
		} else if (voto.isUp() != up) {
			voto.setUp(up);
			voto.setFecha(new Date());
			sess.update(voto);
		}
		return voto;
	}

}
