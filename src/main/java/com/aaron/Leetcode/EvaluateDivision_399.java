package com.aaron.Leetcode;

import java.util.*;

//给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values
//[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。 
//
// 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj =
// ? 的结果作为答案。 
//
// 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替
//代这个答案。 
//
// 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"]
//,["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//条件：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
// 
//
// 示例 2： 
//
// 
//输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], quer
//ies = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]
// 
//
// 示例 3： 
//
// 
//输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a
//","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 20 
// equations[i].length == 2 
// 1 <= Ai.length, Bi.length <= 5 
// values.length == equations.length 
// 0.0 < values[i] <= 20.0 
// 1 <= queries.length <= 20 
// queries[i].length == 2 
// 1 <= Cj.length, Dj.length <= 5 
// Ai, Bi, Cj, Dj 由小写英文字母与数字组成 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 数组 最短路 
// 👍 685 👎 0

/**
 * 399, 除法求值
 * @author Aaron Zhu
 * @date 2022-2-25
 */
public class EvaluateDivision_399{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DFS
     */
    public static class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // 1. 将变量名转换为ID
            Map<String, Integer> idMap = new HashMap<>();
            int id = 0;
            for (List<String> equation : equations) {
                String varA = equation.get(0);
                String varB = equation.get(1);
                if( !idMap.containsKey(varA) ) {
                    idMap.put(varA, id);
                    id++;
                }
                if( !idMap.containsKey(varB) ) {
                    idMap.put(varB, id);
                    id++;
                }
            }

            // 2. 构建有向加权图
            Graph graph = new Graph(id);
            for (int i=0; i<values.length; i++) {
                String varA = equations.get(i).get(0);
                String varB = equations.get(i).get(1);
                Integer varAId = idMap.get(varA);
                Integer varBId = idMap.get(varB);
                Double weight = values[i];
                graph.addEdge(varAId, varBId, weight);
                graph.addEdge(varBId, varAId, 1.0/weight);
            }

            // 3. 计算表达式结果
            double[] res = new double[queries.size()];
            for (int i=0; i<queries.size(); i++) {
                String varA = queries.get(i).get(0);
                String varB = queries.get(i).get(1);
                Integer varAId = idMap.get(varA);
                Integer varBId = idMap.get(varB);
                // 变量不存在
                if (varAId==null || varBId==null) {
                    res[i] = -1.0;
                } else if( varAId.equals(varBId) ) {
                    // 两个变量均存在, 但其实为相同变量
                    res[i] = 1.0;
                } else {
                    // DFS搜索
                    double[] pathRes = new double[id];
                    Arrays.fill(pathRes, -1.0);
                    pathRes[ varAId ] = 1;
                    dfs(graph, pathRes, varAId, varBId);
                    res[i] = pathRes[varBId];
                }
            }

            return res;
        }

        private void dfs(Graph graph, double[] pathRes, int current, int target) {
            if( pathRes[target]>0 ) {
                return;
            }

            graph.getAdj(current);
            List<Graph.Edge> edgeList = graph.getAdj(current);
            for (Graph.Edge edge : edgeList) {
                if( pathRes[target]>0 ) {
                    return;
                }

                Integer next = edge.nextVId;
                // 该节点已经访问过, 无需重复遍历
                if( pathRes[next]>0 ) {
                    continue;
                }
                // 计算从当前节点到下一节点路径计算结果
                pathRes[next] = pathRes[current] * edge.weight;
                dfs(graph, pathRes, next, target);
            }
        }
    }

    /**
     * BFS
     */
    public static class Solution1 {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // 1. 将变量名转换为ID
            Map<String, Integer> idMap = new HashMap<>();
            int id = 0;
            for (List<String> equation : equations) {
                String varA = equation.get(0);
                String varB = equation.get(1);
                if( !idMap.containsKey(varA) ) {
                    idMap.put(varA, id);
                    id++;
                }
                if( !idMap.containsKey(varB) ) {
                    idMap.put(varB, id);
                    id++;
                }
            }

            // 2. 构建有向加权图
            Graph graph = new Graph(id);
            for (int i=0; i<values.length; i++) {
                String varA = equations.get(i).get(0);
                String varB = equations.get(i).get(1);
                Integer varAId = idMap.get(varA);
                Integer varBId = idMap.get(varB);
                Double weight = values[i];
                graph.addEdge(varAId, varBId, weight);
                graph.addEdge(varBId, varAId, 1.0/weight);
            }

            // 3. 计算表达式结果
            double[] res = new double[queries.size()];
            for (int i=0; i<queries.size(); i++) {
                String varA = queries.get(i).get(0);
                String varB = queries.get(i).get(1);
                Integer varAId = idMap.get(varA);
                Integer varBId = idMap.get(varB);
                // 变量不存在
                if (varAId==null || varBId==null) {
                    res[i] = -1.0;
                } else if( varAId.equals(varBId) ) {
                    // 两个变量均存在, 但其实为相同变量
                    res[i] = 1.0;
                } else {
                    // BFS搜索
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(varAId);
                    double[] pathRes = new double[id];
                    Arrays.fill(pathRes, -1.0);
                    pathRes[ varAId ] = 1;
                    while ( !queue.isEmpty() && pathRes[varBId]<0 ) {
                        Integer current = queue.poll();
                        List<Graph.Edge> edgeList = graph.getAdj(current);
                        for (Graph.Edge edge : edgeList) {
                            Integer next = edge.nextVId;
                            if( pathRes[ next ] > 0) {
                                // 该节点已经访问过, 无需重复遍历
                                continue;
                            }

                            // 计算从当前节点到下一节点路径计算结果
                            pathRes[next] = pathRes[current] * edge.weight;
                            queue.add( next );
                        }
                    }
                    res[i] = pathRes[varBId];
                }
            }

            return res;
        }
    }

    /**
     * 加权有向图
     */
    public static class Graph {
        /**
         * 顶点数目
         */
        private int V;

        /**
         * 邻接表
         */
        private Map<Integer, List<Edge>> adj;

        public Graph(int V) {
            this.V = V;
            adj = new HashMap<>();
        }

        public void addEdge(int v, int w, double weight) {
            List list = adj.computeIfAbsent(v, k->new LinkedList<>());
            list.add( new Edge(w, weight) );
        }

        public List<Edge> getAdj(int v) {
            return adj.get(v);
        }

        public static class Edge {
            /**
             * 下一个节点的Id
             */
            private int nextVId;

            /**
             * 当前节点 到 下一个节点 的边的权重
             */
            private double weight;

            public Edge(int nextVId, double weight) {
                this.nextVId = nextVId;
                this.weight = weight;
            }
        }
    }
}

