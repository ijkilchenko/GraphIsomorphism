package com.gi.test;

import com.gi.base.*;

import org.junit.Test;

import java.io.FileReader;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * User: ijk Date: 1/21/14
 */
public class ConnectedGraphTest {
	//@Test
	/*public void testMakeTree() throws Exception {
		 connected graph
		   1--4
		   |  |
		0--3--2

		 

		int n = 5;
		BitMatrix adjMatrix = new BitMatrix(n);
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
		ConnectedGraph G1 = new ConnectedGraph(adjMatrix);
		AbstractTree tree = ConnectedGraph.BFS(G1, 0);
		assertEquals(3, tree.height);
		assertEquals(1, tree.width[1]);
		assertEquals(2, tree.width[2]);
		assertEquals(3, tree.getLevel(4));

		tree = ConnectedGraph.BFS(G1, 1);
		assertEquals(2, tree.height);
		assertEquals(2, tree.width[2]);
		assertEquals(2, tree.width[2]);
		assertEquals(2, tree.getLevel(0));

		 non-connected graph
		   1--4

		0--3--2

		 

		n = 5;
		adjMatrix = new BitMatrix(n);
		adjMatrix.setBit(0, 3, true);
		adjMatrix.setBit(1, 4, true);
		adjMatrix.setBit(2, 3, true);
		adjMatrix.setBit(3, 0, true);
		adjMatrix.setBit(3, 2, true);
		adjMatrix.setBit(4, 1, true);
		ConnectedGraph G2 = new ConnectedGraph(adjMatrix);
		AbstractTree tree2 = ConnectedGraph.BFS(G2, 0);
		assertEquals(2, tree2.height);
		assertEquals(1, tree2.width[1]);
		assertEquals(1, tree2.width[2]);
		assertEquals(1, tree2.getLevel(3));

		tree2 = ConnectedGraph.BFS(G2, 1);
		assertEquals(1, tree2.height);
		assertEquals(1, tree2.width[0]);
		assertEquals(1, tree2.width[1]);
		assertEquals(1, tree2.getLevel(4));
	}

	//@Test
	public void testPredefinedGraphs() throws Exception {
		// TODO: Fix this method...
		BitMatrix adjMatrix = AdjMatrix.readAdj("hypersquare");
		BitMatrix adjMatrix2 = AdjMatrix.readAdj("kite");

		ConnectedGraph G1 = new ConnectedGraph(adjMatrix);
		ConnectedGraph G2 = new ConnectedGraph(adjMatrix2);
		int n = G1.V.length;

		Map map = ConnectedGraph.areIsomorphic(G1, G2);

		if (map.length < n) {
			System.out.println("Error. Map returned between isomorphic graphs was not of length " + n + ".");
			fail();
		} else {
			long startCheck = System.nanoTime();
			if (!ConnectedGraph.checkAllEdges(G1, G2, map)) {
				System.out.println("Error. Non-null map between isomorphic graphs did not preserve edges");
				fail();
			}
			// System.out.println("Check took " + checkDuration/Math.pow(10,9)+ " seconds");
		}
	}
*/
	@Test
	public void testAreIsomorphic() throws Exception {
		long startTime0 = System.nanoTime();

		/* Default values */
		int n = 16;
		int t = 100;
		int p = 1;
		int q = 2;

		/* Load new values from properties file */
		FileReader reader = new FileReader("./graph.properties");
		Properties properties = new Properties();
		properties.load(reader);
		n = Integer.parseInt(properties.getProperty("n"));
		t = Integer.parseInt(properties.getProperty("t"));
		p = Integer.parseInt(properties.getProperty("p"));
		q = Integer.parseInt(properties.getProperty("q"));

		long totalTime = 0;
		long minTime = -1;
		long maxTime = 0;
		long totalCheckTime = 0;

		/*Test isomorphic graphs*/
		for (int i = 0; i < t; i++) {
			BitMatrix adjMatrix = AdjMatrix.makeRandom(n, p, q);

			BitMatrix permMatrix = PermMatrix.makeRandom(n);
			BitMatrix permMatrixT = PermMatrix.transpose(permMatrix);
			BitMatrix adjMatrixPerm = PermMatrix.multiply(permMatrix, adjMatrix);
			adjMatrixPerm = PermMatrix.multiply(adjMatrixPerm, permMatrixT);

			ConnectedGraph G1 = new ConnectedGraph(adjMatrix);
			ConnectedGraph G2 = new ConnectedGraph(adjMatrixPerm);

			long startTime = System.nanoTime();
			Map map = ConnectedGraph.areIsomorphic(G1, G2, n);
			long endTime = System.nanoTime();
			long duration = endTime - startTime;

			if (duration < minTime || minTime < 0) {
				minTime = duration;
			}
			if (duration > maxTime) {
				maxTime = duration;
			}

			totalTime += duration;

			if (map.length < n) {
				System.out.println("Error. Map returned between isomorphic graphs was not of length " + n + ".");
				fail();
			} else {
				long startCheck = System.nanoTime();
				if (!ConnectedGraph.checkAllEdges(G1, G2, map)) {
					System.out.println("Error. Non-null map between isomorphic graphs did not preserve edges");
					fail();
				}
				long endCheck = System.nanoTime();
				long checkDuration = endCheck - startCheck;
				totalCheckTime += checkDuration;
				// System.out.println("Check took " + checkDuration/Math.pow(10,9)+ " seconds");
			}
			System.out.println("Test " + i + " is finished!");
		}
		System.out.println("Average time is \t\t" + totalTime / Math.pow(10, 9) / t + " seconds.");
		System.out.println("Min time is \t\t" + minTime / Math.pow(10, 9) + " seconds.");
		System.out.println("Max time is \t\t" + maxTime / Math.pow(10, 9) + " seconds.");
		System.out.println("Average check time is \t" + totalCheckTime / Math.pow(10, 9) / t + " seconds.");

		long endTime0 = System.nanoTime();
		long duration0 = endTime0 - startTime0;
		System.out.println("Total time spent in method is \t\t" + duration0 / Math.pow(10, 9) + " seconds.");
	}

