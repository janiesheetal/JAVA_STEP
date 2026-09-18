public class EmployeeCompany {

    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    EmployeeCompany(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {

        EmployeeCompany employee1 =
            new EmployeeCompany("Divya", 65000);

        EmployeeCompany employee2 =
            new EmployeeCompany("Arjun", 30000);

        EmployeeCompany employee3 =
            new EmployeeCompany("Ravi", 50000);

        EmployeeCompany.printCompanyInfo();
    }
}