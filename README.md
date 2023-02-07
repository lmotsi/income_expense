#                             EPF Income and Expense

This is a website that enables the user to enter a customer's name, surname, date of birth and an excel sheet of the customers incomeand expenses for the last 12 months and then convert the data into a visualized temporal graph.

#                                  Usage Example
<!-- STEP 1 -->
    If you have maven installed on your PC then you can skip step 1. If you don't have maven then follow these instructions

    Windows:
#       TODO!!!!
    Linux:
        run -> sudo apt-get install maven -y
        run -> mvn --version
        If this returns the maven version then you can move on to step 2, else:
            navigate to -> https://linuxhint.com/mvn-command-found/ and follow the steps to Cause #2


<!-- STEP 2 -->
    Windows:
#       TODO!!!!
    Linux:
        Ensure you are in the directory that contains this README.md ("/income_expense)
        1.  run         -> $ mvn clean install
        2.  run         -> $ mvn exec:java -Dexec.mainClass="epf.expenditure.server.BudgetServer"
        3.  navigate to -> http://localhost:5050/
        4.  Fill in the form that appears on the screen, don't forget to enter an excel sheet with an ".xlsx" extension (the program can only process excel sheets with the format of "/sheet/expense.xlsx)
        5. Click Enter to submit and you will see your temporal graph.

    
