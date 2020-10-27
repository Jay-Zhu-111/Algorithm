package utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MinHeap {

    List<Integer> list = new LinkedList<>();

    @Override
    public String toString() {
        return "utils.MinHeap{" +
                "list=" + list +
                '}';
    }

    public MinHeap(){

    }

    public MinHeap(int[] ints) {
        for (int i : ints) {
            list.add(i);
        }
        if(list.size() > 1)
            for (int i = father(list.size() - 1); i >= 0; i--) {
                heapify(i);
            }
    }

    private void heapify(int position) {
        int min = position;
        //找较小的孩子
        if (leftChild(position) != -1 && list.get(leftChild(position)) < list.get(min)) {
            min = leftChild(position);
        }
        if (rightChild(position) != -1 && list.get(rightChild(position)) < list.get(min)) {
            min = rightChild(position);
        }
        //有较小的孩子则交换，并对其heapify
        if (min != position) {
            swap(min, position);
            heapify(min);
        }
    }

    private void swap(int p1, int p2) {
        int temp = list.get(p1);
        list.set(p1, list.get(p2));
        list.set(p2, temp);
    }

    private int father(int position) {
        return (position + 1) / 2 - 1;
    }

    private int leftChild(int position) {
        int re = (position + 1) * 2 - 1;
        if (re < list.size()) {
            return re;
        }
        return -1;
    }

    private int rightChild(int position) {
        int re = (position + 1) * 2;
        if (re < list.size())
            return re;
        return -1;
    }

    public int getMin() {
        if(list.size() != 0){
            int re = list.get(0);
            list.set(0, list.remove(list.size() - 1));
            heapify(0);
            return re;
        }
        else
            throw new ArrayIndexOutOfBoundsException("The heap is empty.");
    }

    public void add(int num){
        list.add(num);
        int index = list.size() - 1;
        while(index != 0){
            if(list.get(father(index)) > list.get(index)){
                swap(index, father(index));
            }
            else
                break;
            index = father(index);
        }
    }
}
