import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {
    public static int n;
    public static int k;
    public static HashMap<Integer, Integer> map = new HashMap<>();
    public static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<n; i++){
            map.put(i, Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                int sum = map.get(i)+ map.get(j);
                if(sum == k){
                    count++;
                }
            }
        }
        
        System.out.print(count);
    }
}