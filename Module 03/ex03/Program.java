
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Program
 */
public class Program {

    public static void main(String[] args) 
    {
        if (args.length == 1) {
            
            try {
                DownloadResources obj = new DownloadResources();
                obj.downloadResources(args[0]);
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }
        else
            System.out.println("sould enter like: --threadsCount=3");
    }
}