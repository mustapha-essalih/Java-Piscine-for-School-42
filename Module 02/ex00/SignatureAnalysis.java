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

class SignatureAnalysis {

    public SignatureAnalysis(){}

    public void signatureHandling() throws IOException {
        
        Map<String, String> signature;
        File resultFile;

        signature = getKeyValueOfSignature();
        
        resultFile = new File("result.txt");
        
        try {
            resultFile.createNewFile();
        } catch (IOException e) {e.printStackTrace();}
        
        readFilesFromUser(signature, resultFile );
    }

    private void readFilesFromUser(Map<String, String> signature, File resultFile) throws IOException {
        
        String filePath;
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNext() && !(filePath = scanner.nextLine()).equals("42")) 
        { 
            File file = new File(filePath);
            
            if (file.exists() && file.canRead()) 
            {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] readSomeBytes = inputStream.readNBytes(1000);
                String hex = bytesToHex(readSomeBytes);
                boolean isFound = false;

                for (Map.Entry<String, String> entry : signature.entrySet()) 
                {
                    if(hex.contains(entry.getKey()))
                    {
                        String extention = entry.getValue() + '\n';
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

    private  Map<String, String> getKeyValueOfSignature() {
        
        HashMap<String, String> mapOfSignature = new HashMap<String, String>();

        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/signatures.txt")); // read text file
			String line = reader.readLine();

			while (line != null) {
                 
                String keyValue[] = line.split(", +\\s");
                mapOfSignature.put(keyValue[0], keyValue[1]);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("UNDEFINED");
		}
        return mapOfSignature;
    }

    public  String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(1000);

        for (int i = 0; i < 1000 ; i++) 
        {
            hexStringBuilder.append(String.format("%02X ", bytes[i]));
        }
        return hexStringBuilder.toString();
    }
}