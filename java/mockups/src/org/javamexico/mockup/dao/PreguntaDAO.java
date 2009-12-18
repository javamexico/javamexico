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

	private List<TagPregunta> tags;
	private Set<Pregunta> pregs;
	private UserDao udao;
	private Random rng = new Random(System.currentTimeMillis());

	@PostConstruct
	public void init() {
		tags = new ArrayList<TagPregunta>();
		//Crear algunos tags
		TagPregunta t1 = new TagPregunta();
		t1.setTid(1);
		t1.setTag("jsf");
		TagPregunta t2 = new TagPregunta();
		t2.setTid(2);
		t2.setTag("servlet");
		TagPregunta t3 = new TagPregunta();
		t3.setTid(3);
		t3.setTag("hibernate");
		TagPregunta t4 = new TagPregunta();
		t4.setTid(4);
		t4.setTag("spring");
		TagPregunta t5 = new TagPregunta();
		t5.setTid(5);
		t5.setTag("jdbc");
		TagPregunta t6 = new TagPregunta();
		t6.setTid(6);
		t6.setTag("jee");
		TagPregunta t7 = new TagPregunta();
		t7.setTid(7);
		t7.setTag("glassfish");
		TagPregunta t8 = new TagPregunta();
		t8.setTid(8);
		t8.setTag("threads");
		TagPregunta t9 = new TagPregunta();
		t9.setTid(9);
		t9.setTag("swing");
		tags.add(t1);
		tags.add(t2);
		tags.add(t3);
		tags.add(t4);
		tags.add(t5);
		tags.add(t6);
		tags.add(t7);
		tags.add(t8);
		tags.add(t9);
		Collections.sort(tags, new TagPregunta.CountComparator());

		ArrayList<Usuario> users = new ArrayList<Usuario>();
		users.addAll(udao.getAllUsers());
		TreeSet<TagPregunta> tags = new TreeSet<TagPregunta>();
		pregs = new TreeSet<Pregunta>();
		Pregunta p = new Pregunta();
		p.setPid(1);
		p.setStatus(1);
		p.setTitulo("Algo de servlets");
		p.setPregunta("Primera pregunta; algo acerca de servlets con JSF, tal vez especificamente con ICEfaces");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (86400200L*5)));
		tags.add(t1); countTag(t1);
		tags.add(t2); countTag(t2);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(2);
		p.setStatus(1);
		p.setTitulo("Hibernate");
		p.setPregunta("Segunda pregunta, algo acerca de Hibernate para un primerizo");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (86006000L*7)));
		tags = new TreeSet<TagPregunta>();
		tags.add(t3); countTag(t3);
		tags.add(t6); countTag(t6);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(3);
		p.setStatus(1);
		p.setTitulo("Duda de Spring");
		p.setPregunta("Esta es la tercera pregunta, que trata de algo con Spring");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (86900500L*2)));
		tags = new TreeSet<TagPregunta>();
		tags.add(t4); countTag(t4);
		tags.add(t5); countTag(t5);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(4);
		p.setStatus(2);
		p.setTitulo("No puedo levantar Glassfish");
		p.setPregunta("Pregunta numero cuatro, una duda de como instalar bien Glassfish en Windows porque no corre");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (89300300L*15)));
		p.setFechaRespuesta(new Date(System.currentTimeMillis() - (87100000L*2)));
		tags = new TreeSet<TagPregunta>();
		tags.add(t6); countTag(t6);
		tags.add(t7); countTag(t7);
		tags.add(t2); countTag(t2);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(5);
		p.setStatus(1);
		p.setTitulo("Multithreading");
		p.setPregunta("La quinta pregunta es acerca de hilos");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (82900800L*3)));
		tags = new TreeSet<TagPregunta>();
		tags.add(t8); countTag(t8);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(6);
		p.setStatus(1);
		p.setTitulo("MySQL con Java");
		p.setPregunta("Pregunta Seis, algo de base de datos, especificamente como conectarse a MySQL");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (81200000L*9)));
		tags = new TreeSet<TagPregunta>();
		tags.add(t5); countTag(t5);
		tags.add(t6); countTag(t6);
		p.setTags(tags);
		p.setAutor(users.get(rng.nextInt(users.size())));
		pregs.add(p);

		p = new Pregunta();
		p.setPid(7);
		p.setStatus(1);
		p.setTitulo("Cliente Swing");
		p.setPregunta("Aqui esta la pregunta numero siete, que trata de programacion de aplicaciones de escritorio");
		p.setFechaPregunta(new Date(System.currentTimeMillis() - (84350030L*11)));
		tags = new TreeSet<TagPregunta>();
		tags.add(t9); countTag(t9);
		tags.add(t4); countTag(t4);
		tags.add(t5); countTag(t5);
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

	public List<Pregunta> getPreguntasConTag(String tag) {
		ArrayList<Pregunta> rv = new ArrayList<Pregunta>();
		for (Pregunta p : pregs) {
			for (TagPregunta t : p.getTags()) {
				if (tag.equals(t.getTag())) {
					rv.add(p);
					continue;
				}
			}
		}
		return rv;
	}

	public List<Pregunta> getPreguntasConTags(Set<TagPregunta> tags) {
		ArrayList<Pregunta> rv = new ArrayList<Pregunta>();
		for (Pregunta p : pregs) {
			for (TagPregunta t : p.getTags()) {
				for (TagPregunta t2 : tags) {
					if (t.getTid() == t2.getTid()) {
						rv.add(p);
						continue;
					}
				}
			}
		}
		return rv;
	}

	public Set<TagPregunta> getTagsPopulares(int limit) {
		Set<TagPregunta> rv = new TreeSet<TagPregunta>(new TagPregunta.CountComparator());
		if (limit >= tags.size()) {
			rv.addAll(tags);
		} else {
			for (int i = 0; i < limit; i++) {
				rv.add(tags.get(i));
			}
		}
		return rv;
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
		if (q.getRespuestas() == null) {
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			users.addAll(udao.getAllUsers());
			TreeSet<Respuesta> ts = new TreeSet<Respuesta>();
			Respuesta resp = new Respuesta();
			resp.setRid(1);
			resp.setPregunta(q);
			resp.setAutor(users.get(rng.nextInt(users.size())));
			resp.setFecha(new Date(q.getFechaPregunta().getTime() + 43500500l));
			resp.setRespuesta("Una respuesta");
			ts.add(resp);

			resp = new Respuesta();
			resp.setRid(2);
			resp.setPregunta(q);
			resp.setAutor(users.get(rng.nextInt(users.size())));
			resp.setFecha(new Date(q.getFechaPregunta().getTime() + 49500500l));
			resp.setRespuesta("Otra respuesta");
			ts.add(resp);

			resp = new Respuesta();
			resp.setRid(3);
			resp.setPregunta(q);
			resp.setAutor(users.get(rng.nextInt(users.size())));
			resp.setFecha(new Date(q.getFechaPregunta().getTime() + 52500500l));
			resp.setRespuesta("Una respuesta mas");
			ts.add(resp);

			resp = new Respuesta();
			resp.setRid(4);
			resp.setPregunta(q);
			resp.setAutor(users.get(rng.nextInt(users.size())));
			resp.setFecha(new Date(q.getFechaPregunta().getTime() + 95250704l));
			resp.setRespuesta("Cuarta respuesta");
			ts.add(resp);

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
				ts.add(q.getRespuestaElegida());
			}
			q.setRespuestas(ts);
		}
		ArrayList<Respuesta> resps = new ArrayList<Respuesta>();
		resps.addAll(q.getRespuestas());
		return resps;
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

	private void countTag(TagPregunta t) {
		t.setCount(t.getCount()+1);
	}

}
