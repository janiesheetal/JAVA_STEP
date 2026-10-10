
import java.util.*;
import java.time.LocalDate;
import java.util.function.Function;

abstract class SubscriptionPlan {
    String name;
    LocalDate startDate;

    SubscriptionPlan(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    abstract int validityDays();

    LocalDate renewalDate() {
        return startDate.plusDays(validityDays());
    }
}

class Basic extends SubscriptionPlan {
    Basic(String name, LocalDate date) {
        super(name, date);
    }

    int validityDays() {
        return 30;
    }
}

class Standard extends SubscriptionPlan {
    Standard(String name, LocalDate date) {
        super(name, date);
    }

    int validityDays() {
        return 90;
    }
}

class Premium extends SubscriptionPlan {
    Premium(String name, LocalDate date) {
        super(name, date);
    }

    int validityDays() {
        return 365;
    }
}

public class StreamingRenewal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Object[], SubscriptionPlan>> types =
            new HashMap<>();

        types.put("BASIC", a ->
            new Basic((String) a[0], (LocalDate) a[1]));
        types.put("STANDARD", a ->
            new Standard((String) a[0], (LocalDate) a[1]));
        types.put("PREMIUM", a ->
            new Premium((String) a[0], (LocalDate) a[1]));

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            LocalDate date = LocalDate.parse(sc.next());

            SubscriptionPlan plan = types.get(type).apply(
                new Object[]{name, date}
            );

            System.out.println(
                plan.name + ": " + plan.renewalDate()
            );
        }

        sc.close();
    }
}
