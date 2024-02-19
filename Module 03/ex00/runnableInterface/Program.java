 

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
                        EggThread egg_ = new EggThread("egg" , count );
                        HenThread hen_ = new HenThread("hen" , count);
                        HumanThread human_ = new HumanThread("human" , count);

                        Thread egg = new Thread(egg_); // should pass to it Runnable interface

                        Thread hen = new Thread(hen_);

                        Thread human = new Thread(human_);

                        egg.start();
                        hen.start();

                        egg.join();//pause the current thread execution (main thread) until finished
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


// after finish printing egd and hen , print human means stop execute main thread intel the two thread finish these work. 