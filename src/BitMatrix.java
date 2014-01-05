import java.util.BitSet;

/**
 * User: ijk
 * Date: 1/4/14
 */
public class BitMatrix {
    BitSet[] matrix;

    public BitMatrix(){

    }

    public BitMatrix(int n){
        this.matrix= new BitSet[n];
        for (int i= 0; i < n; i++){
            this.matrix[i]= new BitSet(n); //matrix[i].get(j) gives (i,j)th element.
        }
    }

    public void setBit(int i, int j, boolean flag){
        this.matrix[i].set(j, flag);

    }

    public static BitSet[] makeTranspose(BitSet[] matrix){
        BitSet[] transpose= new BitSet[matrix.length];
        for (int i= 0; i < matrix.length; i++){
            transpose[i]= new BitSet(matrix.length);
            for (int j= 0; j < matrix.length; j++){
                if (matrix[i].get(j) == true){
                    transpose[i].set(j, true); //Default value is set to false.
                }
            }
        }
        return transpose;
    }

    public static BitSet[] multiply(BitSet[] A, BitSet[] B){
        BitSet[] product= new BitSet[B.length];
        for (int i= 0; i < B.length; i++){
            product[i]= new BitSet(B.length);
            for (int j= 0; j < B.length; j++){
                for (int k= 0; k < B.length; k++){
                    if (A[i].get(k) && B[k].get(j)){
                        product[i].set(j, true);
                        break; //Each element in the product matrix is at most 1, so we can break when we get 1.
                    }
                }
            }
        }
        return product;

    }


}