	//@Test
	public void testAreNonIsomorphic() throws Exception {
		long startTime0 = System.nanoTime();

		/* Default values */
		int n = 2048;
		int t = 10;
		int p = 1;
		int q = 2;

		/* Load new values from properties file */
		FileReader reader = new FileReader("./graph.properties");
		Properties properties = new Properties();
		properties.load(reader);
		n = Integer.parseInt(properties.getProperty("n"));
		t = Integer.parseInt(properties.getProperty("t"));
		p = Integer.parseInt(properties.getProperty("p"));
		q = Integer.parseInt(properties.getProperty("q"));

		long totalTime = 0;
		long minTime = -1;
		long maxTime = 0;
		long totalCheckTime = 0;

		/*Test non-isomorphic graphs*/
		for (int i = 0; i < t; i++) {
			BitMatrix adjMatrix = AdjMatrix.makeRandom(n, p, q);
			BitMatrix adjMatrix2 = AdjMatrix.makeRandom(n, p, q);

			ConnectedGraph G1 = new ConnectedGraph(adjMatrix);
			ConnectedGraph G2 = new ConnectedGraph(adjMatrix2);

			long startTime = System.nanoTime();
			Map map = ConnectedGraph.areIsomorphic(G1, G2, n);
			long endTime = System.nanoTime();
			long duration = endTime - startTime;

			if (duration < minTime || minTime < 0) {
				minTime = duration;
			}
			if (duration > maxTime) {
				maxTime = duration;
			}

			totalTime += duration;

			if (map == null || map.length == 0) {
				System.out.println("Success. Graphs are non-isomorphic. Map is null. ");
			} else {
				long startCheck = System.nanoTime();
				if (!ConnectedGraph.checkAllEdges(G1, G2, map)) {
					System.out.println("Improbable result. Two random graphs are isomorphic.");
				}
				long endCheck = System.nanoTime();
				long checkDuration = endCheck - startCheck;
				totalCheckTime += checkDuration;
				// System.out.println("Check took " + checkDuration/Math.pow(10,9)+ " seconds");
			}
			System.out.println("Test " + i + " is finished!");
		}
		System.out.println("Average time is \t\t" + totalTime / Math.pow(10, 9) / t + " seconds.");
		System.out.println("Min time is \t\t" + minTime / Math.pow(10, 9) + " seconds.");
		System.out.println("Max time is \t\t" + maxTime / Math.pow(10, 9) + " seconds.");
		System.out.println("Average check time is \t" + totalCheckTime / Math.pow(10, 9) / t + " seconds.");

		long endTime0 = System.nanoTime();
		long duration0 = endTime0 - startTime0;
		System.out.println("Total time spent in method is \t\t" + duration0 / Math.pow(10, 9) + " seconds.");
	}

	
	//@Test
	public void testAreIsomorphicSimple() throws Exception {
		
		/*
	     G1
	    2--3
	     \/
	     1--0
	       / \
	      4--5

	       G2
	    5--1
	     \/
	     4--0
	       / \
	      2--3
		
		*/
	     

	    int n= 6;
	    BitMatrix adjMatrix= new BitMatrix(n);
	    adjMatrix.setBit(0, 1, true);
	    adjMatrix.setBit(0, 4, true);
	    adjMatrix.setBit(0, 5, true);
	    adjMatrix.setBit(1, 0, true);
	    adjMatrix.setBit(1, 2, true);
	    adjMatrix.setBit(1, 3, true);
	    adjMatrix.setBit(2, 1, true);
	    adjMatrix.setBit(2, 3, true);
	    adjMatrix.setBit(3, 1, true);
	    adjMatrix.setBit(3, 2, true);
	    adjMatrix.setBit(4, 0, true);
	    adjMatrix.setBit(4, 5, true);
	    adjMatrix.setBit(5, 0, true);
	    adjMatrix.setBit(5, 4, true);

	    BitMatrix adjMatrix2= new BitMatrix(n);
	    adjMatrix2.setBit(0, 2, true);
	    adjMatrix2.setBit(0, 3, true);
	    adjMatrix2.setBit(0, 4, true);
	    adjMatrix2.setBit(4, 0, true);
	    adjMatrix2.setBit(4, 5, true);
	    adjMatrix2.setBit(4, 1, true);
	    adjMatrix2.setBit(5, 1, true);
	    adjMatrix2.setBit(5, 4, true);
	    adjMatrix2.setBit(2, 0, true);
	    adjMatrix2.setBit(2, 3, true);
	    adjMatrix2.setBit(3, 0, true);
	    adjMatrix2.setBit(3, 2, true);
	    adjMatrix2.setBit(1, 5, true);
	    adjMatrix2.setBit(1, 4, true);

	    ConnectedGraph G1= new ConnectedGraph(adjMatrix);
	    ConnectedGraph G2= new ConnectedGraph(adjMatrix2);

	    Map map= ConnectedGraph.areIsomorphic(G1,G2, G1.V.length);
	    assertTrue(ConnectedGraph.checkAllEdges(G1, G2, map));
	}

