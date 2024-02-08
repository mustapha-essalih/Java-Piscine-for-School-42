import java.io.FileInputStream;
import java.io.IOException;

/**
 * Program
 */
public class Program {

    public static void main(String[] args) {
        
        try (FileInputStream fileInputStream = new FileInputStream("file")) {
            int byteRead;
            while ((byteRead = fileInputStream.read()) != -1) {
                System.out.print(byteRead + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}