package zyj;

import org.junit.Test;
import utils.ListNode;
import utils.TreeNode;
import zyj.algorithm.Jz;

import javax.swing.*;
import java.util.Arrays;
import java.util.Stack;

public class jzTest {

    private final Jz main = new Jz();


    @Test
    public void findDup() {
        System.out.println(main.findRepeatNumber(new int[]{1, 1, 2, 1, 4,5,6,7,8,9}));
    }

    @Test
    public void sort() {
        int[] array = new int[] {8, 5, 3 ,4 ,9 ,10, 23};
        main.quickSort(array);
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
        main.findNumberIn2DArray(matrix, 5);
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

        main.reversePrint(listNode1);
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

    @Test
    public void fib() {
        System.out.println(1000000007);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void isPalindrome2() {
    }

    @Test
    public void isPalindrome() {
    }

    @Test
    public void buildTree() {
    }

    @Test
    public void quickSort() {
    }

    @Test
    public void testQuickSort() {
    }

    @Test
    public void findRepeatNumber3() {
    }

    @Test
    public void findRepeatNumber2() {
    }

    @Test
    public void findNumberIn2DArray__2() {
    }

    @Test
    public void findNumberIn2DArray__1() {
    }

    @Test
    public void reversePrint__3() {
    }

    @Test
    public void reversePrint__2() {
    }

    @Test
    public void reversePrint__1() {
    }

    @Test
    public void treePSeq() {
    }

    @Test
    public void treePre() {
    }

    @Test
    public void treePre2() {
    }

    @Test
    public void treePre3() {
    }

    @Test
    public void treeMid3() {
    }

    @Test
    public void treeTail3() {
    }

    @Test
    public void treeMid() {
    }

    @Test
    public void treeMid2() {
    }

    @Test
    public void treeTail() {
    }

    @Test
    public void hammingWeight() {
        int n = 3;
        n >>>= 1;
        System.out.println(n);
    }

    @Test
    public void exist() {
    }

    @Test
    public void minArray2() {
    }

    @Test
    public void minArray() {
    }

    @Test
    public void numWays() {
    }

    @Test
    public void myPow() {
        System.out.println(main.myPow(0.00001, 2147483647));
    }

    @Test
    public void printNumbers2() {
        System.out.println(Arrays.toString(main.printNumbers2(8)));
    }

    @Test
    public void printNumbers() {
    }

    @Test
    public void verifyPostorder() {
        System.out.println(main.verifyPostorder(new int[]{4,8,6,12,16,14,10}));
    }

    @Test
    public void levelOrderIII() {
    }

    @Test
    public void levelOrderII() {
    }

    @Test
    public void levelOrder() {
    }

    @Test
    public void validateStackSequences() {
    }

    @Test
    public void spiralOrder() {
    }

    @Test
    public void isSymmetric2() {
    }

    @Test
    public void isSymmetric() {
    }

    @Test
    public void mirrorTree() {
    }

    @Test
    public void isSubStructure() {
    }

    @Test
    public void mergeTwoLists() {
    }

    @Test
    public void reverseList() {
    }

    @Test
    public void getKthFromEnd() {
    }

    @Test
    public void exchange() {
    }

    @Test
    public void isNumber() {
    }

    @Test
    public void isMatch() {
    }

    @Test
    public void deleteNode() {
    }

    @Test
    public void pathSum() {
        TreeNode n = new TreeNode(5);
        n.left = new TreeNode(4);
        n.right = new TreeNode(8);
        System.out.println(main.pathSum(n, 13));
    }

    @Test
    public void tailorder() {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(5);
        head.right.right = new TreeNode(6);
        main.tailorder(head);
    }

    @Test
    public void treeToDoublyList2() {
    }

    @Test
    public void treeToDoublyList() {
    }

    @Test
    public void copyRandomList() {
    }

    @Test
    public void serialize() {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(5);
        head.right.right = new TreeNode(6);
        System.out.println(main.serialize2(head));
    }

    @Test
    public void deserialize() {
        TreeNode treeNode = main.deserialize("[4,2,5,1,3,null,6]");
        System.out.println(treeNode.val);
        System.out.println(treeNode.left.val);
        System.out.println(treeNode.right.val);
    }

    @Test
    public void cuttingRopeII() {
    }

    @Test
    public void cuttingRope2() {
    }

    @Test
    public void cuttingRope() {
    }

    @Test
    public void serialize2() {
    }
}