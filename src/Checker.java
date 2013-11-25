import java.util.Random;

/**
 * User: ijk
 * Date: 11/25/13
 */
public class Checker {
    public static int[][] makePermutation(int n){
        //Makes a (square) permutation matrix of size n
        Random random= new Random();

        int[] list= new int[n];
        for (int i= 0; i < n; i++){
            list[i]= i;
        }
        for (int i= 0; i < n; i++){
            int index= random.nextInt(n-i)+i;
            int temp= list[index];
            list[index]= list[i];
            list[i]= temp;
        }

        int[][] matrix= new int[n][n];
        for (int i= 0; i < n; i++){
            matrix[i][list[i]]= 1;
        }
        return matrix;
    }

    public static int[][] makeTranspose(int[][] matrix){
        //Be careful, arrays are passed by value, but the value is a reference to the original array.
        int[][] trans= new int[matrix.length][matrix.length];
        for (int i= 0; i < matrix.length; i++){
            for (int j= i; j < matrix.length; j++){
                trans[i][j]= matrix[j][i];
                trans[j][i]= matrix[i][j];
            }
        }
        return trans;
    }

    public static int[][] matrixMultiply(int[][] A, int[][] B){
        int[][] matrix= new int[A.length][A.length];
        for (int i= 0; i < A.length; i++){
            for (int j= 0; j < A.length; j++){
                for (int k= 0; k < A.length; k++){
                    matrix[i][j]+= A[i][k]*B[k][j];
                }
            }
        }
        return matrix;
    }
}
