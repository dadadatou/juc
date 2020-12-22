package basic;

import java.util.concurrent.*;

/**
 * @Description: 四种创建方式
 * @Date: 2020/12/22
 * @Auther: luoyan
 */
public class T01_HowToCreateThread {

    /**
     *
     */
    private static class T1 extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 extends Thread");
        }
    }

    private static class T2 implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 implements Runnable");
        }
    }

    static class T3 implements Callable{
        @Override
        public Object call() throws Exception {
            System.out.println("T3 implements Callable");
            return null;
        }
    }

    static class T4{
        ExecutorService service = Executors.newSingleThreadExecutor();
        void run(){
            service.submit(new Runnable() {
               @Override
                public void run() {
                    System.out.println("T4 :Executors.newSingleThreadExecutor");
                }
            });
            service.shutdown();
        }
    }
    public static void main(String[] args) {

        new T1().start();

        new Thread(new T2()).start();

        new FutureTask(new T3()).run();

        new T4().run();
    }
}
