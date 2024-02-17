/**
 * Egg
 */
public class Egg extends Thread {

    private int count; 
    private String name;
    
    public Egg(int count , String name){
        this.count = count;
        this.name = name;
    }


    @Override
    public  void run() {
        for (int i = 0; i < 5; i++) {
                synchronized (this) {
                
                    System.out.println("egg");
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                notify();
            
            }
            }
    }
}