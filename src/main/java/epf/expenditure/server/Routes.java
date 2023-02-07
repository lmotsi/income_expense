package epf.expenditure.server;

import epf.expenditure.model.Record;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStreamReader;
import java.util.*;

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

        List<Record> records = new ArrayList<>();
        UploadedFile file = context.uploadedFile("myfile");
        XSSFWorkbook workbook = new XSSFWorkbook(file.getContent());

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator <Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            String month = cell.getStringCellValue();
            cell = cellIterator.next();
            double income = cell.getNumericCellValue();
            cell = cellIterator.next();
            double expense = cell.getNumericCellValue();
            Record record = new Record(month, income, expense);
            System.out.println(record);
            records.add(record);
        }

        Map<String, Object> formDetails = Map.of("name",name,
                                                 "surname", surname,
                                                 "dateofbirth",dateOfBirth,
                                                 "records",records);
        context.render("table.html", formDetails);
    };

}
