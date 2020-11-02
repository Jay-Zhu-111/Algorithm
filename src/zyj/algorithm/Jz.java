package zyj.algorithm;

import utils.ListNode;
import utils.TreeNode;

import java.util.*;

public class Jz {

    //19
    public boolean isMatch(String s, String p) {
        if(p.equals(".*"))
            return true;
        if(p.length() != 0 && p.charAt(0) == '*')
            return false;
        for (int i = 1; i < p.length(); i++) {
            if(p.charAt(i) == '*' && p.charAt(i - 1) == '*')
                return false;
        }

        Map<String, Boolean> map = new HashMap<>();
        return isMatch(s, 0, p, 0, map);
    }

    private boolean isMatch(String s, int s_index, String p, int p_index, Map<String, Boolean> map){
        String keyStr = s_index + "," + p_index;
        if(map.containsKey(keyStr))
            return map.get(keyStr);

        boolean re;

        //s end
        if(s_index == s.length()){
            while(p_index < p.length() - 1 && p.charAt(p_index + 1) == '*'){
                p_index += 2;
            }
            re = p_index == p.length();
            map.put(keyStr,re);
            return re;
        }
        else if(p_index >= p.length()){//s not end but p end
            re = false;
            map.put(keyStr,re);
            return re;
        }

        boolean nextStar = p_index != p.length() - 1 && p.charAt(p_index + 1) == '*';
        if(!isMatch_equal(s.charAt(s_index), p.charAt(p_index))){
            if(nextStar){
                re = isMatch(s, s_index, p, p_index + 2, map);
            }
            else{
                re = false;
            }
        }
        else{
            if(nextStar){
                re = isMatch(s, s_index + 1, p, p_index, map) || isMatch(s, s_index, p, p_index + 2, map);
            }
            else{
                re = isMatch(s, s_index + 1, p, p_index + 1, map);
            }
        }

        map.put(keyStr,re);
        return re;
    }

    private boolean isMatch_equal(char s, char p){
        if(p == '.')
            return true;
        return s == p;
    }

    //18
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null)
            return head;
        if(head.val == val){
            return head.next;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
                return head;
            }
            pre = cur;
            cur = cur.next;
        }
        throw new RuntimeException("can't found val");
    }

    //17
    public int[] printNumbers2(int n) {
        if(n == 0)
            return new int[]{};

        List<String> re = new LinkedList<>();
        List<String> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(String.valueOf(i));
            if(i != 0){
                re.add(String.valueOf(i));
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= 9; j++) {
                String head = String.valueOf(j);
                for (String s : list1) {
                    String newS = head + s;
                    list2.add(newS);
                    if(newS.charAt(0) != '0')
                        re.add(newS);
                }
            }

            List<String> temp = list1;
            list1 = list2;
            list2 = temp;
            list2.clear();
        }

        int[] ints = new int[re.size()];
        int index = 0;
        for (String s : re) {
            ints[index] = Integer.parseInt(s);
            index++;
        }
        return ints;
//        return re.toArray(new String[0]);
    }

    //17
    public int[] printNumbers(int n) {
        if(n == 0)
            return new int[]{};

        int num = 1;
        for (int i = 0; i < n; i++) {
            num *= 10;
        }
        num--;

        int[] re = new int[num];
        for (int i = 0; i < num; i++) {
            re[i] = i + 1;
        }

        return re;
    }

    //16
    public double myPow(double x, int n) {
        if(n == 0 || x == 1)
            return 1;
        if(x == -1){
            if(n % 2 == 0)
                return 1;
            else
                return -1;
        }

        double re = 1;
        if(n == Integer.MIN_VALUE){
            re *= x;
            n++;
        }
        for (int i = 0; i < Math.abs(n); i++) {
            re *= x;
            if(!Double.isFinite(re) || re == 0)
                break;
        }
        System.out.println(re);
        if(n > 0)
            return re;
        else
            return 1 / re;
    }

    //15
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    //12
    public boolean exist(char[][] board, String word) {
        if(word.length() == 0)
            return true;
        if(board.length == 0 || board[0].length == 0)
            return false;

        int n = board.length;
        int m = board[0].length;
        boolean[][] state = new boolean[n][m];
        for (boolean[] booleans : state) {
            Arrays.fill(booleans, true);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == word.charAt(0)){
                    state[i][j] = false;
                    if(exist(board, word.substring(1), state, i, j))
                        return true;
                    state[i][j] = true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String word, boolean[][] state, int row, int col){
        if(word.length() == 0)
            return true;

        int n = board.length;
        int m = board[0].length;

        //left
        if(col > 0){
            int newrow = row;
            int newcol = col - 1;
            if(state[newrow][newcol] && board[newrow][newcol] == word.charAt(0)){
                state[newrow][newcol] = false;
                if(exist(board, word.substring(1), state, newrow, newcol))
                    return true;
                state[newrow][newcol] = true;
            }
        }

        //right
        if(col < m - 1){
            int newrow = row;
            int newcol = col + 1;
            if(state[newrow][newcol] && board[newrow][newcol] == word.charAt(0)){
                state[newrow][newcol] = false;
                if(exist(board, word.substring(1), state, newrow, newcol))
                    return true;
                state[newrow][newcol] = true;
            }
        }

        //up
        if(row > 0){
            int newrow = row - 1;
            int newcol = col;
            if(state[newrow][newcol] && board[newrow][newcol] == word.charAt(0)){
                state[newrow][newcol] = false;
                if(exist(board, word.substring(1), state, newrow, newcol))
                    return true;
                state[newrow][newcol] = true;
            }
        }

        //down
        if(row < n - 1){
            int newrow = row + 1;
            int newcol = col;
            if(state[newrow][newcol] && board[newrow][newcol] == word.charAt(0)){
                state[newrow][newcol] = false;
                if(exist(board, word.substring(1), state, newrow, newcol))
                    return true;
                state[newrow][newcol] = true;
            }
        }

        return false;
    }

    //11
    public int minArray2(int[] numbers) {
        if(numbers.length == 0)
            return 0;
        if(numbers.length == 1)
            return numbers[0];
        if(numbers[0] < numbers[numbers.length - 1])
            return numbers[0];

        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] < numbers[high]) {
                high = mid;
            } else if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else {
                high--;
            }
        }
        return numbers[low];
    }

    //11
    public int minArray(int[] numbers) {
        if(numbers.length == 0)
            return 0;
        if(numbers.length == 1)
            return numbers[0];
        if(numbers[0] < numbers[numbers.length - 1])
            return numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if(numbers[i] < numbers[i - 1])
                return numbers[i];
        }

        return numbers[0];
    }

    //10-II
    public int numWays(int n) {
        if(n == 0 || n == 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    //10
    public int fib(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    //9
    public boolean isPalindrome2(int x) {
        if(x < 0)
            return false;
        if(x < 10)
            return true;

        int temp = x;
        int count = 0;
        while(temp >= 10){
            temp /= 10;
            count++;
        }

        int left = count;
        int right = 0;
        while(left > right){
            if(isPalindrome2_getRight(x, left) != isPalindrome2_getRight(x, right)){
                return false;
            }
            left--;
            right++;
        }
        return true;
    }

    private int isPalindrome2_getRight(int x, int index){
        for (int i = 0; i < index; i++) {
            x /= 10;
        }
        return x % 10;
    }

    //9
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        if(x < 10)
            return true;

        String s = String.valueOf(x);
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //8 CQueue

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
