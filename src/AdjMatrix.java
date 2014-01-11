import java.util.Random;

/**
 * User: ijk
 * Date: 1/4/14
 */
public class AdjMatrix extends BitMatrix{

    public static BitMatrix makeRandom(int n){
        BitMatrix matrix= new BitMatrix(n);
        Random random= new Random();

        for (int i= 0; i < n; i++){
            for (int j= i; j < n; j++){
                if (i == j){
                    matrix.setBit(i, j, false);
                }
                else{
                    boolean flag= random.nextBoolean();
                    matrix.setBit(i, j, flag);
                    matrix.setBit(j, i, flag);
                }
            }
        }
        return matrix;
    }

}
