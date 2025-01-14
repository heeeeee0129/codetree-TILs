import java.util.*;
import java.io.*;


public class Main {
    static int N; // 좌표 크기
    static int M; // 전사의 수
    static int[][] arr;
    static Pos startM;
    static Pos endM;
    static Pos curM;
    static int warriorMove = 0;
    static int warriorStone = 0;
    static int warriorAttack = 0;
    static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static boolean[][] dfsVisited;
    static int[][] warriors;
    static int[][] sisun;

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        warriors = new int[N][N];
        st = new StringTokenizer(in.readLine());
        startM = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        curM = new Pos(startM.x, startM.y);
        endM = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i<M; i++){
            warriors[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]++;
        } // 전사는 격자별 위치하고 있는 전사의 수로 기록


        for(int i = 0; i<N; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //메두사 이동 경로 구해두기
        dfsVisited = new boolean[N][N];
        int dist = minimumDistance();
        if(dist == -1){
            System.out.println("-1");
            return;
        }
        dfs(startM.x, startM.y, 0, new ArrayList<>(), dist);
        // 메두사가 이동하는 경로는 2로 표시함.
        arr[curM.x][curM.y] = 0;


//         메두사의 이동
        while(true){

            for(int d=0; d<4; d++){
                int nx = curM.x + dx[d];
                int ny = curM.y + dy[d];
                if(nx == endM.x && ny == endM.y){
                    System.out.println(0);
                    return;
                }
                if(isIn(nx, ny) && arr[nx][ny] == 2){
                    arr[nx][ny] = 0;
                    if(warriors[nx][ny] > 0) {
                        warriorAttack += warriors[nx][ny];
                        warriors[nx][ny] = 0;
                    }
                    curM.x = nx;
                    curM.y = ny;
                    break;
                }
            }
            //메두사의 시선

            int up = medusaSpreadUp(curM, false);
            int down = medusaSpreadDown(curM, false);
            int left = medusaSpreadLeft(curM, false);
            int right = medusaSpreadRight(curM, false);

            int maximum = Math.max(Math.max(up, down), Math.max(left, right));
            warriorStone += maximum;
            if(up == maximum){
                medusaSpreadUp(curM, true);
            }else if(down == maximum){
                medusaSpreadDown(curM, true);
            }else if(left == maximum){
                medusaSpreadLeft(curM, true);
            }else{
                medusaSpreadRight(curM, true);
            }

            sisun[curM.x][curM.y] = 0;

            // 전사들의 이동
            moveWarrior();


            System.out.println(warriorMove + " " + warriorStone + " " + warriors[curM.x][curM.y]);
            warriors[curM.x][curM.y] = 0;
            warriorMove = 0;
            warriorStone = 0;

        }

    }

    public static void moveWarrior(){
        int[][] d_warriors = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(warriors[i][j] > 0){

                    if(sisun[i][j] == 1){
                        //돌
                        d_warriors[i][j] += warriors[i][j];
                        warriors[i][j] = 0;
                    }else{
                        int curx = i;
                        int cury = j;
                        if(curM.x < curx && isIn(curx-1, cury) && sisun[curx-1][cury] != 1){ // 상
                            curx--;
                        }else if(curM.x > curx && isIn(curx+1, cury) && sisun[curx+1][cury] != 1) { // 하
                            curx++;
                        }else{
                            if(curM.y < cury && isIn(curx, cury-1) && sisun[curx][cury-1] != 1) { //left
                                cury--;
                            }else if(curM.y > cury && isIn(curx, cury+1) && sisun[curx][cury+1] != 1) { //right
                                cury++;
                            }
                        }
                        //두 번째로 좌우상하
                        if(curM.y < cury && isIn(curx, cury-1) && sisun[curx][cury-1] != 1) { //left
                            cury--;
                        }else if(curM.y > cury && isIn(curx, cury+1) && sisun[curx][cury+1] != 1) { //right
                            cury++;
                        }else {
                            if (curM.x < curx && isIn(curx - 1, cury) && sisun[curx - 1][cury] != 1) { // 상
                                curx--;
                            } else if (curM.x > curx && isIn(curx + 1, cury) && sisun[curx + 1][cury] != 1) { // 하
                                curx++;
                            }
                        }

                        d_warriors[curx][cury] += warriors[i][j];
                        warriorMove += (Math.abs(i-curx) + Math.abs(j-cury))*warriors[i][j];
                        warriors[i][j] = 0;
                    }
                }
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(d_warriors[i][j] > 0){
                    warriors[i][j] += d_warriors[i][j];
                }
            }
        }
    }

    public static int medusaSpreadDown(Pos p, boolean flag){
        sisun = new int[N][N];
        int ddy = 0;
        int warriorCount = 0;
        for(int i = p.x; i<N; i++){
            for(int j = p.y - ddy; j<=p.y + ddy; j++){
                if(isIn(i, j) && sisun[i][j] == 0){

                    if(warriors[i][j] > 0){
                        warriorCount += warriors[i][j];
                        // 전사가 있는 칸에 시선 도착하면, 전사에 가려지는 범위를 계산해야함.
                        // 전사와 메두사 방향 체크
                        if(j == p.y) {
                            // 하
                            for(int a = i; a<N; a++){
                                sisun[a][j] = -1;
                            }
                        }else if(j > p.y){
                            // 우하
                            int db = 0;
                            for(int a = i; a<N; a++){
                                for(int b = j; b <= j + db; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }else{
                            // 좌하
                            int db = 0;
                            for(int a = i; a<N; a++){
                                for(int b = j - db; b <= j; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }

                    }
                    sisun[i][j] = 1;
                }

            }
            ddy++;
        }

        return warriorCount;
    }

    public static int medusaSpreadRight(Pos p, boolean flag){
        sisun = new int[N][N];
        int ddx = 0;
        int warriorCount = 0;
        for(int j = p.y; j<N; j++){
            for(int i = p.x - ddx; i<=p.x + ddx; i++){
                if(isIn(i, j) && sisun[i][j] == 0){

                    if(warriors[i][j] > 0){
                        warriorCount += warriors[i][j];
                        // 전사가 있는 칸에 시선 도착하면, 전사에 가려지는 범위를 계산해야함.
                        // 전사와 메두사 방향 체크
                        if(i == p.x) {
                            // 우
                            for(int a = j; a<N; a++){
                                sisun[i][a] = -1;
                            }
                        }else if(i > p.x){
                            // 우하
                            int db = 0;
                            for(int a = j; a<N; a++){
                                for(int b = i; b <= i + db; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }else{
                            // 좌하
                            int db = 0;
                            for(int a = j; a<N; a++){
                                for(int b = i - db; b <= i; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }
                    }sisun[i][j] = 1;
                }

            }
            ddx++;
        }

        return warriorCount;
    }

    public static int medusaSpreadLeft(Pos p, boolean flag){
        sisun = new int[N][N];
        int ddx = 0;
        int warriorCount = 0;
        for(int j = p.y; j>= 0; j--){
            for(int i = p.x - ddx; i<=p.x + ddx; i++){
                if(isIn(i, j) && sisun[i][j] == 0){

                    if(warriors[i][j] > 0){
                        warriorCount += warriors[i][j];
                        // 전사가 있는 칸에 시선 도착하면, 전사에 가려지는 범위를 계산해야함.
                        // 전사와 메두사 방향 체크
                        if(i == p.x) {
                            // 우
                            for(int a = j; a>=0; a--){
                                sisun[i][a] = -1;
                            }
                        }else if(i > p.x){
                            // 우하
                            int db = 0;
                            for(int a = j; a>=0; a--){
                                for(int b = i; b <= i + db; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }else{
                            // 좌하
                            int db = 0;
                            for(int a = j; a>=0; a--){
                                for(int b = i - db; b <= i; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }
                    }sisun[i][j] = 1;
                }

            }
            ddx++;
        }

        return warriorCount;
    }

    public static int medusaSpreadUp(Pos p, boolean flag){
        sisun = new int[N][N];
        int ddy = 0;
        int warriorCount = 0;
        for(int i = p.x; i>=0; i--){
            for(int j = p.y - ddy; j<=p.y + ddy; j++){
                if(isIn(i, j) && sisun[i][j] == 0){

                    if(warriors[i][j] > 0){
                        warriorCount += warriors[i][j];
                        // 전사가 있는 칸에 시선 도착하면, 전사에 가려지는 범위를 계산해야함.
                        // 전사와 메두사 방향 체크
                        if(j == p.y) {
                            for(int a = i; a>=0; a--){
                                sisun[a][j] = -1;
                            }
                        }else if(j > p.y){
                            // 우상
                            int db = 0;
                            for(int a = i; a>=0; a--){
                                for(int b = j; b <= j + db; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }else{
                            // 좌상
                            int db = 0;
                            for(int a = i; a>=0; a--){
                                for(int b = j - db; b <= j; b++){
                                    if(isIn(a, b)){
                                        sisun[a][b] = -1;
                                    }
                                }
                                db++;
                            }
                        }
                    }
                    sisun[i][j] = 1;
                }

            }
            ddy++;
        }

        return warriorCount;
    }

    public static void dfs(int x, int y, int cost, List<Pos> path, int minimum){
        dfsVisited[x][y] = true;
        path.add(new Pos(x,y));
        if(x == endM.x && y == endM.y && cost == minimum){
            for(Pos p : path) {
                arr[p.x][p.y] = 2;
            }
            return;
        }
        for(int d = 0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(isIn(nx, ny) && !dfsVisited[nx][ny] && arr[nx][ny] == 0){
                dfs(nx, ny, cost + 1, path, minimum);
            }
        }
        dfsVisited[x][y] = false;
        path.remove(path.size()-1);
    }

    public static int minimumDistance() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {startM.x, startM.y, 0});
        boolean[][] visited = new boolean[N][N];
        while(!(queue.isEmpty())){
            int[] curr = queue.poll();
            if(curr[0] == endM.x && curr[1] == endM.y){
                return curr[2];
            }
            for(int d = 0; d<4; d++){
                int nx = curr[0] + dx[d];
                int ny = curr[1] + dy[d];
                if(isIn(nx, ny) && !visited[nx][ny] && arr[nx][ny] == 0){
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, curr[2]+1});
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < N){
            return true;
        }
        return false;
    }

    public static class Pos {
        int x;
        int y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
