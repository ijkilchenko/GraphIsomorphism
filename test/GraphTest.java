import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: ijk
 * Date: 1/21/14
 */
public class GraphTest {
    @Test
    public void testMakeTable() throws Exception {
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


    }

    @Test
    public void testAreIsomorphic() throws Exception {
        int n= 3;

        BitMatrix adjMatrix= AdjMatrix.makeRandom(n);

        BitMatrix permMatrix= PermMatrix.makeRandom(n);
        BitMatrix permMatrixTrans= PermMatrix.transpose(permMatrix);
        BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
        adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixTrans);

        Graph G1= new Graph(adjMatrix);
        Graph G2= new Graph(adjMatrixPerm);

        Graph.areIsomorphic(G1,G2);

    }
}
