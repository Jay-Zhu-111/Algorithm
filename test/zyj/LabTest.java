package zyj;

import org.junit.Test;
import zyj.algorithm.Labu;

public class LabTest {

    Labu labu = new Labu();

    @Test
    public void pickPile() {
        System.out.println(labu.pickPile(new int[]{1, 100, 3}));
    }
}