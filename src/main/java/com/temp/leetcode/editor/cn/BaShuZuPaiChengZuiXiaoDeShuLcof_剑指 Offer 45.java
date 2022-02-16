package com.temp.leetcode.editor.cn;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。 
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 
// 👍 380 👎 0


/**
 * @author Aaron Zhu
 * @date 2022-2-16
 */
public class BaShuZuPaiChengZuiXiaoDeShuLcof_剑指 Offer 45{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

import java.math.BigDecimal;

class Solution {

    private String res;

    private boolean[] usedFlag;

    private List<Integer> state;

    public String minNumber(int[] nums) {
        init(nums);
        search(nums, 1);

        return res.toString();
    }

    private void init(int[] nums) {
        res = null;
        usedFlag = new boolean[nums.length];
        state = new ArrayList<>();
    }

    private void search(int[] nums, int count) {
        if(count > nums.length) {
            convertRes();
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if( usedFlag[i] ) {
                continue;
            }

            usedFlag[i] = true;
            state.add( nums[i] );

            search(nums, count+1);

            usedFlag[i] = false;
            state.remove( state.size()-1 );
        }
    }

    private void convertRes() {
         String currentStr = state.stream()
             .map( e -> e.toString() )
            .collect( Collectors.joining() );

         if( res==null ) {
             res = currentStr;
             return;
         }

        BigDecimal currentStrNum = new BigDecimal(currentStr);
        BigDecimal resNum = new BigDecimal(res);

        if( resNum.compareTo( currentStrNum ) > 0 ) {
             res = currentStr;
         }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
