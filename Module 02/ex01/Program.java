import java.io.IOException;

public class Program { // it handle unicode and imogies...
    
    public static void main(String[] args) {
        
        if(args.length == 2)
        {
            TextSimilarity textSimilarity = new TextSimilarity();
            try {
                textSimilarity.getTextSimilarity(args);
            } catch (IOException e) {e.printStackTrace();}
        }
        else
        {
            System.out.println("enter inputA.txt inputB.txt");
        } 
    }
    
}
