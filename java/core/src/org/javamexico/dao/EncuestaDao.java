package org.javamexico.dao;

import org.javamexico.entity.encuesta.Encuesta;
import org.javamexico.entity.encuesta.ComentEncuesta;

import java.util.Date;
import java.util.Set;

public interface EncuestaDao {

	public Set<Encuesta> getEncuestasRecientes(Date desde);

	public Encuesta getEncuesta(Encuesta poll);

	public Set<ComentEncuesta> getComentarios(Encuesta poll, int pageSize, int page, boolean crono);

}
