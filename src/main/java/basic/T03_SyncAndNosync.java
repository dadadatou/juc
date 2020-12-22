package basic;

/**
 * @Description:同步和非同步方法是否可以同时调用？ 可以
 * @Date: 2020/12/22
 * @Auther: luoyan
 */
public class T03_SyncAndNosync {

    public static void main(String[] args) {
        final T03_SyncAndNosync c = new T03_SyncAndNosync();

        new Thread(new Runnable() {
            @Override
            public void run() {
                c.sync();
            }
        },"Thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                c.nosync();
            }
        },"Thread2").start();

    }

    synchronized void sync(){
        try {
            System.out.println(Thread.currentThread().getName() + " start ");
            Thread.currentThread().sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void nosync(){
        System.out.println(Thread.currentThread().getName() + " start ");
        System.out.println(Thread.currentThread().getName() + " end ");
    }
}
