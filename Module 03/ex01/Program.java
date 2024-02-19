import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        
         
        if(args.length == 1)
        {
            if(args[0].startsWith("--count="))
            {
                try 
                {
                    int count = Integer.parseInt(args[0].substring(8));
                    if(count > 9)
                    {     
                        Object lock = new Object();// shared between threads
        
                        Thread t1 = new Thread(new Thread1(lock , count));
                        Thread t2 = new Thread(new Thread2(lock , count));

                        t1.start();
                        t2.start();

                        t1.join();
                        t2.join();

                    }
                    else
                    {
                        System.out.println("enter cout greater then 9.");
                    }
                    
                } catch (Exception e) {
                
                    System.out.println(e);
                }
            }            

        }
        else
        {
            System.out.println("should pass one argument like --count=10");
        }
    }
}



