package zyj.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Labu {

    //find right target
    public int binarySearch_right(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int cur = nums[mid];
            if(cur <= target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        if(right < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }

    //find left target
    public int binarySearch_left2(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int cur = nums[mid];
            if(cur >= target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        if(left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    //find left target
    public int binarySearch_left(int[] nums, int target){
        if(nums.length == 0)
            return -1;
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            int cur = nums[mid];
            if(cur == target){
                right = mid;
            }
            else if(cur > target){
                right = mid - 1;
            }
            else if(cur < target){
                left = mid + 1;
            }
        }
        return nums[left] == target? left : -1;
    }

    public int binarySearch(int[] nums, int target) {
        if(nums.length == 0)
            return -1;
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int cur = nums[mid];
            if(cur == target){
                return mid;
            }
            else if(cur > target){
                right = mid - 1;
            }
            else if(cur < target){
                left = mid + 1;
            }
        }
        return -1;
    }

    public int fourKey_keyboard(int n){
        if(n == 0)
            return 0;
        Map<String, Integer> map = new HashMap<>();
        return fourKey_keyboard(n, 0, 0, map);
    }

    private int fourKey_keyboard(int n, int num, int copy, Map<String, Integer> map){
        if(n == 0)
            return num;
        if(n < 0)
            return Integer.MIN_VALUE;

        String keyStr = n + "," + num + "," + copy;
        if(map.containsKey(keyStr))
            return map.get(keyStr);

        int print = fourKey_keyboard(n - 1, num + 1, copy, map);
        int selectAndCopy = fourKey_keyboard(n - 2, num, num, map);
        int paste = fourKey_keyboard(n - 1, num + copy, copy, map);

        int re = Math.max(Math.max(print, selectAndCopy), paste);
        map.put(keyStr, re);
        return re;
    }

    public int pickPile(int[] piles){
        if(piles.length == 0)
            return 0;

        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }

        for (int i = piles.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < piles.length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][piles.length - 1];
    }
}
