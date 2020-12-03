package zyj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) throws IOException {
        String[] array = new String[3];
        array[0] = "bab";
        array[1] = "aab";
        array[2] = "cab";
        Arrays.sort(array, (o1, o2) -> o1.charAt(0) - o2.charAt(0));
        System.out.println(Arrays.toString(array));
    }
}
