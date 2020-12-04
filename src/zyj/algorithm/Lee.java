package zyj.algorithm;

import utils.ListNode;
import utils.TreeNode;

import javax.swing.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.lang.reflect.Array;
import java.util.*;

public class Lee {

    private boolean flag = false;

    //34
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1, -1};
        }

        int[] re = new int[2];

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
        if(left >= nums.length || nums[left] != target){
            re[0] = -1;
        }
        else{
            re[0] = left;
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
        if(right < 0 || nums[right] != target){
            re[1] = -1;
        }
        else{
            re[1] = right;
        }

        return re;
    }

    //752
    //双向BFS 先判断再加入
    public int openLock4(String[] deadends, String target) {
        if(target.equals("0000"))
            return 0;

        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        visited.add(target);
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add("0000");
        s2.add(target);
        int count = 0;

        while(!s1.isEmpty() && !s2.isEmpty()){
            if(s1.size() > s2.size()){
                Set<String> swapTemp = s1;
                s1 = s2;
                s2 = swapTemp;
            }
            Set<String> temp = new HashSet<>();
            for (String cur : s1) {
                if(deads.contains(cur))
                    continue;
                for (int i = 0; i < 4; i++) {
                    char curChar = cur.charAt(i);
                    char newChar1 = curChar == '9'? '0' : (char)(curChar + 1);
                    char newChar2 = curChar == '0'? '9' : (char)(curChar - 1);
                    String newString1 = cur.substring(0, i) + newChar1 + cur.substring(i + 1);
                    String newString2 = cur.substring(0, i) + newChar2 + cur.substring(i + 1);
                    if(s2.contains(newString1) || s2.contains(newString2)){
                        return count + 1;
                    }
                    if(!visited.contains(newString1)){
                        visited.add(newString1);
                        temp.add(newString1);
                    }
                    if(!visited.contains(newString2)){
                        visited.add(newString2);
                        temp.add(newString2);
                    }
                }
            }
            count++;
            s1 = s2;
            s2 = temp;
        }
        return -1;
    }

    //752
    //双向BFS  先加入后判断
    public int openLock3(String[] deadends, String target) {
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add("0000");
        s2.add(target);
        int count = 0;

        while(!s1.isEmpty() && !s2.isEmpty()){
            if(s1.size() > s2.size()){
                Set<String> swapTemp = s1;
                s1 = s2;
                s2 = swapTemp;
            }
            Set<String> temp = new HashSet<>();
            for (String cur : s1) {
                if(deads.contains(cur))
                    continue;
                if(s2.contains(cur)){
                    return count;
                }
                visited.add(cur);
                for (int i = 0; i < 4; i++) {
                    char curChar = cur.charAt(i);
                    char newChar1 = curChar == '9'? '0' : (char)(curChar + 1);
                    char newChar2 = curChar == '0'? '9' : (char)(curChar - 1);
                    String newString1 = cur.substring(0, i) + newChar1 + cur.substring(i + 1);
                    String newString2 = cur.substring(0, i) + newChar2 + cur.substring(i + 1);
                    if(!visited.contains(newString1)){
                        temp.add(newString1);
                    }
                    if(!visited.contains(newString2)){
                        temp.add(newString2);
                    }
                }
            }
            count++;
            s1 = s2;
            s2 = temp;
        }
        return -1;
    }

    //752
    //双向BFS
    public int openLock2(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        Set<String> visitedSource = new HashSet<>();
        Set<String> visitedTarget = new HashSet<>();

        int count = 0;
        Queue<String> queueSource = new LinkedList<>();
        Queue<String> queueTarget = new LinkedList<>();

        queueSource.offer("0000");
        queueTarget.offer(target);

        visitedSource.add("0000");
        visitedTarget.add(target);

        while(!queueSource.isEmpty() && !queueTarget.isEmpty()){
            if(openLock2_search(queueSource, set, visitedSource, visitedTarget))
                return count;
            count++;
            if(openLock2_search(queueTarget, set, visitedTarget, visitedSource))
                return count;
            count++;
        }
        return -1;
    }

    private boolean openLock2_search(Queue<String> queue, Set<String> set, Set<String> visited, Set<String> otherVisited){
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            String cur = queue.poll();
            assert cur != null;
            if(set.contains(cur))
                continue;
            if(otherVisited.contains(cur)) {
                return true;
            }
            for (int j = 0; j < 4; j++) {
                char c = cur.charAt(j);
                char newc1 = c == '9'? '0' : (char) (c + 1);
                char newc2 = c == '0'? '9' : (char) (c - 1);
                String news1 = cur.substring(0, j) + newc1 + cur.substring(j + 1);
                String news2 = cur.substring(0, j) + newc2 + cur.substring(j + 1);
                if(!visited.contains(news1)){
                    visited.add(news1);
                    queue.offer(news1);
                }
                if(!visited.contains(news2)){
                    visited.add(news2);
                    queue.offer(news2);
                }
            }
        }

        return false;
    }

    //752
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                assert cur != null;
                if(set.contains(cur))
                    continue;
                if(cur.equals(target)) {
                    return count;
                }
                for (int j = 0; j < 4; j++) {
                    char c = cur.charAt(j);
                    char newc1 = c == '9'? '0' : (char) (c + 1);
                    char newc2 = c == '0'? '9' : (char) (c - 1);
                    String news1 = cur.substring(0, j) + newc1 + cur.substring(j + 1);
                    String news2 = cur.substring(0, j) + newc2 + cur.substring(j + 1);
                    System.out.println(news1);
                    System.out.println(news2);
                    if(!visited.contains(news1)){
                        visited.add(news1);
                        queue.offer(news1);
                    }
                    if(!visited.contains(news2)){
                        visited.add(news2);
                        queue.offer(news2);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    //111
    public int minDepth2(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        if(root.left == null)
            return 1 + minDepth2(root.right);
        else if(root.right == null)
            return 1 + minDepth2(root.left);
        else
            return 1 + Math.min(minDepth2(root.left), minDepth2(root.right));
    }

    //111
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        int count = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = queue.poll();
                assert cur != null;
                if(cur.left == null && cur.right == null)
                    return count;
                if(cur.left != null){
                    queue.add(cur.left);
                }
                if(cur.right != null){
                    queue.add(cur.right);
                }
            }
            count++;
        }
        return -1;
    }

    //51
    public List<List<String>> solveNQueens2(int n) {
        List<String> board = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            board.add(".".repeat(n));
        }
        List<List<String>> allPath = new LinkedList<>();
        solveNQueens2(0, board, allPath);
        return allPath;
    }

    private void solveNQueens2(int row, List<String> board, List<List<String>> allPath){
        int n = board.size();
        if(row == n){
            allPath.add(List.copyOf(board));
            return;
        }
        for (int i = 0; i < n; i++) {
            if(solveNQueens2_isValid(board, row, i)){
                board.set(row, ".".repeat(i) + "Q" + ".".repeat(n - i - 1));
                solveNQueens2(row + 1, board, allPath);
            }
        }
    }

    private boolean solveNQueens2_isValid(List<String> board, int row, int col){
        int left = col - 1;
        int right = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            if(left >= 0 && board.get(i).charAt(left) == 'Q')
                return false;
            if(right < board.size() && board.get(i).charAt(right) == 'Q')
                return false;
            left--;
            right++;
            if(board.get(i).charAt(col) == 'Q')
                return false;
        }
        return true;
    }

    //51
    public List<List<String>> solveNQueens(int n) {
        List<String> path = new LinkedList<>();
        boolean[][] state = new boolean[n][n];
        for (boolean[] booleans : state) {
            Arrays.fill(booleans, true);
        }
        List<List<String>> allPath = new LinkedList<>();
        solveNQueens(state, path, allPath);
        return allPath;
    }

    private void solveNQueens(boolean[][] state, List<String> path, List<List<String>> allPath){
        int cur = path.size();
        int n = state.length;
        if(cur == n){
            allPath.add(List.copyOf(path));
            return;
        }

        for (int i = 0; i < n; i++) {
            if(state[cur][i]){
                //增加到路径
                String s = ".".repeat(i) +
                        'Q' +
                        ".".repeat(n - i - 1);
                path.add(s);
                //改变state
                //保存state以恢复
                boolean[][] stateTemp = new boolean[n][n];
                for (int j = 0; j < n; j++) {
                    stateTemp[j] = state[j].clone();
                }
                int left = i - 1;
                int right = i + 1;
                for (int j = cur + 1; j < n; j++) {
                    if(left >= 0)
                        state[j][left] = false;
                    if(right < n)
                        state[j][right] = false;
                    left--;
                    right++;
                    state[j][i] = false;
                }

                solveNQueens(state, path, allPath);

                //从路径移除
                path.remove(path.size() - 1);
                //重置state
                for (int j = 0; j < n; j++) {
                    state[j] = stateTemp[j].clone();
                }
            }
        }
    }

    //354
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length == 0)
            return 0;

        Arrays.sort(envelopes, (o1, o2) -> {
            if(o1[0] > 0 && o2[0] < 0)
                return 1;
            else if(o1[0] < 0 && o2[0] > 0)
                return -1;
            return o1[0] - o2[0];
        });

        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if(envelopes[j][1] < envelopes[i][1] && envelopes[j][0] != envelopes[i][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }

    //300
    public int lengthOfLIS3(int[] nums) {
        if(nums.length == 0){
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int max = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] > nums[i]){
                    max = Math.max(max, 1 + dp[j]);
                }
            }
            dp[i] = max;
        }

        int re = Integer.MIN_VALUE;
        for (int i : dp) {
            re = Math.max(i, re);
        }
        return re;
    }

    //300
    public int lengthOfLIS2(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }

    //300
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        return lengthOfLIS(nums, 0, Integer.MIN_VALUE, map);
    }

    private int lengthOfLIS(int[] nums, int index, int cur, Map<String, Integer> map) {
        if(index == nums.length) {
            return 0;
        }
        String keyString = index + "," + cur;
        if(map.containsKey(keyString)){
            System.out.println(keyString);
            return map.get(keyString);
        }
        int temp = index;
        while(temp < nums.length && nums[temp] <= cur){
            temp++;
        }
        if(temp == nums.length)
            return 0;
        int re = Math.max(lengthOfLIS(nums, temp + 1, nums[temp], map) + 1, lengthOfLIS(nums, temp + 1, cur, map));
        map.put(keyString, re);
        return re;
    }

    //10
    public boolean isMatch(String s, String p) {
        if(p.equals(".*"))
            return true;

        //不合法模式，连续*
        for (int i = 1; i < p.length(); i++) {
            if(p.charAt(i) == p.charAt(i - 1) && p.charAt(i) == '*')
                return false;
        }

        int s_index = 0;
        int p_index = 0;

        while(s_index < s.length() && p_index < p.length()){
            if(p_index < p.length() - 1 && p.charAt(p_index + 1) == '*'){
                //找出当前字母可能重复几遍
                int temp = s_index;
                while(temp < s.length() && isMatch(s.charAt(temp), p.charAt(p_index))){
                    temp++;
                }
                int count = temp - s_index;

                //可能重复0 - count遍
                for (int i = 0; i < count + 1; i++) {
                    if(isMatch(s.substring(s_index + i), p_index + 2 < p.length()? p.substring(p_index + 2) : "")){
                        return true;
                    }

//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append(String.valueOf(p.charAt(p_index)).repeat(i));
//                    if(p_index + 2 < p.length())
//                        stringBuilder.append(p.substring(p_index + 2));
//                    if(isMatch(s.substring(s_index), stringBuilder.toString()))
//                        return true;
                }
                return false;
            }
            if(!isMatch(s.charAt(s_index), p.charAt(p_index)))
                return false;
            s_index++;
            p_index++;
        }

        //可以忽视p中剩余的 c*c*c*
        while(p_index < p.length()){
            if(p_index + 1 < p.length() && p.charAt(p_index + 1) == '*'){
                p_index += 2;
            }
            else
                break;
        }
        return s_index == s.length() && p_index == p.length();
    }

    private boolean isMatch(char s, char p){
        if(s == p){
            return true;
        }
        else return p == '.';
    }

    //1563
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        if(n == 1)
            return 0;

        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = i; j < n; j++) {
                cur += stoneValue[j];
                sum[i][j] = cur;
            }
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = i; k < j; k++) {
                    int sumLeft = sum[i][k];
                    int sumRight = sum[k + 1][j];
                    int left = sumLeft + dp[i][k];
                    int right = sumRight + dp[k + 1][j];
                    int temp;
                    if(sumLeft < sumRight)
                        temp = left;
                    else if(sumLeft > sumRight)
                        temp = right;
                    else
                        temp = Math.max(left, right);
                    max = Math.max(max, temp);
                }
                dp[i][j] = max;
            }
        }

        return dp[0][n - 1];
    }

    //1510
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        Arrays.fill(dp, false);

        List<Integer> squares = new LinkedList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }

        for(int cur = n; cur > 0; cur--){
            while(dp[cur]){
                cur--;
            }
            for (Integer square : squares) {
                if(cur - square < 0)
                    break;
                if(cur - square == 0)
                    return true;
                dp[cur - square] = true;
            }
        }
        return false;
    }

    //1406
    public String stoneGameIII2(int[] stoneValue) {

        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = stoneValue[n - 1];

        for (int i = stoneValue.length - 2; i >= 0; i--) {
            int getSum = 0;
            int max = Integer.MIN_VALUE;

            for (int j = i; j < i + 3 && j < n; j++) {
                getSum += stoneValue[j];
                max = Math.max(max, getSum - dp[j + 1]);
            }

            dp[i] = max;
        }

        if(dp[0] > 0)
            return "Alice";
        else if(dp[0] == 0)
            return "Tie";
        else
            return "Bob";
    }

    //1406
    public String stoneGameIII(int[] stoneValue) {
        int[] dp = new int[stoneValue.length];

        dp[stoneValue.length - 1] = stoneValue[stoneValue.length - 1];
        for (int i = stoneValue.length - 2; i >= 0; i--) {
            int max = Integer.MIN_VALUE;
            int getSum = 0;

            getSum += stoneValue[i];
            int getOne = getSum - dp[i + 1];
            max = getOne;

            getSum += stoneValue[i + 1];
            int getTwo;
            if(i + 2 < stoneValue.length)
                getTwo = getSum - dp[i + 2];
            else
                getTwo = getSum;
            max = Math.max(max, getTwo);


            if(i + 2 < stoneValue.length){
                getSum += stoneValue[i + 2];
                int getThree;
                if(i + 3 < stoneValue.length)
                    getThree = getSum - dp[i + 3];
                else
                    getThree = getSum;
                max = Math.max(max, getThree);
            }

            dp[i] = max;
        }

        if(dp[0] > 0)
            return "Alice";
        else if(dp[0] == 0)
            return "Tie";
        else
            return "Bob";
    }

    //1140
    public int stoneGameII(int[] piles) {
        if(piles.length == 1)
            return piles[0];

        int allPile = 0;
        for (int pile : piles) {
            allPile += pile;
        }

        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            for (int j = piles.length - 1; j * 2 >= piles.length - i; j--) {
                for (int k = i; k < piles.length; k++) {
                    dp[i][j] += piles[k];
                }
            }
        }

        for (int i = piles.length - 1; i >= 0; i--) {
            for (int j = 1; j * 2 < piles.length - i; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = 1; k <= 2 * j; k++) {
                    int sum = 0;
                    for (int l = 0; l < k; l++) {
                        sum += piles[i + l];
                    }
                    sum -= dp[i + k][Math.max(k, j)];
                    max = Math.max(sum, max);
                }
                dp[i][j] = max;
            }
        }

        return (dp[0][1] + allPile) / 2;
    }

    //877
    public boolean stoneGame2(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }
        for (int j = 1; j < piles.length; j++) {
            for (int i = 0; i < piles.length - j; i++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][piles.length - 1] > 0;
    }

    //877
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = piles.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < piles.length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][piles.length - 1] > 0;
    }

    //452
    public int findMinArrowShots(int[][] points) {
        if(points.length < 2)
            return points.length;

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] > 0 && o2[1] < 0)
                    return 1;
                else if(o1[1] < 0 && o2[1] > 0)
                    return -1;
                return o1[1] - o2[1];
            }
        });

