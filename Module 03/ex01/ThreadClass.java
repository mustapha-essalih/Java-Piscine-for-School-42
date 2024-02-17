public class ThreadClass extends Thread {

    private String name;
    private int count ; 

    ThreadClass(String name , int count)
    {
        this.name = name;
        this.count = count;
    }


    
    @Override
    public synchronized void run()
    {
        for(int i = 0; i < count ; i++)
            System.out.println(this.name);
    }

    
}