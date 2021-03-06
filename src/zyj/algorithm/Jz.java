package zyj.algorithm;

import genneric.A;
import utils.Node;
import utils.ListNode;
import utils.TreeNode;

import javax.management.relation.InvalidRelationTypeException;
import java.lang.reflect.Array;
import java.util.*;


public class Jz {

    //68 - II
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    //68 - II
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        if(root.val == p.val || root.val == q.val){
            return root;
        }

        boolean containsP = containNode(root.left, p);
        boolean containsQ = containNode(root.left, q);
        if(containsP && containsQ){
            return lowestCommonAncestor(root.left, p, q);
        }
        else if((containsP && !containsQ) || (!containsP && containsQ)){
            return root;
        }
        else{
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    private boolean containNode(TreeNode root, TreeNode target){
        if(root == null){
            return false;
        }
        if(root.val == target.val){
            return true;
        }
        return containNode(root.left, target) || containNode(root.right, target);
    }

    //55 - II
    public boolean isBalanced2(TreeNode root){
        if(root == null){
            return true;
        }
        return isBalanced_Depth(root) != -1;
    }

    private int isBalanced_Depth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = isBalanced_Depth(root.left);
        int right = isBalanced_Depth(root.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    //55 - II
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }

        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(maxDepth2(root.left) - maxDepth2(root.right)) <= 1;
    }

