 
                               
public class Program {

    public static void main(String[] args) throws InterruptedException {
        

        Thread egg = new Egg(10 , "egg");
        Thread hen = new Hen(10 , "hen");

        egg.start();
        hen.start();
 

    }

}