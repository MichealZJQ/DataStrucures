package org.example.recursion;

public class Migong {
    public static void main(String[] args) {
        // 初始化一个迷宫
        int[][] m = new int[8][7];
        //设置墙
        for (int i=0;i<8;i++){
            m[i][0] = 1;
            m[i][6] = 1;
        }
        for (int i=0;i<7;i++){
            m[0][i] = 1;
            m[7][i] = 1;
        }
        m[3][1] = 1;
        m[3][2] = 1;

        // 打印迷宫
        System.out.println("初始化的迷宫：");
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }

        // 走迷宫
        setWay(m,1,1);

        // 打印走之后的迷宫
        System.out.println("走之后的迷宫：");
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }

        // 最短路径思路：列举出所有可能的策略，每个策略都走一次，比较得出最短的路径
    }

    /**
     *
     * @param m 地图
     * @param i 开始走的行
     * @param j 开始走的列
     * @return
     * 1：表示墙
     * 2：表示走过的位置
     * 3：走不通的位置
     * 0：没走过的位置
     * 策略：下 -> 右 -> 上 -> 左
     */
    public static boolean setWay(int[][] m, int i, int j){
        if (m[6][5] == 2){
            return true;
        }else {
            if (m[i][j] == 0){
                m[i][j] = 2;
                if (setWay(m,i+1,j)){ // 下
                    return true;
                }else if (setWay(m,i,j+1)){ // 右
                    return true;
                }else if (setWay(m,i-1,j)){ // 上
                    return true;
                }else if (setWay(m,i,j-1)){ // 左
                    return true;
                }else { // 走不通
                    m[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
