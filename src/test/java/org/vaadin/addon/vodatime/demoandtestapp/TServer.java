package org.vaadin.addon.vodatime.demoandtestapp;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.AbstractUIProvider;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServlet.ServletService;
import com.vaadin.server.VaadinSessionInitializationListener;
import com.vaadin.server.VaadinSessionInitializeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import java.io.File;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class TServer {

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

        final AbstractUIProviderImpl uiprovider = new AbstractUIProviderImpl();

        Server server = new Server();

        final Connector connector = new SelectChannelConnector();

        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        WebAppContext context = new WebAppContext();
        VaadinServlet vaadinServlet = new VaadinServlet() {
            @Override
            public void init(ServletConfig servletConfig) throws ServletException {
                super.init(servletConfig);

            }

            @Override
            protected ServletService createServletService(DeploymentConfiguration deploymentConfiguration) {
                final ServletService createServletService = super.createServletService(deploymentConfiguration);
                createServletService.addVaadinSessionInitializationListener(new VaadinSessionInitializationListener() {
                    @Override
                    public void vaadinSessionInitialized(VaadinSessionInitializeEvent event) throws ServiceException {
                        event.getVaadinSession().addUIProvider(uiprovider);
                    }
                });
                return createServletService;
            }
        };

        ServletHolder servletHolder = new ServletHolder(
                vaadinServlet);
        servletHolder.setInitParameter("widgetset",
                "org.vaadin.addon.vodatime.demoandtestapp.TestWidgetset");

        File file = new File("target/testwebapp");
        context.setWar(file.getPath());
        context.setContextPath("/");

        context.addServlet(servletHolder, "/*");
        server.setHandler(context);
        server.start();
        return server;
    }
}
