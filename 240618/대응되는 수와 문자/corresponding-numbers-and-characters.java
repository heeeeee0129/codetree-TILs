import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int m;
    public static HashMap<Integer, String> intToStr = new HashMap<>();
    public static HashMap<String, Integer> strToInt = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i<n; i++){
            String str = in.readLine();
            strToInt.put(str, i+1);
            intToStr.put(i+1, str);
        }
        for(int i = 0; i<m; i++){
            String cmd = in.readLine();
            int result = strToInt.getOrDefault(cmd, 0);
            if(result == 0){
                System.out.println(intToStr.get(Integer.parseInt(cmd)));
            }else{
                System.out.println(result);
            }
        }
    }
}