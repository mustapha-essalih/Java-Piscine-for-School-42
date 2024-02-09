import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
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

        while (!(filePath = scanner.nextLine()).equals("42")) {
            
            File file = new File(filePath);
            
            if (file.exists()) 
            {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] allBytesOfFile = inputStream.readAllBytes();
                String hex = bytesToHex(allBytesOfFile);
                int i = 0;
                for (Map.Entry<String, String> entry : signature.entrySet()) 
                {
                        
                    if(hex.startsWith(entry.getValue()))
                    {
                        String extention = entry.getKey() + '\n';
                        Files.write(
                            Path.of(resultFile.getName()), 
                            extention.getBytes(), 
                            StandardOpenOption.APPEND);
                            i = 0;
                        break;
                    }

                }
                if(i == 1)
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
    }

    private static Map<String, String> getKeyValueOfSignature() {
        
        HashMap<String, String> mapOfSignature = new HashMap<String, String>();

        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("/home/messalih/Desktop/Java-Piscine-for-School-42/Module 02/ex00/signatures.txt"));
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
