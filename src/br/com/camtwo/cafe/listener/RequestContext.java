package br.com.camtwo.cafe.listener;

import com.db4o.ObjectServer;

public class RequestContext {
	private static ThreadLocal<ObjectServer> databaseServer = new ThreadLocal<ObjectServer>();

	public static ObjectServer getServer() {
		return databaseServer.get();
	}

	static void setServer(ObjectServer server) {
		databaseServer.set(server);
	}
}
