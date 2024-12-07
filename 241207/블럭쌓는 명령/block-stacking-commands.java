import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] d_arr = new int[n+1];
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            d_arr[a]++;
            d_arr[b+1]--;
        }

        int[] num_arr = new int[n];
        int count = 0;
        
        for(int i = 0; i<n; i++){
            count += d_arr[i];
            num_arr[i] = count;
        }

        Arrays.sort(num_arr);
        System.out.print(num_arr[n/2]);
        
    }
}