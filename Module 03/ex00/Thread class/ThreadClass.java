public class ThreadClass extends Thread {

    private String name;
    private int count ; 

    ThreadClass(String name , int count)
    {

        this.name = name;
        this.count = count;
    }


    
    @Override
    public void run()
    {
        for(int i = 0; i < count ; i++)
            System.out.println("#thread " +  this.name + " in Running state");
    }

    
}