import java.io.*;
import java.util.*;



public class Main {
    public static int n;
    public static List<Integer>[] list;
    public static HashMap<Integer, Integer> count_1 = new HashMap<>();
    public static HashMap<Integer, Integer> count_2 = new HashMap<>();
    public static int result = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());
        list = new ArrayList[4];
        
        for(int i = 0; i<4; i++){
            list[i] = new ArrayList<>();
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j<n; j++){
                int input_number = Integer.parseInt(st.nextToken());
                list[i].add(input_number);
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                int number = list[0].get(i) + list[1].get(j);
                count_1.put(number, count_1.getOrDefault(number, 0)+1);
            }
        }
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                int number = list[2].get(i) + list[3].get(j);
                count_2.put(number, count_2.getOrDefault(number, 0)+1);
            }
        }

        for(Map.Entry<Integer, Integer> entry: count_1.entrySet()) {
            int number = entry.getKey();
            int freq = entry.getValue();

            if(count_2.containsKey(-number)){
                result += (freq*count_2.get(-number));
            }
        }
        System.out.print(result);
        

    }
}