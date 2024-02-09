 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        
        BufferedReader reader;
 

		try {
			reader = new BufferedReader(new FileReader("/home/messalih/Desktop/Java-Piscine-for-School-42/Module 02/test/signatures.txt"));
			String line = reader.readLine();

                
            String lines[] =    line.split(", ");
                
            for (int lines2 = 0; lines2 < lines.length; lines2++) {
                System.out.println(lines[lines2]);
            }

			reader.close();
		} catch (IOException e) {
            System.out.println(e);
			System.out.println("UNDEFINED");
		}

    }
}