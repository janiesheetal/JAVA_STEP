
import java.util.*;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.regex.*;

abstract class LibraryItem {
    String title;
    LocalDate currentDate = LocalDate.of(2023, 10, 26);

    LibraryItem(String title) {
        this.title = title;
    }

    abstract int borrowingDays();

    LocalDate dueDate() {
        return currentDate.plusDays(borrowingDays());
    }
}

class Book extends LibraryItem {
    Book(String title) {
        super(title);
    }

    int borrowingDays() {
        return 14;
    }
}

class DVD extends LibraryItem {
    DVD(String title) {
        super(title);
    }

    int borrowingDays() {
        return 7;
    }
}

class Magazine extends LibraryItem {
    Magazine(String title) {
        super(title);
    }

    int borrowingDays() {
        return 3;
    }
}

public class LibraryDueDate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<String, LibraryItem>> types = new HashMap<>();
        types.put("BOOK", Book::new);
        types.put("DVD", DVD::new);
        types.put("MAGAZINE", Magazine::new);

        int n = Integer.parseInt(sc.nextLine().trim());
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            Matcher m = pattern.matcher(line);
            List<String> parts = new ArrayList<>();

            while (m.find()) {
                parts.add(m.group(1) != null ? m.group(1) : m.group(2));
            }

            String type = parts.get(0);
            String title = parts.get(1);

            LibraryItem item = types.get(type).apply(title);
            System.out.println(item.title + ": " + item.dueDate());
        }

        sc.close();
    }
}
