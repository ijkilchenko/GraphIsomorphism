package com.gi.test;
import org.junit.Test;

import com.gi.base.BitMatrix;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * User: ijk
 * Date: 1/20/14
 */
public class BitMatrixTest {
    int n= 2048; //Test matrix size.

    @org.junit.Test
    public void testSetBit() throws Exception {
        BitMatrix matrix= new BitMatrix(n);
        Random random= new Random();
        for (int k= 0; k < 10; k++){
            int i= random.nextInt(n);
            int j= random.nextInt(n);
            assertFalse(matrix.matrix[i].get(j));
            matrix.setBit(i, j, true);
            assertTrue(matrix.matrix[i].get(j));
        }
    }

    @Test
    public void testGetBit() throws Exception {
        BitMatrix matrix= new BitMatrix(n);
        Random random= new Random();
        for (int k= 0; k < 10; k++){
            int i= random.nextInt(n);
            int j= random.nextInt(n);
            assertEquals(matrix.getBit(i,j), matrix.matrix[i].get(j));
            matrix.setBit(i, j, true);
            assertEquals(matrix.getBit(i,j), matrix.matrix[i].get(j));
        }
    }

    @Test
    public void testTranspose() throws Exception {
        BitMatrix matrix= new BitMatrix(n);
        boolean[][] keys= new boolean[n][n];
        Random random= new Random();
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                keys[i][j]= (random.nextInt(2) == 1)? true: false;
                matrix.setBit(i, j, keys[i][j]);
            }
        }

        BitMatrix matrixT= BitMatrix.transpose(matrix);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(matrix.getBit(i, j), matrixT.getBit(j, i));
            }
        }

        BitMatrix matrixTT= BitMatrix.transpose(matrixT);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                assertEquals(matrix.getBit(i, j), matrixTT.getBit(i, j));
            }
        }
    }

}
