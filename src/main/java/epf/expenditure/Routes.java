package epf.expenditure;

import io.javalin.http.Handler;
import org.thymeleaf.context.IContext;

import java.util.Map;
import java.util.Objects;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;

public class Routes {

    public static void configure(BudgetServer budgetServer) {
        budgetServer.routes(() -> {
            post("/submit.action",  Routes.submit);
        });
    }

    public static final Handler submit = context -> {
        String name = context.formParamAsClass("name", String.class)
                .check(Objects::nonNull, "Name is required")
                .get();
        String surname = context.formParamAsClass("surname", String.class)
                .check(Objects::nonNull, "Surname is required")
                .get();
        String dateOfBirth = context.formParamAsClass("dateofbirth", String.class)
                .check(Objects::nonNull, "Date of birth is required")
                .get();
        Map<String, Object> formDetails = Map.of("name",name,
                                                 "surname", surname,
                                                 "dateofbirth",dateOfBirth);
        context.render("table.html", formDetails);
    };

}