    //55 - I
    public int maxDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }

        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    //55 - I
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while(!queue.isEmpty()){
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                assert cur != null;
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return count;
    }

    //54
    public int kthLargest(TreeNode root, int k) {
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.right;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                count++;
                if(count == k){
                    return root.val;
                }
                root = root.left;
            }
        }
        throw new RuntimeException("can't find");
    }

    //53 - II
    public int missingNumber(int[] nums) {
        if(nums[nums.length - 1] == nums.length - 1){
            return nums.length;
        }
        if(nums[0] == 1){
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > mid){
                right = mid - 1;
            }
            else if(nums[mid] == mid){
                left = mid + 1;
            }
        }
        return right + 1;
    }

    //53 - I
    public int search(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        int firstIndex;
        if(left < nums.length && nums[left] == target){
            firstIndex = left;
        }
        else{
            return 0;
        }

        right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return right - firstIndex + 1;
    }


    //52
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }

        int lengthA = 0;
        for(ListNode p = headA; p != null; p = p.next){
            lengthA++;
        }

        int lengthB = 0;
        for(ListNode p = headB; p != null; p = p.next){
            lengthB++;
        }

        if(lengthA > lengthB){
            ListNode temp = headA;
            headA = headB;
            headB = temp;
        }

        for (int i = 0; i < Math.abs(lengthB - lengthA); i++) {
            headB = headB.next;
        }

        while(headA != null && headB != null){
            if(headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    //51
    public int reversePairs2(int[] nums) {
        if(nums.length == 0)
            return 0;
        return reversePairs2(nums, 0, nums.length - 1);
    }

    private int reversePairs2(int[] nums, int left, int right){
        if(left == right){
            return 0;
        }
        if(left + 1 == right){
            if(nums[left] <= nums[right]){
                return 0;
            }
            else{
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                return 1;
            }
        }

        int count = 0;
        int mid = left + (right - left) / 2;
        count += reversePairs2(nums, left, mid);
        count += reversePairs2(nums, mid + 1, right);

        int leftPoint = left;
        int rightPoint = mid + 1;
        int[] array = new int[right - left + 1];
        for (int i = 0; i < array.length; i++) {
            if(leftPoint == mid + 1){
                System.arraycopy(nums, rightPoint, array, i, array.length - i);
                break;
            }
            if(rightPoint == right + 1){
                System.arraycopy(nums, leftPoint, array, i, array.length - i);
                break;
            }
            if(nums[leftPoint] <= nums[rightPoint]){
                array[i] = nums[leftPoint];
                leftPoint++;
            }
            else{
                array[i] = nums[rightPoint];
                rightPoint++;
                count += mid - leftPoint + 1;
            }
        }
        System.arraycopy(array, 0, nums, left, array.length);
        return count;
    }

    //51
    //timeout
    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if(nums[j] > nums[j + 1]){
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    count++;
                }
            }
        }

        return count;
    }

    //50
    public char firstUniqChar(String s) {
        if(s.length() == 0){
            return ' ';
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.get(c) == 1){
                return c;
            }
        }
        return ' ';
    }

    //49
    public int nthUglyNumber(int n) {
        if(n == 1){
            return 1;
        }

        int[] array = new int[n];
        array[0] = 1;

        int point2 = 0;
        int point3 = 0;
        int point5 = 0;
        for (int i = 1; i < n; i++) {
            int temp2 = array[point2] * 2;
            int temp3 = array[point3] * 3;
            int temp5 = array[point5] * 5;
            array[i] = Math.min(Math.min(temp2, temp3), temp5);
            if(temp2 == array[i]){
                point2++;
            }
            if(temp3 == array[i]){
                point3++;
            }
            if(temp5 == array[i]){
                point5++;
            }
        }

        return array[n - 1];
    }

    //46
    public int translateNum(int num) {
        if(num < 10){
            return 1;
        }

        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];
        dp[str.length() - 1] = 1;
        dp[str.length()] = 1;
        for (int i = str.length() - 2; i >= 0; i--) {
            char c = str.charAt(i);
            if(c == '1' || (c == '2' && str.charAt(i + 1) < '6')){
                dp[i] = dp[i + 1] + dp[i + 2];
            }
            else{
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    //45
    public String minNumber(int[] nums) {
        if(nums.length == 1){
            return String.valueOf(nums[0]);
        }

        List<String> list = new ArrayList<>();
        for(int num : nums){
            list.add(String.valueOf(num));
        }

        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder re = new StringBuilder();
        for(String str : list){
            re.append(str);
        }
        return re.toString();
    }

    //44
    public int findNthDigit(int n) {
        if(n < 10)
            return n;

        int num = 9;
        int weight = 2;
        int base = 90;

        while(weight < Integer.MAX_VALUE / base && weight * base < Integer.MAX_VALUE - num && num + weight * base < n){
            num += weight * base;
            weight++;
            base *= 10;
        }

        int offset = (n - num) % weight;
        int realNum = (int) Math.pow(10, weight - 1) - 1 + (n - num) / weight;
        if(offset == 0){
            return  String.valueOf(realNum).charAt(weight - 1) - '0';
        }
        else{
            realNum++;
            return String.valueOf(realNum).charAt(offset - 1) - '0';
        }
    }

    //43
    public int countDigitOne(int n){
        Map<Integer, Integer> map = new HashMap<>();
        return countDigitOne(n, map);
    }

    private int countDigitOne(int n, Map<Integer, Integer> map) {
        if(n < 10){
            if(n >= 1)
                return 1;
            else
                return 0;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }

        String s = String.valueOf(n);
        int weight = s.length() - 1;
        int high = s.charAt(0) - '0';
        int remain = Integer.parseInt(s.substring(1));

        int weightKey = (int) Math.pow(10, weight) - 1;

        int extra;
        if(high > 1){
            extra = weightKey + 1;
        }
        else if(high == 1){
            extra = remain + 1;
        }
        else{
            extra = 0;
        }

        int weightValue;
        if(map.containsKey(weight)){
            weightValue = map.get(weight);
        }
        else{
            weightValue = countDigitOne(weightKey, map);
            map.put(weightKey, weightValue);
        }

        return weightValue * high + extra + countDigitOne(remain, map);
    }

    //42
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int pre = nums[n - 1];
        int max = pre;
        for(int i = n - 2; i >= 0; i--){
            int cur = nums[i];
            if(pre > 0){
                cur += pre;
            }
            max = Math.max(max, cur);
            pre = cur;
        }

        return max;
    }

    //41
    class MedianFinder {

        Node head;
        Node point;
        int size;

        /** initialize your data structure here. */
        public MedianFinder() {
            size = 0;
        }

        public void addNum(int num) {
            if(head == null){
                head = new Node(num);
                point = head;
                size++;
                return;
            }

            if(num >= point.val){
                //右边插入num
                if(num == point.val){
                    Node temp = point.next;
                    point.next = new Node(num);
                    point.next.next = temp;
                }
                else{
                    Node cur = point;
                    while(cur.next != null && cur.next.val < num){
                        cur = cur.next;
                    }

                    //已找到插入位置
                    if(cur.next == null){
                        cur.next = new Node(num);
                    }
                    else{
                        Node temp = cur.next;
                        cur.next = new Node(num);
                        cur.next.next = temp;
                    }
                }
                size++;

                //为奇数后移point
                if(size % 2 == 1){
                    point = point.next;
                }
            }
            else{
                Node insert;
                if(head.val >= num){//头插
                    Node temp = head;
                    head = new Node(num);
                    head.next = temp;
                    insert = head;
                }
                else{
                    Node cur = head;
                    while(cur.next.val < num){
                        cur = cur.next;
                    }
                    Node temp = cur.next;
                    cur.next = new Node(num);
                    cur.next.next = temp;
                    insert = cur.next;
                }
                size++;

                //为偶数前移point
                if(size % 2 == 0){
                    Node cur = insert;
                    while(cur.next != point){
                        cur = cur.next;
                    }
                    point = cur;
                }
            }
        }

        public double findMedian() {
            if(size % 2 == 0){
                return ((double) (point.val + point.next.val)) / 2;
            }
            return point.val;
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    //40
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] re = new int[k];
        System.arraycopy(arr, 0, re, 0, k);
        return re;
    }

    //39
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }
            else{
                map.put(num, 1);
            }
            if(map.get(num) >= (nums.length + 1) / 2){
                return num;
            }
        }
        return -1;
    }

    //38
    public String[] permutation(String s) {
        if(s.length() == 0)
            return new String[]{};

        Set<String> set = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set.add(s.substring(0, 1));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            for(String s1 : set){
                insertChar(c, s1, set2);
            }
            Set<String> temp = set;
            set = set2;
            set2 = temp;
            set2.clear();
        }
        return set.toArray(new String[0]);
    }

    private void insertChar(char c, String s1, Set<String> set){
        set.add(c + s1);
        set.add(s1 + c);
        for (int i = 1; i < s1.length(); i++) {
            String newStr = s1.substring(0, i) + c + s1.substring(i);
            set.add(newStr);
        }
    }

    //37
    //mem error
    public String serialize2(TreeNode root) {
        if(root == null)
            return "[null]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Map<Integer, TreeNode> map1 = new HashMap<>();
        Map<Integer, TreeNode> map2 = new HashMap<>();
        map1.put(1, root);
        int limit = 1;
        while(map1.size() != 0){
            for (int i = 1; i <= limit; i++) {
                if(map1.containsKey(i)){
                    TreeNode cur = map1.remove(i);
                    stringBuilder.append(cur.val);
                    stringBuilder.append(',');
                    if(cur.left != null){
                        map2.put(2 * i - 1, cur.left);
                    }
                    if(cur.right != null){
                        map2.put(2 * i, cur.right);
                    }
                }
                else{
                    stringBuilder.append("null,");
                }
            }
            Map<Integer, TreeNode> temp = map1;
            map1 = map2;
            map2 = temp;
            limit *= 2;
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "]");
        return stringBuilder.toString();
    }

    //37
    //mem error
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null)
            return "[null]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(true){
            int size = queue.size();
            boolean flag = false;
            StringBuilder epoch = new StringBuilder();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if(cur != null){
                    epoch.append(cur.val);
                    epoch.append(',');
                    flag = true;
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
                else{
                    epoch.append("null");
                    epoch.append(',');
                    queue.offer(null);
                    queue.offer(null);
                }
            }
            if(!flag){
                stringBuilder.setCharAt(stringBuilder.length() - 1, ']');
                break;
            }
            else{
                stringBuilder.append(epoch);
            }
        }

        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.equals("") || data.equals("[]") || data.equals("[null]"))
            return null;

        String[] datas = data.substring(1, data.length() - 1).split(",");
        TreeNode head = deserialize_one(datas[0]);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        int index = 1;
        while(index < datas.length){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode left = deserialize_one(datas[index]);
                index++;
                queue.offer(left);

                TreeNode right = deserialize_one(datas[index]);
                index++;
                queue.offer(right);

                TreeNode cur = queue.poll();
                if(cur != null){
                    cur.left = left;
                    cur.right = right;
                }
            }
        }

        return head;
    }

    private TreeNode deserialize_one(String data){
        if(data.equals("null"))
            return null;
        else{
            return new TreeNode(Integer.parseInt(data));
        }
    }

    public void tailorder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<Boolean> flag = new Stack<>();
        TreeNode p = root;
        while(p != null || !stack.isEmpty()){
            while(p != null){
                stack.push(p);
                flag.push(false);
                p = p.left;
            }
            while(!stack.isEmpty() && flag.peek()){
                System.out.println(stack.pop().val);
                flag.pop();
            }
            if(!stack.isEmpty() && !flag.peek()){
                flag.pop();
                flag.push(true);
                p = stack.peek().right;
            }
        }
    }

    //36
    public TreeNode treeToDoublyList2(TreeNode root){
        if(root == null)
            return null;

        List<TreeNode> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(p != null || !stack.isEmpty()){
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            if(!stack.isEmpty()){
                p = stack.pop();
                list.add(p);
                p = p.right;
            }
        }

        TreeNode head = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);
        }
        head.left = list.get(list.size() - 1);
        list.get(list.size() - 1).right = head;

        return head;
    }

    //36
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null)
            return null;

        List<TreeNode> list = new ArrayList<>();
        treeToDoublyList_midOrder(root, list);
        TreeNode head = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);
        }
        head.left = list.get(list.size() - 1);
        list.get(list.size() - 1).right = head;

        return head;
    }

    private void treeToDoublyList_midOrder(TreeNode root, List<TreeNode> list){
        if(root == null)
            return;

        treeToDoublyList_midOrder(root.left, list);
        list.add(root);
        treeToDoublyList_midOrder(root.right, list);
    }

    //35
    public Node copyRandomList(Node head) {
        if(head == null)
            return null;

        Map<Node, Node> map = new HashMap<>();
        Node p = head;
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

    //34
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)
            return new LinkedList<>();

        List<Integer> path = new LinkedList<>();
        List<List<Integer>>allPath = new LinkedList<>();
        pathSum(root, sum, path, allPath);
        return allPath;
    }

    private void pathSum(TreeNode root, int sum, List<Integer>path, List<List<Integer>>allPath){
        if(root == null)
            return;

        if(root.val == sum && root.left == null && root.right == null){
            path.add(root.val);
            allPath.add(new LinkedList<>(path));
            path.remove(path.size() - 1);
        }
        else{
            path.add(root.val);
            pathSum(root.left, sum - root.val, path, allPath);
            pathSum(root.right, sum - root.val, path, allPath);
            path.remove(path.size() - 1);
        }
    }

    //33
    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length <= 2)
            return true;

        Map<String, Boolean> map = new HashMap<>();
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int left, int right){
        if(left + 1 >= right)
            return true;

        int root = postorder[right];
        int index = left;
        while(index < right && postorder[index] < root){
            index++;
        }
        int mid_right = index;
        while(index < right && postorder[index] > root){
            index++;
        }
        if(index != right)
            return false;
        return verifyPostorder(postorder, left, mid_right - 1) && verifyPostorder(postorder, mid_right, right - 1);
    }

    //32-III
    public List<List<Integer>> levelOrderIII(TreeNode root) {
        if(root == null){
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        int level = 0;
        while(!stack1.isEmpty()){
            level++;
            int size = stack1.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = stack1.pop();
                assert cur != null;

                list.add(cur.val);
                if(level % 2 == 1){
                    if(cur.left != null){
                        stack2.push(cur.left);
                    }
                    if(cur.right != null){
                        stack2.push(cur.right);
                    }
                }
                else{
                    if(cur.right != null){
                        stack2.push(cur.right);
                    }
                    if(cur.left != null){
                        stack2.push(cur.left);
                    }
                }
            }
            lists.add(list);

            Stack<TreeNode> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
        }

        return lists;
    }

    //32-II
    public List<List<Integer>> levelOrderII(TreeNode root) {
        if(root == null){
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                assert cur != null;

                list.add(cur.val);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            lists.add(list);
        }

        return lists;
    }

    //32
    public int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[]{};
        }

        List<Integer> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                assert cur != null;

                list.add(cur.val);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //31
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length != popped.length)
            return false;
        if(pushed.length == 0){
            return true;
        }

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pushed.length; i++) {
            map.put(pushed[i], i);
        }

        int top = -1;
        for (int cur : popped) {
            if (!map.containsKey(cur))
                return false;
            int seq = map.get(cur);
            if (seq >= top) {
                top = seq - 1;
                set.add(seq);
                while (set.contains(top)) {
                    top--;
                }
            }
            else
                return false;
        }

        return true;
    }

    //29
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return new int[]{};

        int n = matrix.length;
        int m = matrix[0].length;
        int[] re = new int[n * m];
        re[0] = matrix[0][0];
        int index = 1;
        int row = 0;
        int col = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
        visited[0][0] = true;

        while(index < n * m){
            //right
            while(col + 1 < m && !visited[row][col + 1]){
                col++;
                visited[row][col] = true;
                re[index] = matrix[row][col];
                index++;
            }
            //down
            while(row + 1 < n && !visited[row + 1][col]){
                row++;
                visited[row][col] = true;
                re[index] = matrix[row][col];
                index++;
            }
            //left
            while(col - 1 >= 0 && !visited[row][col - 1]){
                col--;
                visited[row][col] = true;
                re[index] = matrix[row][col];
                index++;
            }
            //up
            while(row - 1 >= 0 && !visited[row - 1][col]){
                row--;
                visited[row][col] = true;
                re[index] = matrix[row][col];
                index++;
            }
        }

        return re;
    }

    //28
    //not recurrent
    public boolean isSymmetric2(TreeNode root) {
        if(root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size / 2; i++) {
                TreeNode cur = queue.poll();
                if(cur != null){
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }

                stack.push(cur);
            }
            for (int i = 0; i < size / 2; i++) {
                TreeNode cur = queue.poll();
                if(cur != null){
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }

                TreeNode top = stack.pop();
                if(cur == null && top == null)
                    continue;
                if(cur == null || top == null)
                    return false;
                if(cur.val != top.val){
                    return false;
                }
            }
        }
        return true;
    }

    //28
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return isSymmetric_isMirrorTree(root.left, root.right);
    }

    private boolean isSymmetric_isMirrorTree(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null){
            return true;
        }
        if(t1 == null || t2 == null){
            return false;
        }
        if(t1.val != t2.val){
            return false;
        }
        return isSymmetric_isMirrorTree(t1.left, t2.right) && isSymmetric_isMirrorTree(t1.right, t2.left);
    }

    //27
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode temp = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = temp;
        return root;
    }

    //26
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null)
            return false;

        if(A.val == B.val && isSubStructure_isSub(A, B)){
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSubStructure_isSub(TreeNode A, TreeNode B){
        if(A == null && B == null)
            return true;
        if(A == null || B == null)
            return false;
        if(A.val != B.val){
            return false;
        }
        if(B.left != null && !isSubStructure_isSub(A.left, B.left)){
            return false;
        }
        if(B.right != null && !isSubStructure_isSub(A.right, B.right)) {
            return false;
        }
        return true;
    }

    //25
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode head = new ListNode(0);
        ListNode cur = head;
        while(l1 != null){
            if(l1.val > l2.val){
                ListNode temp = l1;
                l1 = l2;
                l2 = temp;
            }
            cur.next = l1;
            cur = cur.next;
            l1 = l1.next;
        }
        cur.next = l2;

        return head.next;
    }

    //24
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        return getKthFromEnd_reverse(head);
    }

    //22
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head == null)
            return null;
        if(k <= 0)
            return null;

        ListNode tail = getKthFromEnd_reverse(head);

        ListNode cur = tail;
        for (int i = 1; i < k; i++) {
            if(cur == null){
                return null;
            }
            cur = cur.next;
        }
        head = getKthFromEnd_reverse(tail);
        return cur;
    }

    private ListNode getKthFromEnd_reverse(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode nextNode = head.next;
            head.next = pre;
            pre = head;
            head = nextNode;
        }
        return pre;
    }

    //21
    public int[] exchange(int[] nums) {
        if(nums.length <= 1)
            return nums;

        int i = 0;
        while(i < nums.length && nums[i] % 2 != 0){
            i++;
        }
        if(i == nums.length){
            return nums;
        }
        int temp = nums[i];

        int j = nums.length - 1;
        while(i < j){
            while(i < j){
                if(nums[j] % 2 == 1){
                    nums[i] = nums[j];
                    break;
                }
                j--;
            }
            while(i < j){
                if(nums[i] % 2 == 0){
                    nums[j] = nums[i];
                    break;
                }
                i++;
            }
        }
        nums[i] = temp;

        return nums;
    }

    //20
    public boolean isNumber(String s) {
        s = s.strip();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'e' || s.charAt(i) == 'E'){
                return isNumber_withoutE(s.substring(0, i)) && isNumber_integer(s.substring(i + 1));
            }
        }
        return isNumber_withoutE(s);
    }

    private boolean isNumber_withoutE(String s){
        if(s.length() == 0)
            return false;
        if(s.charAt(0) == '-' || s.charAt(0) == '+')
            s = s.substring(1);
        if(s.length() == 0)
            return false;
        boolean dotFlag = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '.'){
                if(i == 0 && s.length() == 1)
                    return false;
                if(!dotFlag){
                    dotFlag = true;
                }
                else{
                    return false;
                }
            }
            else if(c < '0' || c > '9')
                return false;
        }
        return true;
    }

    private boolean isNumber_integer(String s){
        if(!isNumber_withoutE(s))
            return false;
        return s.indexOf('.') == -1;
    }

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
            map.put(keyStr, re);
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

    //14-II errorflag 120
    public int cuttingRopeII(int n) {
        ModNum max = new ModNum(1);
        for (int m = 2; m < n; m++) {
            int base = n / m;
            int remain = n % m;
            ModNum result = new ModNum(1);
            for (int i = 0; i < remain; i++) {
                result.mul(base + 1);
            }
            for (int i = 0; i < m - remain; i++) {
                result.mul(base);
            }
            if(result.compareTo(max)){
                max = result;
            }
        }

        return max.num;
    }


    private static class ModNum{
        public int num;
        public int level = 0;
        public static final int mod = (int) Math.pow(10, 9) + 7;

        public ModNum(){

        }

        public ModNum(int num){
            if(num > mod){
                num %= mod;
                level += num / mod;
            }
            this.num = num;
        }

        public ModNum(int num, int level){
            if(num > mod){
                num %= mod;
                level += num / mod;
            }
            this.num = num;
            this.level = level;
        }

        public ModNum copyOf(){
            return new ModNum(num, level);
        }

        boolean compareTo(ModNum a){
            if(level > a.level){
                return true;
            }
            else if(level == a.level){
                return num > a.num;
            }
            else{
                return false;
            }
        }

        public void mul(int time){
            if(num > mod){
                level += num / mod;
                num %= mod;
                mul(time);
                return;
            }

            if(time == 1){
                return;
            }

            if(Integer.MAX_VALUE / time < num){
                num *= time;
                level += num / mod;
                num %= mod;
                return;
            }

            int re = num;
            for (int i = 0; i < time - 1; i++) {
                re += num;
                level += re / mod;
                re %= mod;
            }

            num = re;
        }
    }

    //14-I
    public int cuttingRope2(int n) {
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i < n + 1; i++) {
            int max = 1;
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, dp[j] * dp[i - j]);
            }
            dp[i] = max;
        }

        return dp[n];
    }

    //14-I
    public int cuttingRope(int n) {
        int max = 1;

        for (int m = 2; m < n; m++) {
            int base = n / m;
            int remain = n % m;
            int result = 1;
            for (int i = 0; i < remain; i++) {
                result *= base + 1;
            }
            for (int i = 0; i < m - remain; i++) {
                result *= base;
            }
            max = Math.max(max, result);
        }

        return max;
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
