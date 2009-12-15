package org.javamexico.test.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.javamexico.dao.PreguntaDao;
import org.javamexico.dao.UserDao;
import org.javamexico.entity.Usuario;
import org.javamexico.entity.pregunta.Pregunta;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/org/javamexico/test/dao/spring.xml"})
public class TestPreguntaDao {

	@Resource(name="userDao")
	private UserDao udao;
	@Resource(name="preguntaDao")
	private PreguntaDao qdao;

	@Test
	public void testPreguntas() {
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
	public void testUsers() {
		Set<Usuario> all = udao.getAllUsers();
		for (Usuario u : all) {
			System.out.printf("Usuario %s nombre %s id %d alta %s%n",
					u.getUsername(), u.getNombre(), u.getUid(), u.getFechaAlta());
			Assert.assertNotNull(udao.validaLogin(u.getUsername(), "pass"));
		}
		Assert.assertNull(udao.validaLogin("alguien-que-no-existe", "no-existe"));
	}

}
