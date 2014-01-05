/**
 * User: ijk
 * Date: 1/4/14
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to Graph Isomorphism!");

        int n= 5;

        BitMatrix adjMatrix= new AdjMatrix(n);
        adjMatrix= AdjMatrix.makeRandom(n);

        BitMatrix permMatrix= new PermMatrix(n);
        permMatrix= PermMatrix.makeRandom(n);

        BitMatrix permMatrixTrans= new PermMatrix(n);
        permMatrixTrans= PermMatrix.makeTranspose(permMatrix);

        BitMatrix adjMatrixPerm= new AdjMatrix(n);
        adjMatrixPerm= BitMatrix.multiply(permMatrix, adjMatrix);
        adjMatrixPerm= BitMatrix.multiply(adjMatrixPerm, permMatrixTrans);

        System.out.println("Breakpoint!");



    }
}
