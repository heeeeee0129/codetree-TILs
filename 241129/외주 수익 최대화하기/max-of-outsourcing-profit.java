import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] times;
    static int[] pays;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());
        times = new int[n];
        pays = new int[n];
        dp = new int[n+1];
        
        for(int i=0; i<n; i++){
            st = new StringTokenizer(in.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            pays[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<n; i++){
            if (i + times[i] > n) continue;
            int d = dp[i+times[i]];
            int temp_max = 0;
            for(int j = 0; j<=i; j++){
                if(temp_max < dp[j]) temp_max = dp[j];
            }
            int new_d = temp_max + pays[i];
            if (d < new_d){
                dp[i+times[i]] = new_d;
            }
        }
        // int max_answer = -1;
        // for(int i = 0; i<=n; i++){
        //     if(max_answer < dp[i]) max_answer = dp[i];
        //     System.out.print(dp[i]+" ");
        // }
        System.out.print(dp[n]);

    }
}