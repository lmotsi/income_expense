package epf.expenditure.model;

/**
 * Class used to represent each record from the excel sheet
 */
public class Record {

    private String month;
    private double income;
    private double expense;

    /**
     * Constructor that initialises all the private fields
     * @param month
     * @param income
     * @param expense
     */
     public Record(String month, double income, double expense) {
         this.month = month;
         this.income = income;
         this.expense = expense;
     }

    /**
     * Getter for the month
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Getter for the income
     * @return income
     */
    public double getIncome() {
        return income;
    }

    /**
     * Getter for the expense
     * @return expense
     */
    public double getExpense() {
        return expense;
    }
    @Override
    public String toString() {
        return "{Month=" + this.month + ";Income=" + this.income + ";Expense=" + this.expense + "}";
    }
}
