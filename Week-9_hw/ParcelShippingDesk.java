
import java.util.*;

interface Insurable {
    double calculateInsurance(double declaredValue);
}

abstract class Parcel {
    double weight, declaredValue;

    Parcel(double weight, double declaredValue) {
        this.weight = weight;
        this.declaredValue = declaredValue;
    }

    abstract double calculateCharge();

    double calculateInsurance() {
        if (this instanceof Insurable) {
            return ((Insurable) this).calculateInsurance(declaredValue);
        }
        return 0;
    }

    double calculateTotal() {
        return calculateCharge() + calculateInsurance();
    }
}

class StandardParcel extends Parcel {
    StandardParcel(double weight, double value) {
        super(weight, value);
    }

    double calculateCharge() {
        return 40 + 10 * weight;
    }
}

class ExpressParcel extends Parcel implements Insurable {
    ExpressParcel(double weight, double value) {
        super(weight, value);
    }

    double calculateCharge() {
        return 80 + 15 * weight;
    }

    public double calculateInsurance(double value) {
        return value * 0.02;
    }
}

class FragileParcel extends Parcel implements Insurable {
    FragileParcel(double weight, double value) {
        super(weight, value);
    }

    double calculateCharge() {
        return 40 + 10 * weight + 50;
    }

    public double calculateInsurance(double value) {
        return value * 0.02;
    }
}

public class ParcelShippingDesk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double grandTotal = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();
            Parcel p;

            switch (type) {
                case "STANDARD":
                    p = new StandardParcel(weight, value);
                    break;
                case "EXPRESS":
                    p = new ExpressParcel(weight, value);
                    break;
                default:
                    p = new FragileParcel(weight, value);
            }

            double charge = p.calculateCharge();
            double insurance = p.calculateInsurance();
            double total = p.calculateTotal();

            System.out.printf(Locale.US,
                    "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    type, charge, insurance, total);

            grandTotal += total;
        }

        System.out.printf(Locale.US, "Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}
