import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        
        Queue<Node> queue = new ArrayDeque<>();
        PriorityQueue<Integer> pqueue = new PriorityQueue<>((e1, e2) -> Integer.compare(e2, e1));
        st = new StringTokenizer(in.readLine());
        
        for(int i = 0; i<n; i++){
            int temp = Integer.parseInt(st.nextToken());
            pqueue.add(temp);
            queue.add(new Node(temp, i));
        }
        int count = 0;

        while(true){

            int max_number = pqueue.peek();
            Node head = queue.poll();
            
            if(max_number == head.number){
                pqueue.poll();
                count++;

                if(head.index == x){
                    System.out.println(count);
                    break;
                }
            }else{
                queue.add(head);
            }
        }

    }

    public static class Node {
        int number;
        int index;

        public Node(int number, int index){
            this.number = number;
            this.index = index;
        }
    }
}