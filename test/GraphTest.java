import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: ijk
 * Date: 1/21/14
 */
public class GraphTest {
    @Test
    public void testMakeTable() throws Exception {
        /* connected graph
           1--4
           |  |
        0--3--2

         */

        int n= 5;
        BitMatrix adjMatrix= new BitMatrix(n);
        adjMatrix.setBit(0, 3, true);
        adjMatrix.setBit(1, 3, true);
        adjMatrix.setBit(1, 4, true);
        adjMatrix.setBit(2, 3, true);
        adjMatrix.setBit(2, 4, true);
        adjMatrix.setBit(3, 0, true);
        adjMatrix.setBit(3, 1, true);
        adjMatrix.setBit(3, 2, true);
        adjMatrix.setBit(4, 1, true);
        adjMatrix.setBit(4, 2, true);
        Graph G1= new Graph(adjMatrix);
        Table table= Graph.makeTable(G1, 0);
        assertEquals(0, table.first.get(0));
        assertEquals(3, table.get(1));
        assertEquals(1, table.get(2));
        assertEquals(2, table.get(3));
        assertEquals(4, table.get(4));
        assertEquals(1, table.first.length);
        assertEquals(1, table.first.next.length);
        assertEquals(2, table.first.next.next.length);
        assertEquals(4, table.length);

        table= Graph.makeTable(G1, 1);
        assertEquals(1, table.first.get(0));
        assertEquals(3, table.get(1));
        assertEquals(4, table.get(2));
        assertEquals(0, table.get(3));
        assertEquals(2, table.get(4));
        assertEquals(1, table.first.length);
        assertEquals(2, table.first.next.length);
        assertEquals(2, table.first.next.next.length);
        assertEquals(3, table.length);

        /* non-connected graph
           1--4

        0--3--2

         */

        n= 5;
        adjMatrix= new BitMatrix(n);
        adjMatrix.setBit(0, 3, true);
        adjMatrix.setBit(1, 4, true);
        adjMatrix.setBit(2, 3, true);
        adjMatrix.setBit(3, 0, true);
        adjMatrix.setBit(3, 2, true);
        adjMatrix.setBit(4, 1, true);
        Graph G2= new Graph(adjMatrix);
        Table table2= Graph.makeTable(G2, 0);
        assertEquals(0, table2.first.get(0));
        assertEquals(3, table2.get(1));
        assertEquals(2, table2.get(2));
        assertEquals(-1, table2.get(3));
        assertEquals(1, table2.first.length);
        assertEquals(1, table2.first.next.length);
        assertEquals(1, table2.first.next.next.length);
        assertEquals(3, table2.length);

        table2= Graph.makeTable(G2, 1);
        assertEquals(1, table2.first.get(0));
        assertEquals(4, table2.get(1));
        assertEquals(-1, table2.get(2));
        assertEquals(1, table2.first.length);
        assertEquals(1, table2.first.next.length);
        assertEquals(2, table2.length);

    }

    @Test
    public void testAreIsomorphic() throws Exception {
        int n= 3;
        int t= 10;

        long totalTime= 0;

        /*Test isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n);

            BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);

            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrixPerm);

            long startTime = System.nanoTime();
            Table map= Graph.areIsomorphic(G1,G2);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            totalTime += duration;

            if (map == null){
                System.out.println("Error. Map return between isomorphic was null");
            }
            else{
                if (!Graph.checkEdges(G1, G2, map)){

                }
            }
        }
        System.out.print("Total time is " + totalTime/Math.pow(10,9) + " seconds.");

    }

    @Test
    public void testCheckEdges() throws Exception {
        /*
           1--4
           |  |
        0--3--2

         */

        int n= 5;
        BitMatrix adjMatrix= new BitMatrix(n);
        adjMatrix.setBit(0, 3, true);
        adjMatrix.setBit(1, 3, true);
        adjMatrix.setBit(1, 4, true);
        adjMatrix.setBit(2, 3, true);
        adjMatrix.setBit(2, 4, true);
        adjMatrix.setBit(3, 0, true);
        adjMatrix.setBit(3, 1, true);
        adjMatrix.setBit(3, 2, true);
        adjMatrix.setBit(4, 1, true);
        adjMatrix.setBit(4, 2, true);
        Graph G1= new Graph(adjMatrix);

        /*
           2--0
           |  |
        1--4--3

         */

        n= 5;
        BitMatrix adjMatrix2= new BitMatrix(n);
        adjMatrix2.setBit(1, 4, true);
        adjMatrix2.setBit(2, 4, true);
        adjMatrix2.setBit(2, 0, true);
        adjMatrix2.setBit(3, 4, true);
        adjMatrix2.setBit(3, 0, true);
        adjMatrix2.setBit(4, 1, true);
        adjMatrix2.setBit(4, 2, true);
        adjMatrix2.setBit(4, 3, true);
        adjMatrix2.setBit(0, 2, true);
        adjMatrix2.setBit(0, 3, true);
        Graph G2= new Graph(adjMatrix2);

        Table map= new Table();

        map.add(0, 0);
        map.add(1, 1);
        map.add(2, 2);
        map.add(3, 3);
        map.add(4, 4);

        map.add(0, 1);
        map.add(1, 2);
        map.add(2, 3);
        map.add(3, 4);
        map.add(4, 0);

        assertTrue(Graph.checkEdges(G1, G2, map));



    }
}
