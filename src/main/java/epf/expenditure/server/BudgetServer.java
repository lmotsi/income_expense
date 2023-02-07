package epf.expenditure.server;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

/**
 * This is the class for starting and stopping the server
 */
public class BudgetServer {
    private final Javalin appServer;

    /**
     * Constructor that initializes the server and configuring the pages and
     * routes that the server will be using
     */
    public BudgetServer() {
        JavalinThymeleaf.configure(templateEngine());
        this.appServer = Javalin.create(config -> {
            config.addStaticFiles("/html", Location.CLASSPATH);
        });
        Routes.configure(this);
    }

    /**
     * Main method. Run this to start the server.
     *
     * @param args command line parameters
     * @throws IOException if server creation fails
     */

    public static void main(String[] args) {
        BudgetServer server = new BudgetServer();
        server.start(5050);
    }

    /**
     * Starts the server on specified port number
     * @param port
     */
    private void start(int port) {
        this.appServer.start(port);
    }

    /**
     * Stops the server
     */
    private void stop() {
        this.appServer.stop();
    }

    /**
     * This creates a temporary static instance of Javalin so you can skip the appServer. prefix
     * before the handlers by grouping the endpoints
     * @param group
     */
    public void routes(EndpointGroup group) {
        appServer.routes(group);
    }


    /**
     * This functions creates a template engine that will access the
     * templates in "/templates/
     * @return generated template engine
     */
    private TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        templateEngine.setTemplateResolver(resolver);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }
}