package zyj;

import org.junit.jupiter.api.Test;
import utils.MinHeap;

public class minHeapTest {

    @Test
    public void getMin() {
        int[] ints = new int[]{7, 29, 32, 2, 6, 43, 3, 7, 8, 5, 49};
        MinHeap minHeap = new MinHeap(ints);
        System.out.println(minHeap);
        System.out.println(minHeap.getMin());
        System.out.println(minHeap);
        minHeap.add(2);
        System.out.println(minHeap);
    }

    @Test
    public void add() {
    }
}