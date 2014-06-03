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
		
		int size1 = 0;
		for (int i = 0; i < n1; i++){
			for (int j = 0; j < G1.G[i].V.length; j++){
				if (G1.G[i].V[j].data > size1){
					size1 = G1.G[i].V[j].data;
				}
			}
		}
		
		int size2 = 0;
		for (int i = 0; i < n1; i++){
			for (int j = 0; j < G2.G[i].V.length; j++){
				if (G2.G[i].V[j].data > size2){
					size2 = G2.G[i].V[j].data;
				}
			}
		}
		
		if (size1 != size2){
			System.out.println("The number of total nodes does not match!");
			return null;
		}
		
		for (int i = 0; i < n1; i++){
			Map map;
			boolean flag = false; 
			for (int j = 0; j < n2; j++){
				if (mapped[j] != true){
					map = ConnectedGraph.areIsomorphic(G1.G[i], G2.G[j], size1+1);
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

}
