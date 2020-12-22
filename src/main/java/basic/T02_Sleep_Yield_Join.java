package basic;

/**
 * @Description:
 *  Thread.yield 从运行状态到就绪状态，让出CPU，但是还是会参与争抢；不会释放锁
 *  Join：主线程加入thread1.join 主线程等待thread1执行完，
 *      如果thread1和thread2争抢锁，主线程中调用thread1.join，不会释放thread1的锁；
 *      如果thread1和主线程争抢锁，主线程中调用thread1.join，会释放thread1的锁，但主线程得到锁后join thread1时会造成死锁；
 *  Sleep ：不会释放锁！！！！
 * @Date: 2020/12/22
 * @Auther: luoyan
 */
public class T02_Sleep_Yield_Join {

    public static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        testYield();
//        testJoin();
    }

    private static void testJoin() throws InterruptedException {
       Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sync1();
            }
        }, "mythread1111111111111111111111111");
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                sync1();
//            }
//        }, "thread2");
//        thread2.start();
        thread1.start();

        for (int i = 0; i < 1000; i++) {

        }
        synchronized (o){
            System.out.println("进了主线程");
            thread1.join();
            System.out.println("主线程运行");
        }
    }

    private static void testYield() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("thread1前");
                sync();
//                System.out.println("thread1后");
            }
       },"name:hread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2");
            }
        },"name:thread2");
        thread1.start();
        thread2.start();
    }


    static void sync1(){
        synchronized (o){
            for (int i = 0; i < 1000; i++) {
                System.out.println(i+"---"+Thread.currentThread().getName());
            }
        }
    }

    static void sync(){
        synchronized (o){
            for (int i = 0; i < 1000; i++) {
                if(i%5==0){
//                    Thread.currentThread().yield();
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i+"---"+Thread.currentThread().getName());
            }
        }
    }
}
