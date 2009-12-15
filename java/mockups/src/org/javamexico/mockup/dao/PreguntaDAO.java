package org.javamexico.mockup.dao;

import org.javamexico.dao.PreguntaDao;
import org.javamexico.dao.UserDao;
import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.entity.pregunta.TagPregunta;
import org.javamexico.entity.pregunta.Respuesta;
import org.javamexico.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/** Un DAO falso de preguntas, para pruebas de GUI y otras cosas.
 * 
 * @author Enrique Zamudio
 */
public class PreguntaDAO implements PreguntaDao {

	private Set<Pregunta> pregs;
	private UserDao udao;
	private Random rng = new Random(System.currentTimeMillis());

	@PostConstruct
	public void init() {
		java.util.ArrayList<Usuario> users = new java.util.ArrayList<Usuario>();
		users.addAll(udao.getAllUsers());
		TreeSet<TagPregunta> tags = new TreeSet<TagPregunta>();
		pregs = new TreeSet<Pregunta>();
		Pregunta p = new Pregunta();
		p.setPid(1);
		p.setStatus(1);
		p.setPregunta("Primera pregunta; algo acerca de servlets con JSF");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (86400200L*5)));
		TagPregunta t = new TagPregunta();
		t.setTid(1);
		t.setPregunta(p);
		t.setTag("jsf");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(2);
		t.setPregunta(p);
		t.setTag("servlet");
		tags.add(t);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(2);
		p.setStatus(1);
		p.setPregunta("Segunda pregunta, algo acerca de Hibernate");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (86006000L*7)));
		tags = new TreeSet<TagPregunta>();
		t = new TagPregunta();
		t.setTid(3);
		t.setPregunta(p);
		t.setTag("hibernate");
		tags.add(t);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(3);
		p.setStatus(1);
		p.setPregunta("Esta es la tercera pregunta, que trata de algo con Spring");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (86900500L*2)));
		tags = new TreeSet<TagPregunta>();
		t = new TagPregunta();
		t.setTid(4);
		t.setPregunta(p);
		t.setTag("spring");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(5);
		t.setPregunta(p);
		t.setTag("jdbc");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(6);
		t.setPregunta(p);
		t.setTag("spring-jdbc");
		tags.add(t);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(4);
		p.setStatus(2);
		p.setPregunta("Pregunta numero cuatro, una duda de Glassfish");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (89300300L*15)));
		p.setFechaRespuesta(new Date(System.currentTimeMillis() - (87100000L*2)));
		tags = new TreeSet<TagPregunta>();
		t = new TagPregunta();
		t.setTid(7);
		t.setPregunta(p);
		t.setTag("jee");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(8);
		t.setPregunta(p);
		t.setTag("glassfish");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(9);
		t.setPregunta(p);
		t.setTag("jndi");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(10);
		t.setPregunta(p);
		t.setTag("ejb");
		tags.add(t);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(5);
		p.setStatus(1);
		p.setPregunta("La quinta pregunta es acerca de hilos");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (82900800L*3)));
		tags = new TreeSet<TagPregunta>();
		t = new TagPregunta();
		t.setTid(11);
		t.setPregunta(p);
		t.setTag("threads");
		tags.add(t);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(6);
		p.setStatus(1);
		p.setPregunta("Pregunta Seis, algo de base de datos");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (81200000L*9)));
		tags = new TreeSet<TagPregunta>();
		t = new TagPregunta();
		t.setTid(12);
		t.setPregunta(p);
		t.setTag("jdbc");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(13);
		t.setPregunta(p);
		t.setTag("mysql");
		tags.add(t);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(7);
		p.setStatus(1);
		p.setPregunta("Aqui esta la pregunta numero siete, que trata de programacion de aplicaciones de escritorio");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (84350030L*11)));
		tags = new TreeSet<TagPregunta>();
		t = new TagPregunta();
		t.setTid(14);
		t.setPregunta(p);
		t.setTag("swing");
		tags.add(t);
		t = new TagPregunta();
		t.setTid(15);
		t.setPregunta(p);
		t.setTag("j2se");
		tags.add(t);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);
	}

	@Resource(name="userDao")
	public void setUserDao(UserDao value) {
		udao = value;
	}

	public List<Pregunta> getPreguntasRecientes(Date desde) {
		ArrayList<Pregunta> rv = new ArrayList<Pregunta>();
		for (Pregunta p : pregs) {
			if (p.getFechaPregunta().compareTo(desde) > 0) {
				rv.add(p);
			}
		}
		return rv;
	}

	public List<Pregunta> getPreguntasUsuario(Usuario user) {
		ArrayList<Pregunta> rv = new ArrayList<Pregunta>();
		for (Pregunta p : pregs) {
			if (p.getAutor().getUid() == user.getUid()) {
				rv.add(p);
			}
		}
		return rv;
	}

	public List<Pregunta> getPreguntasConTag(TagPregunta tag) {
		return getPreguntasConTags(java.util.Collections.singleton(tag));
	}

	public List<Pregunta> getPreguntasConTags(Set<TagPregunta> tags) {
		ArrayList<Pregunta> rv = new ArrayList<Pregunta>();
		for (Pregunta p : pregs) {
			for (TagPregunta t : p.getTags()) {
				for (TagPregunta t2 : tags) {
					if (t.getTid() == t2.getTid()) {
						rv.add(p);
					}
				}
			}
		}
		return rv;
	}

	public Set<TagPregunta> getTagsPopulares(int limit) {
		return null;
	}

	public Pregunta getPregunta(int id) {
		for (Pregunta p : pregs) {
			if (p.getPid() == id) {
				return p;
			}
		}
		return null;
	}

	public List<Respuesta> getRespuestas(Pregunta q, int pageSize, int page, boolean crono) {
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		users.addAll(udao.getAllUsers());
		ArrayList<Respuesta> rv = new ArrayList<Respuesta>();
		Respuesta resp = new Respuesta();
		resp.setRid(1);
		resp.setPregunta(q);
		resp.setAutor(users.get(rng.nextInt(users.size())));
		resp.setFecha(new Date(q.getFechaPregunta().getTime() + 43500500l));
		resp.setRespuesta("Una respuesta");
		rv.add(resp);

		resp = new Respuesta();
		resp.setRid(2);
		resp.setPregunta(q);
		resp.setAutor(users.get(rng.nextInt(users.size())));
		resp.setFecha(new Date(q.getFechaPregunta().getTime() + 49500500l));
		resp.setRespuesta("Otra respuesta");
		rv.add(resp);

		resp = new Respuesta();
		resp.setRid(3);
		resp.setPregunta(q);
		resp.setAutor(users.get(rng.nextInt(users.size())));
		resp.setFecha(new Date(q.getFechaPregunta().getTime() + 52500500l));
		resp.setRespuesta("Una respuesta mas");
		rv.add(resp);

		resp = new Respuesta();
		resp.setRid(4);
		resp.setPregunta(q);
		resp.setAutor(users.get(rng.nextInt(users.size())));
		resp.setFecha(new Date(q.getFechaPregunta().getTime() + 95250704l));
		resp.setRespuesta("Cuarta respuesta");
		rv.add(resp);

		if (q.getStatus() == 2) {
			if (q.getRespuestaElegida() == null) {
				//La seleccionada
				resp = new Respuesta();
				resp.setRid(5);
				resp.setPregunta(q);
				resp.setAutor(users.get(rng.nextInt(users.size())));
				resp.setFecha(new Date(q.getFechaRespuesta().getTime() + 28456129l));
				resp.setRespuesta("Esta fue la respuesta elegida por el que hizo la pregunta");
				q.setRespuestaElegida(resp);
			}
			rv.add(q.getRespuestaElegida());
		}
		return rv;
	}

	public List<Pregunta> getPreguntasMasVotadas(int limit) {
		ArrayList<Pregunta> rv = new ArrayList<Pregunta>();
		rv.addAll(pregs);
		//TODO ordenar por votos
		while (rv.size() > limit) {
			rv.remove(rv.size() - 1);
		}
		return rv;
	}

	public List<Pregunta> getPreguntasSinResolver(int limit) {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	public List<Pregunta> getPreguntasSinResponder(int limit) {
		ArrayList<Pregunta> rv = new ArrayList<Pregunta>();
		for (Pregunta q : pregs) {
			if (q.getRespuestaElegida() == null) {
				rv.add(q);
			}
		}
		while (rv.size() > limit) {
			rv.remove(rv.size() - 1);
		}
		return rv;
	}

}
