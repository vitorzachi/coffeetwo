package br.com.camtwo.cafe.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import br.com.camtwo.cafe.service.Database;

import com.db4o.ObjectServer;

@WebListener
public class RequestContextListener implements ServletRequestListener {
   
    public void requestDestroyed(ServletRequestEvent event) {
    }

	
    public void requestInitialized(ServletRequestEvent event) {
        ObjectServer server = (ObjectServer) event.getServletContext().getAttribute(Database.DATABASE_SERVER_ATTR_NAME);
        RequestContext.setServer(server);;
    }
	
}
