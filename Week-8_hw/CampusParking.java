
import java.util.*;
import java.util.function.Function;

abstract class Vehicle {
    int hours;

    Vehicle(int hours) {
        this.hours = hours;
    }

    abstract double calculateCharge();
}

class Bike extends Vehicle {
    Bike(int hours) {
        super(hours);
    }

    double calculateCharge() {
        return hours * 10.0;
    }
}

class Car extends Vehicle {
    Car(int hours) {
        super(hours);
    }

    double calculateCharge() {
        return 30 + (hours - 1) * 20.0;
    }
}

class Truck extends Vehicle {
    Truck(int hours) {
        super(hours);
    }

    double calculateCharge() {
        return Math.max(100, hours * 50.0);
    }
}

public class CampusParking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Integer, Vehicle>> types = new HashMap<>();
        types.put("BIKE", Bike::new);
        types.put("CAR", Car::new);
        types.put("TRUCK", Truck::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();

            Vehicle v = types.get(type).apply(hours);
            double charge = v.calculateCharge();

            System.out.printf(Locale.US, "%s: %.2f%n", type, charge);
            total += charge;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
