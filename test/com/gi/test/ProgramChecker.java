package com.gi.test;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

import org.junit.Test;

import com.gi.base.AdjMatrix;
import com.gi.base.BitMatrix;
import com.gi.base.ConnectedGraph;
import com.gi.base.Map;
import com.gi.base.PermMatrix;

import static org.junit.Assert.*;

/**
 * User: ijk
 * Date: 3/1/14
 */
public class ProgramChecker {
	
	public boolean Blum(ConnectedGraph G1, ConnectedGraph G2, BitMatrix A1, BitMatrix A2, int k){
		int n= G1.V.length;
		Map map= ConnectedGraph.areIsomorphic(G1,G2, n);
		if (map != null && map.length == n){
			if (ConnectedGraph.checkAllEdges(G1, G2, map)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			Random random= new Random();			
			for (int i= 0; i < k; i++){
				int coin= random.nextInt(2);
				if (coin == 0){
					BitMatrix permMatrix= PermMatrix.makeRandom(n);
		            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
		            A2= PermMatrix.multiply(permMatrix, A2);
		            A2= PermMatrix.multiply(A2, permMatrixT);
		            
		            G2= new ConnectedGraph(A2);		            
		            map= ConnectedGraph.areIsomorphic(G1, G2, n);
		            
		            if (map != null && map.length != n){
		            	return false;
		            }
				}
				if (coin == 1){
					BitMatrix permMatrix= PermMatrix.makeRandom(n);
		            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
		            A1= PermMatrix.multiply(permMatrix, A1);
		            A1= PermMatrix.multiply(A1, permMatrixT);
		            
		            G1= new ConnectedGraph(A1);		            
		            map= ConnectedGraph.areIsomorphic(G1, G2, n);
		            
		            if (map != null && map.length != n){
		            	return false;
		            }
				}
			}
			return true;
		}
		
	}
    
    @Test
    public void ProgramCheckerTest() throws Exception {
        int n= 128;
        int t= 10;
        int p= 1;
        int q= 2;
        int k= 100;

		//Load new values from properties file 
		FileReader reader = new FileReader("./graph.properties");
		Properties properties = new Properties();
		properties.load(reader);
		n = Integer.parseInt(properties.getProperty("n"));
		t = Integer.parseInt(properties.getProperty("t"));
		p = Integer.parseInt(properties.getProperty("p"));
		q = Integer.parseInt(properties.getProperty("q"));
        
        /*Test isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n, p, q);

            BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);

            ConnectedGraph G1= new ConnectedGraph(adjMatrix);
            ConnectedGraph G2= new ConnectedGraph(adjMatrixPerm);

            if (!Blum(G1, G2, adjMatrix, adjMatrixPerm, k)){
                System.out.println("Program check failed on isomorphic graphs!");
                fail();
            }
            
            System.out.println("Test " + i +" is finished!");
        }

        /*Test non-isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n);
            BitMatrix adjMatrix2= AdjMatrix.makeRandom(n);

            ConnectedGraph G1= new ConnectedGraph(adjMatrix);
            ConnectedGraph G2= new ConnectedGraph(adjMatrix2);

            if (!Blum(G1, G2, adjMatrix, adjMatrix2, k)){
                System.out.println("Program check failed on non-isomorphic graphs!");
                fail();
            }
            
            System.out.println("Test " + i +" is finished!");
        }
    }
    
}
