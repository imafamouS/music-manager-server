package com.infamous.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by infamouSs on 8/4/18.
 */
public class Log {
    
    public final static int V = 0;
    public final static int D = 1;
    public final static int I = 2;
    public final static int W = 3;
    public final static int E = 4;
    
    public final static SystemOutPrinter SYSTEM = new SystemOutPrinter();
    
    private final static Map<String, String> mTags = new HashMap<>();
    
    private static String[] mUseTags = new String[]{"tag", "TAG"};
    private static boolean mUseFormat = false;
    private static int mMinLevel = V;
    
    private static Set<Printer> mPrinters = new HashSet<>();
    
    final static int MAX_LOG_LINE_LENGTH = 4000;
    
    private final static Pattern ANONYMOUS_CLASS = Pattern.compile("\\$\\d+$");
    private final static int STACK_DEPTH = 4;
    
    private Log() {
    }
    
    
    
    static {
        usePrinter(SYSTEM, true);
    }
    
    public static synchronized Log useTags(String[] tags) {
        mUseTags = tags;
        return null;
    }
    
    public static synchronized Log level(int minLevel) {
        mMinLevel = minLevel;
        return null;
    }
    
    public static synchronized Log useFormat(boolean yes) {
        mUseFormat = yes;
        return null;
    }
    
    public static synchronized Log usePrinter(Printer p, boolean on) {
        if (on) {
            mPrinters.add(p);
        } else {
            mPrinters.remove(p);
        }
        return null;
    }
    
    public static synchronized Log v(Object msg, Object... args) {
        log(V, mUseFormat, msg, args);
        return null;
    }
    
    public static synchronized Log d(Object msg, Object... args) {
        log(D, mUseFormat, msg, args);
        return null;
    }
    
    public static synchronized Log i(Object msg, Object... args) {
        log(I, mUseFormat, msg, args);
        return null;
    }
    
    public static synchronized Log w(Object msg, Object... args) {
        log(W, mUseFormat, msg, args);
        return null;
    }
    
    public static synchronized Log e(Object msg, Object... args) {
        log(E, mUseFormat, msg, args);
        return null;
    }
    
    private static void log(int level, boolean fmt, Object msg, Object... args) {
        if (level < mMinLevel) {
            return;
        }
        String tag = tag();
        if (mUseTags.length > 0 && tag.equals(msg)) {
            if (args.length > 1) {
                print(level, tag, format(fmt, args[0], Arrays.copyOfRange(args, 1, args.length)));
            } else {
                print(level, tag, format(fmt, (args.length > 0 ? args[0] : "")));
            }
        } else {
            print(level, tag, format(fmt, msg, args));
        }
    }
    
    private static String format(boolean fmt, Object msg, Object... args) {
        Throwable t = null;
        if (args == null) {
            args = new Object[]{null};
        }
        if (args.length > 0 && args[args.length - 1] instanceof Throwable) {
            t = (Throwable) args[args.length - 1];
            args = Arrays.copyOfRange(args, 0, args.length - 1);
        }
        if (fmt && msg instanceof String) {
            String head = (String) msg;
            if (head.indexOf('%') != -1) {
                return String.format(head, args);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(msg == null ? "null" : msg.toString());
        for (Object arg : args) {
            sb.append("\t");
            sb.append(arg == null ? "null" : arg.toString());
        }
        if (t != null) {
            sb.append("\n");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            sb.append(sw.toString());
        }
        return sb.toString();
    }
    
    
    private static void print(int level, String tag, String msg) {
        for (String line : msg.split("\\n")) {
            do {
                int splitPos = Math.min(MAX_LOG_LINE_LENGTH, line.length());
                for (int i = splitPos - 1; line.length() > MAX_LOG_LINE_LENGTH && i >= 0; i--) {
                    if (" \t,.;:?!{}()[]/\\".indexOf(line.charAt(i)) != -1) {
                        splitPos = i;
                        break;
                    }
                }
                splitPos = Math.min(splitPos + 1, line.length());
                String part = line.substring(0, splitPos);
                line = line.substring(splitPos);
                
                for (Printer p : mPrinters) {
                    p.print(level, tag, part);
                }
            } while (line.length() > 0);
        }
    }
    
    
    private static String tag() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length < STACK_DEPTH) {
            throw new IllegalStateException
                      ("Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        String className = stackTrace[STACK_DEPTH - 1].getClassName();
        String tag = mTags.get(className);
        if (tag != null) {
            return tag;
        }
        
        try {
            Class<?> c = Class.forName(className);
            for (String f : mUseTags) {
                try {
                    Field field = c.getDeclaredField(f);
                    if (field != null) {
                        field.setAccessible(true);
                        Object value = field.get(null);
                        if (value instanceof String) {
                            mTags.put(className, (String) value);
                            return (String) value;
                        }
                    }
                } catch (NoSuchFieldException | IllegalAccessException |
                          IllegalStateException | NullPointerException e) {
                }
            }
        } catch (ClassNotFoundException e) { /* Ignore */ }
        
        Matcher m = ANONYMOUS_CLASS.matcher(className);
        if (m.find()) {
            className = m.replaceAll("");
        }
        return className.substring(className.lastIndexOf('.') + 1);
    }
}
