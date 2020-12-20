package test;

import com.sun.source.doctree.TextTree;

import java.util.PriorityQueue;

public class InnerClass {

    private int i = 200;
    TestStaticInner testStaticInner = this.new TestStaticInner();

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();

        innerClass.print();
    }

    public void print(){

        final int index = 0;
        System.out.println(i);
        System.out.println(testStaticInner.i);
    }

    class TestStaticInner{
        private int i = 100;

        public void print(){
            System.out.println(InnerClass.this.i);
        }
    }
}
