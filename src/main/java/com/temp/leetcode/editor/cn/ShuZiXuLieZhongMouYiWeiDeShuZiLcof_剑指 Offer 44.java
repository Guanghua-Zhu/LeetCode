package com.temp.leetcode.editor.cn;

import java.util.*;

//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。 
//
// 请写一个函数，求任意第n位对应的数字。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 输入：n = 11
//输出：0 
//
// 
//
// 限制： 
//
// 
// 0 <= n < 2^31 
// 
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/ 
// Related Topics 数学 二分查找 
// 👍 209 👎 0


/**
 * 剑指 Offer 44, 数字序列中某一位的数字
 * @author Aaron Zhu
 * @date 2022-3-10
 */
public class ShuZiXuLieZhongMouYiWeiDeShuZiLcof_剑指 Offer 44{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * Key: 索引范围最大值; Value: 该索引范围是几位数
     */
    private static Map<Long, Integer> map;

    static {
        long sum = 0;
        long maxIndex = 0;
        long lastMaxIndex=0;
        map = new LinkedHashMap<>();

        for(int i=1; ;i++) {
            if( i==1 ) {
                map.put((long)9,1);
                lastMaxIndex = 9;
                sum = 10;
            } else {
                maxIndex = lastMaxIndex + (long)(Math.pow(10, i-1)*9*i);
                map.put(maxIndex, i);
                lastMaxIndex = maxIndex;

            }
        }

    }


    public int findNthDigit(int n) {

    }

    // 1位数: 0 ~ 9
    // 2位数: 10 ~ 189
    // 3位数: 190 ~ 2889

    private void buildIndexRange() {

    }

}
//leetcode submit region end(Prohibit modification and deletion)
