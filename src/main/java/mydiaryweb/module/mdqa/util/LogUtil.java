package mydiaryweb.module.mdqa.util;


import java.util.HashMap;
import java.util.Map;

public class LogUtil {

    private String name;

    private static Map<String, LogUtil> instances = new HashMap<String, LogUtil>();

    private LogUtil(String name) {
        this.name = name;
    }

    public static LogUtil getLogger(String name) {

        LogUtil l = instances.get(name);
        if (l == null){
            l = new LogUtil(name);
            instances.put(name, l);
        }

        return l;
    }

    public void fatal(Object message) {
        System.err.println(name + ": " + message);
        System.err.flush();
    }

    public void fatal(Object message, Throwable t) {
        concern(message, t);
    }

    public void error(Object message) {
        System.err.println(name + ": " + message);
        System.err.flush();
    }

    public void error(Object message, Throwable t) {
        concern(message, t);
    }

    protected void concern(Object message, Throwable t){
        System.err.println(name + ": " + message);
        t.printStackTrace();
        System.err.flush();
    }

    public void warn(Object message) {
        System.err.println(name + ": " + message);
        System.err.flush();
    }

    public void warn(Object message, Throwable t) {
        System.err.println(name + ": " + message);
        t.printStackTrace();
        System.err.flush();
    }

    public void info(Object message) {
        System.out.println(name + ": " + message);
        System.out.flush();
    }

    public void debug(Object message) {
        System.out.println(name + ": " + message);
        System.out.flush();
    }

    public void trace(Object message) {
        System.out.println(name + ": " + message);
        System.out.flush();
    }

    public static LogUtil def(){
        return getLogger("QAS");
    }
    public static void d(Object message){
        def().debug(message);
    }
}
