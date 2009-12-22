package org.javamexico.test.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.javamexico.dao.PreguntaDao;
import org.javamexico.dao.UserDao;
import org.javamexico.entity.Usuario;
import org.javamexico.entity.pregunta.Pregunta;
import org.javamexico.entity.pregunta.Respuesta;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** Conjunto de pruebas unitarias para el modulo de preguntas (especificamente DAO).
 * 
 * @author Enrique Zamudio
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/org/javamexico/test/dao/spring.xml", "/org/javamexico/test/dao/postgres.xml"})
public class TestPreguntas {

	@Resource(name="usuarioDao")
	private UserDao udao;
	@Resource(name="preguntaDao")
	private PreguntaDao qdao;

	@Test
	public void testPreguntas1() {
		//Preguntas por usuario
		long oldest = System.currentTimeMillis();
		for (Usuario u : udao.getAllUsers()) {
			List<Pregunta> pregs = qdao.getPreguntasUsuario(u);
			System.out.printf("Usuario %s tiene %d preguntas%n", u.getUsername(), pregs.size());
			//Aqui vamos obteniendo la pregunta mas vieja
			for (Pregunta q : pregs) {
				if (q.getFechaPregunta().getTime() < oldest) {
					oldest = q.getFechaPregunta().getTime();
				}
			}
		}
		//Vamos a obtener todas excepto la mas vieja
		List<Pregunta> pregs = qdao.getPreguntasRecientes(new Date(oldest+1000));
		System.out.printf("Probando pregunta mas vieja (%s) solamente hubo %d%n", new Date(oldest), pregs.size());
		for (Pregunta q : pregs) {
			Assert.assertTrue(q.getFechaPregunta().getTime() > oldest);
		}
	}

	@Test
	public void testPreguntas2() {
		List<Usuario> users = udao.getAllUsers();
		Usuario u1 = users.get(0);
		Usuario u2 = users.get(users.size() - 1);
		System.out.println("Creando una pregunta");
		Pregunta p = new Pregunta();
		p.setAutor(u1);
		p.setPregunta("Una pregunta de prueba, creada de manera automatica");
		p.setStatus(1);
		p.setTitulo("Titulo de pregunta de prueba");
		qdao.insert(p);
		qdao.addTag("jee", p);
		qdao.addTag("spring", p);
		qdao.addTag("hibernate", p);
		qdao.addTag("servlet", p);
		qdao.addTag("jsp", p);
		qdao.addTag("ant", p);
		Respuesta r = new Respuesta();
		r.setAutor(u2);
		r.setRespuesta("Esta es una respuesta");
		qdao.addRespuesta(r, p);
		qdao.addComentario("Un comentario a la pregunta", p, u2);
		qdao.addComentario("Un comentario a la respuesta", r, u1);
		if (System.console() != null) {
			System.out.println("Revisa la base de datos y oprime ENTER");
			System.console().readLine();
		}
		System.out.println("Borrando todo");
		qdao.delete(p);
	}

}
