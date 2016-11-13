import java.util.Scanner;

public class ChangeMaker {
    public static void main(String[] args) {
        int k;
        int n;
        int[] d;
        int[] results;
        Scanner scan = new Scanner(System.in);
    
        System.out.println("Enter number of coins: ");
        k = scan.nextInt();
        d = new int[k];

        System.out.println("Enter list of denominations in decreasing order: ");
        for (int i = 0; i < k; i++)
            d[i] = scan.nextInt();

        System.out.println("Enter n value: ");
        n = scan.nextInt();

        if (n > 0) {
            int count = 0;
            results = change_DP(n, d);
            System.out.println("DP algorithms results");
            System.out.println("Amount: " + n);

            System.out.print("Optimal distribution: ");

            for (int i = 0; i < results.length; i++) {
                if (results[i] > 0) {
                    System.out.print(results[i] + "*" + d[i] + "c");

                    count += results[i];
                }
            }
            System.out.println();

            System.out.println("Optimal coin count: " + count);
        }
    }

    public static int[] change_DP(int n, int[] d) {
        int k = d.length;
        int[] c = new int[n + 1];
        int[] a = new int[c.length];
        int[] counts = new int[k];

        c[0] = 0;
        int min = 0;
        for (int j = 1; j < c.length; j++) {
            for (int i = 0; i < k; i++) {
                if (j >= d[i]) {
                    if (c[j - d[i]] <= min) {
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
}
