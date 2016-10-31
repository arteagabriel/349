import java.util.Scanner;
import java.io.*;

public class Project32 {
    public static void game(int n, int m, int[][] a) {
        int[][] s = new int[n][m];
        String[][] r = new String[n][m];
        n--;
        m--;
        int temp1;
        int temp2;

        int max = Integer.MIN_VALUE;
        int maxX = n;
        int maxY = m;
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n && j == m) {
                    s[i][j] = a[n][m];
                    r[i][j] = "e";
                } else if (j == m) {
                    temp1 = s[i + 1][m];
                    s[i][j] = a[i][m];

                    if (temp1 > 0) {
                        s[i][j] += temp1;
                        r[i][j] = "d";
                    } else {
                        r[i][j] = "e"; 
                    }
                } else if (i == n) {
                    temp1 = s[n][j + 1];
                    s[i][j] = a[n][j];

                    if (temp1 > 0) {
                        s[i][j] += temp1;
                        r[i][j] = "r";
                    } else {
                        r[i][j] = "e"; 
                    }
                } else {
                    temp1 = s[i + 1][j];
                    temp2 = s[i][j + 1];

                    if (temp1 > temp2) {
                        s[i][j] = temp1 + a[i][j];
                        r[i][j] = "d";
                    } else {
                        s[i][j] = temp2 + a[i][j];
                        r[i][j] = "r";
                    }
                }

                if (s[i][j] > max) {
                    max = s[i][j];
                    maxX = i;
                    maxY = j;
                } 
            }
        }

        int i = maxX;
        int j = maxY;
        System.out.println("Best score: " + max);
        System.out.print("Best route: ");
        while (!r[i][j].equals("e")) {
            System.out.print("[" + (i+1) + "," + (j+1) + "] to ");

            if (r[i][j].equals("d"))
                i++;
            else
                j++;
        }
        
        System.out.print("[" + (i+1) + "," + (j+1) + "] to ");
        System.out.println("exit");
    }

    public static void main(String[] args) {
        File fileName;
        Scanner scan = new Scanner(System.in);
        int n;
        int m;
        int[][] mat;

        System.out.print("Enter file name: ");
        fileName = new File(scan.next());
        System.out.println();

        scan.close();
        try {
            scan = new Scanner(fileName);
            n = scan.nextInt();
            m = scan.nextInt();
            mat = new int[n][m];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    mat[i][j] = scan.nextInt();

            game(n, m, mat);
            scan.close();         
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
    } 
}
