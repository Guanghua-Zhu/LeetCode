package com.aaron.LeetCode;

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

    /**
     * DFS
     */
    public static class Solution {
        private int m;
        private int n;

        public int numIslands(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int count = 0;

            for(int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if(grid[i][j]=='1') {
                        search(grid, i, j);
                        count++;
                    }
                }
            }
            return count;
        }

        private void search(char[][] grid, int x, int y) {
            // 坐标越界
            if( !validPos(x,y) ) {
                return;
            }

            // 当前网格不是陆地
            if(grid[x][y] != '1') {
                return;
            }

            // 将当前网格标记为已遍历, 以避免重复遍历
            grid[x][y] = '2';

            // 遍历上、下、左、右四个方向
            search(grid, x, y-1);
            search(grid, x, y+1);
            search(grid, x-1, y);
            search(grid, x+1, y);
        }

        /**
         * 判断是否为有效的坐标
         * @param x
         * @param y
         * @return
         */
        private boolean validPos(int x, int y) {
            if( x>=0 && x<m && y>=0 && y<n ) {
                return true;
            }
            return false;
        }
    }

    /**
     * 并查集
     */
    public static class Solution2 {
        public int numIslands(char[][] grid) {
            UnionFind uf = new UnionFind(grid);
            for (int i=0; i<grid.length; i++) {
                for (int j=0; j<grid[0].length; j++) {
                    if( grid[i][j] =='1' ) {
                        uf.union( new UnionFind.Point(i, j) );
                    }
                }
            }
            int res = uf.getCount();
            return res;
        }

        public static class UnionFind {
            /**
             * 并查集属性: 森林、连通分量数目
             */
            private Map<Point, Point> parent;
            private int count;

            /**
             * 方向向量: 上、下、左、右
             */
            private int[] dx = new int[]{-1, 1,  0,  0};
            private int[] dy = new int[]{ 0, 0, -1, -1};

            /**
             * 矩阵大小
             */
            private int m;
            private int n;

            public UnionFind(char[][] grid) {
                m = grid.length;
                n = grid[0].length;
                parent = new HashMap<>();
                count = 0;

                for (int i=0; i<m; i++) {
                    for (int j=0; j<n; j++) {
                        if( grid[i][j] =='1' ) {
                            Point point = new Point(i, j);
                            parent.put(point, point);
                            count++;
                        }
                    }
                }
            }

            public void union(Point point) {
                Point root = find( point );
                List<Point> roundPoints = getRoundPoints(point);
                for (Point tempPoint : roundPoints) {
                    Point tempRoot = find( tempPoint );
                    if( tempRoot==null || root.equals(tempRoot)) {
                        continue;
                    }

                    parent.put( tempRoot, root );
                    count--;
                }
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

            /**
             * 获取周围点
             * @param point
             * @return
             */
            private List<Point> getRoundPoints(Point point) {
                List<Point> list = new ArrayList<>();
                for(int i=0; i<4; i++) {
                    int x = point.x + dx[i];
                    int y = point.y + dy[i];
                    if( x>=0 && x<m && y>=0 && y<n ) {
                        list.add( new Point(x,y) );
                    }
                }
                return list;
            }

            public int getCount() {
                return count;
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
    }

}