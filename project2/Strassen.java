/*
 * Gabriel Arteaga garteaga@calpoly.edu
 * Alexandra Deany adeany@calpoly.edu
 * Project 2
 * 10/19/2016
 */

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

            p[0] = matrixProduct_Strassen(A,    rowA,     columnA,     s[0], 0,        0,           n/2);
            p[1] = matrixProduct_Strassen(s[1], 0,        0,           B,    rowB+n/2, columnB+n/2, n/2);
            p[2] = matrixProduct_Strassen(s[2], 0,        0,           B,    rowB,     columnB,     n/2);
            p[3] = matrixProduct_Strassen(A,    rowA+n/2, columnA+n/2, s[3], 0,        0,           n/2);
            p[4] = matrixProduct_Strassen(s[4], 0,        0,           s[5], 0,        0,           n/2);
            p[5] = matrixProduct_Strassen(s[6], 0,        0,           s[7], 0,        0,           n/2);
            p[6] = matrixProduct_Strassen(s[8], 0,        0,           s[9], 0,        0,           n/2);

            temp[0] = matrixSubtract(matrixAddition(p[4], 0, 0, p[3], 0, 0, n/2), 0, 0, matrixSubtract(p[1], 0, 0, p[5], 0, 0, n/2), 0, 0, n/2);
            temp[1] = matrixAddition(p[0], 0, 0, p[1], 0, 0, n/2);
            temp[2] = matrixAddition(p[2], 0, 0, p[3], 0, 0, n/2);
            temp[3] = matrixSubtract(matrixAddition(p[4], 0, 0, p[0], 0, 0, n/2), 0, 0, matrixAddition(p[2], 0, 0, p[6], 0, 0, n/2), 0, 0, n/2);

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
