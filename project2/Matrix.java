import java.util.Scanner;
import java.util.Arrays;
import java.io.*;



public class Matrix {
    private static int[][] getSubmatrix(int[][] m, int fromX, int toX, int fromY, int toY) {
        int size = m.length / 2;
        int i, j, row = 0, col = 0;
        int[][] ret = new int[size][size]; 

        // System.out.println("n = " + size);

        for (i = fromX; i < toX; i++) {
            for (j = fromY; j < toY; j++) {
                ret[row][col] = m[j][i];
                row++;                                  // points to the next row that will be filled
            }
            col++;                                      // points to the next column that will be filled
            row = 0;                                    // reset the row
        }

        return ret;
    }

    private static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++)
                System.out.print(m[i][j] + " ");

            System.out.println();
        }
    } 

    private static void appendMatrix(int[][] C, int[][] q1, int[][] q2, int[][] q3, int[][] q4) {
        int n = C.length;

        for (int i = 0; i < q1.length; i++)
            for (int j = 0; j < q1[0].length; j++) {
                C[i][j] = q1[i][j];
                C[i][j + n/2] = q2[i][j];
                C[i + n/2][j] = q3[i][j];
                C[i + n/2][j + n/2] = q4[i][j];
            }
    }

    private static int[][] matrixProduct(int[][] A, int[][] B) {
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

    private static int[][] matrixAddition(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
        return matrixProduct_DAC(A, 0, 0, B, 0, 0, A.length);
        
    }

    private static int[][] matrixProduct_DAC(int[][] A, int rowA, int columnA, int[][] B, int rowB, int columnB, int n) {
        int[][] C = new int[n][n];

        if (n == 1) {
            C[0][0] = A[rowA][columnA] * B[rowB][columnB];
        }

        else {
            appendMatrix(C,
                    matrixAddition(
                        matrixProduct_DAC(A, rowA, columnA, B, rowB, columnB, n/2), 
                        matrixProduct_DAC(A, rowA, columnA+n/2, B, rowB+n/2, columnB, n/2)),
                    matrixAddition(
                        matrixProduct_DAC(A, rowA, columnA, B, rowB, columnB+n/2, n/2), 
                        matrixProduct_DAC(A, rowA, columnA+n/2, B, rowB+n/2, columnB+n/2, n/2)),
                    matrixAddition(
                        matrixProduct_DAC(A, rowA+n/2, columnA, B, rowB, columnB, n/2), 
                        matrixProduct_DAC(A, rowA+n/2, columnA+n/2, B, rowB+n/2, columnB, n/2)),
                    matrixAddition(
                        matrixProduct_DAC(A, rowA+n/2, columnA, B, rowB, columnB+n/2, n/2), 
                        matrixProduct_DAC(A, rowA+n/2, columnA+n/2, B, rowB+n/2, columnB+n/2, n/2)));
        }

        return C;
    }


    // FROM PART 1 -- we have to adjust 
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

            if (a.length == b[0].length && b.length == a[0].length) {
                // printMatrix(matrixProduct_DAC(a, b));
                printMatrix(matrixProduct(a, b));
            }
            else
                System.out.println("Not a square matrix");

            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    } 

}



// public static void main (String[] args) {
    // int[][] matrix = { {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    // for (int i = 0; i < 8; i++)
    //     for (int j = 0; j < 8; j++)
    //         matrix[i][j] = j;


    // for (int i = 0; i < matrix.length; i++) {
    //     for (int j = 0; j < matrix[i].length; j++)
    //         System.out.print(matrix[i][j] + " ");
    //     System.out.println();
    // }


    // System.out.println();

    // Testing four different quadrants
    // matrix = getSubmatrix(matrix, 0, matrix.length / 2, 0, matrix.length / 2);
    // matrix = getSubmatrix(matrix, 0, matrix.length / 2, matrix.length / 2, matrix.length);
    // matrix = getSubmatrix(matrix, matrix.length / 2, matrix.length, 0, matrix.length / 2);
    // matrix = getSubmatrix(matrix, matrix.length / 2, matrix.length, matrix.length / 2, matrix.length);

    // for (int i = 0; i < matrix.length; i++) {
    //     for (int j = 0; j < matrix[i].length; j++)
    //         System.out.print(matrix[i][j] + " ");
    //     System.out.println();
    // }
    
    // int[][] m1 = { {5, 5}, {5, 5}};
    // int[][] m2 = { {2, 2}, {2, 2}};
    // int[][] m1 = { {1, 2, 3, 4}, {5, 6, 7, 8}, {8, 7, 6, 5}, {4, 3, 2, 1}};
    // int[][] m2 = { {4, 3, 2, 1}, {8, 7, 6, 5}, {5, 6, 7, 8}, {1, 2, 3, 4}};

    // printMatrix(matrixProduct_DAC(m1, m2));
// }


// }

