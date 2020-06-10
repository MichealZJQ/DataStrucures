package org.example.recursion;

/**
 * 八皇后问题
 */
public class Queue8 {

    // 皇后个数
    int max = 8;
    // 数组存结果
    int [] array = new int[max];
    static int  count = 0;
    static int judgeCnt = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("结果总是："+ count);
        System.out.println("判断的次数："+judgeCnt);
    }

    /**
     * 放置第n个皇后
     */
    public void check(int n){
        if (n == max){ // n=max时，表示第max个皇后也就是最后一个皇后已经放置好了，打印即可
            print();
            return;
        }
        // 每个皇后要放置max次
        for (int i=0;i<max;i++){
            array[n] = i;// 将第n个皇后放到第n行的第i列
            if(judge(n)){
                // 不冲突 继续放置第n+1个
                check(n+1);
            }else {
                // 冲突，则继续下次循环，也就是放到i+1的位置继续判断
            }
        }
    }

    /**
     * 判断第n个皇后是否与前几个皇后冲突
     * 冲突： 在同一行，同一列，同一条斜线
     */
    public boolean judge(int n){
        judgeCnt++;
        for (int i=0;i<n;i++){
            // array[i] == array[n] 表示在同一列  array[i]表示第i个皇后在第i-1行，第array[i]-1列
            // Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示在同一斜线
            // 在二维坐标中，若两个点a,b在同一条斜线，则a.x-b.x = a.y-b.y
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果
     */
    public void print(){
        count++;
        for (int i : array) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
