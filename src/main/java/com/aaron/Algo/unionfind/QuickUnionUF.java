package com.aaron.Algo.unionfind;

/**
 * Quick Union版本的并查集
 * @author Aaron Zhu
 * @date 2022-02-20
 */
public class QuickUnionUF {
    /**
     * Key: 节点; Value: 当前节点所在连通分量的下一个节点
     */
    private int[] parent;

    /**
     * 连通分量的数量
     */
    private int count;

    /**
     * 构造并查集实例
     * @param size 节点数量
     */
    public QuickUnionUF(int size) {
        count = size;
        parent = new int[size];
        for (int i=0; i<size; i++) {
            parent[i] = i;
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
        // 直到找到根节点
        while (p != parent[p]) {
            p = parent[p];
        }
        return parent[p];
    }

    /**
     * 建立p节点、q节点之间的连接
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if( pRoot==qRoot ) {
            // p节点、q节点的根节点一样, 故直接返回
            return;
        }
        // 将 p节点的根节点 指向 q节点的根节点
        parent[pRoot] = qRoot;
        // 连通分量的数量减1
        count--;
    }
}
