import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {
    public static int n;
    public static long k;
    public static long[] arr;
    public static HashMap<Long, Integer> count;
    public static int result = 0;

    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        arr = new long[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i< n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
       for (int i = 0; i < n - 2; i++) {
            count = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                long target = k - arr[i] - arr[j];
                if (map.containsKey(target)) {
                    result += map.get(target);
                }
                map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
            }
        }
        
        System.out.println(result);
    }
}