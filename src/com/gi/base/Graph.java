package com.gi.base;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ijk Date: 1/4/14
 */
public class Graph {
	public ConnectedGraph[] G;

	public Graph(BitMatrix matrix) {
		Node[] V;
		
		int n = matrix.getSize();
		V = new Node[n];
		for (int i = 0; i < n; i++) {
			V[i] = new Node(i);
		}
		for (int i = 0; i < n; i++) {
			int childrenSize = 0;
			for (int j = 0; j < n; j++) {
				if (matrix.getBit(i, j) == true) {
					childrenSize++;
				}
			}
			Node[] C = new Node[childrenSize];
			int k = 0;
			for (int j = 0; j < n; j++) {
				if (matrix.getBit(i, j) == true) {
					C[k] = V[j];
					k++;
				}
			}
			V[i].addChildren(C);
		}
		
		this.G = separateIntoComponents(V);
	}

	public static ConnectedGraph[] separateIntoComponents(Node[] V) {		
		int n = V.length;
		List<List<Node>> V1 = new ArrayList<List<Node>>();
		
		int a = 0;		
		boolean[] visited = new boolean[n];
		int b = 0;
		while (b < n){
			if (visited[b] == false){
				
				int[] queue = new int[n];
				int m = b;
				visited[m] = true;
				queue[0] = m;
				
				int l = 1; // Marks the end of the queue in the array.
		
				int i = 0; // Marks the beginning of the queue in the array.
				V1.add(new ArrayList<Node>());
				V1.get(a).add(V[m]);
				while (i < l) { // Check if queue is empty.
					int r = queue[i];
					i++;
					for (int j = 0; j < V[r].children.length; j++) {
						Node s = V[r].children[j];
						if (visited[s.data] == false) {
							visited[s.data] = true;
							V1.get(a).add(s);
							queue[l] = s.data;
							l++;
						}
					}
				}
				a++;
			}
			b++;
		}
		
		ConnectedGraph[] Gfinal = new ConnectedGraph[V1.size()];		
		for (int d = 0; d < V1.size(); d++){
			Node[] Vfinal = new Node[V1.get(d).size()];
			for (int c = 0; c < Vfinal.length; c++){
				Vfinal[c] = V1.get(d).get(c);
			}
			Gfinal[d] = new ConnectedGraph();
			Gfinal[d].V = Vfinal;
		}
		
		return Gfinal;
	}

	public int getNumOfComponents() {
		return this.G.length;
	}

	public static Map[] findIsomorphism(Graph G1, Graph G2) {		
		int n1 = G1.getNumOfComponents();
		int n2 = G2.getNumOfComponents();
		
		if (n1 != n2){
			System.out.println("The number of connected components does not match!");
			return null;
		}
		
		Map[] graphMap = new Map[n1 + 1];
		graphMap[0] = new Map(n1 + 1);
		boolean[] mapped = new boolean[n1];
		
		for (int i = 0; i < n1; i++){
			Map map;
			boolean flag = false; 
			for (int j = 0; j < n2; j++){
				if (mapped[j] != true){
					map = ConnectedGraph.areIsomorphic(G1.G[i], G2.G[j]);
					if (map != null && map.length != 0){
						flag = true;
						graphMap[0].add(i, i, j);
						graphMap[i+1] = map;
						mapped[j] = true;
						break;
					}
				}
			}
			if (flag == false) {
				System.out.println("We could not find a map for one of the connected components!");
				return null;
			}
		}
		
		return graphMap;
	}

