import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int result = 0;
    public static HashMap<String, Integer> count = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i<n; i++){
            String input_str = in.readLine();
            
            for(int l=0; l<input_str.length(); l++){
                String target = input_str.substring(l)+input_str.substring(0, l);
                int target_count = count.getOrDefault(target, 0);
                count.put(target, target_count+1);
                if(target_count >= result){
                    result = target_count+1;
                }
            }
            
        }System.out.println(result);
    }
}