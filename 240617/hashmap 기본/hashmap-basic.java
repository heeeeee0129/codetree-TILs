import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main  {
    public static int n;
    public static HashMap<Integer, Integer> m = new HashMap<>();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("add")){
                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                m.put(k, v);
            }else if(cmd.equals("remove")){
                int k = Integer.parseInt(st.nextToken());
                m.remove(k);
            }else{
                //find
                int k = Integer.parseInt(st.nextToken());
                int v = m.getOrDefault(k, 0);
                if(v == 0){
                    System.out.println("None");
                }
                else{
                    System.out.println(v);
                }
            }
        }

    }
}