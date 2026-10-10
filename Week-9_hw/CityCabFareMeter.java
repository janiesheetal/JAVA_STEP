
import java.util.*;

interface NightService {
}

abstract class Cab {
    static final double MINIMUM_FARE = 100;

    abstract double ratePerKm();

    double calculateFare(double km, boolean night) {
        double fare = Math.max(km * ratePerKm(), MINIMUM_FARE);

        if (night && this instanceof NightService) {
            fare *= 1.20;
        }

        return fare;
    }
}

class Mini extends Cab {
    double ratePerKm() {
        return 10;
    }
}

class Sedan extends Cab implements NightService {
    double ratePerKm() {
        return 14;
    }
}

class SUV extends Cab implements NightService {
    double ratePerKm() {
        return 18;
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();

            Cab cab;

            switch (type) {
                case "MINI":
                    cab = new Mini();
                    break;
                case "SEDAN":
                    cab = new Sedan();
                    break;
                default:
                    cab = new SUV();
            }

            boolean night = time.equals("NIGHT");

            if (night && !(cab instanceof NightService)) {
                System.out.println(type + ": night service not available");
                continue;
            }

            double fare = cab.calculateFare(km, night);
            System.out.printf(Locale.US, "%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
