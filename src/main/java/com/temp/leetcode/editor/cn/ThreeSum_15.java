package com.temp.leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 4545 👎 0


/**
 * 15, 三数之和
 * @author Aaron Zhu
 * @date 2022-3-28
 */
public class ThreeSum_15{
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.threeSum( new int[]{-2,0,1,1,2} );

        System.out.println("gg");
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/**
 * 哈希表
 */
class Solution1 {
    public List<List<Integer>> threeSum(int[] nums) {
        if( nums==null || nums.length<3 ) {
            return Collections.emptyList();
        }

        Set<List<Integer>> set = new HashSet<>();

        // Key: 数; Value: 索引列表
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            map.compute(num, (k,v) -> {
                if(v==null) {
                    v = 0;
                }
                v++;
                return v;
            } );
        }

        for (Map.Entry<Integer, Integer> entry1 : map.entrySet()) {
            int num1 = entry1.getKey();
            int num1Count = entry1.getValue();
            entry1.setValue( num1Count-1 );

            for(Map.Entry<Integer, Integer> entry2 : map.entrySet() ) {
                int num2 = entry2.getKey();
                int num2Count = entry2.getValue();
                if( num2Count==0 ) {
                    continue;
                }
                entry2.setValue( num2Count-1 );
                int num3 = 0 - num1 - num2;
                int num3Count = map.getOrDefault(num3,0);
                entry2.setValue( num2Count );
                if( num3Count==0 ) {
                    continue;
                }

                set.add( buildSortList( num1, num2, num3 ) );
            }

            entry1.setValue( num1Count );
        }

        List<List<Integer>> list = new ArrayList<>(set);
        return list;
    }

    private List<Integer> buildSortList(int num1, int num2, int num3) {
        Integer[] array = new Integer[]{num1, num2, num3};
        for (int i=0; i<array.length-1; i++) {
            for (int j=0; j<array.length-i-1; j++) {
                if( array[j] > array[j+1] ) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        return Arrays.asList(array);
    }

}
