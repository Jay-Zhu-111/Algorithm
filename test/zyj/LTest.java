package zyj;

import org.junit.Test;
import zyj.algorithm.Lee;

import java.util.Arrays;
import java.util.Comparator;

public class LTest {

    private Lee lee = new Lee();

    @Test
    public void maxProfit_2_1() {
    }

    @Test
    public void maxProfit_1_2() {
    }

    @Test
    public void maxProfit_1_1() {
    }

    @Test
    public void intersect2() {
    }

    @Test
    public void intersect() {
    }

    @Test
    public void isPowerOfThree() {
    }

    @Test
    public void missingNumber2() {
    }

    @Test
    public void maxProfit() {

    }

    @Test
    public void missingNumber() {
    }

    @Test
    public void isPalindrome2() {
    }

    @Test
    public void isPalindrome() {
    }

    @Test
    public void containsNearbyAlmostDuplicate2() {
    }

    @Test
    public void containsNearbyAlmostDuplicate() {
    }

    @Test
    public void containsNearbyDuplicate3() {
    }

    @Test
    public void containsNearbyDuplicate2() {
    }

    @Test
    public void containsNearbyDuplicate() {
    }

    @Test
    public void containsDuplicate() {
    }

    @Test
    public void containsDuplicate2() {
    }

    @Test
    public void quickSort() {
    }

    @Test
    public void countPrimes() {
    }

    @Test
    public void countPrimes2() {
    }

    @Test
    public void rob() {
    }

    @Test
    public void rob_2_1() {
    }

    @Test
    public void rob3() {
    }

    @Test
    public void testRob() {
    }

    @Test
    public void rob2() {
    }

    @Test
    public void reverseBits() {
    }

    @Test
    public void rotate() {
    }

    @Test
    public void rotate2() {
    }

    @Test
    public void rotate3() {
    }

    @Test
    public void trailingZeroes() {
    }

    @Test
    public void testPQ() {
    }

    @Test
    public void getIntersectionNode() {
    }

    @Test
    public void getIntersectionNode2() {
    }

    @Test
    public void hasCycle() {
    }

    @Test
    public void hasCycle2() {
    }

    @Test
    public void testIsPalindrome() {
    }

    @Test
    public void merge_2() {
    }

    @Test
    public void merge() {
    }

    @Test
    public void climbStairs() {
    }

    @Test
    public void climbStairs_helper() {
    }

    @Test
    public void isOverflow_mul() {
    }

    @Test
    public void mySqrt() {
    }

    @Test
    public void crossSum() {
    }

    @Test
    public void helper() {
    }

    @Test
    public void maxSubArray() {
    }

    @Test
    public void maxSubArray_1() {
    }

    @Test
    public void countAndSay() {
    }

    @Test
    public void removeDuplicates() {
    }

    @Test
    public void isValid() {
    }

    @Test
    public void longestCommonPrefix_2() {
    }

    @Test
    public void LCP() {
    }

    @Test
    public void longestCommonPrefix() {
    }

    @Test
    public void reverse_2() {
    }

    @Test
    public void reverse() {
    }

    @Test
    public void isHappy() {
    }

    @Test
    public void moveZeroes() {
    }

    @Test
    public void isAnagram() {
    }

    @Test
    public void change2() {
    }

    @Test
    public void change() {
    }

    @Test
    public void canPartition() {
    }

    @Test
    public void fractionToDecimal() {
    }

    @Test
    public void numDecodings() {
    }

    @Test
    public void myAtoi() {
    }

    @Test
    public void longestPalindromeSubseq3() {
    }

    @Test
    public void longestPalindromeSubseq() {
    }

    @Test
    public void coinChange2() {
    }

    @Test
    public void coinChange() {
    }

    @Test
    public void buildTree() {
    }

    @Test
    public void divide() {
    }

    @Test
    public void superEggDrop() {
    }

    @Test
    public void minDistance4() {
    }

    @Test
    public void minDistance3() {
    }

    @Test
    public void minDistance2() {
    }

    @Test
    public void minDistance() {
    }

    @Test
    public void recoverTree2() {
    }

    @Test
    public void recoverTree() {
    }

    @Test
    public void maxPathSum() {
    }

    @Test
    public void maxProfit_6_2() {
    }

    @Test
    public void maxProfit_6_1() {
    }

    @Test
    public void maxProfit_5_1() {
    }

    @Test
    public void maxProfit_4_3() {
        System.out.println(lee.maxProfit_4_3(2, new int[]{3,3,5,0,0,3,1,4}));
    }

    @Test
    public void maxProfit_4_2() {

    }

    @Test
    public void maxProfit_4_1() {
    }

    @Test
    public void maxProfit_3_1() {
    }

    @Test
    public void maxProfit_2_2() {
    }

    @Test
    public void maxProfit_1_3() {
    }

    @Test
    public void eraseOverlapIntervals() {
        int[] ints = new int[]{2, 3, 4};
        int[] ints1 = new int[]{1, 1, 1};
        ints = ints1;
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void findMinArrowShots() {

        int[][] points = new int[][]{{-2147483646,-2147483645}, {2147483646,2147483647}};
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        System.out.println(Arrays.toString(points[0]) + "\n" + Arrays.toString(points[1]));

        Integer[] ints = new Integer[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
        Arrays.sort(ints, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(ints));
    }
}