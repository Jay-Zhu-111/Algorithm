package zyj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) throws IOException {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");

        list.removeIf("1"::equals);

        System.out.println(list);
    }
}
