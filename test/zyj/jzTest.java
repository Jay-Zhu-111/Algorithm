package zyj;

import org.junit.Test;
import utils.ListNode;
import utils.TreeNode;
import zyj.algorithm.Jz;

import java.util.Arrays;
import java.util.Stack;

public class jzTest {

    private final Jz jz = new Jz();


    @Test
    public void findDup() {
        System.out.println(jz.findRepeatNumber(new int[]{1, 1, 2, 1, 4,5,6,7,8,9}));
    }

    @Test
    public void sort() {
        int[] array = new int[] {8, 5, 3 ,4 ,9 ,10, 23};
        jz.quickSort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void findRepeatNumber() {
    }

    @Test
    public void findDup2() {
    }

    @Test
    public void findDup3() {
    }

    @Test
    public void findNumberIn2DArray() {
        int[][] matrix = new int[5][5];
        matrix[0] = new int[]{1, 4, 7, 11, 15};
        matrix[1] = new int[]{2, 5, 8, 12, 19};
        matrix[2] = new int[]{3, 6, 9, 16, 22};
        matrix[3] = new int[]{10, 13, 14, 17, 24};
        matrix[4] = new int[]{18, 21, 23, 26, 30};
        jz.findNumberIn2DArray(matrix, 5);
    }

    @Test
    public void findNumberIn2DArray_2() {
    }

    @Test
    public void replaceSpace() {
    }

    @Test
    public void reversePrint() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(2);

        listNode1.next = listNode2;
        listNode2.next = listNode3;

        jz.reversePrint(listNode1);
    }

    @Test
    public void tree() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        pre(treeNode1);

//        jz.treePre3(treeNode1);
//        System.out.println("");
//        jz.treeMid3(treeNode1);
//        System.out.println("");
//        jz.treeTail3(treeNode1);
    }

    public void pre(TreeNode head){
        Stack<TreeNode> stack = new Stack<>();
        while(head != null || !stack.isEmpty()){
            while(head != null){
                System.out.println(head.val);
                stack.push(head);
                head = head.left;
            }
            if(!stack.isEmpty()){
                head = stack.pop().right;
            }
        }
    }
}