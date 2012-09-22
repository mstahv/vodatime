package org.vaadin.addon.vodatime.demoandtestapp;

import java.io.File;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.vaadin.terminal.gwt.server.ApplicationServlet;

public class TestServer {

    private static final int PORT = 9998;

    /**
     * 
     * Test server for the addon.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Server server = startServer(PORT);
    }

    public static Server startServer(int port) throws Exception {
        Server server = new Server();

        final Connector connector = new SelectChannelConnector();

        connector.setPort(port);
        server.setConnectors(new Connector[] { connector });

        WebAppContext context = new WebAppContext();
        ServletHolder servletHolder = new ServletHolder(
                ApplicationServlet.class);
        servletHolder.setInitParameter("widgetset",
                "org.vaadin.addon.vodatime.demoandtestapp.TestWidgetset");
        servletHolder.setInitParameter("application",
                TestApplication.class.getName());

        File file = new File("target/testwebapp");
        context.setWar(file.getPath());
        context.setContextPath("/");

        context.addServlet(servletHolder, "/*");
        server.setHandler(context);
        server.start();
        return server;
    }

}
