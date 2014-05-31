package com.gi.exp;

import com.gi.base.AbstractTree;
import com.gi.base.AdjMatrix;
import com.gi.base.BitMatrix;
import com.gi.base.ConnectedGraph;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DiameterDistribution {
	/*This class is meant to help find the diameter distribution of random graphs analytically*/
	/*We focus on looking at lambda=2 and lambda unbounded*/

	public static void main(String[] args) throws IOException{
    	long startTime0 = System.nanoTime();
    	   	
    	/* Default values */
        int n= 16;
        int t= 100;
        int p= 1;
        int q= 2;

        /* Load new values from properties file */
        FileReader reader = new FileReader("./graph.properties");
        Properties properties = new Properties();
        properties.load(reader);
        n= Integer.parseInt(properties.getProperty("n"));
        t= Integer.parseInt(properties.getProperty("t"));
        p= Integer.parseInt(properties.getProperty("p"));
        q= Integer.parseInt(properties.getProperty("q"));

        int[] DD= new int[n];
        int[] DDc= new int[n];

        long totalTime= 0;
        long minTime= -1;
        long maxTime= 0;
        long totalCheckTime= 0;

        /*Test isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n, p, q);

            /*BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);*/

            ConnectedGraph G1= new ConnectedGraph(adjMatrix);
            //Graph G2= new Graph(adjMatrixPerm);

            long startTime = System.nanoTime();
            AbstractTree[] trees1= new AbstractTree[n];
            //AbstractTree[] trees2= new AbstractTree[n];

            for (int j= 0; j < n; j++){
                trees1[j]= ConnectedGraph.BFS(G1, j);
                DD[trees1[j].height]+= 1;
                //trees2[i]= Graph.BFS(G2, i);
            }
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            if (duration < minTime || minTime < 0){
            	minTime= duration;
            }
            if (duration > maxTime){
            	maxTime= duration;
            }
            
            totalTime += duration;
            System.out.println("Test " + i +" is finished!");
        }
        System.out.println("Average time is \t\t" + totalTime/Math.pow(10,9)/t + " seconds.");
        System.out.println("Min time is \t\t" + minTime/Math.pow(10,9) + " seconds.");
        System.out.println("Max time is \t\t" + maxTime/Math.pow(10,9) + " seconds.");
        System.out.println("Average check time is \t" + totalCheckTime/Math.pow(10,9)/t + " seconds.");
        
        long endTime0 = System.nanoTime();
        long duration0= endTime0-startTime0;
        System.out.println("Total time spent in method is \t\t" + duration0/Math.pow(10, 9) + " seconds.");
        
        for (int i= n-1; i >= 0; i--){
        	System.out.println("DD[" + i + "]=" + DD[i]);
        }

	}

}
