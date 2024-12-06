import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] A = new int[n+m];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for(int i = n; i<n+m; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        for(int num: A){
            System.out.print(num +  " ");
        }


    }
}