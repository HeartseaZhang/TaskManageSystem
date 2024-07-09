package app.heartsea.taskmanager;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        int sum = -1;
        Queue<int[]> q = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] tufu = new int[2];
        int[] exits = new int[2];
        int[] start = new int[2];
        int[][] map = new int[n][m];
        int count=0;
        int[][] fadianji = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            char[] c = s.toCharArray();
            for (int j = 0; j < m; j++) {
                if (c[j] == '.')map[i][j] = 0;
                else if (c[j] == 'S') {
                    map[i][j] = 4;
                    start = new int[]{i,j};
                    q.add(new int[] {i, j,0});
                } else if (c[j] == 'E') {
                    map[i][j] = 3;
                    exits = new int[] {i, j};
                } else if (c[j] == '$'){
                    map[i][j] = 1;
                    fadianji[i][j]=count;
                    count++;
                }
                else if (c[j] == 'T') {
                    map[i][j] = 5;
                    tufu = new int[] {i, j};
                } else if (c[j] == '#')map[i][j] = 2;
            }
        }
        if (Math.abs(exits[0] - tufu[0]) + Math.abs(exits[1] - tufu[1]) <= k) {
            System.out.println("-1");
            System.exit(0);
        }
        int fadianjizongshu = 0;
        for (int i = 0 ; i < n; i++) {
            for (int j = 0 ; j < m ; j++) {
                if (Math.abs(i - tufu[0]) + Math.abs(j - tufu[1]) <= k) {
                    map[i][j] = 2;
                }
                if (map[i][j] == 1)fadianjizongshu++;
            }
        }

        if (fadianjizongshu < 5) {
            System.out.println("-1");
            System.exit(0);
        }
        boolean flag = true;
        //0 = 路  1=发电机  2=障碍物 3=出口 4 = 起点 5=屠夫
        map[tufu[0]][tufu[1]] = 2;
        map[start[0]][start[1]] = 2;
        //map[0][4]=3;
        //         for (int i = 0 ; i < n; i++) {
        //     for (int j = 0 ; j < m ; j++) {
        //         System.out.print(map[i][j]);
        //     }
        //     System.out.println("");
        // }


        while (!q.isEmpty()) {
            sum++;
            int size  = q.size();
            // System.out.println(size);

            for (int i = 0 ; i < size; i++) {
                int[] location = q.poll();

                //                 System.out.println(location[0]);
                // System.out.println(location[1]);
                // System.out.println(map[0][1]);

                if (map[location[0]][location[1]] == 3) {
                    if (hasAtLeastFiveOnes(location[2])) {
                        System.out.println(sum);
                        System.exit(0);
                    }
                } else if (map[location[0]][location[1]] == 1) {
                    //map[location[0]][location[1]]=0;
                    if (location[0] + 1 < n &&
                            map[location[0] + 1][location[1]] != 2)q.add(new int[] {location[0] + 1, location[1],setBit(location[2],fadianji[location[0]][location[1]])});
                    if (location[0] - 1 >= 0 &&
                            map[location[0] - 1][location[1]] != 2)q.add(new int[] {location[0] - 1, location[1],setBit(location[2],fadianji[location[0]][location[1]])});
                    if (location[1] + 1 < m &&
                            map[location[0]][location[1] + 1] != 2)q.add(new int[] {location[0], location[1] + 1,setBit(location[2],fadianji[location[0]][location[1]])});
                    if (location[1] - 1 >= 0 &&
                            map[location[0]][location[1] - 1] != 2)q.add(new int[] {location[0], location[1] - 1,setBit(location[2],fadianji[location[0]][location[1]])});
                } else if (map[location[0]][location[1]] == 0 || flag == true) {
                    flag = false;
                    if (location[0] + 1 < n &&
                            map[location[0] + 1][location[1]] != 2)q.add(new int[] {location[0] + 1, location[1],location[2]});
                    if (location[0] - 1 >= 0 &&
                            map[location[0] - 1][location[1]] != 2)q.add(new int[] {location[0] - 1, location[1],location[2]});
                    if (location[1] + 1 < m &&
                            map[location[0]][location[1] + 1] != 2)q.add(new int[] {location[0], location[1] + 1,location[2]});
                    if (location[1] - 1 >= 0 &&
                            map[location[0]][location[1] - 1] != 2)q.add(new int[] {location[0], location[1] - 1,location[2]});
                }
            }
        }

        System.out.println(sum);
    }
    public static int setBit(int num, int position) {
        return num | (1 << position);
    }
    public static boolean hasAtLeastFiveOnes(int num) {
        int count = 0;
        while (num != 0) {
            // 使用位与运算 num & 1 检查最低位是否为1
            if ((num & 1) == 1) {
                count++;
            }
            // 右移一位
            num >>= 1;
            // 如果count已经达到或超过5，可以提前返回true
            if (count >= 5) {
                return true;
            }
        }
        return false; // 如果循环结束count不足5，则返回false
    }

}


//5 5 1
//        #S$..
//        $$..$
//        .#.T$
//        .$..E
//        $#...
