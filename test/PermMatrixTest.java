import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * User: ijk
 * Date: 1/20/14
 */
public class PermMatrixTest {
    int n= 1024; //Test matrix size.

    @Test
    public void testMakeRandom() throws Exception {
        BitMatrix matrix= PermMatrix.makeRandom(n);
        BitMatrix identity= PermMatrix.multiply(matrix, matrix);

        Random random= new Random();
        random.nextInt(n);

        for (int j= 0; j < n; j++){
            int value= random.nextInt(n);
            int key= value;
            for (int i= 0; i < n; i++){
                if (matrix.getBit(value, i) == true){
                    value= i;
                    break;
                }
            }
            for (int i= 0; i < n; i++){
                if (matrix.getBit(value, i) == true){
                    value= i;
                    break;
                }
            }
            for (int i= 0; i < n; i++){
                if (identity.getBit(key, i) == true){
                    assertEquals(i, value);
                }
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

        BitMatrix identity= PermMatrix.multiply(matrix, matrix);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(matrix.getBit(i, j), identity.getBit(i, j));
            }
        }

        BitMatrix A= new BitMatrix(n);
        Random random= new Random();
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                A.setBit(i, j, (random.nextInt(2) == 1)? true: false);
            }
        }

        BitMatrix B= new BitMatrix();
        B= PermMatrix.makeRandom(n);

        /*Test A*B */

        BitMatrix product= new BitMatrix(n);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                for (int k= 0; k < n; k++){
                    if (A.getBit(i, k) && B.getBit(k, j)){
                        if (product.getBit(i, j) == true){
                            fail();
                        }
                        product.setBit(i, j, true);
                    }
                }
            }
        }

        BitMatrix C= PermMatrix.multiply(A,B);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(C.getBit(i, j), product.getBit(i, j));
            }
        }

        /*Test B*A */

        product= new BitMatrix(n);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                for (int k= 0; k < n; k++){
                    if (B.getBit(i, k) && A.getBit(k, j)){
                        if (product.getBit(i, j) == true){
                            fail();
                        }
                        product.setBit(i, j, true);
                    }
                }
            }
        }

        C= PermMatrix.multiply(B,A);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(C.getBit(i, j), product.getBit(i, j));
            }
        }
    }
}
