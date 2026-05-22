package comitheima;

public class ThreadLocalTest {
    private static ThreadLocal<String> local = new ThreadLocal<>();
    public static void main(String[] args) {
        local.set("Main Message");//向当前线程的局部变量存值


        new Thread(new Runnable() {
            @Override
            public void run() {
                local.set("Thread Message");
                System.out.println(Thread.currentThread().getName() + ":" + local.get());
            }
        }).start();

        System.out.println(Thread.currentThread().getName() + ":" + local.get());

        local.remove();//删除当前线程的局部变量的值


    }
    //每个线程存的只能每个线程取、删
}
