package zyj.algorithm;

import com.sun.jdi.IntegerValue;
import utils.ListNode;
import utils.TreeNode;

import javax.imageio.stream.IIOByteBuffer;
import javax.management.NotCompliantMBeanException;
import java.util.*;

public class Jz2 {

    //7   preLeft > preorder.length 以及 preleft边界问题
    //11  不一样的二分，跟numbers[right]比
    //29

    //3
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if(cur != i){
                int temp = nums[cur];
                if(temp == cur){
                    return cur;
                }
                nums[cur] = cur;
                nums[i] = temp;
            }
        }
        return -1;
    }

    //4
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        if(n == 0){
            return false;
        }
        int m = matrix[0].length;
        if(m == 0){
            return false;
        }

        int i = 0;
        int j = m - 1;
        while(i < n && j >= 0){
            int num = matrix[i][j];
            if(num == target){
                return true;
            }
            else if(num < target){
                i++;
            }
            else{
                j--;
            }
        }
        return false;
    }

    //5
    public String replaceSpace(String s) {
        if (s.length() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' '){
                stringBuilder.append("%20");
            }
            else{
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    //6
    public int[] reversePrint(ListNode head) {
        if(head == null){
            return new int[0];
        }

        int length = 0;
        for(ListNode p = head; p != null; p = p.next){
            length++;
        }

        int[] re = new int[length];
        int index = 0;
        ListNode tail = reverseList(head);
        for (ListNode p = tail; p != null; p = p.next) {
            re[index] = p.val;
        }
        reverseList(tail);
        return re;
    }

    private ListNode reverseList(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //7
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }

        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight){
        if(preLeft > preRight || inLeft > inRight || preLeft > preorder.length){
            return null;
        }

        if(preLeft == preRight){
            return new TreeNode(preorder[preLeft]);
        }

        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootIndex = 0;
        for (int i = inLeft; i <= inRight; i++) {
            if(inorder[i] == root.val){
                rootIndex = i;
            }
        }
        int leftLen = rootIndex - inLeft;
        int rightLen = inRight - rootIndex;

        root.left = buildTree(preorder, inorder, preLeft + 1, preLeft + leftLen, inLeft, rootIndex - 1);
        root.right = buildTree(preorder, inorder, preLeft + leftLen + 1, preRight + leftLen + rightLen, rootIndex + 1, inRight);

        return root;
    }

    //9
    class CQueue {

        Stack<Integer> input;
        Stack<Integer> output;

        public CQueue() {
            input = new Stack<>();
            output = new Stack<>();
        }

        public void appendTail(int value) {
            input.push(value);
        }

        public int deleteHead() {
            if(!output.isEmpty()){
                return output.pop();
            }
            else{
                if(input.isEmpty()){
                    return -1;
                }
                else{
                    while(!input.isEmpty()){
                         output.push(input.pop());
                    }
                }
            }

            return output.pop();
        }
    }

    //10 - II
    public int numWays(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }

        int base = (int) Math.pow(10, 9) + 7;
        int[] array = new int[3];
        array[0] = 1;
        array[1] = 2;

        for(int i = 3; i <= n; i++){
            array[2] = (array[0] + array[1]) % base;
            array[0] = array[1];
            array[1] = array[2];
        }

        return array[2];
    }

    //11
    public int minArray(int[] numbers) {
        if(numbers.length == 0){
            return 0;
        }
        if(numbers.length == 1){
            return numbers[0];
        }

        int left = 0;
        int right = numbers.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(numbers[mid] < numbers[right]){
                right = mid;
            }
            else if(numbers[mid] > numbers[right]){
                left = mid + 1;
            }
            else{
                right--;
            }
        }
        return numbers[right];
    }

    //12
    public boolean exist(char[][] board, String word) {
        if(word.length() == 0){
            return true;
        }

        int n = board.length;
        int m = board[0].length;
        boolean[][] state = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            state[i] = new boolean[m];
            Arrays.fill(state[i], true);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == word.charAt(0)){
                    state[i][j] = false;
                    if(exist(board, word.substring(1), i, j, state)){
                        return true;
                    }
                    state[i][j] = true;
                }
            }
        }

        return false;
    }

    private boolean exist(char[][] board, String word, int row, int col, boolean[][] state){
        if(word.length() == 0){
            return true;
        }

        int[][] adds = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] add : adds) {
            row += add[0];
            col += add[1];
            if(row >= board.length || row < 0 || col >= board[0].length || col < 0){
                row -= add[0];
                col -= add[1];
                continue;
            }

            if(state[row][col] && board[row][col] == word.charAt(0)){
                state[row][col] = false;
                if(exist(board, word.substring(1), row, col, state)){
                    return true;
                }
                state[row][col] = true;
            }

            row -= add[0];
            col -= add[1];
        }
        return false;
    }

    //13
    public int movingCount(int m, int n, int k) {
        if(k == 0){
            return 1;
        }

        int row = 0;
        int col = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            visited[i] = new boolean[n];
            Arrays.fill(visited[i], false);
        }

        int count = 1;
        visited[0][0] = true;
        count += movingCount(0, 0, k, visited);
        return count;
    }

    private int movingCount(int row, int col, int k, boolean[][] visited){
        int count = 0;
        int[][] adds = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = visited.length;
        int n = visited[0].length;
        for(int[] add : adds){
            row += add[0];
            col += add[1];
            if(row >= m || row < 0 || col >= n || col < 0 || !isValid(row, col, k) || visited[row][col]){
                row -= add[0];
                col -= add[1];
                continue;
            }

            count++;
            visited[row][col] = true;
            count += movingCount(row, col, k, visited);

            row -= add[0];
            col -= add[1];
        }
        return count;
    }

    private boolean isValid(int row, int col, int k){
        int count = 0;
        while(row > 0){
            count += row % 10;
            row /= 10;
        }
        while(col > 0){
            count += col % 10;
            col /= 10;
        }
        return count <= k;
    }

    //14 - I
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
            dp[i] = max;
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }


    //29
    public int[] spiralOrder(int[][] matrix) {
        int n = matrix.length;
        if(n == 0){
            return new int[0];
        }
        int m = matrix[0].length;
        if(m == 0){
            return new int[0];
        }

        int[] re = new int[n * m];
        int index = 0;

        int up = 0;
        int right = m - 1;
        int down = n - 1;
        int left = 0;

        while(left <= right && up <= down){
            for (int i = left; i <= right; i++) {
                re[index] = matrix[up][i];
                index++;
            }
            up++;
            if(up > down){
                break;
            }

            for (int i = up; i <= down; i++) {
                re[index] = matrix[i][right];
                index++;
            }
            right--;
            if(left > right){
                break;
            }

            for (int i = right; i >= left; i--) {
                re[index] = matrix[down][i];
                index++;
            }
            down--;
            if(up > down){
                break;
            }

            for (int i = down; i >= up; i--) {
                re[index] = matrix[i][left];
                index++;
            }
            left++;
        }
        return re;
    }
}
