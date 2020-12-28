package zyj.algorithm;

import com.sun.jdi.IntegerValue;
import utils.ListNode;
import utils.Node;
import utils.TreeNode;

import javax.imageio.stream.IIOByteBuffer;
import javax.management.NotCompliantMBeanException;
import java.security.Key;
import java.util.*;
import java.util.function.BiFunction;

public class Jz2 {

    //7   preLeft > preorder.length 以及 preleft边界问题
    //11  不一样的二分，跟numbers[right]比
    //14  循环超时，可快速幂
    //15  位运算
    //16  快速幂
    //18  可能删除的是头节点
    //20  ".1"和"1."是数，"."不是数
    //29
    //35  建立Node,Node的map，再调整指针关系
    //37

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

    //14 - II
    public int cuttingRopeII(int n) {
        int[] dp = new int[n + 1];
        int mod = (int) Math.pow(10, 9) + 7;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, multiMod(Math.max(dp[j], j), Math.max(dp[i - j], i - j), mod));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public static int multiMod(int num1, int num2, int mod){
        if(num2 > num1){
            return multiMod(num2, num1, mod);
        }

        int re = num1;
        for (int i = 0; i < num2 - 1; i++) {
            re = (re + num1) % mod;
        }

        return re;
    }

    //16
    public double myPow(double x, int n) {
        if(x == 0){
            return 0;
        }

        long b = n;
        double re = 1d;
        if(n < 0){
            x = 1 / x;
            b = -b;
        }

        while(b > 0){
            if((b & 1) == 1){
                re *= x;
            }
            x *= x;
            b >>= 1;
        }
        return re;
    }

    //17
    public int[] printNumbers(int n) {
        int limit = (int) Math.pow(10, n);
        int[] re = new int[limit - 1];
        for (int i = 1; i < limit; i++) {
            re[i - 1] = i;
        }
        return re;
    }

