
import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract double calculatePay();
}

class FullTime extends Staff {
    double salary;

    FullTime(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    double calculatePay() {
        return salary;
    }
}

class Hourly extends Staff {
    double hours, rate;

    Hourly(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        }
        return 40 * rate + (hours - 40) * rate * 1.5;
    }
}

class Intern extends Staff {
    double stipend;

    Intern(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    double calculatePay() {
        return stipend;
    }
}

public class WeeklyStaff {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] a = sc.nextLine().trim().split("\\s+");
            Staff s;

            switch (a[0]) {
                case "FULLTIME":
                    s = new FullTime(a[1], Double.parseDouble(a[2]));
                    break;
                case "HOURLY":
                    s = new Hourly(a[1],
                            Double.parseDouble(a[2]),
                            Double.parseDouble(a[3]));
                    break;
                default:
                    s = new Intern(a[1], Double.parseDouble(a[2]));
            }

            double pay = s.calculatePay();
            System.out.printf(Locale.US, "%s: %.2f%n", s.name, pay);
            total += pay;
        }

        System.out.printf(Locale.US, "Total Payroll: %.2f%n", total);
        sc.close();
    }
}
