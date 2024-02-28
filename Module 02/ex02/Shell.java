import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Shell {

    private static Scanner scanner;
    private  String currentDir;

    public void executeCommands(String string) {
        
        
        if (string.startsWith("--current-folder=")) 
        {
            currentDir = string.split("=")[1];
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
                    if (input.length() == 0)
                    {
                        continue;
                    }
                    List<String> command = parseTokens(input);
                    
                    if (command.size() > 3) 
                    {
                        System.out.println("command not found");
                    }
                    else  
                    {
                        if (command.get(0).equals("cd") && command.size() <= 2) 
                        {
                            if(command.size() == 1){
                                System.setProperty("user.dir", Paths.get(System.getProperty("user.home")).toString());
                            }
                            else
                                cd(command.get(1));
                        }
                        else if (command.get(0).equals("ls") && command.size() == 1) {
                            ls(new File(System.getProperty("user.dir")) );
                        } 
                        else if (command.get(0).equals("mv") && command.size() == 3) 
                        {
                            if (command.get(1) != null && command.get(2) != null)
                            {
                                String relativePath = System.getProperty("user.dir");
                                Path p = Paths.get(relativePath + "/" + command.get(2));

                                if(new File(p.toString()).isDirectory())
                                {
                                    move(command.get(1) , command.get(2) , true);
                                }
                                else if (!command.get(1).isEmpty() && !command.get(2).isEmpty()) 
                                {
                                    move(command.get(1) , command.get(2) , false);
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
            else
            {
                System.out.println("enter current directory");
            }
        }
    }
    
    private  List<String> parseTokens(String input) {
        
        List<String> tokens = new ArrayList<>();
        
        StringBuilder currentToken = new StringBuilder();
        
        boolean insideQuotes = false;

        for (char c : input.toCharArray()) 
        {
            if (c == ' ' && !insideQuotes) {
                if (currentToken.length() > 0) 
                {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0); // Reset the StringBuilder
                }
            } else if (c == '\'' || c == '\"') {
                insideQuotes = !insideQuotes;
            } else {
                currentToken.append(c);
            }
        }

        // Add the last token if there's any
        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private  void move(String source, String target , boolean falg)  
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

    private   void ls(File directory) {

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

    private   void displayDirSize(long fileSize , File file){ // 1kb has 1024 btes
        String sizeToStr = null;                              // 1mb equals 1 milion kb
         
        sizeToStr = String.format("%d KB", fileSize / 1024);
        
        System.out.println(file.getName() + " " + sizeToStr);
    }

    private long getDirectorySize(File directory) {
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

    private void  cd(String source)
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