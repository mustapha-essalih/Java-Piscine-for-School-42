import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

 
/**
 * Program
 */
public class Program {

    private static int arraySize;
    private static int threadsCount;
    private static List<Integer> starts = new ArrayList<Integer>();
    private static List<Integer> ends = new ArrayList<Integer>();
    public static void main(String[] args) throws InterruptedException {

        //  --arraySize=13 --threadsCount=3

        if (args.length == 2) {
            
            if (checkArgs(args)) 
            {    
                List<Integer> arrayElements = generateArrayElements(arraySize);
                getStartAndEnd();
                
                Thread[] threads = new Thread[threadsCount];
                
                Semaphore semaphore = new Semaphore(1);
                
                for (int i = 0; i < threadsCount; i++) 
                {
                    threads[i] = new SumArrayElements(starts.get(i), ends.get(i) , arrayElements , semaphore , "Thread " + (i + 1));
                    threads[i].start();    
                }
                for (Thread thread : threads) {
                    thread.join();
                }
                System.out.println("Sum by threads: " + SumArrayElements.totalSum);
 
            }
            else
            {
                System.out.println("error in args");
            } 
        }
        else{
            System.out.println("should enter like: --arraySize=13 --threadsCount=3");
        }


    }

    private static boolean checkArgs(String[] args) {
        
        if(!args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")){
            return false;
        }

        String strArraySize = args[0].substring(args[0].indexOf("=") + 1) ;
        String strThreadsCount = args[1].substring(args[1].indexOf("=") + 1) ;
        
        try {
            arraySize = Integer.parseInt(strArraySize);
            threadsCount = Integer.parseInt(strThreadsCount);
            if( threadsCount < 3 ||  threadsCount >  arraySize)
            {
                return false;
            }

            if( arraySize > 2000000)
            {
                return false; 
            }
                
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    static List<Integer> generateArrayElements( Integer arraySize)
    {
        List<Integer> list = new ArrayList<>();
        int elementsSum = 0;
        
        for(int i = 0 ; i <  arraySize ; i++)
        {
            int min = 1;  
            int max = 1000;  

            int randomNumber = (int)Math.floor(Math.random() * (max - min + 1) + min);
            elementsSum += randomNumber;
            list.add(randomNumber);
        }
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // list.add(1);
        // System.out.println("Sum: " + 13); 
        System.out.println("Sum: " + elementsSum); 
        return list;
    }

    private static void getStartAndEnd()
    {
        int mod = arraySize / threadsCount;
         
        int t = 0;
        int j = 0;

        for (int i = 0; i < arraySize; i++) {
            
            if(j == mod) // 4
            {
                starts.add(t); // 4
                while (t <= i) { //
                    t++;
                }
                ends.add(t - 1);
                j = -1;
            }
            j++;
        }
        
        starts.add(t);
        while (t <= arraySize - 1) {
            t++;
        }
        ends.add(t - 1);
    }
    
}