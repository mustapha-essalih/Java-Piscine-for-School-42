/**
 * Thread1
 */
public class Thread1 implements Runnable {

    Object lock ;
    int count;
    public Thread1(Object lock , int count )
    {
        this.count = count;
        this.lock = lock;
    } 

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                synchronized (lock) {
                    System.out.println("Egg");
                    lock.wait();
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}