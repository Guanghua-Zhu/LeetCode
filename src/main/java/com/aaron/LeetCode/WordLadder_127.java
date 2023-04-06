package com.aaron.LeetCode;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列
// beginWord -> s1 -> s2 -> ... -> sk： 
//
// 
// 每一对相邻的单词只差一个字母。 
// 
// 对于 1 <= i <= k 时，每个
// si 都在
// wordList 中。注意， beginWord 不需要在
// wordList 中。
// 
// sk == endWord 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 
//中的 单词数目 。如果不存在这样的转换序列，返回 0 。 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
//
// Related Topics 广度优先搜索 哈希表 字符串 👍 1228 👎 0


/**
 * 127, 单词接龙
 * @author Aaron Zhu
 * @date 2023-4-6
 */
public class WordLadder_127{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        String begin = "hit";
        String end = "cog";
        List<String> list = new ArrayList<>( Arrays.asList("hot","dot","dog","lot","log","cog") ) ;
        solution.ladderLength(begin, end, list);
        System.out.println("gg");

    }


    /**
     * BFS
     */
    public static class Solution {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if( !wordList.remove(endWord) ) {
                return 0;
            }

            wordList.add(0, endWord);
            boolean res = false;
            int level = 0;
            // 访问标记
            boolean[] visited = new boolean[wordList.size()];

            Queue<String> queue = new LinkedList<>();
            queue.offer( beginWord );
            int index = wordList.indexOf(beginWord);
            if( index>=0 ) {
                visited[index] = true;
            }

            while ( !res && !queue.isEmpty() ) {
                int size = queue.size();
                level++;
                while (size-- > 0) {
                    String cur = queue.poll();
                    if(endWord.equals(cur)) {
                        res = true;
                        break;
                    }

                    for(int i=0; i<wordList.size(); i++) {
                        String str = wordList.get(i);
                        // 已经访问过
                        if( visited[i] ) {
                            continue;
                        }
                        // 不可以转换
                        if( !isConvert(cur, str) ) {
                            continue;
                        }
                        queue.offer(str);
                        visited[i] = true;
                    }
                }
            }

            return res ? level : 0;
        }

        /**
         * 判断是否可以转换
         * @return
         */
        private boolean isConvert(String cur, String str) {
            int count = 0;
            for (int i=0; i<cur.length(); i++) {
                if(count > 1 ) {
                    return false;
                }else if( cur.charAt(i) != str.charAt(i) ) {
                    count++;
                }
            }
            return count==1;
        }

    }

    /**
     * DFS: TLE
     */
    public static class Solution1 {

        private String endWord;

        private List<String> wordList;

        /**
         * 状态变量
         */
        private Set<String> usedSet;

        /**
         * 全局结果
         */
        private int count;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if( !wordList.remove(endWord) ) {
                return 0;
            }

            init(endWord, wordList);
            search( beginWord );
            return count;
        }

        private void init(String endWord, List<String> wordList) {
            this.endWord = endWord;
            this.wordList = wordList;
            this.usedSet = new HashSet<>();
            this.count = 0;
        }

        /**
         * 回溯搜索
         */
        private boolean search(String cur) {
            if( endWord.equals(cur) ) {  // Base Case
                int tempCount = usedSet.size()+1;
                if( count==0 ) {
                    count = tempCount;
                } else {
                    count = Math.min(count, tempCount);
                }
                return true;
            }

            List<String> validList = calcValidList(cur);
            for (String str : validList) {
                usedSet.add( str );     // 做出选择
                search( str );
                usedSet.remove( str );  // 撤销选择
            }

            return false;
        }

        /**
         * 计算可选择的有效的字符串列表
         * @return
         */
        private List<String> calcValidList(String cur) {
            // 筛选未被使用的字符串
            List<String> noUsedList  = wordList.stream()
                .filter( str -> !usedSet.contains(str) )
                .collect( Collectors.toList() );
            noUsedList.add(0, endWord);

            // 转换规则
            char[] curChs = cur.toCharArray();
            Predicate<String> rule = str -> {
                int count = 0;
                char[] chs = str.toCharArray();
                for (int i=0; i<chs.length; i++) {
                    if( curChs[i] != chs[i] ) {
                        count++;
                    }
                }
                return count == 1;
            };

            // 筛选符合转换规则的字符串
            List<String> list = noUsedList.stream()
                .filter(rule)
                .collect(Collectors.toList());
            return list;
        }


    }

}