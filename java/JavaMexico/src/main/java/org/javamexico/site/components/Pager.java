package org.javamexico.site.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/** Componente que implementa paginacion, por medio de PageLinks con contexto.
 * 
 * @author Enrique Zamudio
 */
public class Pager {

	//@Property @Parameter(required=false, allowNull=true, defaultPrefix=BindingConstants.PROP)
	//private Object context;
	@Property @Parameter(required=true, allowNull=false, defaultPrefix=BindingConstants.PROP)
	private int page;
	@Property @Parameter(required=false, allowNull=false, defaultPrefix=BindingConstants.PROP)
	private boolean ultima;
	@Inject private ComponentResources resources;

	public String getPageName() {
		return resources.getPageName();
	}

	public boolean isSegunda() {
		return page > 1;
	}

	public int getSiguiente() {
		return page + 1;
	}
	public int getPrevia() {
		return page - 1;
	}

}
