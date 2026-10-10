
import java.util.*;

abstract class Booking {
    static final double BOOKING_FEE = 50.0;
    double distance;

    Booking(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();

    double totalFare() {
        return calculateFare() + BOOKING_FEE;
    }
}

class Bus extends Booking {
    Bus(double distance) {
        super(distance);
    }

    double calculateFare() {
        return distance * 2;
    }
}

class Train extends Booking {
    Train(double distance) {
        super(distance);
    }

    double calculateFare() {
        return distance * 1.5;
    }
}

class Flight extends Booking {
    Flight(double distance) {
        super(distance);
    }

    double calculateFare() {
        return 2500 + distance * 4;
    }
}

public class TravelBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] a = sc.nextLine().trim().split("\\s+");
            double distance = Double.parseDouble(a[1]);
            Booking b;

            switch (a[0]) {
                case "BUS":
                    b = new Bus(distance);
                    break;
                case "TRAIN":
                    b = new Train(distance);
                    break;
                default:
                    b = new Flight(distance);
            }

            System.out.printf(Locale.US, "%s: %.2f%n",
                    a[0], b.totalFare());
        }

        sc.close();
    }
}
