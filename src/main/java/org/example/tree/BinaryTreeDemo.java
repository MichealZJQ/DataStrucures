package org.example.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        HeroTree node1 = new HeroTree(1,"宋江");
        HeroTree node2 = new HeroTree(2,"卢俊义");
        HeroTree node3 = new HeroTree(3,"吴用");
        HeroTree node4 = new HeroTree(4,"林冲");

        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        tree.root = node1;

        System.out.println("前序遍历：");
        tree.preOrder();
        System.out.println("后序遍历：");
        tree.postOrder();
        System.out.println("中序遍历：");
        tree.infixOrder();
        System.out.println("删除节点2之后前序遍历：");
        tree.delete(2);
        tree.preOrder();
    }
}

class BinaryTree {
    public HeroTree root;

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
    public void delete(int id){
        if (root != null){
            if (root.id == id){
                root = null;
            }else {
                root.delete(id);
            }
        }else {
            System.out.println("二叉树为空，不能删除");
        }
    }
}

class HeroTree {
    public Integer id;
    public String name;
    public HeroTree left;
    public HeroTree right;

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
    public HeroTree preSearch(int id){
        if (this.id == id){
            return this;
        }
        HeroTree ret = null;
        if (this.left != null){
            ret = this.left.preSearch(id);
        }
        if (ret != null){
            return ret;
        }
        if (this.right != null){
            ret = this.right.preSearch(id);
        }
        return ret;
    }

    /**
     * 中序遍历查找
     */
    public HeroTree infixSearch(int id){
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
     * @return
     */
    public HeroTree postSearch(int id){
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
    public void delete(int id){
        // 判断左子节点
        if (this.left != null && this.left.id == id){
            this.left = null;
            return;
        }
        // 判断右子节点
        if (this.right != null && this.right.id == id){
            this.right = null;
            return;
        }
        // 递归调用左子树
        if (this.left != null){
            this.left.delete(id);
        }
        // 递归调用右子树
        if (this.right != null){
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