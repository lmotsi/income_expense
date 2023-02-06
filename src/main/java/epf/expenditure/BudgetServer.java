package epf.expenditure;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.staticfiles.Location;
import io.javalin.core.security.AccessManager;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;


public class BudgetServer {
    private final Javalin appServer;
    public BudgetServer() {
        JavalinThymeleaf.configure(templateEngine());
        this.appServer = Javalin.create(config -> {
            config.addStaticFiles("/html", Location.CLASSPATH);
            config.accessManager(accessManager());
//            config.sessionHandler(sessionHandler());
        });
        Routes.configure(this);
    }
    public static void main(String[] args) {
        BudgetServer server = new BudgetServer();
        server.start(5050);
    }

    private void start(int port) {
        this.appServer.start(port);
    }

    public void routes(EndpointGroup group) {
        appServer.routes(group);
    }
    private AccessManager accessManager() {
        return (handler, context, set) -> context.redirect("/");
    }

    private TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        templateEngine.setTemplateResolver(resolver);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }
}