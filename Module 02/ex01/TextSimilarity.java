import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

class TextSimilarity {

    private StringBuilder stringBuilderA;
    private StringBuilder stringBuilderB;
    private BufferedReader bufferOfFileA ;
    private BufferedReader bufferOfFileB ;

    public TextSimilarity()
    {
        stringBuilderA = new StringBuilder();  
        stringBuilderB = new StringBuilder();  
    }


	public void getTextSimilarity(String[] args) throws IOException {
        
        if(getBufferOfFile(args) == false) // check if files are empty or one of them
        {
            return;
        }
       
        Set<String> dictionary = getDictionary(bufferOfFileA , bufferOfFileB);
        Vector<Integer> vectorA = getVectorLenght(dictionary.size() , false , dictionary);
        Vector<Integer> vectorB =  getVectorLenght(dictionary.size(), true , dictionary);

        Integer numerator = getNumerator(vectorA , vectorB);
        if (numerator == 0) {
            System.out.println("Similarity = " + 0);
            return;
        }
        double denominator = getDenominator(vectorA , vectorB);
        double similarity = numerator / denominator;
        similarity = Math.floor(similarity * 100) / 100;
        
        if(similarity == 0.0)
            System.out.println(0);
        else if(similarity == 1.0)
            System.out.println(1);
        else
        {
            System.out.println("Similarity = " + similarity);
        }
    }

    private double getDenominator(Vector<Integer> vectorA, Vector<Integer> vectorB) {
    
        double sqrtA;
        double sqrtB;

        sqrtA = sqrtB = 0;

        for (int i = 0; i < vectorA.size(); i++) 
        {
            int a = vectorA.get(i) * vectorA.get(i);
            int b = vectorB.get(i) * vectorB.get(i);
            sqrtA += a;
            sqrtB += b;
        }

        double sqrt = Math.sqrt(sqrtA) * Math.sqrt(sqrtB);
        double truncatedNumber = Math.floor(sqrt * 10) / 10; // so floor alway make .0 means if we have 10.225 and floor it will be 10.0 * 10 / 10 to get just one number after .
        return truncatedNumber;
    }

    private Integer getNumerator(Vector<Integer> vectorA, Vector<Integer> vectorB) {
    
        Integer result;

        result = 0;
        for (int i = 0; i < vectorA.size(); i++) 
        {
            int x = vectorA.get(i) * vectorB.get(i);
            result += x;
        }
        return result;
    }
    private Vector<Integer> getVectorLenght(int length, boolean flag ,Set<String> dictionary) throws IOException{
        
        Vector<Integer> vector = new Vector<>(length);
 
        for(String value : dictionary)
        {
            if (flag == false) 
            {    
                Integer counter = countWord(stringBuilderA.toString().split(" "), value);
                vector.add(counter);
            }
            else
            {
                Integer counter = countWord(stringBuilderB.toString().split(" "), value);
                vector.add(counter);
            }
        }
        return vector;
    }

    private  Set<String>  getDictionary(BufferedReader bufferOfFileA, BufferedReader bufferOfFileB) throws IOException{

        Set<String> dicSet = new TreeSet<>(); 
        String line;
    
        while ((line = bufferOfFileA.readLine()) != null) 
        {
            String[] words = line.split(" ");

            for (String word : words) 
            {
                dicSet.add(word);    
                stringBuilderA.append(word);
                stringBuilderA.append(" ");
            }
        }

        while ((line = bufferOfFileB.readLine()) != null) 
        {
            String[] words = line.split(" ");

            for (String word : words) 
            {
                dicSet.add(word);    
                stringBuilderB.append(word);
                stringBuilderB.append(" ");
            }
        } 
 
        FileWriter writer = new FileWriter("dictionary.txt");
       
        for (String string : dicSet) {
            writer.write(string + " ");
        }
        writer.close();
        return dicSet;
    }

    private boolean getBufferOfFile(String[] string) throws FileNotFoundException 
    {
        File file1 = new File(string[0]);
        File file2 = new File(string[1]);
        
        if (file1.exists() && file2.exists() && file1.canRead() && file2.canRead()) 
        {
            long size = 10500000;
            if (file1.length() > size  || file2.length() > size) {
                System.out.println("file size > 10 mb");
                return false;
            }
            if (file1.length() == 0 && file2.length() == 0) {
                System.out.println("Similarity = " + 0);
                return false;
            }
            
            if (file1.length() == 0 || file2.length() == 0) {
                System.out.println("Similarity = " + 0);
                return false;
            }
            
            bufferOfFileA = new BufferedReader(new FileReader(file1.toString()));
            bufferOfFileB = new BufferedReader(new FileReader(file2.toString()));
            
            return true;
        }
        System.out.println("ERROR");
        return false;
    }

    public Integer countWord(String[] words, String word)
    {
        int count = 0;
        
        for (String string : words) {
            if (string.equals(word)) {
                count++;
            }
        }
        return count;
    }
}