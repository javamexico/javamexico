package org.javamexico.site.components;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.javamexico.dao.UserDao;
import org.javamexico.entity.Usuario;
import org.javamexico.site.base.Pagina;
import org.slf4j.Logger;

/** Este componente presenta una liga con cierto texto a los usuarios no registrados
 * y una liga con texto distinto para usuarios registrados; la liga para usuarios no registrados
 * abre una seccion de Login y la liga de usuarios registrados hace visible una zona oculta en la pagina.
 * 
 * Extiende la clase Pagina para tener acceso al usuario en la sesion.
 * 
 * @author Enrique Zamudio
 */
public class LigaUsuario extends Pagina {

	@Inject
	private Logger log;
	@Parameter(required=true, defaultPrefix="literal") @Property
	private String text;
	@Property
	private String uname;
	@Property
	private String passwd;
	@Property
	private String loginErr;
	@InjectComponent
	private Zone innerZone;
	@InjectComponent
	private Zone loginZone;
	@Inject
	@Service("userDao")
	private UserDao udao;

	Object onSuccessFromLogin() {
		log.info("Validando {}/{}", uname, passwd);
		Usuario u = udao.validaLogin(uname, passwd);
		if (u == null) {
			loginErr = "Usuario inexistente, o password invalido.";
			return loginZone.getBody();
		} else {
			//Con esto ya se queda el usuario en la sesion
			setUser(u);
			return innerZone.getBody();
		}
	}

	Object onActionFromLoggedLink() {
		return innerZone.getBody();
	}

	Object onActionFromLoginLink() {
		return loginZone.getBody();
	}

}
