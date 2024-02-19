import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * SumArrayElements
 */
public class SumArrayElements extends Thread {

    private int start;
    private int end;
    private List<Integer> arrayElements;
    public static int totalSum;
    private String threadName;
    private Semaphore semaphore;
    private static int x;

    public SumArrayElements(Integer start, Integer end , List<Integer> arrayElements , Semaphore semaphore , String threadName) {
        this.start = start; 
        this.end = end;
        this.arrayElements = arrayElements;
        this.threadName = threadName;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int localSum = 0;
        int temp = 0;
        for (int i = start; i <= end; i++) {
            temp += arrayElements.get(x);
            localSum = temp;
            x++;
        }
        // Thread 1: from 0 to 4 sum is 5
        totalSum += localSum;
        System.out.println(threadName + ": from " + start + " to " + end + " sum is " + temp);
        temp = 0;
        semaphore.release();
    }   
}