//        quickSort(points);

        int count = 1;
        int cur_end = points[0][1];
        for (int[] point : points) {
            if(point[0] > cur_end){
                cur_end = point[1];
                count++;
            }
        }

        return count;
    }

    //435
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2)
            return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

//        quickSort(intervals);

        int count = 1;
        int index = 0;
        while(index < intervals.length){
            int cur_end = intervals[index][1];
            while(index < intervals.length && intervals[index][0] < cur_end)
                index++;
            if(index < intervals.length)
                count++;
        }
        return intervals.length - count;
    }

    private void quickSort(int[][] intervals){
        if(intervals.length != 0)
            quickSort(intervals, 0, intervals.length - 1);
    }

    private void quickSort(int[][] intervals, int left, int right){
        if(left >= right)
            return;
        int i = left;
        int j = right;
        int[] temp = intervals[left];
        while(i < j){
            while(i < j){
                if(intervals[j][1] <= temp[1]){
                    intervals[i] = intervals[j];
                    break;
                }
                j--;
            }
            while(i < j){
                if(intervals[i][1] > temp[1]){
                    intervals[j] = intervals[i];
                    break;
                }
                i++;
            }
        }

        intervals[i] = temp;

        quickSort(intervals, left, i - 1);
        quickSort(intervals, i + 1, right);
    }

    //714  含手续费
    public int maxProfit_6_2(int[] prices, int fee) {
        if(prices.length < 2)
            return 0;

        int not_hold = 0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int temp = not_hold;
            not_hold = Math.max(not_hold, hold + prices[i] - fee);
            hold = Math.max(hold, temp - prices[i]);
        }

        return not_hold;
    }

    //714  含手续费
    public int maxProfit_6_1(int[] prices, int fee) {
        if(prices.length < 2)
            return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }










    //309  卖出后冷冻一天无法买入
    public int maxProfit_5_1(int[] prices){
        if(prices.length < 2)
            return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0){
                    int sell = dp[i - 1][1] + prices[i];
                    int rest = dp[i - 1][j];
                    dp[i][j] = Math.max(sell, rest);
                }
                else{
                    int buy = Integer.MIN_VALUE;
                    if(i == 1){
                        buy = -prices[i];
                    }
                    else
                        buy = dp[i - 2][0] - prices[i];
                    int rest = dp[i - 1][j];
                    dp[i][j] = Math.max(buy, rest);
                }
            }
        }

        return dp[prices.length - 1][0];
    }

    //188
    //k为当前可能的最大交易次数
    public int maxProfit_4_3(int k, int[] prices) {
        if(k == 0 || prices.length < 2)
            return 0;

        if(k > prices.length / 2)
            return maxProfit_2_1(prices);

        int[][] dp = new int[k + 1][2];

        for (int j = 0; j < k + 1; j++) {
            dp[j][0] = 0;
            if(j > 0)
                dp[j][1] = -prices[0];
        }

        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }

        return dp[k][0];
    }

    //188
    //k为当前可能的最大交易次数
    public int maxProfit_4_2(int k, int[] prices) {
        if(k == 0 || prices.length < 2)
            return 0;

        if(k > prices.length / 2)
            return maxProfit_2_1(prices);

        int[][][] dp = new int[prices.length][k + 1][2];

        for (int j = 0; j < k + 1; j++) {
            dp[0][j][0] = 0;
            if(j > 0)
                dp[0][j][1] = -prices[0];
        }

        for (int i = 0; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l < 2; l++) {
                    if(l == 0){
                        int sell = dp[i - 1][j][1] + prices[i];
                        int rest = dp[i - 1][j][0];
                        dp[i][j][l] = Math.max(sell, rest);
                    }
                    else{//l == 1
                        int buy = dp[i - 1][j - 1][0] - prices[i];
                        int rest = dp[i - 1][j][1];
                        dp[i][j][l] = Math.max(buy, rest);
                    }
                }
            }
        }

        return dp[prices.length - 1][k][0];
    }

    //188
    //k为当前剩余可交易次数
    public int maxProfit_4_1(int k, int[] prices) {
        if(k == 0 || prices.length < 2)
            return 0;

        if(k > prices.length / 2)
            return maxProfit_2_1(prices);

        int[][][] dp = new int[prices.length][k + 1][2];

        for (int j = 0; j < k + 1; j++) {
            dp[0][j][0] = 0;
            if(j < k - 1)
                dp[0][j][1] = Integer.MIN_VALUE;
        }
        dp[0][k - 1][1] = -prices[0];

        for (int i = 0; i < prices.length; i++) {
            dp[i][k][0] = 0;
            dp[i][k][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = k - 1; j >= 0; j--) {
                for (int l = 0; l < 2; l++) {
                    if(l == 0){
                        int sell = dp[i - 1][j][1] + prices[i];
                        int rest = dp[i - 1][j][0];
                        dp[i][j][l] = Math.max(sell, rest);
                    }
                    else{//l == 1
                        int buy = dp[i - 1][j + 1][0] - prices[i];
                        int rest = dp[i - 1][j][1];
                        dp[i][j][l] = Math.max(buy, rest);
                    }
                }
            }
        }

        int re = 0;
        for (int j = 0; j < k + 1; j++) {
            re = Math.max(re, dp[prices.length - 1][j][0]);
        }
        return re;
    }

    //123  2次交易
    public int maxProfit_3_1(int[] prices){
        if(prices.length < 2)
            return 0;

        int[][][] dp = new int[prices.length][3][2];
        for (int j = 0; j < 3; j++) {
            dp[0][j][0] = 0;
            if(j > 0)
                dp[0][j][1] = -prices[0];
        }
        for (int i = 0; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    if(k == 0){
                        int sell = dp[i - 1][j][1] + prices[i];
                        int rest = dp[i - 1][j][0];
                        dp[i][j][k] = Math.max(sell, rest);
                    }
                    else{
                        int buy = dp[i - 1][j - 1][0] - prices[i];
                        int rest = dp[i - 1][j][1];
                        dp[i][j][k] = Math.max(buy, rest);
                    }
                }
            }
        }

        return dp[prices.length - 1][2][0];
    }

    //122  无限次交易
    public int maxProfit_2_2(int[] prices) {
        if(prices.length < 2)
            return 0;

        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0){
                    int sell = dp[i - 1][1] + prices[i];
                    int rest = dp[i - 1][0];
                    dp[i][j] = Math.max(sell, rest);
                }
                else{//j == 1
                    int buy = dp[i - 1][0] - prices[i];
                    int rest = dp[i - 1][1];
                    dp[i][j] = Math.max(buy, rest);
                }
            }
        }

        return dp[prices.length - 1][0];
    }

    //122  无限次交易
    public int maxProfit_2_1(int[] prices) {
        if(prices.length < 2)
            return 0;

        int re = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = prices[i] - prices[i - 1];
            if(temp > 0)
                re += temp;
        }
        return re;
    }

    //121  一次交易
    public int maxProfit_1_3(int[] prices){
        if(prices.length < 2)
            return 0;

        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0){
                    int sell = dp[i - 1][1] + prices[i];
                    int rest = dp[i - 1][0];
                    dp[i][j] = Math.max(sell, rest);
                }
                else{//j == 1
                    int buy = -prices[i];
                    int rest = dp[i - 1][1];
                    dp[i][j] = Math.max(buy, rest);
                }
            }
        }

        return dp[prices.length - 1][0];
    }

    //121
    public int maxProfit_1_2(int[] prices){
        if(prices.length < 2)
            return 0;

        int min = prices[0];
        int re = 0;
        for (int price : prices) {
            if(price < min){
                min = price;
            }
            else if(price > min){
                re = Math.max(re, price - min);
            }
        }

        return re;
    }

    //121
    public int maxProfit_1_1(int[] prices){
        if(prices.length < 2)
            return 0;

        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }

        return max;
    }


    //350
    public int[] intersect2(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0)
            return new int[]{};

        int[] re = new int[Math.min(nums1.length, nums2.length)];
        int index_re = 0;

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        while(index1 < nums1.length && index2 < nums2.length){
            int num1 = nums1[index1];
            int num2 = nums2[index2];

            if(num1 == num2){
                re[index_re] = num1;
                index_re++;
                index1++;
                index2++;
            }
            else if(num1 < num2){
                index1++;
            }
            else
                index2++;
        }

        return Arrays.copyOf(re, index_re);
    }

    //350
    public int[] intersect(int[] nums1, int[] nums2) {

        if(nums1.length == 0 || nums2.length == 0)
            return new int[]{};

        List<Integer> list = new LinkedList<>();

        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        for (int i : nums1) {
            if(hashMap1.containsKey(i)){
                hashMap1.put(i, hashMap1.get(i) + 1);
            }
            else
                hashMap1.put(i, 1);
        }

        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i : nums2) {
            if(hashMap2.containsKey(i)){
                hashMap2.put(i, hashMap2.get(i) + 1);
            }
            else
                hashMap2.put(i, 1);
        }


        if(hashMap1.size() <= hashMap2.size()){
            for (Integer key : hashMap1.keySet()) {
                if(hashMap2.containsKey(key)){
                    for (int i = 0; i < Math.min(hashMap1.get(key), hashMap2.get(key)); i++) {
                        list.add(key);
                    }
                }
            }
        }
        else{
            for (Integer key : hashMap2.keySet()) {
                if(hashMap1.containsKey(key)){
                    for (int i = 0; i < Math.min(hashMap1.get(key), hashMap2.get(key)); i++) {
                        list.add(key);
                    }
                }
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //326
    public boolean isPowerOfThree(int n) {
        if(n < 1)
            return false;
        while(n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }

    //268
    public int missingNumber2(int[] nums) {
        if(nums.length == 0)
            return -1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != i)
                return i;
        }
        return nums.length;
    }

    //268
    public int missingNumber(int[] nums) {
        if(nums.length == 0)
            return -1;
        Boolean[] flags = new Boolean[nums.length + 1];
        Arrays.fill(flags, false);
        for (int num : nums) {
            flags[num] = true;
        }
        for (int i = 0; i < flags.length; i++) {
            if(!flags[i])
                return i;
        }
        return -1;
    }

    //234
    public boolean isPalindrome2(ListNode head) {
        if(head == null)
            return true;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tail = isPalindrome_reverseList(slow.next);

        boolean result = true;
        ListNode tail_temp = tail;
        while(tail_temp != null){
            if(head.val != tail_temp.val) {
                result = false;
                break;
            }
            head = head.next;
            tail_temp = tail_temp.next;
        }

        //恢复
        slow.next = isPalindrome_reverseList(tail);

        return result;
    }

    private ListNode isPalindrome_reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //234
    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return true;
        ListNode p = head;
        int len = 0;
        while(p != null){
            len++;
            p = p.next;
        }
        Stack<Integer> stack = new Stack<>();
        p = head;
        for (int i = 0; i < len / 2; i++) {
            stack.push(p.val);
            p = p.next;
        }
        if(len % 2 != 0){
            p = p.next;
        }
        while(p != null){
            if(stack.pop() != p.val)
                return false;
            p = p.next;
        }
        return true;
    }

    //220
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;

            // Find the predecessor of current element
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }



    //220
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length < 2 || k <= 0)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (Integer num : set) {
                if(num > 0 && nums[i] < 0 && Integer.MAX_VALUE + nums[i] < num) {
                }
                else if(nums[i] > 0 && num < 0 && Integer.MAX_VALUE + num < nums[i]) {
                }
                else if(Math.abs(num - nums[i]) <= t)
                    return true;
            }
            if(set.size() == k)
                set.remove(nums[i - k]);
            set.add(nums[i]);
        }
        return false;
    }

    //219
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if(nums.length < 2 || k <= 0)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i]))
                return true;
            else{
                if(set.size() == k){
                    set.remove(nums[i - k]);
                }
                set.add(nums[i]);
            }
        }
        return false;
    }

    //219
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(nums.length < 2 || k <= 0)
            return false;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(treeSet.contains(nums[i]))
                return true;
            else{
                if(treeSet.size() == k){
                    treeSet.remove(nums[i - k]);
                    treeSet.add(nums[i]);
                }
                else{
                    treeSet.add(nums[i]);
                }
            }
        }
        return false;
    }

    //219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length < 2 || k <= 0)
            return false;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                List<Integer> list = map.get(nums[i]);
                for (Integer num : list) {
                    if(Math.abs(num - i) <= k){
                        return true;
                    }
                }
                list.add(i);
            }
            else{
                List<Integer> list = new LinkedList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }
        return false;
    }

    //217
    public boolean containsDuplicate(int[] nums) {
        if(nums.length < 2)
            return false;
        quickSort(nums);
//        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1])
                return true;
        }
        return false;
    }

    //217
    public boolean containsDuplicate2(int[] nums) {
        if(nums.length < 2)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else
                set.add(num);
        }
        return false;
    }

    public void quickSort(int[] nums){
        if(nums.length == 0)
            return;
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right){
        if(left >= right)
            return;
        int i = left;
        int j = right;
        int temp = nums[left];
        while(i < j){
            while(i < j){
                if(nums[j] < temp){
                    nums[i] = nums[j];
                    i++;
                    break;
                }
                j--;
            }
            while(i < j){
                if(nums[i] >= temp){
                    nums[j] = nums[i];
                    j--;
                    break;
                }
                i++;
            }
        }
        nums[i] = temp;
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    //204
    public int countPrimes(int n) {
        if(n < 2)
            return 0;
        int count = 0;
        for (int i = 2; i < n; i++) {
            if(isPrim(i))
                count++;
        }
        return count;
    }

    //204
    int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i])
                count++;

        return count;
    }

    private boolean isPrim(int num){
        for (int i = 2; i * i <= num; i++) {
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    //337
    public int rob(TreeNode root) {
        if(root == null)
            return 0;

        Map<TreeNode, Integer> map = new HashMap<>();
        return rob(root, map);
    }

    private int rob(TreeNode root, Map<TreeNode, Integer> map){
        if(root == null)
            return 0;
        if(map.containsKey(root))
            return map.get(root);

        int rob = 0;
        rob += root.val;
        if(root.left != null){
            rob += rob(root.left.left, map);
            rob += rob(root.left.right, map);
        }
        if(root.right != null){
            rob += rob(root.right.left, map);
            rob += rob(root.right.right, map);
        }

        int not_rob = 0;
        not_rob += rob(root.left, map);
        not_rob += rob(root.right, map);

        int re = Math.max(rob, not_rob);
        map.put(root, re);
        return re;
    }

    //213
    public int rob_2_1(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0], nums[1]);

        return Math.max(rob_2_1(nums, 1, nums.length - 1), rob_2_1(nums, 2, nums.length - 2) + nums[0]);
    }

    private int rob_2_1(int[] nums, int left, int right){
        if(left > right)
            return 0;
        if(left == right)
            return nums[left];

        int[] dp = new int[right - left + 2];
        dp[0] = 0;
        dp[1] = nums[left];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[left + i - 1], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    //198
    public int rob3(int[] nums) {
        if(nums.length == 0)
            return 0;

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }

        return dp[nums.length];
    }

    //198
    public int rob(int[] nums) {
        if(nums.length <= 0)
            return 0;

        int[] max = new int[nums.length];

        max[0] = nums[0];
        if(nums.length == 1)
            return max[0];

        max[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            max[i] = Math.max(max[i - 2] + nums[i], max[i - 1]);
        }

        return max[nums.length - 1];
    }

    //198
    public int rob2(int[] nums) {
        if(nums.length <= 0)
            return 0;

        int[] max = new int[3];

        max[0] = nums[0];
        if(nums.length == 1)
            return max[0];

        max[1] = Math.max(nums[0], nums[1]);
        if(nums.length == 2)
            return max[1];

        for (int i = 2; i < nums.length; i++) {
            if(i != 2){
                max[0] = max[1];
                max[1] = max[2];
            }
            max[2] = Math.max(max[0] + nums[i], max[1]);
        }

        return max[2];
    }

    //190
    public int reverseBits(int n) {
        if(n == 1)
            return 1 << 31;
        if(n == 1 << 31)
            return 1;
        if(n == 0)
            return 0;
        int firstBit = Math.abs(getBit(n, 0));
        if(n < 0){
            n = -n;
            n = setBit(n, 0, 1);
        }
        else{
            n = setBit(n, 0, 0);
        }
        int i = 1;
        int j = 30;
        while(i < j){
            int temp = getBit(n, i);
            n = setBit(n, i, getBit(n, j));
            n = setBit(n, j, temp);
            i++;
            j--;
        }
        if(firstBit == 1)
            n = -n;
        return n;
    }

    private int getBit(int n, int position){
        for (int i = 0; i < position; i++) {
            n /= 2;
        }
        return n % 2;
    }

    private int setBit(int n, int position, int num){
        if(num == getBit(n, position))
            return n;
        else{
            int change = 1;
            for (int i = 0; i < position; i++) {
                change *= 2;
            }
            if(num == 0)
                return n - change;
            else
                return n + change;
        }
    }

    //189
    public void rotate(int[] nums, int k) {
        if (k == 0 || nums.length == 0)
            return;
        k = k % nums.length;
        int[] lastK = Arrays.copyOfRange(nums, nums.length - k, nums.length);
        if (nums.length - k >= 0)
            System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(lastK, 0, nums, 0, k);
    }

    //189
    public void rotate2(int[] nums, int k) {
        if (k == 0 || nums.length == 0)
            return;
        k = k % nums.length;
        if (k == 0)
            return;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int pre = nums[start];
            nums[start] = nums[nums.length - k + start];
            count++;
            for (int i = start + k; i != start; i = (i + k) % nums.length) {
                int temp = nums[i];
                nums[i] = pre;
                pre = temp;
                count++;
            }
        }
    }

    //189
    public void rotate3(int[] nums, int k) {
        k = k % nums.length;
        if(k <= 0)
            return;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int from, int to){
        if(from >= to)
            return;
        int i = from;
        int j = to;
        while(i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    //172
    public int trailingZeroes(int n) {
        int count = 0;
        for (int i = 5; i <= n; i = i + 5) {
            int temp = i;
            while (temp % 5 == 0) {
                temp = temp / 5;
                count++;
            }
        }
        return count;
    }

    //priorityQueue
    public void testPQ() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return (int) (Math.pow(t1, 2) - Math.pow(integer, 1));
            }
        });
        queue.add(4);
        queue.add(5);
        queue.add(2);
        System.out.println(queue.peek());
        System.out.println(queue.remove());
    }

    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    //160
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        int lenA = 0;
        int lenB = 0;
        ListNode p;
        for (p = headA; p != null; p = p.next) lenA++;
        for (p = headB; p != null; p = p.next) lenB++;
        for (int i = 0; i < Math.abs(lenA - lenB); i++) {
            if (lenA > lenB) {
                headA = headA.next;
            } else {
                headB = headB.next;
            }
        }
        while (headA != null) {
            if (headA == headB)
                return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    //155
    class MinStack {

        List<Integer> list = new LinkedList<>();
        int top = -1;
        int min = Integer.MAX_VALUE;
        int min_index = -1;


        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            top++;
            if (x < min) {
                min = x;
                min_index = top;
            }
            list.add(x);
        }

        public void pop() {
            list.remove(top);
            if (top == min_index) {
                min = Integer.MAX_VALUE;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) < min) {
                        min = list.get(i);
                        min_index = i;
                    }
                }
            }
            top--;
        }

        public int top() {
            return list.get(top);
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */


    //141
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        Set<ListNode> set = new HashSet<>();
        while (head.next != null) {
            if (set.contains(head))
                return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    //141
    public boolean hasCycle2(ListNode head) {
        if (head == null)
            return false;
        ListNode quick = head;
        ListNode slow = head;
        while (quick.next != null && quick.next.next != null && slow.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow)
                return true;
        }
        return false;
    }

    //125
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j) {
                if (isNumOrCharacter(s.charAt(j))) {
                    break;
                }
                j--;
            }
            while (i < j) {
                if (isNumOrCharacter(s.charAt(i))) {
                    break;
                }
                i++;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isNumOrCharacter(Character c) {
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= 'A' && c <= 'Z')
            return true;
        return c >= '0' && c <= '9';
    }


    //88
    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (p2 == -1 || (p1 != -1 && nums1[p1] > nums2[p2])) {
                nums1[i] = nums1[p1];
                p1--;
            } else {
                nums1[i] = nums2[p2];
                p2--;
            }
        }
    }

    //88
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        for (int i = 0; i < n; i++) {
            int num2 = nums2[i];
            for (; j < nums1.length; j++) {
                int num1 = nums1[j];
                if (j >= i + m) {
                    for (; i < n; i++) {
                        nums1[j] = nums2[i];
                        j++;
                    }
                    break;
                }
                if (num1 >= num2) {
                    nums1[j] = num2;
                    int temp = num1;
                    for (int k = j; k < m + i; k++) {
                        int temp2 = nums1[k + 1];
                        nums1[k + 1] = temp;
                        temp = temp2;
                    }
                    break;
                }
            }
        }
    }

    //70
    public int climbStairs(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        return climbStairs_helper(n, map);
    }

    public int climbStairs_helper(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n))
            return map.get(n);

        int one, two;

        if (map.containsKey(n - 1))
            one = map.get(n - 1);
        else {
            one = climbStairs_helper(n - 1, map);
            map.put(n - 1, one);
        }

        if (map.containsKey(n - 2))
            two = map.get(n - 2);
        else {
            two = climbStairs_helper(n - 2, map);
            map.put(n - 2, two);
        }
        return one + two;
    }

    public boolean isOverflow_mul(int a, int b) {
        int maximum = Integer.signum(a) == Integer.signum(b) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        if (a != 0 && ((b > 0 && b > maximum / a)
                || (b < 0 && b < maximum / a)))
            return true;
        return false;
    }

    //69
    public int mySqrt(int x) {
        int left = 0, right = x;
        for (int cur = (left + right) / 2; cur != left && cur != right; cur = (left + right) / 2) {
            if (cur != 0 && Integer.MAX_VALUE / cur < cur) {
                right = cur;
                continue;
            }
            int mul = cur * cur;
            if (mul == x)
                return cur;
            else if (mul < x)
                left = cur;
            else
                right = cur;
        }
        if (right * right == x)
            return right;
        return left;
    }

    //53
    public int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    public int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int p = (left + right) / 2;

        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    //53
    public int maxSubArray_1(int[] nums) {
        int re = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            re = Math.max(temp, re);
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                re = Math.max(temp, re);
            }
        }
        return re;
    }

    //38
    public String countAndSay(int n) {
        String before = "1";
        if (n == 1) return before;
        for (int i = 0; i < n - 1; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;
            char temp = before.charAt(0);
            for (int j = 1; j < before.length(); j++) {
                char c = before.charAt(j);
                if (c == temp) {
                    count++;
                } else {
                    next.append(count);
                    next.append(temp);
                    temp = c;
                    count = 1;
                }
            }
            next.append(count).append(temp);
            before = next.toString();
        }
        return before;
    }

    //26
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int slow = 0;
        int before = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != before) {
                slow++;
                nums[slow] = nums[i];
            }
            before = nums[i];
        }
        return slow + 1;
    }

    //20
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else if (c == ')')
                if (stack.isEmpty() || stack.pop() != '(')
                    return false;
                else ;
            else if (c == ']')
                if (stack.isEmpty() || stack.pop() != '[')
                    return false;
                else ;
            else if (c == '}')
                if (stack.isEmpty() || stack.pop() != '{')
                    return false;
                else ;
        }
        return stack.isEmpty();
    }

    //14
    public String longestCommonPrefix_2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return LCP(strs, 0, strs.length - 1);
    }

    public String LCP(String[] strs, int begin, int end) {
        if (begin == end)
            return strs[begin];
        int mid = (begin + end) / 2;
        String first = LCP(strs, begin, mid);
        String second = LCP(strs, mid + 1, end);
        return commonPrefix(first, second);
    }

    private String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i))
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }

    //14
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String temp;
        temp = strs[0];
        int index = temp.length();
        for (String str : strs) {
            int i = 0;
            for (; i < Math.min(temp.length(), str.length()); i++) {
                char c = str.charAt(i);
                if (c != temp.charAt(i)) {
                    break;
                }
            }
            index = Math.min(i, index);
            temp = temp.substring(0, index);
            if (index == 0)
                return "";
        }
        return temp.substring(0, index);
    }

    //7
    public int reverse_2(int x) {
        int re = 0;
        while (x != 0) {
            if (re > Integer.MAX_VALUE / 10 || re < Integer.MIN_VALUE / 10) {
                return 0;
            }
            re *= 10;
            int num = x % 10;
            if ((re > Integer.MAX_VALUE - num && num > 0) || (re < Integer.MIN_VALUE - num && num < 0)) {
                return 0;
            }
            re += num;
            x /= 10;
        }
        return re;
    }

    //7
    public int reverse(int x) {
        boolean flag = false;
        long l = x;
        if (l < 0) {
            flag = true;
            l = -l;
        }
        String s = String.valueOf(l);
        StringBuilder stringBuilder = new StringBuilder(s);
        String re = stringBuilder.reverse().toString();
        l = Long.parseLong(re);
        if (flag) {
            if (-l < Integer.MIN_VALUE)
                return 0;
            return (int) -l;
        }
        if (l > Integer.MAX_VALUE)
            return 0;
        return (int) l;
    }

    //202
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int next = n;
        while (!set.contains(next)) {
            n = next;
            set.add(n);
            next = 0;
            while (n != 0) {
                next += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (next == 1)
                return true;
        }
        return false;
    }



    //283
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while (i < nums.length) {
            if (nums[i] != 0) {
                if (i != j)
                    nums[j] = nums[i];
                j++;
            }
            i++;
        }
        while (j < nums.length) {
            nums[j] = 0;
            j++;
        }
    }

    //242
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.replace(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) == 1)
                    map.remove(c);
                else
                    map.replace(c, map.get(c) - 1);
            } else
                return false;
        }

        return true;
    }

    //518
    public int change2(int amount, int[] coins) {
        if(amount == 0)
            return 1;
        if(coins.length == 0)
            return 0;

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int j = 1; j < amount + 1; j++) {
                if (j - coin >= 0) {
                    dp[j] += dp[j - coin];
                }
            }
        }
        return dp[amount];
    }

    //518
    public int change(int amount, int[] coins) {
        if(amount == 0)
            return 1;
        if(coins.length == 0)
            return 0;

        int[][] dp = new int[coins.length][amount + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, 0);
        }
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if(i > 0){
                    dp[i][j] += dp[i - 1][j];
                }
                if(j - coins[i] >= 0){
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

    //416
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if(sum % 2 != 0)
            return false;
        Set<Integer> set = new HashSet<>();
        Set<Integer> tempSet = new HashSet<>();
        set.add(0);
        for (int num : nums) {
            tempSet.clear();
            for (Integer index : set) {
                if(index + 2 * num < sum)
                    tempSet.add(index + 2 * num);
                else if(index + 2 * num == sum)
                    return true;
            }
            set.addAll(tempSet);
        }
        return set.contains(sum);
    }

    //166
    public String fractionToDecimal(int numerator, int denominator) {
        float f = (float)numerator / (float)denominator;
        String s = String.valueOf(f);
        return null;
    }

    //91
    public int numDecodings(String s) {
        if(s.charAt(0) == '0')
            return 0;
        if(s.length() == 1)
            return 1;
        int[] nums = new int[s.length()];
        nums[0] = 1;

        int num = 0;
        if(s.charAt(0) == '1' || (s.charAt(0) == '2' && s.charAt(1) <= '6'))
            num++;
        if(s.charAt(1) != '0')
            num++;
        nums[1] = num;

        for (int i = 2; i < nums.length; i++) {
            num = 0;
            if(s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')){
                num += nums[i - 2];
            }
            if(s.charAt(i) != '0'){
                num += nums[i - 1];
            }
            nums[i] = num;
        }
        return nums[nums.length - 1];
    }

    //8
    public int myAtoi(String str) {
        if(str.length() == 0)
            return 0;
        int index = 0;
        int sign = 1;
        int re = 0;
        while(index < str.length()){
            if(str.charAt(index) != ' ')
                break;
            index++;
        }
        if(index == str.length())
            return 0;

        char cur = str.charAt(index);
        if(cur == '+'){
            index++;
        }
        else if(cur == '-'){
            sign = -1;
            index++;
        }
        else if(!(cur >= '0' && cur <= '9')){
            return 0;
        }

        while(index < str.length()){
            cur = str.charAt(index);
            if(cur >= '0' && cur <= '9'){
                if(re == 0){
                    re += cur - '0';
                }
                else{
                    if(re > Integer.MAX_VALUE / 10){
                        if(sign == 1)
                            return Integer.MAX_VALUE;
                        else
                            return Integer.MIN_VALUE;
                    }
                    re *= 10;

                    if(sign == 1){
                        if(re > Integer.MAX_VALUE - (cur - '0')){
                            return Integer.MAX_VALUE;
                        }
                    }
                    else{
                        if(cur - '0' != 0 && re > Integer.MAX_VALUE - (cur - '0') + 1){
                            return Integer.MIN_VALUE;
                        }
                    }
                    re += cur - '0';
                }
            }
            else{
                break;
            }
            index++;
        }

        return re * sign;
    }

    //516
    public int longestPalindromeSubseq3(String s) {
        if(s.length() == 0)
            return 0;

        int[][] nums = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(nums[i], 0, i, 0);
        }
        for (int i = 0; i < s.length(); i++) {
            nums[i][i] = 1;
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j))
                    nums[i][j] = nums[i + 1][j - 1] + 2;
                else {
                    nums[i][j] = Math.max(nums[i + 1][j], nums[i][j - 1]);
                }
            }
        }

        return nums[0][s.length() - 1];
    }

    //516
    public int longestPalindromeSubseq(String s) {
        if(s.length() == 0)
            return 0;
        return longestPalindromeSubseq2(s, 0, s.length() - 1, new HashMap<>());
    }

    private int longestPalindromeSubseq2(String s, int begin, int end, Map<String, Integer> map) {
        if(begin > end || s.length() == 0)
            return 0;
        if(begin == end)
            return 1;
        String sub = s.substring(begin, end + 1);
        if(map.containsKey(sub))
            return map.get(sub);
        Set<Character> set = new HashSet<>();
        int max = 0;
        while(begin <= end){
            char c = s.charAt(begin);
            if(!set.contains(c)){
                set.add(c);
                int index = s.lastIndexOf(c, end);
                int cur;
                if(begin == index)
                    cur = 1 + longestPalindromeSubseq(s, begin + 1, index - 1, map);
                else
                    cur = 2 + longestPalindromeSubseq(s, begin + 1, index - 1, map);
                if(cur > max)
                    max = cur;
            }
            begin++;
        }
        map.put(sub, max);
        return max;
    }

    private int longestPalindromeSubseq(String s, int begin, int end, Map<String, Integer> map) {
        if(begin > end || s.length() == 0)
            return 0;
        if(begin == end)
            return 1;
        String sub = s.substring(begin, end + 1);
        if(map.containsKey(sub))
            return map.get(sub);
        Set<Character> set = new HashSet<>();
        int max = 0;
        while(begin <= end){
            if(!set.contains(s.charAt(begin))){
                set.add(s.charAt(begin));
                for (int i = end; i >= begin; i--) {
                    if(s.charAt(begin) == s.charAt(i)){
                        int cur;
                        if(begin == i)
                            cur = 1 + longestPalindromeSubseq(s, begin + 1, i - 1, map);
                        else
                            cur = 2 + longestPalindromeSubseq(s, begin + 1, i - 1, map);
                        if(cur > max)
                            max = cur;
                    }
                }
            }
            begin++;
        }
        map.put(sub, max);
        return max;
    }

    //322
    public int coinChange2(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        if(coins.length == 0)
            return -1;

        int[] nums = new int[amount + 1];
        Arrays.fill(nums, Integer.MAX_VALUE);

        nums[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int coin : coins) {
                if(i - coin >= 0 && nums[i - coin] != Integer.MAX_VALUE){
                    if(nums[i - coin] + 1 < nums[i])
                        nums[i] = nums[i - coin] + 1;
                }
            }
        }

        if(nums[amount] == Integer.MAX_VALUE)
            return -1;
        else
            return nums[amount];
    }

    //322
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        if(coins.length == 0)
            return -1;

        Arrays.sort(coins);

        Map<Integer ,Integer> map = new HashMap<>();

        int re = coinChange(coins, amount, map);
        if(re != Integer.MAX_VALUE)
            return re;
        else
            return -1;
    }

    private int coinChange(int[] coins, int amount, Map<Integer, Integer> map){
        if(amount == 0)
            return 0;
        if(map.containsKey(amount)){
            System.out.println("contain:  " + amount);
            return map.get(amount);
        }
        int re = Integer.MAX_VALUE;
        for (int coin : coins) {
            if(coin <= amount){
                int cur = coinChange(coins, amount - coin, map);
                if(cur != Integer.MAX_VALUE && cur + 1 < re){
                    re = cur + 1;
                }
            }
        }

        map.put(amount, re);
        return re;
    }

    //105
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0)
            return null;
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preS, int preE, int[] inorder, int inS, int inE){
        TreeNode treeNode = new TreeNode(preorder[preS]);
        for (int i = inS; i <= inE; i++) {
            if(inorder[i] == preorder[preS]){
                if(i != inS){
                    int leftNum = i - inS;
                    treeNode.left = buildTree(preorder, preS + 1, preS + leftNum, inorder, inS, i - 1);
                }
                if(i != inE){
                    int rightNum = inE - i;
                    treeNode.right = buildTree(preorder, preE - rightNum + 1, preE, inorder, i + 1, inE);
                }
            }
        }
        return treeNode;
    }

    //29
    public int divide(int dividend, int divisor) {
        assert divisor != 0;
        if (dividend == 0)
            return 0;
        if (divisor == 1)
            return dividend;
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            return -dividend;
        }

        int re = 0;
        boolean rePositive = true;
        if (Integer.signum(dividend) != Integer.signum(divisor)) {
            rePositive = false;
            dividend = -dividend;
        }
        while (Math.abs(dividend) >= Math.abs(divisor) || dividend == Integer.MIN_VALUE) {
            if (re <= Integer.MAX_VALUE - 1)
                re++;
            dividend -= divisor;
        }
        if (rePositive)
            return re;
        else if (re != Integer.MIN_VALUE)
            return -re;
        else
            return Integer.MAX_VALUE;
    }

    //887
    public int superEggDrop(int K, int N){
        if(N == 0)
            return 0;
        if(K == 1)
            return N;

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        return superEggDrop2(K, N, map);
    }

    private int superEggDrop2(int K, int N, Map<Integer, Map<Integer, Integer>> map) {
        if(N == 0)
            return 0;
        if(K == 1)
            return N;
        if(map.containsKey(K)){
            if(map.get(K).containsKey(N)){
                return map.get(K).get(N);
            }
        }

        int min = Integer.MAX_VALUE;
        int low = 1;
        int high = N;
        while(low <= high){
            int mid = (low + high) / 2;
            int broken = superEggDrop2(K - 1, mid - 1, map);
            int not_broken = superEggDrop2(K,N - mid, map);
            if(broken > not_broken){
                high = mid - 1;
                min = Math.min(min, broken + 1);
            }
            else {
                low = mid + 1;
                min = Math.min(min, not_broken + 1);
            }
        }

        if(map.containsKey(K)){
            map.get(K).put(N, min);
        }
        else{
            Map<Integer, Integer> map1 = new HashMap<>();
            map1.put(N, min);
            map.put(K, map1);
        }
        return min;
    }

    private int superEggDrop(int K, int N, Map<Integer, Map<Integer, Integer>> map) {
        if(N == 0)
            return 0;
        if(K == 1)
            return N;
        if(map.containsKey(K)){
            if(map.get(K).containsKey(N)){
                return map.get(K).get(N);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            min = Math.min(min, 1 + Math.max(superEggDrop(K,N - i, map), superEggDrop(K - 1, i - 1, map)));
        }

        if(map.containsKey(K)){
            map.get(K).put(N, min);
        }
        else{
            Map<Integer, Integer> map1 = new HashMap<>();
            map1.put(N, min);
            map.put(K, map1);
        }
        return min;
    }

    //72
    public int minDistance__3(String word1, String word2) {
        if(word1.length() > word2.length())
            return minDistance__3(word2, word1);

        if(word1.length() == 0)
            return word2.length();

        int[][] dp = new int[2][word1.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[1][i] = word1.length() - i;
        }

        for (int j = word2.length() - 1; j >= 0; j--) {
            dp[0][word1.length()] = word2.length() - j;
            for (int i = word1.length() - 1; i >= 0; i--) {
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[0][i] = dp[1][i + 1];
                }
                else{
                    int add = dp[1][i] + 1;
                    int delete = dp[0][i + 1] + 1;
                    int replace = dp[1][i + 1] + 1;
                    dp[0][i] = Math.min(Math.min(add, delete), replace);
                }
            }
            System.arraycopy(dp[0], 0, dp[1], 0, word1.length() + 1);
        }

        return dp[0][0];
    }

    //72
    public int minDistance__2(String word1, String word2) {
        if(word1.length() == 0)
            return word2.length();
        if(word2.length() == 0)
            return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word2.length(); i++) {
            dp[word1.length()][i] = word2.length() - i;
        }
        for (int i = 1; i < word1.length(); i++) {
            dp[i][word2.length()] = word1.length() - i;
        }
        dp[word1.length()][word2.length()] = 0;

        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i][j] = dp[i + 1][j + 1];
                }
                else{
                    int add = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;
                    int replace = dp[i + 1][j + 1] + 1;
                    dp[i][j] = Math.min(Math.min(add, delete), replace);
                }
            }
        }

        return dp[0][0];
    }

    //72
    public int minDistance__1(String word1, String word2) {
        Map<String, Integer> map = new HashMap<>();
        return minDistance__1(word1, 0, word2, 0, map);
    }

    private int minDistance__1(String word1, int i, String word2, int j, Map<String, Integer> map) {
        if(i == word1.length())
            return word2.length() - j;
        if(j == word2.length())
            return word1.length() - i;

        int re = Integer.MAX_VALUE;
        String keyStr = i + "," + j;
        if(map.containsKey(keyStr)){
            System.out.println(keyStr);
            return map.get(keyStr);
        }

        while(i < word1.length() && j < word2.length()){
            if(word1.charAt(i) == word2.charAt(j)){
                i++;
                j++;
            }
            else{
                int replace = 1 + minDistance__1(word1, i + 1, word2, j + 1, map);
                int delete = 1 + minDistance__1(word1, i, word2, j + 1, map);
                int add = 1 + minDistance__1(word1, i + 1, word2, j, map);
                re = Math.min(replace, Math.min(delete, add));
                map.put(keyStr, re);
                return re;
            }
        }

        if(i == word1.length())
            re = word2.length() - j;
        if(j == word2.length())
            re = word1.length() - i;
        map.put(keyStr, re);
        return re;
    }

    //72
    public int minDistance4(String word1, String word2) {
        if(word1.length() > word2.length())
            return minDistance4(word2, word1);
        if(word1.length() == 0)
            return word2.length();


        int[][] dp = new int[2][word1.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[0][i] = i;
        }
        dp[1][0] = 1;

        for (int index = 0; index < word2.length(); index++) {
            dp[1][0] = dp[0][0] + 1;
            for (int i = 1; i < word1.length() + 1; i++) {
                if(word1.charAt(i - 1) == word2.charAt(index))
                    dp[1][i] = dp[0][i - 1];
                else
                    dp[1][i] = Math.min(Math.min(dp[0][i] + 1, dp[1][i - 1] + 1), dp[0][i - 1] + 1);
            }
            System.arraycopy(dp[1], 0, dp[0], 0, word1.length() + 1);
        }
        return dp[1][word1.length()];
    }

    //72
    public int minDistance3(String word1, String word2) {
        if(word1.length() == 0)
            return word2.length();
        if(word2.length() == 0)
            return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
            }
        }
        return dp[word1.length()][word2.length()];
    }

    //72
    public int minDistance2(String word1, String word2) {
        if(word1.length() == 0)
            return word2.length();
        if(word2.length() == 0)
            return word1.length();

        int[][] dp = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                boolean flag = word1.charAt(i) == word2.charAt(j);
                if(i == 0 && j == 0){
                    if(flag)
                        dp[i][j] = 0;
                    else
                        dp[i][j] = 1;
                    continue;
                }
                if(i == 0) {
                    if(flag)
                        dp[i][j] = j;
                    else
                        dp[i][j] = dp[i][j - 1] + 1;
                    continue;
                }
                if(j == 0){
                    if(flag)
                        dp[i][j] = i;
                    else
                        dp[i][j] = dp[i - 1][j] + 1;
                    continue;
                }

                if(flag)
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
            }
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }

    //72
    //wrong key
    public int minDistance(String word1, String word2) {
        Map<Integer[], Integer> map = new HashMap<>();
        return minDistance(word1, word2, word1.length() - 1, word2.length() - 1, map);
    }

    private int minDistance(String word1, String word2, int i, int j, Map<Integer[], Integer> map){
        if(i == -1)
            return j + 1;
        if(j == -1)
            return i + 1;

        if(map.containsKey(new Integer[]{i, j})){
            System.out.println(i + "  " + j);
            return map.get(new Integer[]{i, j});
        }

        if(word1.charAt(i) == word2.charAt(j)){
            return minDistance(word1, word2, i - 1, j - 1, map);
        }
        else {
            int[] res = new int[3];
            res[0] = minDistance(word1, word2, i, j - 1, map) + 1;  //增加
            res[1] = minDistance(word1, word2, i - 1, j, map) + 1;  //删除
            res[2] = minDistance(word1, word2, i - 1, j - 1, map) + 1;  //替换
            int min = res[0];
            for (int k = 1; k < res.length; k++) {
                if(res[k] < min)
                    min = res[k];
            }
            map.put(new Integer[]{i, j}, min);
            return min;
        }
    }

    //99
    public void recoverTree2(TreeNode root) {
        if(root == null)
            return;
        flag = false;
        Map<String, TreeNode> map = new HashMap<>();
        recoverTree2(root, map);
        int temp = map.get("pre").val;
        map.get("pre").val = map.get("min").val;
        map.get("min").val = temp;
    }

    private void recoverTree2(TreeNode root, Map<String, TreeNode> map){
        if(root == null)
            return;
        recoverTree2(root.left, map);
        if(!flag){
            if(map.containsKey("pre")){
                if(map.get("pre").val > root.val)
                    flag = true;
                else{
                    map.replace("pre", root);
                }
            }
            else{
                map.put("pre", root);
            }
        }
        if(flag){
            if(map.containsKey("min")){
                if(map.get("min").val > root.val){
                    map.replace("min", root);
                }
            }
            else{
                map.put("min", root);
            }
        }
        recoverTree2(root.right, map);
    }

    //99
    public void recoverTree(TreeNode root) {
        if(root == null)
            return;
        List<TreeNode> list = new LinkedList<>();
        recoverTree(root, list);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i - 1).val > list.get(i).val){
                int minIndex = i;
                for (int j = i + 1; j < list.size(); j++) {
                    if(list.get(j).val < list.get(minIndex).val)
                        minIndex = j;
                }
                int temp = list.get(i - 1).val;
                list.get(i - 1).val = list.get(minIndex).val;
                list.get(minIndex).val = temp;
            }
        }
    }

    private void recoverTree(TreeNode root, List<TreeNode> list){
        if(root == null)
            return;
        recoverTree(root.left, list);
        list.add(root);
        recoverTree(root.right, list);
    }


    //124
    public int maxPathSum(TreeNode root){
        Map<TreeNode, Integer> map = new HashMap<>();
        return maxPathSum_Map(root, map);
    }

    private int maxPathSum_Map(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null)
            return Integer.MIN_VALUE;

        int[] re = new int[6];
        re[0] = root.val;
        re[1] = maxPathSum_side(root.left, map) + root.val;
        re[2] = maxPathSum_side(root.right, map) + root.val;
        re[3] = maxPathSum_side(root.left, map) + maxPathSum_side(root.right, map) + root.val;
        re[4] = maxPathSum_Map(root.left, map);
        re[5] = maxPathSum_Map(root.right, map);
        int max = Integer.MIN_VALUE;
        for (int i : re) {
            if(i > max)
                max = i;
        }
        return max;
    }

    private int maxPathSum_side(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null)
            return 0;
        if (map.containsKey(root)){
            return map.get(root);
        }

        int[] re = new int[3];
        re[0] = root.val;
        re[1] = root.val + maxPathSum_side(root.left, map);
        re[2] = root.val + maxPathSum_side(root.right, map);

        int max = Integer.MIN_VALUE;
        for (int i : re) {
            if(i > max)
                max = i;
        }

        map.put(root, max);

        return max;
    }
}
