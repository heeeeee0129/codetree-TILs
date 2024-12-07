import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        HashMap<String, Integer> count_map = new HashMap<>();
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            String s3 = st.nextToken();
            Room room = new Room(s1, s2, s3);
            count_map.put(room.toString(), count_map.getOrDefault(room.toString(), 0)+1);
        }

        int max_count = 0;
        for(int cnt: count_map.values()){
            if (max_count < cnt){
                max_count = cnt;
            }
        }

        System.out.println(max_count);

        
    }
    public static class Room {
            String[] names = new String[3];

            public Room(String s1, String s2, String s3){
                names[0] = s1;
                names[1] = s2;
                names[2] = s3;
                Arrays.sort(names);
            }

            public String toString(){
                return names[0]+names[1]+names[2];
            }
        }
}