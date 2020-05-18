package org.seaman.algorithm.linkedlist;

import java.util.HashSet;
import java.util.Stack;

public class StackUtils {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    //1.删除单链表中的指定节点
    public static void deleteNode(Node head, Node node) {
        //删除尾节点，采用顺序查找找到尾结点的前一个节点
        if (node.next == null) {
            while (head.next != node) {
                head = head.next;
            }
            head.next = null;
        } else if (head == node) {  //要删除的节点是头节点
            head.next = null;
        } else { //要删除的节点是中间普通节点
            Node q = node.next;
            node.data = q.data;
            node.next = q.next;
        }
    }

    //2. 单链表中删除指定数据的节点方法一：利用栈
    public Node removeValue1(Node head, int num) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            if (head.data != num) {
                stack.push(head);
            }
            head = head.next;
        }

        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }

        return head;
    }

    //3. 单链表中删除指定数据的节点方法一：不利用栈
    public Node removeValue2(Node head, int num) {
        while (head != null) {
            if (head.data != num) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.data == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }

            cur = cur.next;
        }

        return head;
    }

    //4. 删除单链表中数值重复出现的节点
    public void deleteDuplication(Node head) {
        if (head == null) {
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.data);
        while (cur != null) {
            if (set.contains(cur.data)) {
                pre.next = cur.next; //移除重复项
            } else {
                set.add(cur.data);
                pre = cur;
            }
            cur = cur.next;
        }

    }


    //6. 两个单链表生成相加链接
    public Node addList2(Node head1, Node head2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1.data);
            head1 = head1.next;
        }

        while (head2 != null) {
            stack2.push(head2.data);
            head2 = head2.next;
        }

        int n1 = 0; //链表1的数值
        int n2 = 0; //链表2的数值
        int n = 0; //n1+n2+ca
        int ca = 0; //进位

        Node node = null; //当前节点
        Node pNode = null; //当前节点的前驱节点
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            n1 = stack1.isEmpty() ? 0 : stack1.pop();
            n2 = stack2.isEmpty() ? 0 : stack2.pop();
            n = n1 + n2 + ca;
            node = new Node(n % 10);
            node.next = pNode;
            pNode = node;
            ca = n / 10;
        }

        if (ca == 1) {
            pNode = node;
            node = new Node(n / 10);
            node.next = pNode;
        }

        return node;
    }

    //7.判断一个单链表是否回文结构(比如：1-2-2-1)
    public boolean isPalindrome1(Node head) {
        if (head == null) {
            return false;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) { //记住这个地方不是cur.next，不然最后一个节点没有压入栈
            stack.push(cur);
            cur = cur.next;
        }

        while (head.next != null) {
            if (head.data != stack.pop().data) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    //8. 删除单链表的倒数第K个节点
    public static Node removeLastKthNode(Node head, int k) {
        if (k <= 0 || head == null) {
            return head;
        }

        //先找到顺数第K-1个节点
        Node p = head;
        for (int i = 0; i < k; i++) {
            if (p.next != null) {
                p = p.next;
            } else {
                return head;
            }
        }

        //找到目标删除节点的前一个节点
        Node q = head;
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        //删除倒数第K个节点
        q.next = q.next.next;

        return head;
    }

}
