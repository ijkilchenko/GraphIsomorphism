import java.util.Random;

/**
 * User: ijk
 * Date: 1/4/14
 */
public class PermMatrix extends BitMatrix{

    public static BitMatrix makeRandom(int n){
        BitMatrix matrix= new BitMatrix(n);
        Random random= new Random();

        int[] list= new int[n];
        //Make set {1, 2, 3, ...}.
        for (int i= 0; i < n; i++){
            list[i]= i;
        }
        //Randomly permute set.
        for (int i= 0; i < n; i++){
            int index= random.nextInt(n-i)+i;
            int temp= list[index];
            list[index]= list[i];
            list[i]= temp;
        }
        //Finally put 1 in i-th row and list(i)-th column for all i until n.
        for (int i= 0; i < n; i++){
            matrix.setBit(i, list[i], true);
        }
        return matrix;
    }

    public static BitMatrix multiply(BitMatrix A, BitMatrix B){
        //Note: this method assumes at least one of A and B is a permutation matrix.
        int n= B.getSize();
        BitMatrix product= new BitMatrix(n);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                for (int k= 0; k < n; k++){
                    if (A.getBit(i, k) && B.getBit(k, j)){
                        product.setBit(i, j, true);
                        break; //Each element in the product matrix is at most 1, so we can break when we get 1.
                    }
                }
            }
        }
        return product;
    }

}
