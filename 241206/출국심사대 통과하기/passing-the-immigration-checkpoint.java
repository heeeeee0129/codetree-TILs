import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Person[] arr = new Person[n];
        StringTokenizer st;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[i] = new Person(a, c);
        }
        Arrays.sort(arr, (Person p1, Person p2) -> {
            return Integer.compare(p1.arrived, p2.arrived);
        });

        int time_count = 0;
        for(Person p: arr){
            if (time_count > p.arrived){
                time_count = time_count + p.check;
            }else{
                time_count = p.arrived + p.check;
            }
        }
        System.out.print(time_count);

    }

    public static class Person{
        int arrived;
        int check;

        public Person(int arrived, int check){
            this.arrived = arrived;
            this.check = check;
        }
    }   


}