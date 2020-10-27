package zyj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) throws IOException {
        int[] dp = new int[200];
        dp['a'] = 11;
        System.out.println(dp['a']);

        KMP kmp = new KMP("ababc");
        System.out.println(kmp.search("aaaatabaccababababctt"));
    }
}
