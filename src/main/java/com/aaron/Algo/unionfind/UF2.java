package com.aaron.Algo.unionfind;

/**
 * 优化版本的并查集
 * @author Aaron Zhu
 * @date 2022-02-20
 */
public class UF2 {
    /**
     * Key: 节点; Value: 当前节点所在连通分量的下一个节点
     */
    public int[] parent;

    /**
     * 根节点对应的秩, 即连通分量中的节点数
     */
    private int[] rank;

    /**
     * 连通分量的数量
     */
    private int count;

    /**
     * 构造并查集实例
     * @param size 节点数量
     */
    public UF2(int size) {
        count = size;
        parent = new int[size];
        rank = new int[size];
        for(int i=0; i<size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
     * @apiNote 路径压缩: 隔代路径压缩
     */
    public int find(int p) {
        // 直到找到根节点
        while (p != parent[p]) {
            // 隔代路径压缩: 将当前节点直接指向祖父节点(即父节点的父节点)
            parent[p] = parent[ parent[p] ];
            p = parent[p];
        }
        return parent[p];
    }

    /**
     * 获取p节点所在连通分量的标识
     * @param p
     * @return
     * @apiNote 路径压缩: 完全路径压缩(迭代版本)
     */
/*
    public int find(int p) {
        if( p != parent[p] ) {
            parent[p] = find( parent[p] );
        }
        return parent[p];
    }
*/

    /**
     * 获取p节点所在连通分量的标识
     * @param p
     * @return
     * @apiNote 路径压缩: 完全路径压缩(循环版本)
     */
/*
    public int find(int p) {
        // 1. 找到根节点Root
        int root = p;
        while(root!=parent[root]) {
            root = parent[root];
        }

        // 2. 循环压缩 p->...->root 的路径
        while( p != root ) {
            // 暂存当前节点的下一个节点
            int temp = parent[p];
            // 将当前节点直接指向Root
            parent[p] = root;
            // 继续处理下一个节点
            p = temp;
        }

        return root;
    }
*/

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

        // 将 小树的根节点 指向 大树的根节点
        // 同时, 更新大树根节点对应的节点数
        if(rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
            rank[qRoot] += rank[pRoot];
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += rank[qRoot];
        }
        // 连通分量的数量减1
        count--;
    }

    public static void main(String[] args) {
        UF2 uf = new UF2(5);
        uf.parent = new int[]{1, 2, 3, 4, 4};
        uf.find(0);
        System.out.println("gg");
    }
}
