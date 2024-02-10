 
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// /**
//  * Test
//  */
// public class Test {

//     public static void main(String[] args) {
        
        
//         // System.setProperty("user.dir", "/home/messalih/Desktop/"); // cd
//         // System.out.println(System.getProperty("user.dir")); // pwd

//         Path path = Paths.get("/home/messalih/Desktop/Java-Piscine-for-School-42/Module 02/ex02/");
//         System.out.println(Files.isDirectory(path.toAbsolutePath()));
//         // System.out.println(Files.exists(path));

//         // System.setProperty("user.dir", "/home/messalih/Desktop/");
        
//     }
// }


import java.io.File;

public class Test {
    public static void main(String[] args) {
        String directoryPath = ".";  // Replace with the path of the directory you want to list

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("File: " + file.getName() + " | Size: " + file.length() + " bytes");
                } else if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName() + " | Size: " + getDirectorySize(file) + " bytes");
                }
            }
        }
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
