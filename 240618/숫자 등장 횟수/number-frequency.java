import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    public static int n;
    public static int m;
    public static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<n; i++){
            int number = Integer.parseInt(st.nextToken());
            map.put(number, map.getOrDefault(number, 0)+1);
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<m; i++){
            int number = Integer.parseInt(st.nextToken());
            System.out.print(map.getOrDefault(number, 0)+ " ");
        }
    }
}