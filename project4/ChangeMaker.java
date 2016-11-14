import java.util.Scanner;

public class ChangeMaker {
    public static void main(String[] args) {
        int k;
        int n;
        int[] d;
        int[] results;
        Scanner scan = new Scanner(System.in);
    
        System.out.println("Enter number of coin-denominations and the set of coin values:");
        k = scan.nextInt();
        d = new int[k];

        for (int i = 0; i < k; i++)
            d[i] = scan.nextInt();

        System.out.println();
        System.out.println("Enter a positive amount to be changed(enter 0 to quit):");
        n = scan.nextInt();

        while (n != 0) {
            int count = 0;
            results = change_DP(n, d);
            System.out.println();
            System.out.println("DP algorithms results");
            System.out.println("Amount: " + n);

            System.out.print("Optimal distribution: ");

            boolean first = true;
            for (int i = 0; i < results.length; i++) {
                if (results[i] > 0) {
                    if (first) {
                        System.out.print(results[i] + "*" + d[i] + "c");
                        first = false;
                    }
                    else
                        System.out.print(" + " + results[i] + "*" + d[i] + "c");

                    count += results[i];
                }
            }
            System.out.println();
            System.out.println("Optimal coin count: " + count);

            count = 0;
            results = change_greedy(n, d);
            System.out.println();
            System.out.println("Greedy algorithms results");
            System.out.println("Amount: " + n);

            System.out.print("Optimal distribution: ");

            first = true;
            for (int i = 0; i < results.length; i++) {
                if (results[i] > 0) {
                    if (first) {
                        System.out.print(results[i] + "*" + d[i] + "c");
                        first = false;
                    }
                    else
                        System.out.print(" + " + results[i] + "*" + d[i] + "c");

                    count += results[i];
                }
            }
            System.out.println();
            System.out.println("Optimal coin count: " + count);

            System.out.println();
            System.out.println("Enter a positive amount to be changed(enter 0 to quit):");
            n = scan.nextInt();
        }

        System.out.println();
        System.out.println("Thanks for playing. Good Bye.");
    }

    public static int[] change_DP(int n, int[] d) {
        int k = d.length;
        int[] c = new int[n + 1];
        int[] a = new int[c.length];
        int[] counts = new int[k];

        c[0] = 0;
        int min = 0;
        for (int j = 1; j < c.length; j++) {
            min = Integer.MAX_VALUE;

            for (int i = 0; i < k; i++) {
                if (j >= d[i]) {
                    if (c[j - d[i]] < min) {
                        min = c[j - d[i]];
                        c[j] = 1 + min;
                        a[j] = i;
                    }
                }
            }
        }

        while (n > 0) {
            counts[a[n]]++;
            n -= d[a[n]];
        }

        return counts; 
    }

    public static int[] change_greedy(int n, int[] d) {
        int remainder = n;
        int[] counts = new int[d.length];
        int i = 0;

        while (remainder > 0 && i < d.length) {
            while (d[i] <= remainder) {
                counts[i]++;
                remainder -= d[i];
            }

            i++;
        }

        return counts;
    }
}
