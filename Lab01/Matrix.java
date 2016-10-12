import java.util.Scanner;
import java.io.*;

public class Matrix {
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
        String fileName;
        Scanner scan = new Scanner(System.in);
        int[][] a;
        int[][] b;
        int[][] c;
        int aRows;
        int aCols;
        int bRows;
        int bCols;
        String line = null;
        String[] tokens;

        System.out.print("Enter file name: ");
        fileName = scan.next();
        System.out.println();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);


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
            
            // bufferedReader.readLine();
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


            // while((line = bufferedReader.readLine()) != null) {
            // }   

            // Always close files.
            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + 
                    fileName + "'");                
        } catch(IOException ex) {
            System.out.println(
                    "Error reading file '" 
                    + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    } 
}
