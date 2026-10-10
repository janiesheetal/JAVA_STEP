
import java.util.*;

interface SaverMode {
    default double reduceUnits(double units) {
        return units * 0.75;
    }
}

abstract class Appliance {
    abstract double powerRating();

    double calculateUnits(double hours) {
        return powerRating() * hours / 1000.0;
    }

    double calculateCost(double units) {
        return units * 8;
    }
}

class Fridge extends Appliance {
    double powerRating() {
        return 150;
    }
}

class AC extends Appliance implements SaverMode {
    double powerRating() {
        return 1500;
    }
}

class TV extends Appliance {
    double powerRating() {
        return 100;
    }
}

class Washer extends Appliance implements SaverMode {
    double powerRating() {
        return 500;
    }
}

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double totalCost = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double hours = sc.nextDouble();
            boolean saver = sc.hasNext("SAVER");

            if (saver) {
                sc.next();
            }

            Appliance a;

            switch (type) {
                case "FRIDGE":
                    a = new Fridge();
                    break;
                case "AC":
                    a = new AC();
                    break;
                case "TV":
                    a = new TV();
                    break;
                default:
                    a = new Washer();
            }

            if (saver && !(a instanceof SaverMode)) {
                System.out.println(type + ": saver mode not supported");
                continue;
            }

            double units = a.calculateUnits(hours);

            if (saver) {
                units = ((SaverMode) a).reduceUnits(units);
            }

            double cost = a.calculateCost(units);

            System.out.printf(Locale.US,
                    "%s: Units=%.2f Cost=%.2f%n", type, units, cost);

            totalCost += cost;
        }

        System.out.printf(Locale.US, "Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}
