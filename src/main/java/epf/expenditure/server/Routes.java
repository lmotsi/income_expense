package epf.expenditure.server;

import epf.expenditure.model.Record;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.*;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;

/**
 * Class for handling endpoints
 */
public class Routes {

    /**
     * maps the endpoints of the budgetServer to the appropriate HTTP handler.
     * @param budgetServer
     */
    public static void configure(BudgetServer budgetServer) {
        budgetServer.routes(() -> {
            post("/submit.action",  Routes.submit);
        });
    }

    /**
     * Handles the form submit and redirects to the graph
     */
    public static final Handler submit = context -> {

        //extract name from the form
        String name = context.formParamAsClass("name", String.class)
                .check(Objects::nonNull, "Name is required")
                .get();

        //extract surname from the form
        String surname = context.formParamAsClass("surname", String.class)
                .check(Objects::nonNull, "Surname is required")
                .get();

        //extract date of birth from the form
        String dateOfBirth = context.formParamAsClass("dateofbirth", String.class)
                .check(Objects::nonNull, "Date of birth is required")
                .get();

        //extract excel sheet from the form
        UploadedFile file = context.uploadedFile("myfile");

        List<Record> records = getRecords(file);

        Map<String, Object> formDetails = Map.of("name",name,
                                                 "surname", surname,
                                                 "dateofbirth",dateOfBirth,
                                                 "records",records);
        context.render("table.html", formDetails);
    };

    /**
     * Reads the file and creates Record object for each row
     * @param file
     * @return list of all the records from the excel
     * @throws IOException
     */

    private static List<Record> getRecords(UploadedFile file) throws IOException {
        List<Record> records = new ArrayList<>();
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
            records.add(record);
        }
        return records;
    }


}
