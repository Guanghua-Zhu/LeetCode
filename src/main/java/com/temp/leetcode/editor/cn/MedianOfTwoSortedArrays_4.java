package com.temp.leetcode.editor.cn;

import java.util.*;

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治 
// 👍 5182 👎 0


/**
 * 4, 寻找两个正序数组的中位数
 * @author Aaron Zhu
 * @date 2022-3-22
 */
public class MedianOfTwoSortedArrays_4{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1Size = nums1==null ? 0 : nums1.length;
        int num2Size = nums2==null ? 0 : nums2.length;
        int allSize = num1Size + num2Size;
        int targetIndex = allSize/2;

        int firstNum=0;
        int secondNum=0;

        int num1Index=0;
        int num2Index=0;
        for(int i=0; i<=targetIndex; i++) {
            int temp;
            if( num1Index >= num1Size ) {
                temp = nums2[ num2Index ];
                num2Index++;
            } else if ( num2Index >= num2Size ) {
                temp = nums1[ num1Index ];
                num1Index++;
            } else if( nums1[num1Index] <= nums2[num2Index] ) {
                temp = nums1[ num1Index ];
                num1Index++;
            } else {
                temp = nums2[ num2Index ];
                num2Index++;
            }

            if( i==targetIndex-1 ) {
                firstNum = temp;
            } else if( i==targetIndex ) {
                secondNum = temp;
            }
        }

        double res;
        if( allSize%2==1 ) {
            res = secondNum;
        } else {
            res = (firstNum+secondNum)/2.0;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
