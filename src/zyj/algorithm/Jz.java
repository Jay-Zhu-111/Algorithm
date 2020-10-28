package zyj.algorithm;

import utils.ListNode;
import utils.TreeNode;
import zyj.Pair;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Jz {

    //8

    //7
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0)
            return null;
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight){
        if(preLeft > preRight || inLeft > inRight)
            return null;
        if(preLeft == preRight && inLeft == inRight)
            return new TreeNode(preorder[preLeft]);

        TreeNode root = new TreeNode(preorder[preLeft]);
        int inRoot = 0;
        boolean findFlag = false;
        for (int i = inLeft; i <= inRight; i++) {
            if(inorder[i] == root.val){
                inRoot = i;
                findFlag = true;
            }
        }
        if(!findFlag)
            throw new RuntimeException("can't find in inorder");
        int leftLength = inRoot - inLeft;
        int rightLength = inRight - inRoot;
        root.left = buildTree(preorder, preLeft + 1, preLeft + leftLength, inorder, inLeft, inRoot - 1);
        root.right = buildTree(preorder, preLeft + leftLength + 1, preLeft + leftLength + rightLength, inorder, inRoot + 1, inRight);
        return root;
    }

    public void quickSort(int[] array){
        quickSort(array, 0, array.length - 1);
    }

    public void quickSort(int[] array, int left, int right){
        if(left >= right)
            return;
        if(left < 0 || right > array.length - 1){
            return;
        }
        int i = left;
        int j = right;
        int flag = array[i];
        while(i < j){
            while(j > i){
                if(array[j] < flag){
                    array[i] = array[j];
                    i++;
                    break;
                }
                j--;
            }
            while(j > i){
                if(array[i] >= flag){
                    array[j] = array[i];
                    break;
                }
                i++;
            }
        }
        array[i] = flag;
        quickSort(array, left, i);
        quickSort(array, i + 1, right);
    }

    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int left, int right) {
        if (left >= right)
            return;
        int i = left;
        int j = right;
        int flag = array[left];
        while (i < j) {
            for (; array[j] >= flag && i < j; j--) ;
            for (; array[i] <= flag && i < j; i++) ;
            if (i < j)
                swap(array, i, j);
        }
        swap(array, left, i);

        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //3
    //不适合本题，可能有数字没有
    public int findRepeatNumber3(int[] nums) {
        if(nums.length < 2)
            return -1;

        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(findRepeatNumber3_countNumber(nums, left, mid) > mid - left + 1){
                right = mid;
            }
            else
                left = mid + 1;
        }
        return left;
    }

    private int findRepeatNumber3_countNumber(int[] nums, int left, int right){
        int count = 0;
        for (int num : nums) {
            if(num >= left && num <= right)
                count++;
        }
        return count;
    }

    //3
    //排序后直接检索
    public int findRepeatNumber2(int[] nums) {
        if(nums.length < 2)
            return -1;

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1])
                return nums[i];
        }
        return -1;
    }

    //3
    //不适合本题，可能有数字没有
    public int findRepeatNumber(int[] nums) {
        return findRepeatNumber(nums, 1, nums.length - 1);
    }

    private int findRepeatNumber(int[] nums, int begin, int end) {
        int mid = (begin + end) / 2;
        if(begin == end)
            return begin;
        if(findRepeatNumber_countNum(nums, begin, mid) > mid - begin + 1)
            return findRepeatNumber(nums, begin, mid);
        else
            return findRepeatNumber(nums, mid + 1, end);
    }

    private int findRepeatNumber_countNum(int[] nums, int numBegin, int numEnd){
        int count = 0;
        for (int num : nums) {
            if (num >= numBegin && num <= numEnd) {
                count++;
            }
        }
        return count;
    }

    public int findDup(int[] array, int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num = array[i];
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return -1;
    }

    public int findDup2(int[] array) {
        int begin = 0;
        int end = array.length - 2;
        while (begin != end) {
            int mid = (begin + end) / 2;
            if (findDup2_numCount(array, begin, mid) > mid - begin + 1) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;

    }

    private int findDup2_numCount(int[] array, int beginNum, int endNum) {
        int count = 0;
        for (int i : array) {
            if (i >= beginNum && i <= endNum) {
                count++;
            }
        }
        return count;
    }

    public int findDup3(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            if (i == num) {
            } else if (array[num] != num) {
                swap(array, i, array[i]);
            } else
                return num;
        }
        return -1;
    }


    //4
    public boolean findNumberIn2DArray__2(int[][] matrix, int target) {
        if(matrix.length == 0)
            return false;
        if(matrix[0].length == 0)
            return false;
        return findNumberIn2DArray__2(matrix, 0, 0, target);
    }

    private boolean findNumberIn2DArray__2(int[][] matrix, int i, int j, int target) {
        int num = matrix[i][j];
        if(num == target)
            return true;
        if(i + 1 < matrix.length && matrix[i + 1][j] <= target){
            if(findNumberIn2DArray__2(matrix, i + 1, j, target))
                return true;
        }
        if(j + 1 < matrix[0].length && matrix[i][j + 1] <= target){
            if(findNumberIn2DArray__2(matrix, i, j + 1, target))
                return true;
        }
        return false;
    }

    //4
    public boolean findNumberIn2DArray__1(int[][] matrix, int target) {
        if(matrix.length == 0)
            return false;
        if(matrix[0].length == 0)
            return false;

        int i = 0;
        int j = matrix[0].length - 1;
        while(i < matrix.length && j >= 0){
            int num = matrix[i][j];
            if(num == target)
                return true;
            else if(num > target){
                j--;
            }
            else
                i++;
        }
        return false;
    }

    //4
    public boolean findNumberIn2DArray(int[][] matrix, int target){
        if(matrix.length == 0)
            return false;
        int i = 0;
        int j = matrix[0].length - 1;
        while(i < matrix.length && j >= 0){
            if(matrix[i][j] > target)
                j--;
            else if(matrix[i][j] < target)
                i++;
            else
                return true;
        }
        return false;
    }

    public boolean findNumberIn2DArray_2(int[][] matrix, int target) {
        int i = 0;
        int j = 0;
        while (i < matrix.length) {
            while(j < matrix[0].length && matrix[i][j] <= target) j++;
            j--;
            if(j == -1)
                return false;
            if(matrix[i][j] == target)
                return true;
            while(i < matrix.length && matrix[i][j] <= target) i++;
            i--;
            if(matrix[i][j] == target)
                return true;
            while(j < matrix[0].length && matrix[i][j] <= target) j++;
            j--;
            if(matrix[i][j] == target)
                return true;
            if(i == matrix.length - 1)
                return false;
            while(j >= 0 && matrix[i + 1][j] > target) j--;
            if(j == -1)
                return false;
            i++;
        }
        return false;
    }

    //5
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c != ' '){
                stringBuilder.append(c);
            }
            else{
                stringBuilder.append("%20");
            }
        }
        return stringBuilder.toString();
    }

    //6
    //恢复结构
    public int[] reversePrint__3(ListNode head) {
        if(head == null)
            return new int[]{};

        int count = 0;
        ListNode p = head;
        while(p != null){
            count++;
            p = p.next;
        }

        ListNode tail = reversePrint__3_reverseList(head);

        int[] re = new int[count];
        int i = 0;
        p = tail;
        while(p != null){
            re[i] = p.val;
            i++;
            p = p.next;
        }

        reversePrint__3_reverseList(tail);

        return re;
    }

    private ListNode reversePrint__3_reverseList(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode nextTemp = head.next;
            head.next = pre;
            pre = head;
            head = nextTemp;
        }
        return pre;
    }

    //6
    //不恢复结构
    public int[] reversePrint__2(ListNode head) {
        if(head == null)
            return new int[]{};

        ListNode p = head;
        ListNode pre = null;
        int count = 0;
        while(p != null){
            ListNode nextTemp = p.next;
            p.next = pre;
            count++;
            pre = p;
            p = nextTemp;
        }
        ListNode tail = pre;

        int[] re = new int[count];
        int i = 0;
        while(tail != null){
            re[i] = tail.val;
            i++;
            tail = tail.next;
        }

        return re;
    }

    //6
    public int[] reversePrint__1(ListNode head) {
        if(head == null)
            return new int[]{};

        int count = 0;
        Stack<Integer> stack = new Stack<>();
        while(head != null){
            stack.push(head.val);
            count++;
            head = head.next;
        }

        int[] re = new int[count];
        int i = 0;
        while(!stack.isEmpty()){
            re[i] = stack.pop();
            i++;
        }

        return re;
    }

    //6
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        while(p != null){
            stack.push(p.val);
            p = p.next;
        }
        List<Integer> list = new LinkedList<>();
        while(!stack.empty()){
            list.add(stack.pop());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public void treePSeq(TreeNode head){
        if(head == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p;
        queue.add(head);
        while(!queue.isEmpty()){
            p = queue.poll();
            System.out.println(p.val);
            if(p.left != null)
                queue.add(p.left);
            if(p.right != null)
                queue.add(p.right);
        }
    }

    public void treePre(TreeNode head){
        if(head == null)
            return;
        System.out.println(head.val);
        treePre(head.left);
        treePre(head.right);
    }

    public void treePre2(TreeNode head){
        if(head == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p;
        stack.push(head);
        while(!stack.isEmpty()){
            p = stack.pop();
            System.out.println(p.val);
            if(p.right != null)
                stack.push(p.right);
            if(p.left != null)
                stack.push(p.left);
        }
    }

    public void treePre3(TreeNode head){
        if(head == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = head;
        while(p  != null || !stack.isEmpty()){
            while(p != null){
                System.out.println(p.val);
                stack.push(p);
                p = p.left;
            }
            if(!stack.isEmpty()){
                p = stack.pop();
                p = p.right;
            }
        }
    }

    public void treeMid3(TreeNode head){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = head;
        while(p != null || !stack.isEmpty()){
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            if(!stack.isEmpty()){
                p = stack.pop();
                System.out.println(p.val);
                p = p.right;
            }
        }
    }

    public void treeTail3(TreeNode head){
        Integer left = 1;
        Integer right = 2;

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        TreeNode p = head;
        while(p != null || !stack.isEmpty()){
            while(p != null){
                stack.push(p);
                stack2.push(left);
                p = p.left;
            }
            if(!stack.isEmpty() && stack2.peek().equals(right)){
                stack2.pop();
                System.out.println(stack.pop().val);
            }
            if(!stack.isEmpty() && stack2.peek().equals(left)){
                stack2.pop();
                stack2.push(right);
                p = stack.peek().right;
            }
        }
    }


    public void treeMid(TreeNode head){
        if(head == null)
            return;
        treeMid(head.left);
        System.out.println(head.val);
        treeMid(head.right);
    }

    public void treeMid2(TreeNode head){
        if(head == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            TreeNode p = stack.pop();
        }
    }

    public void treeTail(TreeNode head){
        if(head == null)
            return;
        treeTail(head.left);
        treeTail(head.right);
        System.out.println(head.val);
    }
}
