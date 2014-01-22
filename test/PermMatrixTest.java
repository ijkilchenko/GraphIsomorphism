import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * User: ijk
 * Date: 1/20/14
 */
public class PermMatrixTest {
    @Test
    public void testMakeRandom() throws Exception {

        int n= 256;
        BitMatrix matrix= PermMatrix.makeRandom(n);
        BitMatrix identity= BitMatrix.multiply(matrix, matrix);

        Random random= new Random();
        random.nextInt(n);

        for (int j= 0; j < n; j++){
            int map= random.nextInt(n);
            int source= map;
            for (int i= 0; i < n; i++){
                if (matrix.getBit(map, i) == true){
                    map= i;
                    break;
                }
            }
            for (int i= 0; i < n; i++){
                if (matrix.getBit(map, i) == true){
                    map= i;
                    break;
                }
            }
            for (int i= 0; i < n; i++){
                if (identity.getBit(source, i) == true){
                    assertEquals(i, map);
                }
            }
        }

    }
}
