package zyj;

import domain.Good;
import utils.Create;
import utils.TreeNode;

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

    public static void main(String[] args) throws IOException, InterruptedException {
        String s = "0000";
        System.out.println(-1 % 4);
    }

    //752 BFS
    public int openLock(String[] deadends, String target) {
        if(target.equals("0000")){
            return 0;
        }

        int count = 0;

        Set<String> visited = new HashSet<>();
        visited.add("0000");
        Collections.addAll(visited, deadends);

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
                    String up = cur.substring(0, j) + (cur.charAt(j) + 1) % 4 + cur.substring(j + 1);
                    if(!visited.contains(up)){
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = cur.substring(0, j) + (cur.charAt(j) + 3) % 4 + cur.substring(j + 1);
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
