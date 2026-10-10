
import java.util.*;

abstract class Student {
    static final double TRANSPORT_FEE = 12000;
    String name;

    Student(String name) {
        this.name = name;
    }

    abstract double tuitionFee();

    abstract boolean usesBus();

    double totalFee() {
        double fee = tuitionFee();
        if (usesBus()) {
            fee += TRANSPORT_FEE;
        }
        return fee;
    }
}

class DayScholar extends Student {
    DayScholar(String name) {
        super(name);
    }

    double tuitionFee() {
        return 40000;
    }

    boolean usesBus() {
        return true;
    }
}

class Hosteller extends Student {
    Hosteller(String name) {
        super(name);
    }

    double tuitionFee() {
        return 40000 + 60000;
    }

    boolean usesBus() {
        return false;
    }
}

class Scholar extends Student {
    Scholar(String name) {
        super(name);
    }

    double tuitionFee() {
        return 20000;
    }

    boolean usesBus() {
        return true;
    }
}

public class CollegeFeeCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            Student s;

            switch (type) {
                case "DAY_SCHOLAR":
                    s = new DayScholar(name);
                    break;
                case "HOSTELLER":
                    s = new Hosteller(name);
                    break;
                default:
                    s = new Scholar(name);
            }

            double fee = s.totalFee();
            System.out.printf(Locale.US, "%s: %.2f%n", name, fee);
            total += fee;
        }

        System.out.printf(Locale.US, "Total Collected: %.2f%n", total);
        sc.close();
    }
}
