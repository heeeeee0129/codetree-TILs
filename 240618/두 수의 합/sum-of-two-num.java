import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// (target_sum - 값)이 HashMap에 존재하는 지 확인
// −10^9 ≤ 정수 ≤ 10^9 -> long
// 
public class Main {
    public static int n;
    public static long k;
    public static long[] arr;
    public static HashMap<Long, Integer> count = new HashMap<>();
    public static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n];

        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        for(int i = 0; i<n; i++){
            long target = k - arr[i];
            result += count.getOrDefault(target, 0);
            count.put(arr[i], count.getOrDefault(arr[i], 0)+1);
        }
        System.out.print(result);
        
    }
}