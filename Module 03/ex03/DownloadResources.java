import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * DownloadResources
 */
public class DownloadResources {

    public DownloadResources(){}

    public void downloadResources(String string) throws IOException{
        
        if (string.startsWith("--threadsCount=")) 
        {
            int threadsCount = Integer.parseInt(string.substring(15));

            List<String> URLs = getUrls();
            Semaphore semaphore = new Semaphore(1);
            if(threadsCount > 2 && threadsCount <= 10)  
            {
                ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
                for (int i = 0; i < URLs.size(); i++) {
             
                }
                executor.shutdown();
            }
            else
            {
                System.out.println("number of threads sould be greter then 2 and less then 10.");
            }    
        }
        else 
        {
            System.out.println("sould enter like: --threadsCount=3");
        }
        
    }

    private List<String> getUrls() throws IOException
    {
        List<String> URLs = new ArrayList<>();

        File file = new File("files_urls.txt");
    
        FileReader fr = new FileReader(file);
        
        BufferedReader br = new BufferedReader(fr);
        
        String line;
        
        while((line = br.readLine()) != null){
            URLs.add(line);    
        }

        return URLs;
    }

    
}


