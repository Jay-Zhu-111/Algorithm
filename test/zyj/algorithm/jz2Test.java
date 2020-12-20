package zyj.algorithm;

import org.junit.jupiter.api.Test;
import utils.TreeNode;

import static org.junit.jupiter.api.Assertions.*;

class jz2Test {

    Jz2 main = new Jz2();

    @Test
    void findRepeatNumber() {
    }

    @Test
    void findNumberIn2DArray() {
    }

    @Test
    void replaceSpace() {
    }

    @Test
    void reversePrint() {
    }

    @Test
    void buildTree() {
        main.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
    }

    @Test
    void testBuildTree() {
    }

    @Test
    void spiralOrder() {
    }

    @Test
    void numWays() {
    }

    @Test
    void minArray() {
    }

    @Test
    void exist() {
    }

    @Test
    void movingCount() {
    }

    @Test
    void cuttingRope() {
        System.out.println(main.cuttingRope(10));
    }

    @Test
    void cuttingRopeII() {
    }

    @Test
    void multiMod() {
    }

    @Test
    void myPow() {
    }

    @Test
    void printNumbers() {
    }

    @Test
    void deleteNode() {
    }

    @Test
    void isMatch() {
    }

    @Test
    void isNumber() {
    }

    @Test
    void exchange() {
    }

    @Test
    void getKthFromEnd() {
    }

    @Test
    void reverseList2() {
    }

    @Test
    void mergeTwoLists() {
    }

    @Test
    void isSubStructure() {
    }

    @Test
    void mirrorTree() {
    }

    @Test
    void isSymmetric() {
    }

    @Test
    void validateStackSequences() {
        System.out.println(main.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
    }

    @Test
    void validateStackSequences2() {
    }

    @Test
    void verifyPostorder() {
    }

    @Test
    void pathSum() {
    }

    @Test
    void copyRandomList() {
    }

    @Test
    void treeToDoublyList() {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(5);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        TreeNode re = main.treeToDoublyList(head);
    }
}