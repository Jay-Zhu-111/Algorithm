package utils;

import utils.TreeNode;

import java.util.Random;

public class Create {


    //backward  6 -1 -4 -6 -5 3 2 -3 9
    public static TreeNode createTree(){
        TreeNode treeNode = new TreeNode(9);
        treeNode.left = new TreeNode(6);
        treeNode.right = new TreeNode(-3);
        treeNode.right.left = new TreeNode(-1);
        treeNode.right.right = new TreeNode(2);
        treeNode.right.right.left = new TreeNode(3);
        treeNode.right.right.left.left = new TreeNode(-6);
        treeNode.right.right.left.right = new TreeNode(-5);
        treeNode.right.right.left.left.left = new TreeNode(-4);
        return treeNode;
    }

    public static int[] createRandomArray(){
        int[] array = new int[15];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int[] createSortedArray(){
        return new int[]{1,2,3,4,4,5,6,7,7,8,8,9,9,9};
    }

    public static int[] create7Array(){
        return new int[]{7,7,7,7,7,7,7};
    }
}
