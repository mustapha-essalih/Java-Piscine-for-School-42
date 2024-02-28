import java.io.IOException;

/**
 * Program
 */
public class Program {

    public static void main(String[] args)  
    {
        SignatureAnalysis signatureAnalysis = new SignatureAnalysis();

        try {
            signatureAnalysis.signatureHandling();
        } catch (IOException e) {e.printStackTrace();}

    }
}
