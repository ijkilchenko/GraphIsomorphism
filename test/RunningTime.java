import org.junit.Test;

/**
 * User: ijk
 * Date: 1/21/14
 */
public class RunningTime {

    /*@Test
    public void RunningTime() throws Exception {
        int n= 256;
        int t= 5;

        long totalTime= 0;
        double totalCount= 0;

        *//*Test isomorphic graphs*//*
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n);

            BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);

            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrixPerm);

            AbstractTree[] tables1= new AbstractTree[n];
            AbstractTree[] tables2= new AbstractTree[n];

            for (int j= 0; j < n; j++){
                tables1[j]= Graph.BFS(G1, j);
                tables2[j]= Graph.BFS(G2, j);
            }

            long startTime = System.nanoTime();
            int count= 0;

            for (int j= 0; j < n; j++){
                for (int k= 0; k < n; k++){
                    if (Graph.checkConditions(new AbstractTree(), tables1[j], tables2[k], true)){
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

    @Test
    public void RunningTimeHeight() throws Exception {
        int n= 32;
        int t= 100;

        long totalTime= 0;
        double totalCount= 0;

        *//*Test isomorphic graphs*//*
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n);

            BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);

            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrixPerm);

            AbstractTree[] tables1= new AbstractTree[n];
            AbstractTree[] tables2= new AbstractTree[n];

            for (int j= 0; j < n; j++){
                tables1[j]= Graph.BFS(G1, j);
                tables2[j]= Graph.BFS(G2, j);
            }

            long startTime = System.nanoTime();
            int count= 0;

            for (int j= 0; j < 1; j++){
                for (int k= 0; k < n; k++){
                    if (Graph.checkHeight(new AbstractTree(), tables1[j], tables2[k], true)){
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
        System.out.println("Average number of height-compatible matches is \t" + totalCount/t + ".");
        System.out.println("Average time is \t\t" + totalTime/Math.pow(10,9)/t + " seconds.");

    }*/
}
