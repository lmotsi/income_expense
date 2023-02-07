package epf.expenditure.model;

public class Record {

    String month;
    double income;
    double expense;
     public Record(String month, double income, double expense) {
         this.month = month;
         this.income = income;
         this.expense = expense;
     }

    public String getMonth() {
        return month;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }

    @Override
    public String toString() {
        return "{Month=" + this.month + ";Income=" + this.income + ";Expense=" + this.expense + "}";
    }
}
