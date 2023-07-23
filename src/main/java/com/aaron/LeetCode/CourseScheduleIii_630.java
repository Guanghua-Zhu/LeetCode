package com.aaron.LeetCode;

import java.util.*;

//这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, 
//lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。 
//
// 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。 
//
// 返回你最多可以修读的课程数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
//输出：3
//解释：
//这里一共有 4 门课程，但是你最多可以修 3 门：
//首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
//第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
//第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
//第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。 
//
// 示例 2： 
//
// 
//输入：courses = [[1,2]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：courses = [[3,2],[4,3]]
//输出：0
// 
//
// 
//
// 提示: 
//
// 
// 1 <= courses.length <= 10⁴ 
// 1 <= durationi, lastDayi <= 10⁴ 
// 
//
// Related Topics 贪心 数组 排序 堆（优先队列） 👍 401 👎 0


/**
 * 630, 课程表 III
 * @author Aaron Zhu
 * @date 2023-7-20
 */
public class CourseScheduleIii_630{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 贪心策略
     */
    public static class Solution {
        public int scheduleCourse(int[][] courses) {
            // 对所有课程按ddl升序排序
            Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
            // 最大堆用于记录以选择的课程时长
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2-o1);

            int curDay = 0;
            for (int[] course : courses) {
                int duration = course[0];
                int ddl = course[1];

                // 当前这门课如果来得及学，就学
                if( curDay+duration<=ddl ) {
                    curDay = curDay + duration;
                    maxHeap.offer( duration );
                } else if( !maxHeap.isEmpty() && maxHeap.peek()>duration ) {
                    // 将队列中已选择的课程 时长最长课程与当前课程进行替换,以给后面的课程节约时间
                    curDay = curDay - maxHeap.poll() + duration;
                    maxHeap.offer( duration );
                }
            }

            return maxHeap.size();
        }
    }
}