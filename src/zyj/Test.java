package zyj;

import domain.Good;
import test.InnerClass;
import utils.Create;
import utils.TreeNode;
import zyj.algorithm.Jz2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    long a = 1L;
    double d = 1d;
    float f = 1f;


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(findNumRotate(new int[]{1,2,3,4,5,6,7}, 7));

    }

    public static int findNumRotate(int[] array, int target){
        if(array == null || array.length == 0){
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        int mid;
        if(target > array[array.length - 1]){
            while(left <= right){
                mid = left + (right - left) / 2;
                if(array[mid] < target){
                    if(array[mid] <= array[array.length - 1]){
                        right = mid - 1;
                    }
                    else{
                        left = mid + 1;
                    }
                }
                else if(array[mid] == target){
                    return mid;
                }
                else{
                    right = mid - 1;
                }
            }
        }
        else if(target == array[array.length - 1]){
            return array.length - 1;
        }
        else{
            while(left <= right){
                mid = left + (right - left) / 2;
                if(array[mid] > target){
                    if(array[mid] <= array[array.length - 1]){
                        right = mid - 1;
                    }
                    else{
                        left = mid + 1;
                    }
                }
                else if(array[mid] == target){
                    return mid;
                }
                else{
                    right = mid - 1;
                }
            }
        }
        if(array[left] == target){
            return left;
        }
        else{
            return -1;
        }
    }

    public static int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    //快速幂
    public static int quickMod(int a, int b, int c){
        int re = 1;
        while(b > 0){
            if(b % 2 == 1){
                re = (re * a) % c;
            }
            b /= 2;
            a = (a * a) % c;
        }
        return re;
    }

    //TOP-K
    public static int[] topK(int[] array, int k){
        if(k <= 0){
            return new int[0];
        }
        if(k >= array.length){
            return array;
        }

        Queue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            queue.offer(array[i]);
        }

        for (int i = k; i < array.length; i++) {
            assert queue.peek() != null;
            if(queue.peek() < array[i]){
                queue.poll();
                queue.offer(array[i]);
            }
        }

        int[] re = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            assert queue.peek() != null;
            re[i] = queue.poll();
        }

        return re;
    }

    //752 doubleBFS
    public int openLock2(String[] deadends, String target){
        if(target.equals("0000")){
            return 0;
        }

        int count = 0;

        Set<String> visited = new HashSet<>();
        Collections.addAll(visited, deadends);
        if(visited.contains("0000")){
            return -1;
        }
        visited.add("0000");
        visited.add(target);

        Set<String> source = new HashSet<>();
        source.add("0000");

        Set<String> end = new HashSet<>();
        end.add(target);

        while(!source.isEmpty()){
            Set<String> temp = new HashSet<>();
            for(String s : source){
                for (int i = 0; i < 4; i++) {
                    String up = s.substring(0, i) + ((s.charAt(i) - '0' + 1) % 10) + s.substring(i + 1);
                    if(end.contains(up)){
                        return count + 1;
                    }
                    if(!visited.contains(up)){
                        temp.add(up);
                        visited.add(up);
                    }

                    String down = s.substring(0, i) + ((s.charAt(i) - '0' + 9) % 10) + s.substring(i + 1);
                    if(end.contains(down)){
                        return count + 1;
                    }
                    if(!visited.contains(down)){
                        temp.add(down);
                        visited.add(down);
                    }
                }
            }
            source = end;
            end = temp;
            count++;
        }
        return -1;
    }

    //752 BFS
    public int openLock(String[] deadends, String target) {
        if(target.equals("0000")){
            return 0;
        }

        int count = 0;

        Set<String> visited = new HashSet<>();
        Collections.addAll(visited, deadends);
        if(visited.contains("0000")){
            return -1;
        }
        visited.add("0000");

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                assert cur != null;
                if(cur.equals(target)){
                    return count;
                }
                for (int j = 0; j < 4; j++) {
                    String up = cur.substring(0, j) + ((cur.charAt(j) - '0' + 1) % 10) + cur.substring(j + 1);
                    if(!visited.contains(up)){
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = cur.substring(0, j) + ((cur.charAt(j) - '0' + 9) % 10) + cur.substring(j + 1);
                    if(!visited.contains(down)){
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            count++;
        }

        return -1;
    }

    public static void quickSort(int[] array){
        if(array.length <= 1){
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right){
        if(array.length == 0 || left >= right){
            return;
        }

        int l = left;
        int r = right;
        int temp = array[left];
        while(l < r){
            while(l < r){
                if(array[r] < temp){
                    array[l] = array[r];
                    break;
                }
                r--;
            }
            while(l < r){
                if(array[l] >= temp){
                    array[r] = array[l];
                    break;
                }
                l++;
            }
        }
        array[l] = temp;

        quickSort(array, left, l - 1);
        quickSort(array, l + 1, right);
    }

    public static void rotateArray(int[] array, int k){
        if(array.length == 0){
            return;
        }
        k = k % array.length;
        if(k == 0){
            return;
        }
        
        int count = 0;
        for (int i = 0; i < k; i++) {
            int pre = array[i];
            int start = (i + k) % array.length;
            while(start != i){
                int temp = array[start];
                array[start] = pre;
                pre = temp;
                count++;
                if(count == array.length){
                    return;
                }
                start = (start + k) % array.length;
            }
            array[i] = pre;
            count++;
        }
    }

    public static int findNum(int[] array, int target){
        if(array.length == 0){
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(array[mid] < target){
                left = mid + 1;
            }
            else if(array[mid] > target){
                right = mid - 1;
            }
            else{
                return mid;
            }
        }
        return -1;
    }

    public static int findFirstNum(int[] array, int target){
        if(array.length == 0){
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(array[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        if(left < array.length && array[left] == target){
            return left;
        }
        return -1;
    }

    public static int findLastNum(int[] array, int target){
        if(array.length == 0){
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(array[mid] > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        if(right >= 0 && array[right] == target){
            return right;
        }
        return -1;
    }

    public static void pre_order(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                System.out.println(root.val);
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                TreeNode cur = stack.pop();
                root = cur.right;
            }
        }
    }

    public static void mid_order(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                TreeNode cur = stack.pop();
                System.out.println(cur.val);
                root = cur.right;
            }
        }
    }

    public static void back_order(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Boolean> flagStack = new Stack<>();

        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                flagStack.push(true);
                root = root.left;
            }
            if(!stack.isEmpty()){
                TreeNode cur = stack.peek();
                if(flagStack.peek()){
                    flagStack.pop();
                    flagStack.push(false);
                    root = cur.right;
                }
                else{
                    flagStack.pop();
                    cur = stack.pop();
                    System.out.println(cur.val);
                }
            }
        }
    }





}
