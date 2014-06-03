package com.gi.test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

import com.gi.base.AdjMatrix;
import com.gi.base.BitMatrix;
import com.gi.base.ConnectedGraph;
import com.gi.base.Graph;
import com.gi.base.Map;
import com.gi.base.Node;
import com.gi.base.PermMatrix;

public class GraphTest {

	//@Test
	public void separateIntoComponentsTest() {
		/* non-connected graph
		1--4

		0--3--2

		*/
		int n = 5;
		BitMatrix adjMatrix = new BitMatrix(n);
		adjMatrix = new BitMatrix(n);
		adjMatrix.setBit(0, 3, true);
		adjMatrix.setBit(1, 4, true);
		adjMatrix.setBit(2, 3, true);
		adjMatrix.setBit(3, 0, true);
		adjMatrix.setBit(3, 2, true);
		adjMatrix.setBit(4, 1, true);
		ConnectedGraph G1 = new ConnectedGraph(adjMatrix);
		Node[] V1 = G1.V;
		ConnectedGraph[] G = Graph.separateIntoComponents(V1);

		assertFalse(G.length != 2);

		/*
		G2
		2--3
		 \/
		 1--0
		   / \
		  4--5

		G3
		5  1
		 \
		 4  0
		   / \
		  2--3
		*/

		n = 6;
		BitMatrix adjMatrix2 = new BitMatrix(n);
		adjMatrix2.setBit(0, 1, true);
		adjMatrix2.setBit(0, 4, true);
		adjMatrix2.setBit(0, 5, true);
		adjMatrix2.setBit(1, 0, true);
		adjMatrix2.setBit(1, 2, true);
		adjMatrix2.setBit(1, 3, true);
		adjMatrix2.setBit(2, 1, true);
		adjMatrix2.setBit(2, 3, true);
		adjMatrix2.setBit(3, 1, true);
		adjMatrix2.setBit(3, 2, true);
		adjMatrix2.setBit(4, 0, true);
		adjMatrix2.setBit(4, 5, true);
		adjMatrix2.setBit(5, 0, true);
		adjMatrix2.setBit(5, 4, true);

		BitMatrix adjMatrix3 = new BitMatrix(n);
		adjMatrix3.setBit(0, 2, true);
		adjMatrix3.setBit(0, 3, true);
		// adjMatrix3.setBit(0, 4, true);
		// adjMatrix3.setBit(4, 0, true);
		adjMatrix3.setBit(4, 5, true);
		// adjMatrix3.setBit(4, 1, true);
		// adjMatrix3.setBit(5, 1, true);
		adjMatrix3.setBit(5, 4, true);
		adjMatrix3.setBit(2, 0, true);
		adjMatrix3.setBit(2, 3, true);
		adjMatrix3.setBit(3, 0, true);
		adjMatrix3.setBit(3, 2, true);
		// adjMatrix3.setBit(1, 5, true);
		// adjMatrix3.setBit(1, 4, true);

		ConnectedGraph G2 = new ConnectedGraph(adjMatrix2);
		Node[] V2 = G2.V;
		ConnectedGraph[] GG = Graph.separateIntoComponents(V2);

		assertFalse(GG.length != 1);

		ConnectedGraph G3 = new ConnectedGraph(adjMatrix3);
		Graph GGG = new Graph(adjMatrix3);

		assertFalse(GGG.getNumOfComponents() != 3);

	}
	
	
	@Test
	public void findIsomorphismTest1() throws IOException {
		long startTime0 = System.nanoTime();

		//Default values 
		int n = 16;
		int t = 100;
		int p = 1;
		int q = 2;

		//Load new values from properties file 
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

		//Test isomorphic graphs
		for (int i = 0; i < t; i++) {
			BitMatrix adjMatrix = AdjMatrix.makeRandom(n, p, q);

			BitMatrix permMatrix = PermMatrix.makeRandom(n);
			BitMatrix permMatrixT = PermMatrix.transpose(permMatrix);
			BitMatrix adjMatrixPerm = PermMatrix.multiply(permMatrix, adjMatrix);
			adjMatrixPerm = PermMatrix.multiply(adjMatrixPerm, permMatrixT);

			Graph G1 = new Graph(adjMatrix);
			Graph G2 = new Graph(adjMatrixPerm);
			
			long startTime = System.nanoTime();
			Map[] map = Graph.findIsomorphism(G1, G2);
			long endTime = System.nanoTime();
			long duration = endTime - startTime;

			if (duration < minTime || minTime < 0) {
				minTime = duration;
			}
			if (duration > maxTime) {
				maxTime = duration;
			}

			totalTime += duration;

			for (int j = 0; j < G1.getNumOfComponents(); j++){
					
				if (map[j+1].length < G1.G[j].V.length) {
					System.out.println("Error. Map returned for " + j + "-th component is less than " + G1.G[j].V.length);
					fail();
				} else {
					long startCheck = System.nanoTime();
					if (!ConnectedGraph.checkAllEdges(G1.G[map[0].getKey(j)], G2.G[map[0].getValue(j)], map[j+1])) {
						System.out.println("Error. Non-null map between isomorphic graphs did not preserve edges");
						fail();
					}
					long endCheck = System.nanoTime();
					long checkDuration = endCheck - startCheck;
					totalCheckTime += checkDuration;
					// System.out.println("Check took " + checkDuration/Math.pow(10,9)+ " seconds");
				}
				System.out.println("Test " + i + " is finished for component " + j);
			}
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
	public void findIsomorphismTest2() {

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

		int n = 6;
		BitMatrix adjMatrix = new BitMatrix(n);
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

		BitMatrix adjMatrix2 = new BitMatrix(n);
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

		ConnectedGraph G11 = new ConnectedGraph(adjMatrix);
		ConnectedGraph G21 = new ConnectedGraph(adjMatrix2);

		Map map1 = ConnectedGraph.areIsomorphic(G11, G21, 6);

		assertFalse(map1.length != 6);

		Graph G1 = new Graph(adjMatrix);
		Graph G2 = new Graph(adjMatrix2);

		ConnectedGraph G12 = G1.G[0];
		ConnectedGraph G22 = G2.G[0];

		Map map2 = ConnectedGraph.areIsomorphic(G12, G22, 6);

		assertFalse(map2.length != 6);
		if (!ConnectedGraph.checkAllEdges(G12, G22, map2)) {
			System.out.println("Error. Non-null map between isomorphic graphs did not preserve edges");
			fail();
		}

		Map[] map = Graph.findIsomorphism(G1, G2);

		assertFalse(map.length != 2);

	}

	//@Test
	/*public void findIsomorphismTest3() {
		//Test sample graphs of size 5
		int n = 5;
		
		BitMatrix adjMatrix = new BitMatrix(n);
		adjMatrix.setBit(0, 1, true);
		adjMatrix.setBit(1, 0, true);
		adjMatrix.setBit(0, 2, true);
		adjMatrix.setBit(2, 0, true);

		BitMatrix adjMatrix2 = new BitMatrix(n);
		adjMatrix2.setBit(0, 1, true);
		adjMatrix2.setBit(1, 0, true);
		adjMatrix2.setBit(0, 3, true);
		adjMatrix2.setBit(3, 0, true);

		ConnectedGraph G11 = new ConnectedGraph(adjMatrix);
		ConnectedGraph G21 = new ConnectedGraph(adjMatrix2);

		Map map1 = ConnectedGraph.areIsomorphic(G11, G21);

		assertFalse(map1.length != n);
		
		Graph G12 = new Graph(adjMatrix);
		Graph G22 = new Graph(adjMatrix2);

		ConnectedGraph G13 = G12.G[0];
		ConnectedGraph G23 = G22.G[0];

		Map map2 = ConnectedGraph.areIsomorphic(G13, G23);

		assertFalse(map2.length != 3);
		
		Map[] map = Graph.findIsomorphism(G12, G22);

		assertFalse(map.length != 4);
	}
*/
}
