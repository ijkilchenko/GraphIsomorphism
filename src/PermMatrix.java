import java.util.Random;

/**
 * User: ijk
 * Date: 1/4/14
 */
public class PermMatrix {

    public static BitMatrix makeRandom(int n){
        BitMatrix matrix= new BitMatrix(n);
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

        for (int i= 0; i < n; i++){
            matrix.setBit(i, list[i], true);
        }
        return matrix;
    }

}
