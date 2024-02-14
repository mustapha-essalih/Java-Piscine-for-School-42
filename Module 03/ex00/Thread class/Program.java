/**
 * Program
 */
public class Program {

    public static void main(String[] args) {
        
        if(args.length == 1)
        {
            if(args[0].startsWith("--count="))
            {
                try 
                {
                    int count = Integer.parseInt(args[0].substring(8));
                    if(count > 9)
                    {     
                        ThreadClass egg = new ThreadClass("egg" , count );
                        ThreadClass hen = new ThreadClass("hen" , count);
                        ThreadClass human = new ThreadClass("human" , count);
 

                        egg.start();
                        hen.start();

                        egg.join();//pause the current thread execution (main thread) until finished eg thread
                        hen.join();

                        human.start();

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