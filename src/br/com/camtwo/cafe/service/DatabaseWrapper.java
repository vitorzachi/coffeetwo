package br.com.camtwo.cafe.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.camtwo.cafe.listener.RequestContext;

import com.db4o.ObjectContainer;

@ManagedBean
@RequestScoped
public final class DatabaseWrapper implements Serializable {
	private static final long serialVersionUID = 3488645174256299470L;
	private ObjectContainer container;

	protected ObjectContainer getDatabase() {
		if (container == null) {
			container = RequestContext.getServer().openClient();
		}

		return container;
	}
}
