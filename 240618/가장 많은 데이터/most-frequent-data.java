import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static HashMap<String, Integer> map = new HashMap<>();
    public static int max_count = 0;
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        for(int i = 0; i< n; i++){
            String str = in.readLine();
            int count = map.getOrDefault(str, 0);
            map.put(str, count+1);
            if(count+1 > max_count){
                max_count = count+1;
            }
        }
        System.out.print(max_count);
    }
}