/**
 * Thread2
 */
public class Thread2 implements Runnable {

    Object lock ;
    int count;

    public Thread2(Object lock , int count)
    {
        this.lock = lock;
        this.count = count;
    }

  
    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                synchronized (lock) {
                    System.out.println("Hen");
                    lock.notify();
                    lock.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}