import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Program
 */
public class Program {

    private static Scanner scanner;
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
                    scanner = new Scanner(System.in);
                    String input;

                    while (scanner.hasNext() && !(input = scanner.nextLine()).equals("exit")) 
                    {
                        String[] command = input.split("\\s+");
                        if (command.length > 3) {
                            System.out.println("command not found");
                        }
                        else  
                        {
                            if (command[0].equals("cd") && command.length == 2) 
                            {
                                cd(command[1]);
                            }
                            else if (command[0].equals("ls") && command.length == 1) {
                                ls(new File(System.getProperty("user.dir")) );
                            } 
                            else if (command[0].equals("mv")) 
                            {
                                if (!command[1].isEmpty() && !command[2].isEmpty())
                                {
                                    String relativePath = System.getProperty("user.dir");
                                    Path p = Paths.get(relativePath + "/" + command[2]);

                                    if(new File(p.toString()).isDirectory())
                                    {
                                        move(command[1] , command[2] , true);
                                    }
                                    else if (!command[1].isEmpty() && !command[2].isEmpty()) 
                                    {
                                        move(command[1] , command[2] , false);
                                    }
                                    else
                                    {
                                        System.out.println("command not found");
                                    }
                                }
                                else
                                {
                                    System.out.println("command not found");
                                }
    
                            }
                            else
                            {
                                System.out.println("command not found");
                            }
                        }
                    }
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

    private static void move(String source, String target , boolean falg)  
    {
        try {
            String relativePath = System.getProperty("user.dir");
            Path filePathSource;
            Path filePathDistination;

            if (falg) {
                
                filePathSource = Paths.get(relativePath + "/" + source);
                filePathDistination = Paths.get(relativePath + "/"  + target + "/" + source);
               
            }
            else{
                filePathSource = Paths.get(relativePath + "/" + source);
                filePathDistination = Paths.get(relativePath + "/"  + target);
            }
            
            Files.move(filePathSource, filePathDistination);
            
        } 
        catch (Exception e) 
        {
            System.out.println("Failed to move or rename .");
        }

    }

    private static void ls(File directory) {

        File[] files = directory.listFiles();

        if (files != null) 
        {
            for (File file : files) {
                if (file.isFile()) 
                    displayDirSize(file.length() , file);
                else if (file.isDirectory()) 
                    displayDirSize(getDirectorySize(file) , file);
            }
        }
    }

    private static void displayDirSize(long fileSize , File file){
       
        String sizeToStr;
        if (fileSize < 1024) 
            sizeToStr = fileSize + " B";
        else if (fileSize < 1024 * 1024) 
            sizeToStr = String.format("%d KB", fileSize / 1024);
        else if (fileSize < 1024 * 1024 * 1024) 
            sizeToStr = String.format("%d MB", fileSize / (1024 * 1024));
        else 
            sizeToStr = String.format("%d GB", fileSize / (1024 * 1024 * 1024));
        
        System.out.println(file.getName() + " " + sizeToStr);
    }

    private static long getDirectorySize(File directory) {
        long size = 0; // because file.len return long
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

    private static void  cd(String source)
    {
        String relativePath = System.getProperty("user.dir");
        Path path = Paths.get(relativePath + "/" + source);

        if (new File(path.toString()).isDirectory()) {// if dir found
            System.setProperty("user.dir", path.toAbsolutePath().toString());
            System.out.println(System.getProperty("user.dir"));
        }
        else{
            System.out.println("directory not found");
        }
    } 
}

 
        