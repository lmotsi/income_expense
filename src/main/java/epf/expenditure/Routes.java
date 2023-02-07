package epf.expenditure;

import io.javalin.http.Handler;
import org.thymeleaf.context.IContext;

import java.util.Objects;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;

public class Routes {

    public static void configure(BudgetServer budgetServer) {
        budgetServer.routes(() -> {
            post("/submit.action",  Routes.submit);
            get("/table",Routes.viewTable);
        });
    }

    public static final Handler viewTable = context -> {
        System.out.println("I ");
        context.render("table.html");
    };
    public static final Handler submit = context -> {
//        String email = context.formParamAsClass("email", String.class)
//                .check(Objects::nonNull, "Email is required")
//                .get();
        context.redirect("/table");
    };

}
