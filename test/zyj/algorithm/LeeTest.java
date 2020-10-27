package zyj.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class LeeTest {

    Lee lee = new Lee();

    @Test
    public void stoneGameII() {
        System.out.println(lee.stoneGameII(new int[]{2,7,9,4,4}));
    }

    @Test
    public void stoneGameV() {
        System.out.println(lee.stoneGameV(new int[]{6,2,3,4,5,5}));
    }

    @Test
    public void winnerSquareGame() {
    }

    @Test
    public void stoneGameIII2() {
    }

    @Test
    public void stoneGameIII() {
    }

    @Test
    public void stoneGame2() {
    }

    @Test
    public void stoneGame() {
    }

    @Test
    public void findMinArrowShots() {
    }

    @Test
    public void eraseOverlapIntervals() {
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
    public void maxProfit_2_1() {
    }

    @Test
    public void maxProfit_1_3() {
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
    public void lengthOfLIS() {
        lee.lengthOfLIS(new int[]{1,2,3,4,5,6});
    }

    @Test
    public void isMatch() {
    }

    @Test
    public void maxEnvelopes() {
//        System.out.println(lee.maxEnvelopes());
    }

    @Test
    public void lengthOfLIS2() {
    }

    @Test
    public void minDistance__1() {
        System.out.println(lee.minDistance__1("horse", "ros"));
    }

    @Test
    public void minDistance__2() {
//        System.out.println(lee.minDistance__2("horse", "ros"));
        int[][] dp = new int[2][3];
        for (int i = 0; i < 3; i++) {
            dp[1][i] = 666;
        }
        dp[0] = dp[1];
        dp[0][0] = 777;
        System.out.println(Arrays.toString(dp));
    }

    @Test
    public void minDistance__3() {
        System.out.println(lee.minDistance__3("horse", "ros"));
    }

    @Test
    public void lengthOfLIS3() {
        System.out.println(lee.lengthOfLIS3(new int[]{1,3,6,7,9,4,10,5,6}));
    }

    @Test
    public void solveNQueens() {
        System.out.println(lee.solveNQueens(4));
    }

    @Test
    public void solveNQueens2() {
        System.out.println(lee.solveNQueens2(8));
    }

    @Test
    public void openLock() {
        System.out.println(lee.openLock(new String[]{}, "0012"));
    }

    @Test
    public void minDepth2() {
    }

    @Test
    public void minDepth() {
    }

    @Test
    public void openLock3() {
        /**
         * ["0201","0101","0102","1212","2002"]
         * "0202"
         */
        System.out.println(lee.openLock3(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
    }

    @Test
    public void openLock2() {
    }
}