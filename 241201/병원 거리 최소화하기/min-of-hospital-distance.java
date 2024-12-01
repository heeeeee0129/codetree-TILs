import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    // static int[] dx = {-1, 1, 0, 0};
    // static int[] dy = {0, 0, -1, 1};
    static ArrayList<Person> persons = new ArrayList<>();
    static ArrayList<Person> hospitals = new ArrayList<>();
    static int answer = 10000000;
    static boolean[] selected;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j<n; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1){
                    persons.add(new Person(i, j));
                }else if( temp == 2 ){
                    hospitals.add(new Person(i, j));
                }
            }
        }

        selected = new boolean[hospitals.size()];
        comb(0, 0);
        System.out.print(answer);
    }

    public static void comb(int idx, int select_count) {
        if (select_count == m){
            ArrayList<Person> new_hosp = new ArrayList<>();
            for(int i = 0; i<hospitals.size(); i++){
                if (selected[i]){
                    new_hosp.add(hospitals.get(i));
                }
            }
            int sum_dist = 0 ;
            for(Person p: persons){
                int person_dist = 10000;
                for(Person h : new_hosp){
                    int dist = Math.abs(p.x-h.x) + Math.abs(p.y-h.y);
                    if(dist < person_dist){
                        person_dist = dist;
                    }
                }
                sum_dist += person_dist;
            }
            if(sum_dist < answer){
                answer = sum_dist;
            }
            return;
        }
        if (idx > hospitals.size()-1){
            return;
        }
        selected[idx] = true;
        comb(idx+1, select_count+1);
        selected[idx] = false;
        comb(idx+1, select_count);
    }


    public static class Person{
        int x;
        int y;
        public Person(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}