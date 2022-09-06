import java.util.Scanner;

public class Main {

    public static double divide(long a, long b) {
        // write your code here
        double x = (a * 2.0 / 2) / b;
        //System.out.println(a * 2.0 / 2);
        //System.out.println((a/b) % b);
        return x;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();
        System.out.println(divide(a, b));
    }
}