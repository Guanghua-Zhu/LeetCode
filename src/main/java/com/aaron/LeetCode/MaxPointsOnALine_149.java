package com.aaron.LeetCode;

import java.util.*;

//给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。 
//
// 
//
// 示例 1： 
// 
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：3
// 
//
// 示例 2： 
// 
// 
//输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 300 
// points[i].length == 2 
// -10⁴ <= xi, yi <= 10⁴ 
// points 中的所有点 互不相同 
// 
//
// Related Topics 几何 数组 哈希表 数学 👍 498 👎 0


/**
 * 149, 直线上最多的点数
 * @author Aaron Zhu
 * @date 2023-7-4
 */
public class MaxPointsOnALine_149{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] points = new int[][] {
            {1,1}, {3,2},{5,3},{4,1},{2,3},{1,4}
        };
        solution.maxPoints(points);
        System.out.println("gg");
    }

    public static class Solution {
        private int maxPoints = 0;

        public int maxPoints(int[][] points) {
            int n = points.length;
            if( n<=2 ) {
                return n;
            }

            for (int i=0; i<n; i++) {
                Map<String, Integer> countMap = new HashMap<>();

                for (int j=i+1; j<n; j++) {
                    int deltaX = points[j][0] - points[i][0];
                    int deltaY = points[j][1] - points[i][1];
                    // deltaX、deltaY 中任意为0时，需特别处理。因为此时不存在gcd最大公约数，无法化简
                    if( deltaX==0 ) {   // 平行于Y轴的直线
                        deltaY = 1;
                    } else if ( deltaY==0 ) {   // 平行于X轴的直线
                        deltaX = 1;
                    } else {    // deltaX、deltaY 均不为0
                        if( deltaX<0 ) {    // 保证斜率的分母不为负数
                            deltaX *= -1;
                            deltaY *= -1;
                        }
                        int gcd = gcd( Math.abs(deltaX), Math.abs(deltaY) );
                        deltaX /= gcd;
                        deltaY /= gcd;
                    }

                    String key = deltaX + "~" + deltaY;
                    int count = countMap.getOrDefault(key, 1)+1;
                    countMap.put(key, count);
                }
                for (Integer counts : countMap.values() ) {
                    maxPoints = Math.max(maxPoints, counts);
                }

                // 剪枝
                // 1. 如果该直线的共线点数 已经达到 下次枚举时可用的总点数，则无需继续
                // 2. 如果该直线的共线点数 超过半数，则无需继续
                if( maxPoints >= (n-i) || maxPoints > (n/2) ) {
                    break;
                }

            }

            return maxPoints;
        }

        /**
         * 计算a 、b 的最大公约数
         * 欧几里得算法：gcd(a,b) = gcd(b, a%b)
         * @param a
         * @param b
         * @return
         */
        private int gcd(int a, int b) {
            if( b==0 ) {
                return a;
            }
            return gcd(b, a%b);
        }
    }
}