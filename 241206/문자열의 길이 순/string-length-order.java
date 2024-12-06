import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] arr = new String[n];
        for(int i = 0; i<n; i++){
            arr[i] = in.readLine();
        }
        Arrays.sort(arr, (s1, s2) -> {
            int ans = Integer.compare(s1.length(), s2.length());
            if(ans == 0){
                return s1.compareTo(s2);
            }
            return ans;
        });
        for(String s: arr){
            System.out.println(s);
        }
    }
}