import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: ijk
 * Date: 3/1/14
 */
public class ProgramChecker {
	
	public boolean Blum(Graph G1, Graph G2, BitMatrix A1, BitMatrix A2, int k){
		int n= G1.V.length;
		Map map= Graph.areIsomorphic(G1,G2);
		if (map != null && map.length == n){
			if (Graph.checkAllEdges(G1, G2, map)){
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
		            
		            G2= new Graph(A2);		            
		            map= Graph.areIsomorphic(G1, G2);
		            
		            if (map != null && map.length != n){
		            	return false;
		            }
				}
				if (coin == 1){
					BitMatrix permMatrix= PermMatrix.makeRandom(n);
		            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
		            A1= PermMatrix.multiply(permMatrix, A1);
		            A1= PermMatrix.multiply(A1, permMatrixT);
		            
		            G1= new Graph(A1);		            
		            map= Graph.areIsomorphic(G1, G2);
		            
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

        /*Test isomorphic graphs*/
        for (int i= 0; i < t; i++){
            BitMatrix adjMatrix= AdjMatrix.makeRandom(n, p, q);

            BitMatrix permMatrix= PermMatrix.makeRandom(n);
            BitMatrix permMatrixT= PermMatrix.transpose(permMatrix);
            BitMatrix adjMatrixPerm= PermMatrix.multiply(permMatrix, adjMatrix);
            adjMatrixPerm= PermMatrix.multiply(adjMatrixPerm, permMatrixT);

            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrixPerm);

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

            Graph G1= new Graph(adjMatrix);
            Graph G2= new Graph(adjMatrix2);

            if (!Blum(G1, G2, adjMatrix, adjMatrix2, k)){
                System.out.println("Program check failed on non-isomorphic graphs!");
                fail();
            }
            
            System.out.println("Test " + i +" is finished!");
        }
    }
    
}
