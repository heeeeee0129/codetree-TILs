import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] count_arr = new int[n];
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for(int j = a-1; j<b; j++){
                count_arr[j]++;
            }
        }
        Arrays.sort(count_arr);
        System.out.print(count_arr[n/2]);
        // for(int num: count_arr){
        //     System.out.print(num + " " );
        // }
    }
}