package algo.ch01;

/**
 * 采用路径压缩的加权连通算法
 * Created by chiyx on 2016/9/6.
 */
public class QuickUnionImproveUF {

    // 父鏈接數組(由触点索引)
    private int[] id;
    // 各个根节点对应的分量数
    private int[] sz;
    // 连通分量数
    private int count;

    public QuickUnionImproveUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        // 将小树的根节点连接到大树的根节点
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    /**
     * 查找根节点过程中，将访问路径上的节点都指向它的祖父节点，以达到压缩目的
     */
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
