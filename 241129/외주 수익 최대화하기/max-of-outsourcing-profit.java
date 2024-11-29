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
            int d = dp[i+times[i]];
            int new_d;
            if(i > 0 && dp[i] == 0){
                new_d = dp[i-1] + pays[i];
            }
            else{
                new_d = dp[i] + pays[i];
            }
            if (d < new_d){
                dp[i+times[i]] = new_d;
            }
        }
        System.out.print(dp[n]);
        // for(int i = 0; i<=n; i++){
        //     System.out.print(dp[i]+ " " );
        // }

    }
}