import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Program
 */
public class Program {

    
    
    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);

        String filePath;
        
        Map<String, String> signature;

        signature = getKeyValueOfSignature();

        File resultFile = new File("result.txt");
        resultFile.createNewFile();
        
        while (scanner.hasNext() && !(filePath = scanner.nextLine()).equals("42")) 
        { 
            File file = new File(filePath);
            
            if (file.exists()) 
            {
                FileInputStream inputStream = new FileInputStream(file);// beacsue we read binnary files like images 
                byte[] allBytesOfFile = inputStream.readAllBytes();
                String hex = bytesToHex(allBytesOfFile);
                boolean isFound = false;
                for (Map.Entry<String, String> entry : signature.entrySet()) 
                {
                    if(hex.startsWith(entry.getValue()))
                    {
                        String extention = entry.getKey() + '\n';
                        Files.write(
                            Path.of(resultFile.getName()), 
                            extention.getBytes(), 
                            StandardOpenOption.APPEND); 
                        isFound = true;
                        break;
                    }
                }
                if(isFound == false)
                {
                    System.out.println("UNDEFINED");
                }
                else
                    System.out.println("PROCESSED");
                inputStream.close();
            }
            else{
                System.out.println("UNDEFINED");
            }
        }
        scanner.close();
    }

    private static Map<String, String> getKeyValueOfSignature() {
        
        HashMap<String, String> mapOfSignature = new HashMap<String, String>();

        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/signatures.txt")); // read text file
			String line = reader.readLine();

			while (line != null) {
                 
                String keyValue[] = line.split(", ");
                mapOfSignature.put(keyValue[0], keyValue[1]);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			System.out.println("UNDEFINED");
		}
        
        return mapOfSignature;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(16);

        // loop on 20 byte in file (magic number)
        for (int i = 0; i < 20 ; i++) 
        {
            hexStringBuilder.append(String.format("%02X ", bytes[i]));
        }
        return hexStringBuilder.toString();
    }

}
