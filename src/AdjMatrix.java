import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * User: ijk
 * Date: 1/4/14
 */
public class AdjMatrix extends BitMatrix{

    public static BitMatrix makeRandom(int n){
        return makeRandom(n, 1, 2);
    }

    public static BitMatrix makeRandom(int n, int p, int q){
        BitMatrix matrix= new BitMatrix(n);
        Random random= new Random();

        for (int i= 0; i < n; i++){
            for (int j= i; j < n; j++){
                if (i == j){
                    matrix.setBit(i, j, false);
                }
                else{
                    boolean flag= (random.nextInt(q) <= p-1)? true: false;
                    matrix.setBit(i, j, flag);
                    matrix.setBit(j, i, flag);
                }
            }
        }
        return matrix;
    }

    public static BitMatrix readAdj(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Graphs/"+name+".txt"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append('\n');
            line = br.readLine();
        }
        br.close();
        String matrixStr = sb.toString();
        int n= (matrixStr.indexOf("\n")+1)/2;
        BitMatrix matrix= new BitMatrix(n);
        for (int i= 0; i < n; i++){
            for (int j= 0; j < 2*n-1; j=j+2){
                boolean val= (Integer.parseInt(matrixStr.substring(i*(2*n-1+1)+j,i*(2*n-1+1)+j+1)) == 1)? true: false;
                matrix.setBit(i,j/2, val);
            }
        }
        return matrix;
    }
}
