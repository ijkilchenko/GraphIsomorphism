package com.gi.gi;
import java.util.BitSet;

/**
 * User: ijk
 * Date: 1/4/14
 */
public class BitMatrix {
    public BitSet[] matrix;

    public BitMatrix(){

    }

    public BitMatrix(int n){ //Initialize a square binary matrix with side n.
        this.matrix= new BitSet[n];
        for (int i= 0; i < n; i++){
            this.matrix[i]= new BitSet(n);
        }
    }

    public void setBit(int i, int j, boolean value){
        this.matrix[i].set(j, value);
    }

    public boolean getBit(int i, int j){
        return this.matrix[i].get(j);
    }

    public int getSize(){
        return  this.matrix.length;
    }

    public static BitMatrix transpose(BitMatrix matrix){
        int n= matrix.getSize();
        BitMatrix transpose= new BitMatrix(n);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                if (matrix.getBit(i, j) == true){
                    transpose.setBit(j, i, true); //Note: default value is set to false.
                }
            }
        }
        return transpose;
    }

}
