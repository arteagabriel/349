import java.util.Scanner;
import java.util.Arrays;
import java.io.*;



public class Matrix {

    private static int[][] getSubmatrix(int[][] m, int fromX, int toX, int fromY, int toY) {
        int size = m.length / 2;
        int i, j, row = 0, col = 0;
        int[][] ret = new int[size][size]; 

        System.out.println("n = " + size);

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

    private static int[][] appendMatrix(int[][] q1, int[][] q2, int[][] q3, int[][] q4) {
        int n = q1.length * 2;
        int i, j, rowC = 0, colC = 0;
        int[][] C = new int[n][n];

        for (i = 0; i < n; i++) {

            if (i < n/2) {
                for (j = 0; j < n/2; j++) {
                    C[rowC][colC] = q1[j][i];
                    C[rowC + n/2][colC] = q3[j][i];
                }

            }

            else {
                for (j = 0; j < n/2; j++) {
                    C[rowC][colC] = q2[j][i];
                    C[rowC + n/2][colC] = q4[j][i];
                }
            }

            colC++;
            rowC = 0;
        }

        return C;
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
        int i, j;
        int n = A.length;
        int[][] C = new int[n][n];

       for (i = 0; i < n; i++) {
           for (j = 0; j < n; j++) {
               C[i][j] = A[i][j] + B[i][j];
           }
        }

        return C;
    }

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

        if (n == 0) {
            C[0][0] = A[rowA][columnA] * B[rowB][columnB];
        }

        else {

            quad1 = matrixAddition(matrixProduct(getSubmatrix(A, 0, n/2, 0, n/2), getSubmatrix(B, 0, n/2, 0, n/2)),
                matrixProduct(getSubmatrix(A, n/2, n, 0, n/2), getSubmatrix(B, 0, n/2, n/2, n)));

            quad2 = matrixAddition(matrixProduct(getSubmatrix(A, 0, n/2, 0, n/2), getSubmatrix(B, n/2, n, 0, n/2)),
                matrixProduct(getSubmatrix(A, n/2, n, 0, n/2), getSubmatrix(B, n/2, n, n/2, n)));

            quad3 = matrixAddition(matrixProduct(getSubmatrix(A, 0, n/2, n/2, n), getSubmatrix(B, 0, n/2, 0, n/2)),
                matrixProduct(getSubmatrix(A, n/2, n, n/2, n), getSubmatrix(B, 0, n/2, n/2, n)));

            quad4 = matrixAddition(matrixProduct(getSubmatrix(A, 0, n/2, n/2, n), getSubmatrix(B, n/2, n, 0, n/2)),
                matrixProduct(getSubmatrix(A, n/2, n, n/2, n), getSubmatrix(B, n/2, n, n/2, n)));

            // A11 = getSubmatrix(A, 0, n/2, 0, n/2)
            // B11 = getSubmatrix(B, 0, n/2, 0, n/2)
            // A12 = getSubmatrix(A, n/2, n, 0, n/2)
            // B12 = getSubmatrix(B, n/2, n, 0, n/2)
            // A21 = getSubmatrix(A, 0, n/2, n/2, n)
            // B21 = getSubmatrix(B, 0, n/2, n/2, n)
            // A22 = getSubmatrix(A, n/2, n, n/2, n)
            // B22 = getSubmatrix(B, n/2, n, n/2, n)
            
        }

        // Fill C matrix values from the quadrants
        C = appendMatrix(quad1, quad2, quad3, quad4);

        return C;
    }


    // FROM PART 1 -- we have to adjust 
    public static void main(String[] args) {
        String fileName, line = null;
        Scanner scan = new Scanner(System.in);
        int[][] a, b, c;
        int aRows, aCols, bRows, bCols;
        String[] tokens;

        System.out.print("Enter file name: ");
        fileName = scan.next();
        System.out.println();

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            aRows = Integer.parseInt(tokens[0]);
            aCols = Integer.parseInt(tokens[1]);
            a = new int[aRows][aCols];

            for (int i = 0; i < aRows; i++) {
                line = bufferedReader.readLine();
                tokens = line.split(" ");

                for (int j = 0; j < aCols; j++) {
                    a[i][j] = Integer.parseInt(tokens[j]);
                }
            }
            
            line = bufferedReader.readLine();
            tokens = line.split(" ");
            bRows = Integer.parseInt(tokens[0]);
            bCols = Integer.parseInt(tokens[1]);
            b = new int[bRows][bCols];

            for (int i = 0; i < bRows; i++) {
                line = bufferedReader.readLine();
                tokens = line.split(" ");

                for (int j = 0; j < bCols; j++) {
                    b[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            if (aCols == bRows) {
                c = matrixProduct(a, b);

                for (int i = 0; i < aRows; i++) {
                    for (int j = 0; j < bCols; j++) {
                        System.out.print(c[i][j] + " ");
                    }
                    System.out.println();
                }
            } else
                System.out.println("Columns and row sizes don't match");
            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    } 

}

    /*

    public static void main (String[] args) {
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

        // Testing four different quadrants
        //matrix = getSubmatrix(matrix, 0, matrix.length / 2, 0, matrix.length / 2);
        //matrix = getSubmatrix(matrix, 0, matrix.length / 2, matrix.length / 2, matrix.length);
        //matrix = getSubmatrix(matrix, matrix.length / 2, matrix.length, 0, matrix.length / 2);
        matrix = getSubmatrix(matrix, matrix.length / 2, matrix.length, matrix.length / 2, matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }


}

*/