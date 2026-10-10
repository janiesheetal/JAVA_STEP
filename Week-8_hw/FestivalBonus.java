
import java.util.*;
import java.util.function.Function;

abstract class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    abstract double calculateBonus();
}

class FullTime extends Employee {
    FullTime(String name, double salary) {
        super(name, salary);
    }

    double calculateBonus() {
        return salary * 0.10;
    }
}

class PartTime extends Employee {
    PartTime(String name, double salary) {
        super(name, salary);
    }

    double calculateBonus() {
        return salary * 0.05;
    }
}

class Intern extends Employee {
    Intern(String name, double salary) {
        super(name, salary);
    }

    double calculateBonus() {
        return 2000;
    }
}

public class FestivalBonus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<String[], Employee>> types = new HashMap<>();
        types.put("FULLTIME", a -> new FullTime(a[0], Double.parseDouble(a[1])));
        types.put("PARTTIME", a -> new PartTime(a[0], Double.parseDouble(a[1])));
        types.put("INTERN", a -> new Intern(a[0], Double.parseDouble(a[1])));

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            String salary = sc.next();

            Employee e = types.get(type).apply(new String[]{name, salary});
            double bonus = e.calculateBonus();

            System.out.printf(Locale.US, "%s: %.2f%n", e.name, bonus);
            total += bonus;
        }

        System.out.printf(Locale.US, "Total Bonus: %.2f%n", total);
        sc.close();
    }
}