    //18
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null){
            return null;
        }
        if(head.val == val){
            return head.next;
        }

        ListNode pre = head;
        ListNode p = head.next;
        while(p != null){
            if(p.val == val){
                pre.next = p.next;
                return head;
            }
            pre = p;
            p = p.next;
        }
        return head;
    }

    //19
    public boolean isMatch(String s, String p){
        Map<String, Boolean> map = new HashMap<>();
        return isMatch(s, p, map);
    }

    private boolean isMatch(String s, String p, Map<String, Boolean> map) {
        String keyStr = s + ',' + p;
        if(map.containsKey(keyStr)){
            return map.get(keyStr);
        }

        boolean re;
        if(s.length() == 0){
            re = isMatchNull(p);
            map.put(keyStr, re);
            return re;
        }
        if(p.length() == 0){
            re = false;
            map.put(keyStr, re);
            return re;
        }
        if(!isValid(p)){
            re = false;
            map.put(keyStr, re);
            return re;
        }

        if(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'){
            if(p.length() > 1 && p.charAt(1) == '*'){
                re = isMatch(s.substring(1), p, map) || isMatch(s, p.substring(2), map);
            }
            else{
                re = isMatch(s.substring(1), p.substring(1), map);
            }
        }
        else{
            if(p.length() > 1 && p.charAt(1) == '*'){
                re = isMatch(s, p.substring(2), map);
            }
            else{
                re = false;
            }
        }
        map.put(keyStr, re);
        return re;
    }

    private boolean isValid(String p){
        if(p.length() == 0){
            return true;
        }
        return p.charAt(0) != '*';
    }

    private boolean isMatchNull(String p){
        if(p.length() == 0){
            return true;
        }
        if(!isValid(p)){
            return false;
        }
        if((p.length() & 1) == 0){
            for (int i = 1; i < p.length(); i += 2) {
                if(p.charAt(i) != '*'){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //20
    public boolean isNumber(String s) {
        if(s.length() == 0){
            return false;
        }
        s = s.strip();

        int eIndex = containE(s);
        if(eIndex == -1){
            s = removeSign(s);
            return isNumberAll(s);
        }
        else{
            String before = s.substring(0, eIndex);
            before = removeSign(before);

            String after = s.substring(eIndex + 1);
            after = removeSign(after);

            return isNumberAll(before) && isNumberPure(after);
        }
    }

    private String removeSign(String s){
        if(s.length() == 0){
            return s;
        }
        if(s.charAt(0) == '+' || s.charAt(0) == '-'){
            return s.substring(1);
        }
        return s;
    }

    private boolean isNumberAll(String s){
        return isNumberPure(s) || isDecimal(s);
    }

    private boolean isNumberPure(String s){
        if(s.length() == 0){
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9')){
                return false;
            }
        }
        return true;
    }

    private boolean isDecimal(String s){
        if(s.length() == 0){
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '.'){
                String before = s.substring(0, i);
                String after = s.substring(i + 1);
                if(before.length() == 0 && after.length() == 0){
                    return false;
                }
                if(before.length() == 0){
                    return isNumberPure(after);
                }
                if(after.length() == 0){
                    return isNumberPure(before);
                }
                return isNumberPure(before) && isNumberPure(after);
            }
        }
        return false;
    }

    private int containE(String s){
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'e' || s.charAt(i) == 'E'){
                return i;
            }
        }
        return -1;
    }

    //21
    public int[] exchange(int[] nums) {
        if(nums.length < 2){
            return nums;
        }

        int left = 0;
        int right = nums.length - 1;
        int temp = nums[0];
        while(left < right){
            while(left < right){
                if((nums[right] & 1) == 1){
                    nums[left] = nums[right];
                    left++;
                    break;
                }
                right--;
            }
            while(left < right){
                if((nums[left] & 1) == 0){
                    nums[right] = nums[left];
                    right--;
                    break;
                }
                left++;
            }
        }
        nums[left] = temp;
        return nums;
    }

    //22
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head;
        for (int i = 0; i < k; i++) {
            if(p == null){
                return null;
            }
            p = p.next;
        }
        while(p != null){
            head = head.next;
            p = p.next;
        }
        return head;
    }

    //24
    public ListNode reverseList2(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode pre = null;
        while(head != null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    //25
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        ListNode head = new ListNode(0);
        ListNode p = head;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                p.next = l1;
                l1 = l1.next;
            }
            else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        p.next = mergeTwoLists(l1, l2);
        return head.next;
    }

    //26
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null){
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        while(A != null || !stack.isEmpty()){
            while(A != null){
                stack.push(A);
                if(isSubStructure_fixHead(A, B)){
                    return true;
                }
                A = A.left;
            }
            if(!stack.isEmpty()){
                A = stack.pop();
                A = A.right;
            }
        }

        return false;
    }

    private boolean isSubStructure_fixHead(TreeNode A, TreeNode B){
        if(B == null){
            return true;
        }
        if(A == null || A.val != B.val){
            return false;
        }
        return isSubStructure_fixHead(A.left, B.left) && isSubStructure_fixHead(A.right, B.right);
    }

    //27
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }

        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);

        return root;
    }

    //28
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode A, TreeNode B){
        if(A == null && B == null){
            return true;
        }
        if(A == null || B == null){
            return false;
        }
        if(A.val != B.val){
            return false;
        }

        return isSymmetric(A.left, B.right) && isSymmetric(A.right, B.left);
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

    //30
    static class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if(minStack.isEmpty() || minStack.peek() >= x){
                minStack.push(x);
            }
        }

        public void pop() {
            int num = stack.pop();
            if(!minStack.isEmpty() && minStack.peek() == num){
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }

    //31
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pushed.length; i++) {
            map.put(pushed[i], i);
        }
        int top = -1;
        for (int i : popped) {
            if(map.get(i) >= top){
                top = map.get(i);
                map.put(i, -1);
                while(top >= 0 && map.get(pushed[top]) == -1){
                    top--;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    //31
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while(!stack.isEmpty() && stack.peek() == popped[i]){
                stack.pop();
                i++;
            }
        }
        return i == popped.length;
    }

    //33
    public boolean verifyPostorder(int[] postorder){
        if(postorder.length <= 1){
            return true;
        }

        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int left, int right) {
        if(right <= left + 1){
            return true;
        }

        int rootVal = postorder[right];
        int index = 0;
        while(index < right && postorder[index] < rootVal){
            index++;
        }
        int mid = index - 1;
        while(index < right && postorder[index] > rootVal){
            index++;
        }
        return index == right && verifyPostorder(postorder, left, mid) && verifyPostorder(postorder, mid + 1, right - 1);
    }

    //34
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> allPath = new LinkedList<>();
        if(root == null){
            return allPath;
        }
        List<Integer> path = new LinkedList<>();
        pathSum(root, sum, allPath, path);
        return allPath;
    }

    private void pathSum(TreeNode root, int sum, List<List<Integer>> allPath, List<Integer> path){
        path.add(root.val);
        if(root.left == null && root.right == null){
            if(root.val == sum){
                allPath.add(new LinkedList<>(path));
            }
        }
        else{
            if(root.left != null)
                pathSum(root.left, sum - root.val, allPath, path);
            if(root.right != null){
                pathSum(root.right, sum - root.val, allPath, path);
            }
        }
        path.remove(path.size() - 1);
    }

    //35
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node p = head;
        Map<Node, Node> map = new HashMap<>();
        while(p != null){
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while(p != null){
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    //36
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null){
            return null;
        }

        TreeNode head = new TreeNode(0);
        TreeNode p = head;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                cur = stack.pop();
                root = cur.right;

                p.right = cur;
                p.left = pre;
                pre = p;
                p = cur;
            }
        }

        p.left = pre;
        p.right = head.right;
        head.right.left = p;
        return head.right;
    }

    //38
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null){
                return "[]";
            }

            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    if(cur == null){
                        sb.append("null,");
                    }
                    else{
                        queue.offer(cur.left);
                        queue.offer(cur.right);
                        sb.append(cur.val).append(",");
                    }
                }
            }
            sb.replace(sb.length() - 1, sb.length(), "]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.length() == 0 || data.equals("[]") || data.equals("[null]")){
                return null;
            }
            
            data = data.substring(1, data.length() - 1);
            String[] strings = data.split(",");
            if(strings.length == 0){
                return null;
            }

            Map<Integer, TreeNode> map = new HashMap<>();
            for (int i = 0; i < strings.length; i++) {
                String s = strings[i];
                if(s.equals("null")){
                    map.put(i, null);
                }
                else{
                    map.put(i, new TreeNode(Integer.parseInt(strings[i])));
                }
            }

            int countNull = 0;
            for (int i = 0; i < strings.length; i++) {
                String s = strings[i];
                if(s.equals("null")){
                    countNull++;
                }
                else{
                    TreeNode cur = map.get(i);
                    cur.left = map.get(2 * (i - countNull) + 1);
                    cur.right = map.get(2 * (i - countNull) + 2);
                }
            }
            return map.get(0);
        }
    }

    //39
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int num = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if(num == nums[i]){
                count++;
            }
            else{
                if(count == 0){
                    num = nums[i];
                }
                else{
                    count--;
                }
            }
        }
        return num;
    }

    //40
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0 || arr == null || arr.length == 0){
            return new int[0];
        }
        if(k >= arr.length){
            return arr;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int num : arr){
            queue.offer(num);
        }
        int[] re = new int[k];
        for (int i = 0; i < k; i++) {
            re[i] = queue.poll();
        }
        return re;
    }

    //40
    public int[] getLeastNumbers2(int[] arr, int k) {
        if(k == 0 || arr == null || arr.length == 0){
            return new int[0];
        }
        if(k >= arr.length){
            return arr;
        }

        quick_sort_k(arr, 0, arr.length - 1, k);
        return Arrays.stream(arr).limit(k).toArray();
    }

    private void quick_sort_k(int[] array, int left, int right, int k){
        if(array == null || array.length == 0 || left >= right){
            return;
        }

        int i = left;
        int j = right;
        int temp = array[left];
        while(i < j){
            while(i < j){
                if(array[j] < temp){
                    array[i] = array[j];
                    break;
                }
                j--;
            }
            while(i < j){
                if(array[i] >= temp){
                    array[j] = array[i];
                    break;
                }
                i++;
            }
        }
        array[i] = temp;

        if(i > k){
            quick_sort_k(array, left, i - 1, k);
        }
        else if(i == k){
            return;
        }
        else{
            quick_sort_k(array, i + 1, right, k);
        }
    }


}
