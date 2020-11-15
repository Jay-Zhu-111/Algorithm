package zyj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) throws IOException {
        int num = 8;
        num %= 3;
        System.out.println(num);
    }
}
