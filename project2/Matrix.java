import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Matrix {
    private static int[][] getSubmatrix(int[][] m, int size, boolean half) {
        int[][] ret; 
        System.out.println("m.length" + m.length);
        System.out.println("size: " + size);
        if (half) {
            ret = new int[size / 2][size / 2];
            for (int i = size / 2; i < size; size++) {
                System.out.println("i: " + i);
                System.out.println("m[i].length " + m[i].length);
                ret[i] = Arrays.copyOfRange(m[i], size / 2 + 1, size);
            }
        }
        else 
            ret = new int[size][size];
            for (int i = 0; i < size; i++)
                ret[i] = Arrays.copyOfRange(m[i], 0, size);
            
        return ret;
    }

    private static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++)
                System.out.print(m[i][j] + " ");

            System.out.println();
        }
    }
    // private static int[][] matrixAddition(int[][] a, int[][] b) {
        
    // }

    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
        int rowA = A.length;
        int columnA = A[0].length;
        int rowB = B.length;
        int columnB = B[0].length;

        return matrixProduct_DAC(A, rowA, columnA, B, rowB, columnB);
    }

    private static int[][] matrixProduct_DAC(int[][] A, int rowA, int columnA, int[][] B, int rowB, int columnB) {
        int n = rowA;
        int[][] C = new int[n][n];
        int[][] quad1 = new int[n/2][n/2];
        int[][] quad2 = new int[n/2][n/2];
        int[][] quad3 = new int[n/2][n/2];
        int[][] quad4 = new int[n/2][n/2];

        if (rowA == 1) {
            C[0][0] = A[rowA][columnA] * B[rowB][columnB];
        } else {
        }

        return C;
    }

    public static int[][] matrixProduct(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < A[0].length; k++)
                    matrix[i][j] += (A[i][k] * B[k][j]);
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = { {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        // for (int i = 0; i < 8; i++)
        //     for (int j = 0; j < 8; j++)
        //         matrix[i][j] = j;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }


        System.out.println();
        matrix = getSubmatrix(Arrays.copyOfRange(matrix, 0, matrix.length), matrix.length, true);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}
