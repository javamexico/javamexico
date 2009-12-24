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
package org.javamexico.dao.hib3;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javamexico.dao.PreguntaDao;
import org.javamexico.entity.Usuario;
import org.javamexico.entity.pregunta.ComentPregunta;
import org.javamexico.entity.pregunta.ComentRespuesta;
import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.entity.pregunta.Respuesta;
import org.javamexico.entity.pregunta.TagPregunta;
import org.javamexico.entity.pregunta.VotoPregunta;
import org.javamexico.entity.pregunta.VotoRespuesta;
import org.javamexico.util.PrivilegioInsuficienteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/** Implementacion del DAO de preguntas, usando Hibernate 3 y el soporte de Spring.
 * 
 * @author Enrique Zamudio
 */
public class QuestionDAO implements PreguntaDao {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	private SessionFactory sfact;

	public void setSessionFactory(SessionFactory value) {
		sfact = value;
	}
	public SessionFactory getSessionFactory() {
		return sfact;
	}

	public Pregunta getPregunta(int id) {
		Session sess = sfact.getCurrentSession();
		Pregunta p = (Pregunta)sess.get(Pregunta.class, id);
		if (p != null) {
			p.getTags().size();
			p.getComentarios().size();
			p.getRespuestas().size();
		}
		return p;
	}

