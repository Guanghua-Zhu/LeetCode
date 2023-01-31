package com.temp.leetcode.editor.cn;

import java.util.*;

//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
//能放在更大的盘子上面)。移动圆盘时受到以下限制: (1) 每次只能移动一个盘子; (2) 盘子只能从柱子顶端滑出移到下一根柱子; (3) 盘子只能叠在比它大的盘
//子上。 
//
// 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。 
//
// 你需要原地修改栈。 
//
// 示例1: 
//
//  输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
// 
//
// 示例2: 
//
//  输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
// 
//
// 提示: 
//
// 
// A中盘子的数目不大于14个。 
// 
//
// Related Topics 递归 数组 👍 202 👎 0


/**
 * 面试题 08.06, 汉诺塔问题
 * @author Aaron Zhu
 * @date 2023-1-31
 */
public class HanotaLcci_面试题_08_06 {
    public static void main(String[] args) {
    }

    public static class Solution {

        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            if( A==null || A.isEmpty() ) {
                return;
            }

            move(A.size(), A, C, B);
        }

        /**
         * 将n个盘子从a移动c, b作为temp使用
         * 目标：将 A中 #1 ~ #N 盘子移动到 C
         * 1. 将 A 中 #1 ~ #N-1 盘子移动到 B
         * 2. 将 A 中 #N 盘子移动到 C
         * 3. 将 B 中 #1 ~ #N-1 盘子移动到 C
         */
        private void move(int n, List<Integer> a, List<Integer> c, List<Integer> b) {
            if( n==1 ) {
                c.add( a.remove(a.size()-1) );
                return;
            }

            // 将 A 中 #1 ~ #N-1 盘子移动到 B
            move(n-1, a, b, c);
            // 将 A 中 #N 盘子移动到 C
            c.add( a.remove(a.size()-1) );
            // 将 B 中 #1 ~ #N-1 盘子移动到 C
            move(n-1, b, c, a);
        }
    }

}