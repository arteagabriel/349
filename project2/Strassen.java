import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Strassen {
    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {
        return matrixProduct_Strassen(A, 0, 0, B, 0, 0, A.length);
    }


    private static int[][] matrixProduct_Strassen(int[][] A, int rowA, int columnA, int[][] B, int rowB, int columnB, int size) {
        int[][] c = new int[size][size];
        int n = size;

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++)
        //         System.out.print(A[i + rowA][j + columnA] + " ");
        //     System.out.println();
        // }
        // System.out.println("row A " + rowA + " columnA " + columnA);
        // System.out.println();
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++)
        //         System.out.print(B[i + rowB][j + columnB] + " ");
        //     System.out.println();
        // }
        // System.out.println("row B " + rowB+ " columnB " + columnB);
        // System.out.println();
        if (n == 1) {
            c[0][0] = A[rowA][columnA] * B[rowB][columnB];
        }
        else {
            int[][][] s = new int[10][n][n];
            int[][][] p = new int[7][][];
            int[][][] temp = new int[4][][];
            s[0] = matrixSubtract(B, rowB,     columnB+n/2, B, rowB+n/2, columnB+n/2, n/2);
            s[1] = matrixAddition(A, rowA,     columnA,     A, rowA,     columnA+n/2, n/2);
            s[2] = matrixAddition(A, rowA+n/2, columnA,     A, rowA+n/2, columnA+n/2, n/2);
            s[3] = matrixSubtract(B, rowB+n/2, columnB,     B, rowB,     columnB,     n/2);
            s[4] = matrixAddition(A, rowA,     columnA,     A, rowA+n/2, columnA+n/2, n/2);
            s[5] = matrixAddition(B, rowB,     columnB,     B, rowB+n/2, columnB+n/2, n/2);
            s[6] = matrixSubtract(A, rowA,     columnA+n/2, A, rowA+n/2, columnA+n/2, n/2);
            s[7] = matrixAddition(B, rowB+n/2, columnB,     B, rowB+n/2, columnB+n/2, n/2);
            s[8] = matrixSubtract(A, rowA,     columnA,     A, rowA+n/2, columnA,     n/2);
            s[9] = matrixAddition(B, rowB,     columnB,     B, rowB,     columnB+n/2, n/2);

            // for (int i = 0; i < s.length; i++) {

            //     System.out.println( "s[" + (i+1) + "]");
            //     printMatrix(s[i]);
            //     System.out.println();
            // }

            // int[][] test = new int[n/2][n/2];
            // for (int i = 0; i < n/2; i++) {
            //     for (int j = 0; j < n/2; j++) {
            //         test[i][j] = A[rowA + i][columnA + j];
            //     }
            // }
            p[0] = matrixProduct_Strassen(createMatrix(A, rowA, columnA, n/2), s[0]);
            p[1] = matrixProduct_Strassen(s[1], createMatrix(B, rowB+n/2, columnB+n/2, n/2));
            p[2] = matrixProduct_Strassen(s[2], createMatrix(B, rowB, columnB, n/2));
            p[3] = matrixProduct_Strassen(createMatrix(A, rowA+n/2, columnA+n/2, n/2), s[3]);
            p[4] = matrixProduct_Strassen(s[4], s[5]);
            p[5] = matrixProduct_Strassen(s[6], s[7]);
            p[6] = matrixProduct_Strassen(s[8], s[9]);
            // p[0] = matrixProduct_Strassen(A,    rowA,     columnA,     s[0], 0,        0,           n/2);
            // p[1] = matrixProduct_Strassen(s[1], 0,        0,           B,    rowB+n/2, columnB+n/2, n/2);
            // p[2] = matrixProduct_Strassen(s[2], 0,        0,           B,    rowB,     columnB,     n/2);
            // p[3] = matrixProduct_Strassen(A,    rowA+n/2, columnA+n/2, s[3], 0,        0,           n/2);
            // p[4] = matrixProduct_Strassen(s[4], 0,        0,           s[5], 0,        0,           n/2);
            // p[5] = matrixProduct_Strassen(s[6], 0,        0,           s[7], 0,        0,           n/2);
            // p[6] = matrixProduct_Strassen(s[8], 0,        0,           s[9], 0,        0,           n/2);
            printMatrix(p[0]);
            System.out.println();

            // for (int i = 0; i < p.length; i++) {
            //     System.out.println("p[" + (i+1)+ "]");
            //     printMatrix(p[i]);
            //     System.out.println();
            // }
            temp[0] = matrixSubtract(matrixAddition(p[4], 0, 0, p[3], 0, 0, n/2), 0, 0, matrixAddition(p[1], 0, 0, p[5], 0, 0, n/2), 0, 0, n/2);
            temp[1] = matrixAddition(p[0], 0, 0, p[1], 0, 0, n/2);
            temp[2] = matrixAddition(p[2], 0, 0, p[3], 0, 0, n/2);
            temp[3] = matrixSubtract(matrixAddition(p[4], 0, 0, p[0], 0, 0, n/2), 0, 0, matrixSubtract(p[2], 0, 0, p[6], 0, 0, n/2), 0, 0, n/2);

            for (int i = 0; i < temp[0].length; i++)
                for (int j = 0; j < temp[0][0].length; j++) {
                    c[i][j] = temp[0][i][j];
                    c[i][j + n/2] = temp[1][i][j];
                    c[i + n/2][j] = temp[2][i][j];
                    c[i + n/2][j + n/2] = temp[3][i][j];
                }
        }

        return c;
    }

    private static int[][] createMatrix(int[][] A, int row, int col, int size) {
        int[][] ret = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                ret[i][j] = A[row + i][col + j];


        return ret;
    }

    private static int[][] matrixAddition(int[][] A, int rowA, int columnA, int[][] B, int rowB, int columnB, int n) {
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = A[rowA + i][columnA + j] + B[rowB + i][columnB + j];
            }
        }  

        return c;
    }

    private static int[][] matrixSubtract(int[][] A, int rowA, int columnA, int[][] B, int rowB, int columnB, int n) {
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = A[rowA + i][columnA + j] - B[rowB + i][columnB + j];
            }
        }  

        return c;
    }

    private static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++)
                System.out.print(m[i][j] + " ");

            System.out.println();
        }
    } 

    public static void main(String[] args) {
        String fileName, line = null;
        Scanner scan = new Scanner(System.in);
        int[][] a, b, c;
        int n;
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
            a = new int[n][n];

            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                tokens = line.split(" ");

                for (int j = 0; j < n; j++) {
                    a[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            b = new int[n][n];

            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                tokens = line.split(" ");

                for (int j = 0; j < n; j++) {
                    b[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            printMatrix(matrixProduct_Strassen(a, b));

            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    } 
}
