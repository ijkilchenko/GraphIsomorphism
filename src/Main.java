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

        BitMatrix permMatrixTrans= PermMatrix.makeTranspose(permMatrix);

        BitMatrix adjMatrixPerm= BitMatrix.multiply(permMatrix, adjMatrix);
        adjMatrixPerm= BitMatrix.multiply(adjMatrixPerm, permMatrixTrans);

        Graph G1= new Graph(adjMatrix);
        Graph.areIsomorphic(G1,G1);

        System.out.println("Breakpoint!");


    }
}
