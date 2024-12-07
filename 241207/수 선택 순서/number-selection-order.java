import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Queue<Integer> queue = new ArrayDeque<>();
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<n; i++){
            int temp = Integer.parseInt(st.nextToken());
            queue.add(temp);
            pqueue.add(-temp);
        }
        int count = 1;
        while(true){
            int max_queue = -pqueue.peek();
            int queue_number = queue.poll();
            if(queue_number == x){
                System.out.print(count);
                break;
            }
            if(queue_number != max_queue){
                queue.add(queue_number);
            }
            count++;
        }
        
    }
}