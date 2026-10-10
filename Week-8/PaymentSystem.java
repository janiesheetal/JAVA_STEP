
import java.util.*;
import java.util.function.Function;

abstract class Payment {
    double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    abstract double calculateAmount();
}

class Card extends Payment {
    Card(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 1.02;
    }
}

class Wallet extends Payment {
    Wallet(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 1.01;
    }
}

class BankTransfer extends Payment {
    BankTransfer(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount;
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Double, Payment>> types = new HashMap<>();
        types.put("CARD", Card::new);
        types.put("WALLET", Wallet::new);
        types.put("BANKTRANSFER", BankTransfer::new);

        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            Payment p = types.get(type).apply(amount);
            double result = p.calculateAmount();

            System.out.printf(Locale.US, "%s: %.2f%n", type, result);
            total += result;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
