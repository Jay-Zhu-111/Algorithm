package utils;

import utils.TreeNode;

public class Create {

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
}
