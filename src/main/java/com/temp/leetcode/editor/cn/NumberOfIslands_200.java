package com.temp.leetcode.editor.cn;

import java.util.*;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1556 👎 0


/**
 * 200, 岛屿数量
 * @author Aaron Zhu
 * @date 2022-2-25
 */
public class NumberOfIslands_200{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if( grid[i][j] ==1 ) {
                    Point point = new .Point(i, j);
                    parent.put(point, point);
                    count++;
                }
            }
        }
    }
}

class UnionFind {
    private Map<Point, Point> parent;

    private int count;

    public UnionFind(char[][] grid) {
        parent = new HashMap<>();
        count = 0;

        int m = grid.length;
        int n = grid[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if( grid[i][j] ==1 ) {
                    Point point = new Point(i, j);
                    parent.put(point, point);
                    count++;
                }
            }
        }
    }

    public void union(Point point) {

    }

    public Point find(Point point) {
        if( !parent.containsKey(point) ) {
            return null;
        }

        while ( !parent.get(point).equals(point) ) {
            // 路径压缩
            Point grandParent = parent.get( parent.get(point) );
            parent.put( point, grandParent);
            point = grandParent;
        }
        return parent.get(point);
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if( obj==null || !(obj instanceof Point) ) {
                return false;
            }

            Point other =(Point)obj;
            if ( this.x==other.x && this.y==other.y ) {
                return true;
            } else {
               return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
}


//leetcode submit region end(Prohibit modification and deletion)
