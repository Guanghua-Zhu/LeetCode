package com.aaron.Algo.unionfind;

/**
 * 并查集
 * @author Aaron Zhu
 * @date 2022-02-20
 */
public class UnionFind {
    /**
     * Key: 节点; Value: 节点对应的连通分量标识
     */
    private int[] id;

    /**
     * 连通分量的数量
     */
    private int count;

    /**
     * 构造并查集实例
     * @param size 节点数量
     */
    public UnionFind(int size) {
        count = size;
        id = new int[size];
        for (int i=0; i<size; i++) {
            id[i] = i;
        }
    }

    /**
     * 获取连通分量的数量
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 判断两个节点是否存在于同一个连通分量当中
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 获取p节点所在连通分量的标识
     * @param p
     * @return
     */
    public int find(int p) {
        return id[p];
    }

    /**
     * 建立p节点、q节点之间的连接
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if( pId == qId ) {
            // p节点、q节点已经位于同一连通分量当中, 故直接返回
            return;
        }

        // 将p节点所在连通分量的标识全部更改为q节点所在连通分量的标识
        for(int i=0; i<id.length; i++) {
            if( id[i] == pId ) {
                id[i] = qId;
            }
        }
        // 连通分量的数量减1
        count--;
    }
}
