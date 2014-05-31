package com.gi.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.gi.base.BitMatrix;
import com.gi.base.ConnectedGraph;
import com.gi.base.Graph;
import com.gi.base.Node;

public class GraphTest {
	
	@Test
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

	     

	    n= 6;
	    BitMatrix adjMatrix2= new BitMatrix(n);
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

	    BitMatrix adjMatrix3= new BitMatrix(n);
	    adjMatrix3.setBit(0, 2, true);
	    adjMatrix3.setBit(0, 3, true);
	    //adjMatrix3.setBit(0, 4, true);
	    //adjMatrix3.setBit(4, 0, true);
	    adjMatrix3.setBit(4, 5, true);
	    //adjMatrix3.setBit(4, 1, true);
	    //adjMatrix3.setBit(5, 1, true);
	    adjMatrix3.setBit(5, 4, true);
	    adjMatrix3.setBit(2, 0, true);
	    adjMatrix3.setBit(2, 3, true);
	    adjMatrix3.setBit(3, 0, true);
	    adjMatrix3.setBit(3, 2, true);
	    //adjMatrix3.setBit(1, 5, true);
	    //adjMatrix3.setBit(1, 4, true);

		ConnectedGraph G2 = new ConnectedGraph(adjMatrix2);
		Node[] V2 = G2.V;
		ConnectedGraph[] GG = Graph.separateIntoComponents(V2);
		
		assertFalse(GG.length != 1);
	    
		ConnectedGraph G3 = new ConnectedGraph(adjMatrix3);
		Node[] V3 = G3.V;
		ConnectedGraph[] GGG = Graph.separateIntoComponents(V3);
		
		assertFalse(GGG.length != 3);

	}

}
