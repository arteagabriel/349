import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Project31 {
    public static void schedule(int n, int e1, int e2, int x1, int x2, int[] a1, int[] a2, int[] t1, int[] t2) {
        int[] f1 = new int[n];  
        int[] f2 = new int[n];  
        int[] l1 = new int[n];
        int[] l2 = new int[n];
        int[] l = new int[n];
        int temp1;
        int temp2;
        int temp;

        f1[0] = e1 + a1[0];
        f2[0] = e2 + a2[0];
        l1[0] = l2[0] = 1;

        for (int j = 1; j < n; j++) {
            temp = 2;
            temp1 = f1[j - 1] + a1[j];
            temp2 = f2[j - 1] + t2[j - 1] + a1[j];
            f1[j] = Math.min(temp1, temp2);
            if (temp1 < temp2) {
                temp = 1;
            } 

            l1[j] = temp;

            temp = 2;
            temp1 = f2[j - 1] + a2[j];
            temp2 = f1[j - 1] + t1[j - 1] + a2[j];
            f2[j] = Math.min(temp1, temp2);
            if (temp1 > temp2)
                temp = 1;

            l2[j] = temp;
        }


        temp1 = f1[n - 1] + x1;
        temp2 = f2[n - 1] + x2;
        l[n - 1] = temp1 < temp2 ? 1 : 2;
        for (int i = n - 1; i > 0; i--)
            l[i - 1] = l[i] == 1 ? l1[i] : l2[i]; 
        System.out.println("Fastest time is: " + Math.min(temp1, temp2));

        System.out.println("The optimal route is:");
        for (int i = 0; i < n; i++)
            System.out.println("Line " + l[i] + " station " + (i + 1)); 
    } 
    
    public static void main(String[] args) {
        String fileName, line = null;
        Scanner scan = new Scanner(System.in);
        int n;
        int e1;
        int e2;
        int x1;
        int x2;
        int[] a1;
        int[] a2;
        int[] t1;
        int[] t2;
        String[] tokens;

        System.out.print("Enter file name: ");
        fileName = scan.next();
        System.out.println();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            n = Integer.parseInt(tokens[0]);
            a1 = new int[n];
            a2 = new int[n];
            t1 = new int[n-1];
            t2 = new int[n-1];

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            e1 = Integer.parseInt(tokens[0]);
            e2 = Integer.parseInt(tokens[1]);

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            x1 = Integer.parseInt(tokens[0]);
            x2 = Integer.parseInt(tokens[1]);

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            for (int i = 0; i < n; i++)
                a1[i] = Integer.parseInt(tokens[i]);

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            for (int i = 0; i < n; i++)
                a2[i] = Integer.parseInt(tokens[i]);

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            for (int i = 0; i < n - 1; i++)
                t1[i] = Integer.parseInt(tokens[i]);

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            for (int i = 0; i < n - 1; i++)
                t2[i] = Integer.parseInt(tokens[i]);

            bufferedReader.close();         

            schedule(n, e1, e2, x1, x2, a1, a2, t1, t2);
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    } 
}
