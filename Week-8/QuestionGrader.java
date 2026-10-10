
import java.util.*;
import java.util.regex.*;
import java.util.function.Function;

abstract class Question {
    String correct, answer;
    double points;

    Question(String correct, String answer, double points) {
        this.correct = correct;
        this.answer = answer;
        this.points = points;
    }

    abstract double grade();
}

class MCQ extends Question {
    MCQ(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        return correct.equals(answer) ? points : 0;
    }
}

class TF extends Question {
    TF(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        return correct.equals(answer) ? points : 0;
    }
}

class Essay extends Question {
    Essay(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        String[] keywords = correct.split(",");
        int matches = 0;

        for (String keyword : keywords) {
            if (answer.toLowerCase().contains(
                    keyword.trim().toLowerCase())) {
                matches++;
            }
        }

        if (matches >= 2) return points * 0.75;
        if (matches == 1) return points * 0.50;
        return 0;
    }
}

public class QuestionGrader {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<String[], Question>> types = new HashMap<>();
        types.put("MCQ", a -> new MCQ(a[1], a[2],
            Double.parseDouble(a[3])));
        types.put("TF", a -> new TF(a[1], a[2],
            Double.parseDouble(a[3])));
        types.put("ESSAY", a -> new Essay(a[1], a[2],
            Double.parseDouble(a[3])));

        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;

        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");

        for (int i = 0; i < n; i++) {
            Matcher m = pattern.matcher(sc.nextLine());
            List<String> parts = new ArrayList<>();

            while (m.find()) {
                parts.add(m.group(1) != null ? m.group(1) : m.group(2));
            }

            String type = parts.get(0);
            String[] data = {
                type, parts.get(2), parts.get(3), parts.get(4)
            };

            Question q = types.get(type).apply(data);
            double score = q.grade();

            System.out.printf(Locale.US, "%s: %.2f%n", type, score);
            total += score;
        }

        System.out.printf(Locale.US, "Total Score: %.2f%n", total);
        sc.close();
    }
}
