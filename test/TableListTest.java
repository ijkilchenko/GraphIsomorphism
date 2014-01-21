import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: ijk
 * Date: 1/20/14
 */
public class TableListTest {
    @Test
    public void testAdd() throws Exception {
        TableList tableList= new TableList();
        tableList.add(123);
        assertEquals(123, tableList.start.next.data);
        assertEquals(1, tableList.length);
        tableList.add(234);
        assertEquals(234, tableList.start.next.next.data);
        assertEquals(2, tableList.length);

    }

    @Test
    public void testFindNode() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        TableList tableList= new TableList();
        tableList.add(123);
        tableList.add(234);
        assertEquals(123, tableList.get(0));
        assertEquals(234, tableList.get(1));

    }
}
