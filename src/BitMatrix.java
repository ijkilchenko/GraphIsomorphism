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

    public boolean getBit(int i, int j){
        return this.matrix[i].get(j);
    }

    public int getSize(){
        return  this.matrix.length;
    }

    public static BitMatrix makeTranspose(BitMatrix matrix){
        int n= matrix.getSize();
        BitMatrix transpose= new BitMatrix(n);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                if (matrix.getBit(i, j) == true){
                    transpose.setBit(j, i, true); //Default value is set to false.
                }
            }
        }
        return transpose;
    }

    public static BitMatrix multiply(BitMatrix A, BitMatrix B){
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
