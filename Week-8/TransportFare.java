
import java.util.*;
import java.util.function.Function;

abstract class Transport {
    double distance;

    Transport(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();
}

class Bus extends Transport {
    Bus(double distance) {
        super(distance);
    }

    double calculateFare() {
        return Math.min(10, 2 + distance * 0.10);
    }
}

class Train extends Transport {
    Train(double distance) {
        super(distance);
    }

    double calculateFare() {
        return 3 + distance * 0.15;
    }
}

class Metro extends Transport {
    double peakFactor;

    Metro(double distance, double factor) {
        super(distance);
        peakFactor = factor;
    }

    double calculateFare() {
        return (1.50 + distance * 0.20) * peakFactor;
    }
}

public class TransportFare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<double[], Transport>> types = new HashMap<>();
        types.put("BUS", a -> new Bus(a[0]));
        types.put("TRAIN", a -> new Train(a[0]));
        types.put("METRO", a -> new Metro(a[0], a[1]));

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double distance = sc.nextDouble();
            double factor = 1;

            if (type.equals("METRO")) {
                factor = sc.nextDouble();
            }

            Transport t = types.get(type).apply(
                new double[]{distance, factor}
            );

            double fare = t.calculateFare();
            System.out.printf(Locale.US, "%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
