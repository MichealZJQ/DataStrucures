package org.example.tree.threadedbinarytree;

/**
 * 线索化二叉树
 */
public class ThreadedArrayBinaryTreeDemo {
    public static void main(String[] args) {
        HeroTree root = new HeroTree(1, "tom");
        HeroTree node2 = new HeroTree(3, "jack");
        HeroTree node3 = new HeroTree(6, "smith");
        HeroTree node4 = new HeroTree(8, "mary");
        HeroTree node5 = new HeroTree(10, "king");
        HeroTree node6 = new HeroTree(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.root = root;
        threadedBinaryTree.threadedNodes(root);

        //测试: 以10号节点测试
        HeroTree leftNode = node5.left;
        HeroTree rightNode = node5.right;
        System.out.println("10号结点的前驱结点是 =" + leftNode); //3
        System.out.println("10号结点的后继结点是=" + rightNode); //1

        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}


class ThreadedBinaryTree {
    public HeroTree root;
    public HeroTree pre;

    //遍历线索化二叉树的方法
    public void threadedList() {
        HeroTree curr = root;
        while (curr != null) {
            //循环的找到leftType == 1的结点，第一个找到就是8结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            while (curr.leftType == 0) {
                curr = curr.left;
            }
            //打印当前这个结点
            System.out.println(curr);
            //如果当前结点的右指针指向的是后继结点,就一直输出,后继节点就是中序遍历要输出的中间节点
            while (curr.rightType == 1) {
                curr = curr.right;
                System.out.println(curr);
            }
            //替换这个遍历的结点
            curr = curr.right;
        }
    }

    /**
     * 对二叉树进行中序线索化
     */
    public void threadedNodes(HeroTree node) {
        if (node == null) return;

        // 线索化左子树
        threadedNodes(node.left);
        // 线索化当前节点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;// 表示左节点是线索化节点
        }
        // 下一次设置右子节点
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;// 表示右节点是线索化节点
        }
        // ！！！！！ 将pre指向node
        pre = node;

        // 线索化右子树
        threadedNodes(node.right);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root != null)
            root.preOrder();
        else System.out.println("二叉树为空");
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public void infixOrder() {
        if (root != null)
            root.infixOrder();
        else System.out.println("二叉树为空");
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public void postOrder() {
        if (root != null)
            root.postOrder();
        else System.out.println("二叉树为空");
    }

    /**
     * 删除
     */
    public void delete(int id) {
        if (root != null) {
            if (root.id == id) {
                root = null;
            } else {
                root.delete(id);
            }
        } else {
            System.out.println("二叉树为空，不能删除");
        }
    }
}

class HeroTree {
    public Integer id;
    public String name;
    public HeroTree left;
    public HeroTree right;

    // 1表示是前驱节点，0表示左子树
    public int leftType;
    // 1表示是后继节点，0表示右子树
    public int rightType;

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public void infixOrder() {
        if (this.left != null) this.left.infixOrder();
        System.out.println(this);
        if (this.right != null) this.right.infixOrder();
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public void postOrder() {
        if (this.left != null) this.left.postOrder();
        if (this.right != null) this.right.postOrder();
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     */
    public HeroTree preSearch(int id) {
        if (this.id == id) {
            return this;
        }
        HeroTree ret = null;
        if (this.left != null) {
            ret = this.left.preSearch(id);
        }
        if (ret != null) {
            return ret;
        }
        if (this.right != null) {
            ret = this.right.preSearch(id);
        }
        return ret;
    }

    /**
     * 中序遍历查找
     */
    public HeroTree infixSearch(int id) {
        HeroTree ret = null;
        if (this.left != null)
            ret = this.left.infixSearch(id);
        if (ret != null)
            return ret;
        if (this.id == id)
            return this;
        if (this.right != null)
            ret = this.right.infixSearch(id);
        return ret;
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public HeroTree postSearch(int id) {
        HeroTree ret = null;
        if (this.left != null)
            ret = this.left.postSearch(id);
        if (ret != null)
            return ret;
        if (this.right != null)
            ret = this.right.postSearch(id);
        if (ret != null)
            return ret;
        if (this.id == id)
            return this;
        return ret;
    }

    public HeroTree(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 递归删除
     * 叶子节点直接删除
     * 非叶子节点删除子树
     */
    public void delete(int id) {
        // 判断左子节点
        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }
        // 判断右子节点
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        // 递归调用左子树
        if (this.left != null) {
            this.left.delete(id);
        }
        // 递归调用右子树
        if (this.right != null) {
            this.right.delete(id);
        }
    }

    @Override
    public String toString() {
        return "HeroTree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}