import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Program
 */
public class Program {

    private static String currentDir;
    public static void main(String[] args) {

        if (args.length == 1) 
        {
            if (args[0].startsWith("--current-folder=")) 
            {
                currentDir = args[0].split("=")[1];
                Path path = Paths.get(currentDir);
                if(Files.isDirectory(path.toAbsolutePath())) // if current dir is directory and is exsit
                {
                    // user.dir is the directory where your app (java) was started (working dir). 
                    System.setProperty("user.dir", path.toAbsolutePath().toString());
                    System.out.println(System.getProperty("user.dir"));

                    // ls(path.toFile());
                
                }
                else{
                    System.out.println("enter current directory");
                }

            }
            else{
                System.out.println("enter current directory");
            }
        }
        else{
            System.out.println(" enter current directory");
        }
        
    }

    private static void ls(File directory) {

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) 
                    displayDirSize(file.length() , file);
                else if (file.isDirectory()) 
                    displayDirSize(getDirectorySize(file) , file);
            }
        }
    }

    private static void displayDirSize(double fileSize , File file){
       
        String sizeToStr;
        if (fileSize < 1024) 
            sizeToStr = fileSize + " B";
        else if (fileSize < 1024 * 1024) 
            sizeToStr = String.format("%,.2f KB", fileSize / 1024);
        else if (fileSize < 1024 * 1024 * 1024) 
            sizeToStr = String.format("%,.2f MB", fileSize / (1024 * 1024));
        else 
            sizeToStr = String.format("%,.2f GB", fileSize / (1024 * 1024 * 1024));
        
        System.out.println(file.getName() + " " + sizeToStr);
    }

    private static long getDirectorySize(File directory) {
        long size = 0;
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else if (file.isDirectory()) {
                    size += getDirectorySize(file);
                }
            }
        }
        return size;
    }
}