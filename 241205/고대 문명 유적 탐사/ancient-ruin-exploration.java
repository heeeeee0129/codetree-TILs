import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int k;
    static int m;
    static int n = 5;
    static int[][] arr;
    static int[][] temp_arr;
    static int[] numbers_m;
    static int index_m = 0;
    static boolean[][] visited;
    static int tamsa_max;
    static int[][] tamsa_max_arr;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers_m = new int[m];
        arr = new int[n][n];
        temp_arr = new int[n][n];
        

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                temp_arr[i][j] = arr[i][j];
            }
        }

        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<m; i++){
            numbers_m[i] = Integer.parseInt(st.nextToken());
        }

        for(int test_case = 0; test_case < k; test_case++){
            int answer_sum = 0;
            tamsa_max = -1;
            tamsa_max_arr = new int[n][n];
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    tamsa_max_arr[i][j] = arr[i][j];
                }
            }
            for(int rev = 0; rev < 3; rev++){
                for(int j = 1; j < n-1; j++){
                    for(int i = 1; i < n-1; i++){
                        temp_arr = revolve(i, j, rev);

                        // for(int a = 0; a < n; a++){
                        //         for(int b = 0; b<n; b++){
                        //             System.out.print(temp_arr[a][b] + " " );
                        //         }
                        //         System.out.println();
                        //     }
                        int temp_cnt = count_piece();
                        if(temp_cnt > tamsa_max){
                            tamsa_max = temp_cnt;
                            for(int a = 0; a < n; a++){
                                for(int b = 0; b<n; b++){
                                    tamsa_max_arr[a][b] = temp_arr[a][b];
                                }
                            }
                        }
                    }
                }
            }

            if(tamsa_max == 0){
                break;
            }
            else{
                for(int i = 0; i<n; i++){
                    for(int j = 0; j<n; j++){
                        arr[i][j] = tamsa_max_arr[i][j];

                    }
                }
                answer_sum += tamsa_max;
                change_piece();
                int c = count_check();
                while(c > 0){
                    answer_sum += c;
                    change_piece();
                    c = count_check();
                }
                System.out.print(answer_sum + " " );
                if(answer_sum == 0){
                    return;
                }
                
            }
        }

    }

    public static int[][] revolve(int x, int y, int revolve_type){

        int[][] result_arr = new int[n][n];
        for(int a = 0; a < n; a++){
            for(int b = 0; b<n; b++){
                result_arr[a][b] = arr[a][b];
            }
        }

        for(int loop = 0; loop < revolve_type+1; loop++){
            int x_1_y_1 = result_arr[x+1][y+1];
            int x_0_y_1 = result_arr[x][y+1];
            result_arr[x+1][y+1] = result_arr[x-1][y+1];
            result_arr[x][y+1] = result_arr[x-1][y];
            result_arr[x-1][y+1] = result_arr[x-1][y-1];
            result_arr[x-1][y] = result_arr[x][y-1];
            result_arr[x-1][y-1] = result_arr[x+1][y-1];
            result_arr[x][y-1] = result_arr[x+1][y];
            result_arr[x+1][y-1] = x_1_y_1;
            result_arr[x+1][y] = x_0_y_1;
        }
        
        // for(int a = 0; a < n; a++){
        //     for(int b = 0; b<n; b++){
        //         temp_arr[a][b] = result_arr[a][b];
        //     }
        // }
        return result_arr;

    }


    public static boolean isIn(int number){
        if (number < 5 && number >= 0) return true;
        return false;
    }

    public static int dfs(int x, int y){
        if(visited[x][y] == true) return 0;
        int cnt = 1;
        visited[x][y] = true;
        
        for(int d = 0; d<4; d++){
            int next_x = x + dx[d];
            int next_y = y + dy[d];
            if(isIn(next_x) && isIn(next_y) && visited[next_x][next_y] == false
            && (temp_arr[x][y] == temp_arr[next_x][next_y])
             ){
                cnt += dfs(next_x, next_y);
            }
        }
        return cnt;
    }
    public static int dfs_for_change(int x, int y){
        if(visited[x][y] == true) return 0;
        int cnt = 1;
        visited[x][y] = true;
        
        for(int d = 0; d<4; d++){
            int next_x = x + dx[d];
            int next_y = y + dy[d];
            if(isIn(next_x) && isIn(next_y) && visited[next_x][next_y] == false
            && (arr[x][y] == arr[next_x][next_y])
             ){
                cnt += dfs_for_change(next_x, next_y);
            }
        }
        return cnt;
    }

    public static void change_piece(){
        visited = new boolean[n][n];
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(visited[i][j] == false){
                    int result_cnt = dfs_for_change(i, j);
                    if(result_cnt >= 3){
                        nodes.add(new Node(i, j));
                    }
                }
            }
        }

        visited = new boolean[n][n];
        Queue<Node> queue = new ArrayDeque<>();
        for(Node node: nodes){
            queue.add(node);
            visited[node.x][node.y] = true;
        }

        while(!(queue.isEmpty())){
            Node node = queue.poll();
            for(int d = 0; d<4; d++){
                int next_x = node.x + dx[d];
                int next_y = node.y + dy[d];

                if( isIn(next_x) && isIn(next_y) && 
                    (arr[node.x][node.y] == arr[next_x][next_y]) 
                    && visited[next_x][next_y] == false ){
                        visited[next_x][next_y] = true;
                        queue.add(new Node(next_x, next_y));
                }
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = n-1; j>=0; j--){
                if(visited[j][i] == true){
                    arr[j][i] = numbers_m[index_m++];
                }
            }
        }

        return;
        // result_sum : 현재 전체 배열에서 획득할 수 있는 유물의 개수

    }
    public static int count_check(){
        visited = new boolean[n][n];
        int result_sum = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(visited[i][j] == false){
                    int result_cnt = dfs_for_change(i, j);

                    if(result_cnt >= 3){
                        result_sum += result_cnt;
                    }
                }
            }
        }
        // result_sum : 현재 전체 배열에서 획득할 수 있는 유물의 개수

        return result_sum;
    }

    public static int count_piece(){
        visited = new boolean[n][n];
        int result_sum = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(visited[i][j] == false){
                    int result_cnt = dfs(i, j);

                    if(result_cnt >= 3){
                        result_sum += result_cnt;
                    }
                }
            }
        }
        // result_sum : 현재 전체 배열에서 획득할 수 있는 유물의 개수

        return result_sum;
    }
    
    public static class Node {
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}