package comitheima.Utils;

public class CurrentHolder {
    private static final ThreadLocal<Integer> tl = new ThreadLocal<>();
    public static void setCurrentId(Integer Id){
        tl.set(Id);
    }
    public static Integer getCurrentId(){
        return tl.get();
    }
    public static void remove(){
        tl.remove();
    }
}