	/*public static Map areIsomorphic(Graph G1, Graph G2) {
		int n = G1.V.length;
		Map map = new Map(n);
		int numOp = 0;

		if (n != G2.V.length) {
			System.out.println("False. Graphs are of different size! ");
			return null;
		}

		AbstractTree[] trees1 = new AbstractTree[n];
		AbstractTree[] trees2 = new AbstractTree[n];

		for (int i = 0; i < n; i++) {
			trees1[i] = Graph.BFS(G1, i);
			trees2[i] = Graph.BFS(G2, i);
		}

		boolean[] matched = new boolean[n]; // Keep track of matched nodes in G2.
		int mismatched = -1;

		for (int i = 0; i < n; i++) {
			int length = map.length;
			for (int j = 0; j < n; j++) {
				            	if (numOp > 200){
				            		System.out.println("Number of operations is " + numOp);
				            	}
				if (matched[j] != true && j > mismatched) {
					boolean match = true;
					match = checkConditions(map, trees1[i], trees2[j], match);
					if (match == true) {
						map.add(length, i, j); // Add key-value pair (i,j) to map.
						// System.out.println("Added!");
						matched[j] = true; // Node j in G2 is now matched.
						numOp++;
						mismatched = -1;
						break; // Break at first match (such that j-th index in G2 is larger than 'mismatched').
					}
				}
			}
			if (map.length == length) { // Check if new key-value pair was not added.
				if (i - 1 < 0) { // If true, we cannot find a match for the very first node in G1.
					System.out.println("False. Graphs are non-isomorphic! ");
					return null;
				}
				mismatched = map.getValue(i - 1); // Update 'mismatched' because last key-value pair is wrong.
				matched[mismatched] = false; // Update information about matched node in G2.
				numOp++;
				map.pop(); // Remove the last key-value pair from map.
				// System.out.println("Deleted! Current map size is " + map.length);
				i = map.length - 1; // On next iteration, we will try to match i-th node in G1 with new node in G2.
			}
		}

		// System.out.println("True. Graphs are isomorphic! ");
		return map;
	}
*/
	/*public static boolean checkConditions(Map map, AbstractTree tree1, AbstractTree tree2, boolean match) {
		if (tree1.height != tree2.height) { // Fastest condition to check for non-isomorphism.
			match = false;
		} else {
			for (int k = 0; k < tree1.height; k++) {
				if (tree1.width[k] != tree2.width[k]) { // Number of nodes a distance k away must be preserved.
					match = false;
					break;
				}
			}
			if (match == true) {
				for (int k = 0; k < map.length; k++) {
					int key = map.getKey(k);
					int value = map.getValue(k);
					int keyLevel = tree1.getLevel(key);
					int valueLevel = tree2.getLevel(value);
					if (keyLevel != valueLevel) { // Check to see if shortest distance is preserved.
						match = false;
						break;
					}
				}
			}
		}
		return match;
	}

	public static boolean checkAllEdges(Graph G1, Graph G2, Map map) {
		int n = map.length;

		// Forward direction.
		if (checkEdges(G1, G2, map, n))
			return false;

		Map map2 = new Map(n); // Backward map.
		for (int i = 0; i < n; i++) {
			map2.add(i, map.getValue(i), map.getKey(i)); // Reverse roles of keys and values.
		}

		// Backward direction. Reverse roles of G1 and G2 for backward map.
		if (checkEdges(G2, G1, map2, n))
			return false;

		// System.out.println("Success! Isomorphism confirmed.");
		return true;
	}

	private static boolean checkEdges(Graph G1, Graph G2, Map map, int n) {
		// Check edges in the forward direction.
		for (int i = 0; i < n; i++) {
			int key = map.getKey(i);
			int value = map.getValue(i);

			if (G1.V[key].children.length != G2.V[value].children.length) {
				System.out.println("Error. Map sets correspondence between nodes with different number of children! ");
				return true;
			}

			for (int j = 0; j < G1.V[key].children.length; j++) {
				int key1 = G1.V[key].children[j].data;
				int value1 = map.mapKey(key1);
				boolean flag = false;

				for (int k = 0; k < G2.V[value].children.length; k++) {
					if (G2.V[value].children[k].data == value1) {
						flag = true;
					}
				}
				if (flag == false) {
					System.out.println("Error. An edge was not mapped! ");
					return true;
				}
			}
		}
		return false;
	}
*/
}
