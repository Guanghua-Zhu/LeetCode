package com.temp.leetcode.editor.cn;

//找出数组中重复的数字。 
//
// 
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。 
//
// 示例 1： 
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3 
// 
//
// 
//
// 限制： 
//
// 2 <= n <= 100000 
// Related Topics 数组 哈希表 排序 
// 👍 659 👎 0


import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Aaron Zhu
 * @date 2022-1-4
 */
public class ShuZuZhongZhongFuDeShuZiLcof_剑指 Offer 03{
  public static void main(String[] args) {
       Solution solution = new Solution();
  }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findRepeatNumber(int[] nums) {

    }
}
//leetcode submit region end(Prohibit modification and deletion)

/**
 * 基于HashSet
 */
class Solution1 {
    public int findRepeatNumber(int[] nums) {
        int result = -1;
        HashSet<Integer> set = new HashSet<>();
        for(int e : nums)  {
            if(!set.add(e)) {
                result = e;
                break;
            }
        }
        return result;
    }
}