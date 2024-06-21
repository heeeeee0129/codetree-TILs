import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int k;
    public static HashMap<Integer, Integer> count_map = new HashMap<>();
    public static List<Map.Entry<Integer, Integer>> count_list;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<n; i++){
            int number = Integer.parseInt(st.nextToken());
            count_map.put(number, count_map.getOrDefault(number,0)+1);
        }

        count_list = new ArrayList<>(count_map.entrySet());
        count_list.sort((e1, e2) -> {
            int compare = e2.getValue()-e1.getValue();
            if(compare == 0){
                return e2.getKey()-e1.getKey();
            }else{
                return compare;
            }
        });

        for(int i = 0; i<k; i++){
            int number = count_list.get(i).getKey();
            System.out.print(number + " ");
        }
        




    }
}