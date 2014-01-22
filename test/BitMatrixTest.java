import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: ijk
 * Date: 1/20/14
 */
public class BitMatrixTest {
    int n= 256;

    @org.junit.Test
    public void testSetBit() throws Exception {
        BitMatrix matrix= new BitMatrix(n);
        assertFalse(matrix.matrix[n - 2].get(n - 2));
        matrix.setBit(n-2, n-2, true);
        assertTrue(matrix.matrix[n - 2].get(n - 2));
    }

    @Test
    public void testGetBit() throws Exception {
        BitMatrix matrix= new BitMatrix(n);
        assertEquals(matrix.getBit(n-2,n-2), matrix.matrix[n - 2].get(n - 2));
        matrix.setBit(n-2, n-2, true);
        assertEquals(matrix.getBit(n-2,n-2), matrix.matrix[n - 2].get(n - 2));
    }

    @Test
    public void testMakeTranspose() throws Exception {
        BitMatrix matrix= new BitMatrix(n);
        boolean[][] keys= new boolean[n][n];
        Random random= new Random();
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                keys[i][j]= (random.nextInt(1) == 1)? true: false;
                matrix.setBit(i, j, keys[i][j]);
            }
        }

        BitMatrix matrixTrans= BitMatrix.makeTranspose(matrix);

        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(matrix.getBit(i, j), matrixTrans.getBit(j, i));
            }
        }


        BitMatrix matrixIdentity= BitMatrix.makeTranspose(matrixTrans);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(matrix.getBit(i, j), matrixIdentity.getBit(i, j));
            }
        }

    }

    @Test
    public void testMultiply() throws Exception {

        BitMatrix matrix= new BitMatrix(n);
        matrix.setBit(0, 0, true);
        matrix.setBit(0, 1, false);
        matrix.setBit(1, 1, true);
        matrix.setBit(1, 0, false);

        BitMatrix matrixIdentity= BitMatrix.multiply(matrix, matrix);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(matrix.getBit(i, j), matrixIdentity.getBit(i, j));
            }
        }

    }
}