	public List<Pregunta> getPreguntasConTag(TagPregunta tag) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Pregunta> qus = sess.createCriteria(Pregunta.class).add(
				Restrictions.in("tags", new Object[]{ tag })).list();
		return qus;
	}

	public List<Pregunta> getPreguntasConTag(String tag) {
		//TODO implementar
		return null;
	}

	public List<Pregunta> getPreguntasConTags(Set<TagPregunta> tags) {
		//TODO implementar
		return null;
	}

	public List<Pregunta> getPreguntasMasVotadas(int limit) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Pregunta> qus = sess.createCriteria(Pregunta.class).add(
				Restrictions.gt("status", 0)).addOrder(Order.desc("votos")).setMaxResults(limit).list();
		return qus;
	}

	public List<Pregunta> getPreguntasRecientes(Date desde) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Pregunta> qus = sess.createCriteria(Pregunta.class).add(
				Restrictions.gt("status", 0)).add(
				Restrictions.ge("fechaPregunta", desde)).addOrder(Order.desc("fechaPregunta")).list();
		return qus;
	}

	public List<Pregunta> getPreguntasSinResolver(int limit) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Pregunta> qus = sess.createCriteria(Pregunta.class).add(
				Restrictions.eq("status", 1)).addOrder(Order.desc("fechaPregunta")).list();
		return qus;
	}

	public List<Pregunta> getPreguntasSinResponder(int limit) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Pregunta> qus = sess.createCriteria(Pregunta.class).add(
				Restrictions.sizeEq("respuestas", 0)).addOrder(Order.desc("fechaPregunta")).setMaxResults(limit).list();
		return qus;
	}

	public List<Pregunta> getPreguntasUsuario(Usuario user) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Pregunta> qus = sess.createCriteria(Pregunta.class).add(
				Restrictions.eq("autor", user)).addOrder(Order.desc("fechaPregunta")).list();
		return qus;
	}

	public List<Respuesta> getRespuestas(Pregunta q, int pageSize, int page,
			boolean crono) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Respuesta> resps = sess.createCriteria(Respuesta.class).add(
				Restrictions.eq("pregunta", q)).setMaxResults(pageSize).setFirstResult((page-1)*pageSize).addOrder(
						crono ? Order.desc("fecha") : Order.desc("votos")).list();
		return resps;
	}

	public List<TagPregunta> getTagsPopulares(int limit) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TagPregunta> tags = sess.createCriteria(TagPregunta.class).addOrder(
				Order.desc("count")).setMaxResults(limit).list();
		return tags;
	}

	public VotoPregunta vota(Usuario user, Pregunta pregunta, boolean up) throws PrivilegioInsuficienteException {
		if (!up && user.getReputacion() < 10) {
			throw new PrivilegioInsuficienteException();
		}
		Session sess = sfact.getCurrentSession();
		VotoPregunta v = findVoto(user, pregunta);
		if (v == null) {
			v = new VotoPregunta();
			v.setFecha(new Date());
			v.setPregunta(pregunta);
			v.setUp(up);
			v.setUser(user);
			sess.save(v);
		} else if (v.isUp() != up) {
			v.setUp(up);
			v.setFecha(new Date());
			sess.update(v);
		}
		return v;
	}

	public VotoRespuesta vota(Usuario user, Respuesta resp, boolean up) throws PrivilegioInsuficienteException {
		if (!up && user.getReputacion() < 10) {
			throw new PrivilegioInsuficienteException();
		}
		Session sess = sfact.getCurrentSession();
		VotoRespuesta v = findVoto(user, resp);
		if (v == null) {
			v = new VotoRespuesta();
			v.setFecha(new Date());
			v.setRespuesta(resp);
			v.setUp(up);
			v.setUser(user);
			sess.save(v);
		} else if (v.isUp() != up) {
			v.setUp(up);
			v.setFecha(new Date());
			sess.update(v);
		}
		return v;
	}

	public VotoPregunta findVoto(Usuario user, Pregunta pregunta) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<VotoPregunta> v = sess.createCriteria(VotoPregunta.class).add(Restrictions.eq("user", user)).add(
				Restrictions.eq("pregunta", pregunta)).setMaxResults(1).list();
		return v.size() > 0 ? v.get(0) : null;
	}

	public VotoRespuesta findVoto(Usuario user, Respuesta respuesta) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<VotoRespuesta> v = sess.createCriteria(VotoRespuesta.class).add(Restrictions.eq("user", user)).add(
				Restrictions.eq("respuesta", respuesta)).setMaxResults(1).list();
		return v.size() > 0 ? v.get(0) : null;
	}

	public void insert(Pregunta p) {
		Session sess = sfact.getCurrentSession();
		if (p.getFechaPregunta() == null) {
			p.setFechaPregunta(new Date());
		}
		sess.save(p);
	}

	public void update(Pregunta p) {
		Session sess = sfact.getCurrentSession();
		if (p.getFechaPregunta() == null) {
			p.setFechaPregunta(new Date());
		}
		sess.update(p);
	}

	@Transactional
	public void delete(Pregunta p) {
		Session sess = sfact.getCurrentSession();
		sess.delete(p);
	}

	public Respuesta addRespuesta(String resp, Pregunta p, Usuario autor) {
		Session sess = sfact.getCurrentSession();
		Respuesta r = new Respuesta();
		r.setFecha(new Date());
		r.setAutor(autor);
		r.setPregunta(p);
		r.setRespuesta(resp);
		sess.save(r);
		sess.flush();
		sess.refresh(p);
		p.getRespuestas().size();
		return r;
	}

	public ComentPregunta addComentario(String c, Pregunta p, Usuario autor) {
		Session sess = sfact.getCurrentSession();
		ComentPregunta cp = new ComentPregunta();
		cp.setAutor(autor);
		cp.setComentario(c);
		cp.setFecha(new Date());
		cp.setPregunta(p);
		sess.save(cp);
		sess.flush();
		sess.refresh(p);
		p.getComentarios().size();
		return cp;
	}

	public ComentRespuesta addComentario(String c, Respuesta r, Usuario autor) {
		Session sess = sfact.getCurrentSession();
		ComentRespuesta cr = new ComentRespuesta();
		cr.setRespuesta(r);
		cr.setAutor(autor);
		cr.setComentario(c);
		cr.setFecha(new Date());
		sess.save(cr);
		sess.flush();
		sess.refresh(r);
		r.getComentarios().size();
		return cr;
	}

	@Transactional
	public void addTag(String tag, Pregunta p) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TagPregunta> tags = sess.createCriteria(TagPregunta.class).add(
				Restrictions.ilike("tag", tag)).setMaxResults(1).list();
		TagPregunta utag = null;
		if (tags.size() == 0) {
			utag = new TagPregunta();
			utag.setTag(tag);
			sess.save(utag);
			sess.flush();
		} else {
			utag = tags.get(0);
		}
		if (p.getTags() == null) {
			sess.refresh(p);
		}
		utag.setCount(utag.getCount() + 1);
		sess.update(utag);
		p.getTags().add(utag);
		sess.update(p);
		p.getTags().size();
	}

	public List<TagPregunta> findMatchingTags(String parcial) {
		Session sess = sfact.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TagPregunta> tags = sess.createCriteria(TagPregunta.class).add(
				Restrictions.ilike("tag", parcial, MatchMode.ANYWHERE)).list();
		return tags;
	}

}
