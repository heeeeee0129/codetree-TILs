import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] arr;
    static boolean[] selected;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        arr = new int[n][n];
        selected = new boolean[n];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j = 0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0);
        System.out.println(answer);
    }

    public static void comb(int idx){
        if(idx == n) {
            //종료 조건
            ArrayList<Integer> sel = new ArrayList<>();
            ArrayList<Integer> un_sel = new ArrayList<>();

            for(int i = 0; i<n; i++){
                if(selected[i]){
                    sel.add(i);
                }else{
                    un_sel.add(i);
                }
            }
            int cha = Math.abs(calc_cha(sel) - calc_cha(un_sel));
            if(cha < answer) {
                answer = cha;
            }

            return;
        }
        selected[idx] = true;
        comb(idx+1);
        selected[idx] = false;
        comb(idx+1);

    }

    public static int calc_cha(ArrayList<Integer> selected){
        int len = selected.size();
        int sum = 0;
        for(int i = 0; i<len; i++){
            for(int j = 0; j<len; j++){
                sum += arr[selected.get(i)][selected.get(j)];
            }
        }
        // System.out.println(sum);

        return sum; 
    }

}