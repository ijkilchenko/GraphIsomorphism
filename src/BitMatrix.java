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

    public BitSet getRow(int i){
        return matrix[i];
    }

    public int getSize(){
        return  this.matrix.length;
    }

    public static BitMatrix makeTranspose(BitMatrix matrix){
        int n= matrix.getSize();
        BitMatrix transpose= new BitMatrix(n);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                if (matrix.getRow(i).get(j) == true){
                    transpose.getRow(j).set(i, true); //Default value is set to false.
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
                    if (A.getRow(i).get(k) && B.getRow(k).get(j)){
                        product.getRow(i).set(j, true);
                        break; //Each element in the product matrix is at most 1, so we can break when we get 1.
                    }
                }
            }
        }
        return product;

    }


}
