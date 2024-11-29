import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] customers;
    static int max_lead;
    static int max_member;
    static StringTokenizer st;
    static long answer;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        customers = new int[n];

        st = new StringTokenizer(in.readLine());
        for(int i =0 ;i< n; i++){
            customers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine());
        max_lead = Integer.parseInt(st.nextToken());
        max_member = Integer.parseInt(st.nextToken());

        for(int i = 0; i<n; i++){
            int cur_customer = customers[i]-max_lead;
            if(cur_customer > 0){
                answer += cur_customer / max_member;
                if(cur_customer % max_member > 0) answer++;
            }
            answer++;
        }
        System.out.println(answer);

    }
}