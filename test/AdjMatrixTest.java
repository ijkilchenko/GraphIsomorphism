import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: ijk
 * Date: 1/26/14
 */
public class AdjMatrixTest {
    @Test
    public void testMakeRandom() throws Exception {

    }

    @Test
    public void testReadAdj() throws Exception {
        BitMatrix matrix= AdjMatrix.readAdj("kite");

        /*

        0-2
        |/|
        1-3

         */
        Graph G1= new Graph(matrix);
        assertEquals(0, G1.V[0].data);
        assertEquals(1, G1.V[1].data);
        assertEquals(2, G1.V[2].data);
        assertEquals(3, G1.V[3].data);
        assertEquals(2, G1.V[0].children.length);
        assertEquals(3, G1.V[1].children.length);
        assertEquals(3, G1.V[2].children.length);
        assertEquals(2, G1.V[3].children.length);
    }

}
