package com.aaron.剑指Offer2ndEdition;

import java.util.*;
import java.util.stream.Collectors;

//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// Related Topics 深度优先搜索 广度优先搜索 动态规划 
// 👍 423 👎 0


/**
 * 剑指offer·第二版: 13, 机器人的运动范围
 * @author Aaron Zhu
 * @date 2022-1-20
 */
public class Offer_13{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 回溯法
     */
    public static class Solution1 {
        private int result;

        private boolean[][] usedFlag;

        /**
         * 方向向量: 向右、向下
         */
        private int[] dx = new int[]{0,1};
        private int[] dy = new int[]{1,0};

        public int movingCount(int m, int n, int k) {
            if( k<0 ) {
                return 0;
            }

            init(m, n);
            search(k, 0, 0);
            return result;
        }

        private void init(int m, int n) {
            usedFlag = new boolean[m][n];
            // 起点(0,0)是合法的
            usedFlag[0][0] = true;
            result = 1;
        }

        private void search(int k, int rowIndex, int colIndex) {
            // 遍历可选路径
            for(int i=0; i<2; i++) {
                // 计算可选路径的坐标
                int nextRowIndex = rowIndex + dx[i];
                int nextColIndex = colIndex + dy[i];

                // 满足搜索条件
                if( nextRowIndex>=0 && nextRowIndex<=usedFlag.length-1
                    && nextColIndex>=0 && nextColIndex<=usedFlag[0].length-1
                    && calcSum(nextRowIndex, nextColIndex)<=k && !usedFlag[nextRowIndex][nextColIndex] ) {
                    // 更新计数值到结果变量
                    result++;
                    // 更新状态变量, 将其设置为已访问
                    usedFlag[nextRowIndex][nextColIndex] = true;
                    // 向下或向右继续搜索
                    search(k, nextRowIndex, nextColIndex);
                }
            }
        }

        /**
         * 计算两个数的数位之和
         * @param num1
         * @param num2
         * @return
         */
        private int calcSum(int num1, int num2) {
            int sum = 0;
            while( num1!=0 ) {
                sum += num1 % 10;
                num1 = num1 / 10;
            }
            while( num2!=0 ) {
                sum += num2 % 10;
                num2 = num2 / 10;
            }
            return sum;
        }
    }
    
    /**
     * BFS
     */
    public static class Solution {

        private int count;

        boolean[][] usedFlag;

        public int movingCount(int m, int n, int k) {
            usedFlag = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<>();
            queue.add( new int[]{0,0} );
            usedFlag[0][0] = true;
            count = 1;
            while ( !queue.isEmpty() ) {
                int[] pos = queue.poll();
                int x = pos[0];
                int y = pos[1];

                int[][] newPos = new int[][]{ {x+1,y}, {x,y+1} };
                for(int[] tempPos : newPos ) {
                    int tempX = tempPos[0];
                    int tempY = tempPos[1];
                    if( tempX<0 || tempX>m-1 || tempY<0 || tempY>n-1
                        || calcSum(tempX, tempY)>k || usedFlag[tempX][tempY] ) {
                        continue;
                    }
                    queue.add( tempPos );
                    usedFlag[tempX][tempY] = true;
                    count++;
                }
            }
            return count;
        }

        /**
         * 计算两个数的数位之和
         * @param num1
         * @param num2
         * @return
         */
        private int calcSum(int num1, int num2) {
            int sum = 0;
            while( num1!=0 ) {
                sum += num1 % 10;
                num1 = num1 / 10;
            }
            while( num2!=0 ) {
                sum += num2 % 10;
                num2 = num2 / 10;
            }
            return sum;
        }

    }

    public static class Solution2 {
        public int movingCount(int m, int n, int k) {
            int res = 1;
            boolean[][] access = new boolean[m][n];
            access[0][0] = true;
            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if( (i==0&&j==0) || calcSum(i,j)>k ) {
                        continue;
                    }

                    if( i-1>=0 ) {
                        access[i][j] = access[i][j] || access[i-1][j];
                    }
                    if( j-1>=0 ) {
                        access[i][j] = access[i][j] || access[i][j-1];
                    }

                    if( access[i][j] ) {
                        res++;
                    }
                }
            }

            return res;
        }

        /**
         * 计算两个数的数位之和
         * @param num1
         * @param num2
         * @return
         */
        private int calcSum(int num1, int num2) {
            int sum = 0;
            while( num1!=0 ) {
                sum += num1 % 10;
                num1 = num1 / 10;
            }
            while( num2!=0 ) {
                sum += num2 % 10;
                num2 = num2 / 10;
            }
            return sum;
        }
    }

}
