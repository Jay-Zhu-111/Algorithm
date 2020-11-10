package zyj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) throws IOException {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2,2);
        map.put(3,3);
        map.put(1, 1);
        map.put(1, 2);
        System.out.println(map);
        System.out.println(3 & 17);
    }
}