	//@Test
	public void testCheckConditions() throws Exception {
		/*
	     G1
	    2--3
	     \/
	     1--0
	       / \
	      4--5

	       G2
	    5--1
	     \/
	     4--0
	       / \
	      2--3
	    */

	     

	    int n= 6;
	    BitMatrix adjMatrix= new BitMatrix(n);
	    adjMatrix.setBit(0, 1, true);
	    adjMatrix.setBit(0, 4, true);
	    adjMatrix.setBit(0, 5, true);
	    adjMatrix.setBit(1, 0, true);
	    adjMatrix.setBit(1, 2, true);
	    adjMatrix.setBit(1, 3, true);
	    adjMatrix.setBit(2, 1, true);
	    adjMatrix.setBit(2, 3, true);
	    adjMatrix.setBit(3, 1, true);
	    adjMatrix.setBit(3, 2, true);
	    adjMatrix.setBit(4, 0, true);
	    adjMatrix.setBit(4, 5, true);
	    adjMatrix.setBit(5, 0, true);
	    adjMatrix.setBit(5, 4, true);

	    BitMatrix adjMatrix2= new BitMatrix(n);
	    adjMatrix2.setBit(0, 2, true);
	    adjMatrix2.setBit(0, 3, true);
	    adjMatrix2.setBit(0, 4, true);
	    adjMatrix2.setBit(4, 0, true);
	    adjMatrix2.setBit(4, 5, true);
	    adjMatrix2.setBit(4, 1, true);
	    adjMatrix2.setBit(5, 1, true);
	    adjMatrix2.setBit(5, 4, true);
	    adjMatrix2.setBit(2, 0, true);
	    adjMatrix2.setBit(2, 3, true);
	    adjMatrix2.setBit(3, 0, true);
	    adjMatrix2.setBit(3, 2, true);
	    adjMatrix2.setBit(1, 5, true);
	    adjMatrix2.setBit(1, 4, true);

	    ConnectedGraph G1= new ConnectedGraph(adjMatrix);
	    ConnectedGraph G2= new ConnectedGraph(adjMatrix2);

	    AbstractTree tree1 = new AbstractTree(G1.V.length);
	    tree1 = ConnectedGraph.BFS(G1, 3, G1.V.length);
	    AbstractTree tree2 = new AbstractTree(G2.V.length);
	    tree2 = ConnectedGraph.BFS(G2, 2, G2.V.length);

	    Map map= new Map(6);
	    map.add(0, 0, 0);
	    map.add(1, 1, 4);
	    map.add(2, 2, 2);

	    assertFalse(ConnectedGraph.checkConditions(map, tree1, tree2, false));
	}

	//@Test
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
	    ConnectedGraph G1= new ConnectedGraph(adjMatrix);

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
	    ConnectedGraph G2= new ConnectedGraph(adjMatrix2);

	    Map map= new Map(n);

	    map.add(0, 0, 1);
	    map.add(1, 1, 2);
	    map.add(2, 2, 3);
	    map.add(3, 3, 4);
	    map.add(4, 4, 0);

	    long startTime = System.nanoTime();
	    assertTrue(ConnectedGraph.checkAllEdges(G1, G2, map));
	    long endTime = System.nanoTime();
	    long duration = endTime - startTime;
	    System.out.println("Check took " + duration/Math.pow(10,9)+ " seconds");
	}
	
}
