package com.infamous.logging;

/**
 * Created by infamouSs on 8/4/18.
 */
public class SystemOutPrinter implements Printer {
    
    private final static String[] LEVELS = new String[]{"V", "D", "I", "W", "E"};
    
    public void print(int level, String tag, String msg) {
        System.out.println(LEVELS[level] + "/" + tag + ": " + msg);
    }
}
