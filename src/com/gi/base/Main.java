package com.gi.base;
/**
 * User: ijk
 * Date: 1/4/14
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to Graph Isomorphism!");

        int n= 5;

        BitMatrix adjMatrix= AdjMatrix.makeRandom(n);

        BitMatrix permMatrix= PermMatrix.makeRandom(n);
        BitMatrix permMatrixTrans= PermMatrix.transpose(permMatrix);
        BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
        adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixTrans);

        ConnectedGraph G1= new ConnectedGraph(adjMatrix);
        ConnectedGraph G2= new ConnectedGraph(adjMatrixPerm);

        System.out.println(ConnectedGraph.areIsomorphic(G1,G1));

        System.out.println("Breakpoint!");

    }
}
