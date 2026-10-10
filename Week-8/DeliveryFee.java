
import java.util.*;
import java.util.function.Function;

abstract class Delivery {
    double weight, distance;

    Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    abstract double calculateFee();
}

class StandardDelivery extends Delivery {
    StandardDelivery(double w, double d) {
        super(w, d);
    }

    double calculateFee() {
        return 5 + weight * 0.50 + distance * 0.10;
    }
}

class ExpressDelivery extends Delivery {
    ExpressDelivery(double w, double d) {
        super(w, d);
    }

    double calculateFee() {
        return 15 + weight + distance * 0.20;
    }
}

class InternationalDelivery extends Delivery {
    double customsFee;

    InternationalDelivery(double w, double d, double c) {
        super(w, d);
        customsFee = c;
    }

    double calculateFee() {
        return 25 + weight * 2 + distance * 0.50 + customsFee;
    }
}

public class DeliveryFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<double[], Delivery>> types = new HashMap<>();
        types.put("STANDARD", a -> new StandardDelivery(a[0], a[1]));
        types.put("EXPRESS", a -> new ExpressDelivery(a[0], a[1]));
        types.put("INTERNATIONAL",
            a -> new InternationalDelivery(a[0], a[1], a[2]));

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double distance = sc.nextDouble();
            double customs = 0;

            if (type.equals("INTERNATIONAL")) {
                customs = sc.nextDouble();
            }

            Delivery d = types.get(type).apply(
                new double[]{weight, distance, customs}
            );

            double fee = d.calculateFee();
            System.out.printf(Locale.US, "%s: %.2f%n", type, fee);
            total += fee;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
