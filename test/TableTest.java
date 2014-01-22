import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: ijk
 * Date: 1/20/14
 */
public class TableTest {
    @Test
    public void testAdd() throws Exception {
        Table table= new Table();
        assertEquals(0, table.length);
        table.add(0, 123);
        assertEquals(1, table.length);
        assertEquals(1, table.first.length);
        table.add(0, 234);
        assertEquals(1, table.length);
        assertEquals(2, table.first.length);
        table.add(1, 345);
        assertEquals(2, table.length);
        assertEquals(2, table.first.length);
        assertEquals(1, table.first.next.length);
        table.add(2, 456);
        assertEquals(3, table.length);
        assertEquals(1, table.first.next.length);
        assertEquals(1, table.first.next.next.length);

    }

    @Test
    public void popTest() throws Exception {
        Table table= new Table();
        table.add(0, 123);
        table.add(0, 234);
        table.add(1, 345);
        table.add(2, 456);
        assertEquals(3, table.length);
        table.pop();
        assertEquals(2, table.length);
        table.add(2, 456);
        assertEquals(3, table.length);
        table.pop();
        table.pop();
        table.pop();
        assertEquals(0, table.length);

    }

    @Test
    public void getWidthTest() throws Exception {
        Table table= new Table();
        table.add(0, 123);
        table.add(0, 234);
        table.add(1, 345);
        table.add(2, 456);
        assertEquals(2, table.getWidth(0));
        assertEquals(1, table.getWidth(1));
        assertEquals(1, table.getWidth(2));

    }

    @Test
    public void testGet() throws Exception {
        Table table= new Table();
        table.add(0, 123);
        table.add(0, 234);
        table.add(1, 345);
        table.add(2, 456);
        assertEquals(123, table.get(0));
        assertEquals(234, table.get(1));
        assertEquals(345, table.get(2));
        assertEquals(456, table.get(3));
    }
}
