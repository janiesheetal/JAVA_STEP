
import java.util.*;

abstract class Plot {
    String owner;

    Plot(String owner) {
        this.owner = owner;
    }

    abstract double area();

    abstract String shape();
}

class Circle extends Plot {
    double radius;

    Circle(String owner, double radius) {
        super(owner);
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }

    String shape() {
        return "CIRCLE";
    }
}

class Rectangle extends Plot {
    double length, width;

    Rectangle(String owner, double length, double width) {
        super(owner);
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }

    String shape() {
        return "RECTANGLE";
    }
}

class Triangle extends Plot {
    double base, height;

    Triangle(String owner, double base, double height) {
        super(owner);
        this.base = base;
        this.height = height;
    }

    double area() {
        return 0.5 * base * height;
    }

    String shape() {
        return "TRIANGLE";
    }
}

public class GardenPlot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] a = sc.nextLine().trim().split("\\s+");
            Plot p;

            switch (a[0]) {
                case "CIRCLE":
                    p = new Circle(a[1], Double.parseDouble(a[2]));
                    break;
                case "RECTANGLE":
                    p = new Rectangle(a[1],
                            Double.parseDouble(a[2]),
                            Double.parseDouble(a[3]));
                    break;
                default:
                    p = new Triangle(a[1],
                            Double.parseDouble(a[2]),
                            Double.parseDouble(a[3]));
            }

            double area = p.area();
            System.out.printf(Locale.US, "%s (%s): %.2f%n",
                    p.owner, p.shape(), area);
            total += area;
        }

        System.out.printf(Locale.US, "Total Area: %.2f%n", total);
        sc.close();
    }
}
