import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: ijk
 * Date: 1/21/14
 */
public class ProgramChecker {
    
    @Test
    public void testAreIsomorphic() throws Exception {
        int n= 2048;
        int t= 2;
        int p= 1;
        int q= 2;

        long totalTime= 0;
        long totalCheckTime= 0;

        /*Test isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n, p, q);

            BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);

            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrixPerm);

            long startTime = System.nanoTime();
            Map map= Graph.areIsomorphic(G1,G2);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            totalTime += duration;

            if (map.length < n){
                System.out.println("Error. Map returned between isomorphic graphs was not of length " + n +".");
                fail();
            }
            else{
                long startCheck = System.nanoTime();
                if (!Graph.checkAllEdges(G1, G2, map)){
                    System.out.println("Error. Non-null map between isomorphic graphs did not preserve edges");
                    fail();
                }
                long endCheck= System.nanoTime();
                long checkDuration = endCheck - startCheck;
                totalCheckTime += checkDuration;
                //System.out.println("Check took " + checkDuration/Math.pow(10,9)+ " seconds");
            }
            System.out.println("Test " + i +" is finished!");
        }
        System.out.println("Average time is \t\t" + totalTime/Math.pow(10,9)/t + " seconds.");
        System.out.println("Average check time is \t" + totalCheckTime/Math.pow(10,9)/t + " seconds.");
    }

    @Test
    public void testAreNonIsomorphic() throws Exception {
        int n= 1024;
        int t= 2;

        long totalTime= 0;
        long totalCheckTime= 0;

        /*Test non-isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n);
            BitMatrix adjMatrix2= AdjMatrix.makeRandom(n);


            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrix2);

            long startTime = System.nanoTime();
            Map map= Graph.areIsomorphic(G1,G2);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            totalTime += duration;

            if (map == null || map.length == 0){
                System.out.println("Success. Graphs are non-isomorphic. Map is null. ");
            }
            else{
                long startCheck = System.nanoTime();
                if (!Graph.checkAllEdges(G1, G2, map)){
                    System.out.println("Improbable result. Two random graphs are isomorphic.");
                }
                long endCheck= System.nanoTime();
                long checkDuration = endCheck - startCheck;
                totalCheckTime += checkDuration;
                //System.out.println("Check took " + checkDuration/Math.pow(10,9)+ " seconds");
            }
            System.out.println("Test " + i +" is finished!");
        }
        System.out.println("Average time is \t\t" + totalTime/Math.pow(10,9)/t + " seconds.");
        System.out.println("Average check time is " + totalCheckTime/Math.pow(10,9)/t + " seconds.");
    }
    
}
