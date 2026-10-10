
import java.util.*;
import java.util.function.Function;

abstract class Room {
    int units;

    Room(int units) {
        this.units = units;
    }

    abstract double calculateBill();
}

class SingleRoom extends Room {
    SingleRoom(int units) {
        super(units);
    }

    double calculateBill() {
        return units * 8.0;
    }
}

class SharedRoom extends Room {
    int occupants;

    SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    double calculateBill() {
        return units * 6.0 / occupants;
    }
}

class ACRoom extends Room {
    ACRoom(int units) {
        super(units);
    }

    double calculateBill() {
        return units * 10.0 + 200;
    }
}

public class HostelElectricity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<int[], Room>> types = new HashMap<>();
        types.put("SINGLE", a -> new SingleRoom(a[0]));
        types.put("SHARED", a -> new SharedRoom(a[0], a[1]));
        types.put("AC", a -> new ACRoom(a[0]));

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();
            int occupants = 1;

            if (type.equals("SHARED")) {
                occupants = sc.nextInt();
            }

            Room room = types.get(type).apply(
                new int[]{units, occupants}
            );

            double bill = room.calculateBill();

            System.out.printf(Locale.US, "%s: %.2f%n", type, bill);
            total += bill;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
