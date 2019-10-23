package dynamicprogramming;


import static java.lang.Math.max;

/**
 * @Author mxdc
 * @Date 2019/10/22
 */
public class Knapsack {

    /**
     * 自底向上求解最优数组
     * @param W 背包的容量
     * @param n 物品的个数
     * @param weigths 物品的大小
     * @param values 物品的价值
     * @return 返回一个背包容量为W,n个物品的最优数组
     */
    public static int[][] knapsackDP(int W, int n, int[] weigths, int[] values ){

        // n和W加一是当n=0或W=0时，最优解为0
        int [][] opt = new int[n+1][W+1];

        // 自底向上，先求容量为W,n = 1时的最优解 OPT[1,W]
        for (int i = 1; i<= n; i++){
            // 当 n = i时，不知道最优解时容量是多少,但是不会大于W,所以遍历W
            for (int j = 1; j<=W; j++ ){
                // 此时j为当前容量
                if (weigths[i-1] > j ){
                // 当物品的容量大于背包容量时,不放入
                    opt[i][j] = opt[i-1][j];
                }else{
                    // 这里要注意values[i-1]和weights[j-1]
                    int optSelectI = opt[i-1][j-weigths[i-1]] + values[i-1];
                    // 选择和不选择都要少一个
                    int optNotSelectI = opt[i-1][j];
                    opt[i][j] =max(optNotSelectI, optSelectI);
                }
            }
        }
        return opt;
    }

    public static void outputKnapsackDP(int W, int n, int[] weigths, int[] values ){
        int[][] opt = knapsackDP(W, n, weigths, values);
        int mark[] = new int[n];
        for (int i = n; i>1; i--){
            if(opt[i][W] == opt[i-1][W]){
                mark[i-1] =  0;
            }else {
                mark[i-1] = 1;
                W =W-weigths[i-1];
            }
        }
        if (opt[1][W] == 0){
            mark[0] =0;
        }else {
            mark[0] =1;
        }
        for (int i=0 ;i<n ;i++ ){
            if (mark[i] == 1){
                System.out.print(values[i]);
                System.out.print("\t");
                System.out.print(weigths[i]);
                System.out.println("");

            }
        }
    }

    public static void main(String[] args) {
        int[] weigths = new int[]{1,2,5,6,7};
        int[] values = new int[]{1,6,18,22,28};
        int[][] opt = knapsackDP(11, 5, weigths, values);
        for (int[] i: opt
             ) {
            for (int j : i){
                System.out.print(j);
                System.out.print("\t");
            }
            System.out.printf("\n");
        }

        outputKnapsackDP(11, 5, weigths, values);
    }
}
