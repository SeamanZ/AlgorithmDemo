package org.seaman.algorithm.binarytree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
//相关参考 [【数据结构】二叉树的前中后序遍历（递归与非递归）_数据结构与算法_白夜行-CSDN博客](https://blog.csdn.net/baiye_xing/article/details/75198255)
public class BinaryTreeUtil {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //1.分层遍历二叉树迭代：宽度优先遍历
    public static void levelTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.removeFirst();
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }

    }

    //分层遍历应用：按层打印二叉树
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayBlockingQueue<>(100);
        TreeNode last = root; //当前的最后节点
        TreeNode nLast = root; //下一行的最右节点
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode out = queue.poll();
            System.out.print(out.val + "");
            list.add(out.val);

            if (out.left != null) {
                queue.add(out.left);
                nLast = out.left;
            }

            if (out.right != null) {
                queue.add(out.right);
                nLast = out.right;
            }

            //遍历到当前行最后一个，则设置last为下一行最后一个
            if (out == last) {
                System.out.println("");
                last = nLast;
            }
        }

        return list;
    }

    //2. 前序遍历（递归）
    public static void preorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preorderTraversalRec(root.left);
        preorderTraversalRec(root.right);
    }

    //2. 前序遍历（迭代）
    public static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop(); //出栈顶元素
            System.out.print(cur.val + " ");
            //关键点：要先压入右孩子，再压入左孩子，这样出栈的时候会先打印左孩子再打印右孩子
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

    }

    //3. 中序遍历（递归）
    public static void inorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversalRec(root.left);
        System.out.println(root.val + " ");
        inorderTraversalRec(root.right);
    }

    //3. 中序遍历（遍历）
    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.val + " ");
                cur = cur.right;
            }
        }

    }

    //4. 后序遍历（递归）
    public void postOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.val + " ");
    }

    //4. 后序遍历（迭代）
    public static void postorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<>(); //第一个stack用于添加node的和它的左右孩子
        Stack<TreeNode> output = new Stack<>(); //第二个stack用于翻转第一个输出

        s.push(root);
        while (!s.isEmpty()) { //确保所有元素都被翻转到第二个stack
            TreeNode cur = s.pop(); //把栈顶元素添加到第二个stack
            output.push(cur);

            if (cur.left != null) { //把栈顶元素的左孩子和右孩子分别添加入第一个stack
                s.push(cur.left);
            }

            if (cur.right != null) {
                s.push(cur.right);
            }
        }

        while (!output.isEmpty()) { //遍历输出第二个stack，即为后序遍历
            System.out.print(output.pop().val + " ");
        }

    }

}

