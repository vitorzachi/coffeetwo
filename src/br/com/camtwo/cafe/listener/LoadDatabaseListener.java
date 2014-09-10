package br.com.camtwo.cafe.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.camtwo.cafe.service.Database;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;

@WebListener
public class LoadDatabaseListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ObjectServer server = (ObjectServer) event.getServletContext()
				.getAttribute(Database.DATABASE_SERVER_ATTR_NAME);

		if (server != null)
			server.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
	    String file = new StringBuilder(System.getProperty("user.home")).append("/")
	                .append(Database.DATABASE_SERVER_NAME).toString();
	    
		ObjectServer server = Db4oClientServer.openServer(file, 0);
		
		event.getServletContext().setAttribute(Database.DATABASE_SERVER_ATTR_NAME, server);
	}

}
