package br.com.camtwo.cafe.service;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.db4o.ObjectContainer;

@ManagedBean
@ApplicationScoped
public class Database implements Serializable {
	private static final long serialVersionUID = 1749522650581968140L;
	public static final String DATABASE_SERVER_ATTR_NAME = "br.com.camtwo.database.server";
	public static final String DATABASE_SERVER_NAME = "cafe_camtwo.db4o";
	
	public ObjectContainer get() {
		FacesContext context = FacesContext.getCurrentInstance();
		DatabaseWrapper bean = context.getApplication().evaluateExpressionGet(context, "#{databaseWrapper}", DatabaseWrapper.class);
		
		return bean.getDatabase();
	}
}
