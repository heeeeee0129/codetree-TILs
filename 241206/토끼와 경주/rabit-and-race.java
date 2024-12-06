import java.util.*;
import java.io.*;

public class Main {
    static int Q;
    static int N;
    static int M;
    static int P;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static StringTokenizer st;
    static ArrayList<Rabbit> rabbits = new ArrayList<>();
    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(in.readLine());


        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(in.readLine());
            int op = Integer.parseInt(st.nextToken());
            switch(op){
                case 100:
                    N = Integer.parseInt(st.nextToken());
                    M = Integer.parseInt(st.nextToken());
                    P = Integer.parseInt(st.nextToken());
                    for(int i = 0; i < P; i++){
                        int pid = Integer.parseInt(st.nextToken());
                        int d = Integer.parseInt(st.nextToken());
                        rabbits.add(new Rabbit(1, 1, pid, d));
                    }
                    break;
                case 200:
                    int K = Integer.parseInt(st.nextToken());
                    int S = Integer.parseInt(st.nextToken());
                    HashSet<Integer> check = new HashSet<>();
                    for(int k = 0; k < K; k++){
                        rabbits.sort((r1, r2) -> {
                            int ans1 = Integer.compare(r1.count, r2.count);
                            if(ans1 == 0){
                                int ans2 = Integer.compare(r1.x + r1.y, r2.x + r2.y);
                                if(ans2 == 0){
                                    int ans3 = Integer.compare(r1.x, r2.x);
                                    if(ans3 == 0){
                                        int ans4 = Integer.compare(r1.y, r2.y);
                                        if(ans4 == 0){
                                            return Integer.compare(r1.id, r2.id);
                                        }
                                        return ans4;
                                    }
                                    return ans3;
                                }
                                return ans2;
                            }
                            return ans1;
                        });

                        Rabbit targerRabbit = rabbits.get(0);
                        check.add(targerRabbit.id);
                        Node res = moveRabbit(targerRabbit);
                        for(Rabbit r: rabbits){
                            if(r.id == targerRabbit.id){
                                r.x = res.x;
                                r.y = res.y;
                                r.count += 1;
                            }else{
                                r.score += (res.x + res.y);
                            }
                        }

                        // System.out.println("id dist x y score count 내부");
                        // for(Rabbit r: rabbits){
                        //     System.out.println(r);
                        // }
                    }

                    rabbits.sort((r1, r2) -> {
                                int ans2 = Integer.compare(r1.x + r1.y, r2.x + r2.y);
                                if(ans2 == 0){
                                    int ans3 = Integer.compare(r1.x, r2.x);
                                    if(ans3 == 0){
                                        int ans4 = Integer.compare(r1.y, r2.y);
                                        if(ans4 == 0){
                                            return -Integer.compare(r1.id, r2.id);
                                        }
                                        return -ans4;
                                    }
                                    return -ans3;
                                }
                                return -ans2;
                            
                    });
                    int idx = 0;
                    while(true){
                        if(check.contains(rabbits.get(idx).id)){
                            rabbits.get(idx).score += S;
                            break;
                        }
                        idx++;
                    }
                    break;
                case 300:
                    rabbits.sort((r1, r2) -> {
                                int ans2 = Integer.compare(r1.id, r2.id);
                                return ans2;
                    });
                    int pid = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());

                    int left = 0;
                    int right = P-1;
                    
                    while(left<right){
                        int mid_idx = (left + right) / 2;

                        if(rabbits.get(mid_idx).id == pid){
                            rabbits.get(mid_idx).dist *= L;
                            break;
                        }else if(rabbits.get(mid_idx).id < pid){
                            left = mid_idx;
                        }else{
                            right = mid_idx;
                        }
                    }
                    break;
                case 400:
                    flag = true;
                    break;
                default:
                    break;
            }
            if(flag == true){
                rabbits.sort((r1, r2) -> {
                            int ans2 = Integer.compare(r1.score, r2.score);
                            return -ans2;
                });
                System.out.println(rabbits.get(0).score);
                break;
            }

            // System.out.println("id dist x y score count");
            // for(Rabbit r: rabbits){
            //     System.out.println(r);
            // }
        }
        
    }

    public static Node moveRabbit(Rabbit r){
        Node[] nodes = new Node[4];
        
        int minus_x = r.x - r.dist;
        if(minus_x <= 0) minus_x = 2;
        nodes[0] = new Node(minus_x, r.y);

        int plus_x = r.x + r.dist;
        if(plus_x > N) plus_x = N-1;
        nodes[1] = new Node(plus_x, r.y);

        int minus_y = r.y - r.dist;
        if(minus_y <= 0) minus_y = 2;
        nodes[2] = new Node(r.x, minus_y);

        int plus_y = r.y + r.dist;
        if(plus_y > M) plus_y = M-1;
        nodes[3] = new Node(r.x, plus_y);

        Arrays.sort(nodes);
        Node answer = nodes[0];
        return answer;
    }

    public static class Rabbit {
        int count = 0;
        int x;
        int y;
        int dist;
        int id;
        int score = 0;

        public Rabbit(int x, int y, int id, int dist){
            this.x = x;
            this.y = y;
            this.id = id;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return id + " " + dist + " " + x + " " + y + " " + score + " " + count;
        }
        

    }

    public static class Node implements Comparable<Node> {
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node other){
            int ans1 = (this.x + this.y)-(other.x + other.y);
            if(ans1 == 0){
                int ans2 = (this.x)- (other.x);
                if(ans2 == 0) return -(this.y)-(other.y);
                return -ans2;
            }
            return -ans1;
        }

    }
}

// [경주 시작 준비]

// p마리 토끼
// 각 토끼는 고유 번호 + 한 번 움직일 때 꼭 이동해야하는 거리
// 처음 토끼들은 전부 (1,1)에 있음

// ---

// [k번 s점수 토끼 이동]

// 가장 우선순위가 높은 토끼를 뽑아 멀리보내주는 것을 k번 반복

// 우선순위: 
// 현재까지의 총 점프 횟수가 적은 토끼, 
// 현재 서 있는 행 번호 + 열 번호가 작은 토끼,
// 행 번호가 작은 토끼
// 열 번호가 작은 토끼
// 고유 번호가 작은 토끼


// ---

// 토끼 이동 우선 순위
// 상하좌우 네 방향으로 di 만큼 이동. 이동하는 도중 격자를 벗어나게 되면 방향을 반대로 바꿔서 한 칸 이동 (?)
// 구해진 4개의 위치 중 
    // 행 + 열 큰 칸
    // 행 큰 칸
    // 열 큰 칸
// 가장 우선순위가 높은 칸으로 토끼 이동

// ---

//  k 번의 턴이 끝나고 나면 우선순위가 높은 토끼를 골라 점수 S를 더해줌.
// 조건!! k번의 턴 동안 한 번이라도 뽑혔던 적이 있던 토끼 중에서 골라야 함!

// ---

// [이동 거리 변경]

// 고유번호가 pid인 토끼의 이동거리 L배. 계산 도중 특정 토끼의 이동거리가 10억을 넘어가는 일은 없음

// ---

// [최고의 토끼 선정]

//점수 가장 높은 토끼의 점수 출력