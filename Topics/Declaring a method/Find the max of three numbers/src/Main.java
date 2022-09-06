import java.util.Scanner;

public class Main {

    public static int getNumberOfMaxParam(int a, int b, int c) {
        // write a body here
        int p;
        if (a >= b && b >= c || a >= b && b <= c) {
            p = 1;
        } else if (a <= b && b < c) {
            p = 3;
        } else {
            p = 2;
        }
        return p;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();

        System.out.println(getNumberOfMaxParam(a, b, c));
    }
}
