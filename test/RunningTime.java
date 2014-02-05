import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: ijk
 * Date: 1/21/14
 */
public class RunningTime {

    @Test
    public void RunningTime() throws Exception {
        int n= 1024;
        int t= 1;

        long totalTime= 0;
        double totalCount= 0;

        /*Test isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n);

            BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);

            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrixPerm);

            Table[] tables1= new Table[n];
            Table[] tables2= new Table[n];

            for (int j= 0; j < n; j++){
                tables1[j]= Graph.makeTable(G1, j);
                tables2[j]= Graph.makeTable(G2, j);
            }

            long startTime = System.nanoTime();
            int count= 0;

            for (int j= 0; j < n; j++){
                for (int k= 0; k < n; k++){
                    if (Graph.checkConditions(new Table(), tables1[j], tables2[k], true)){
                        count++;
                    }
                }
            }
            totalCount += count;

            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            totalTime += duration;

            System.out.println("Test " + i +" is finished!");
        }
        System.out.println("Average number of compatible matches is \t" + totalCount/n/t + ".");
        System.out.println("Average time is \t\t" + totalTime/Math.pow(10,9)/t + " seconds.");

    }
}
