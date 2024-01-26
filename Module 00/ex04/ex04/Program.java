import java.util.Scanner;

/**
 * Program
 */
public class Program {
    
    private static int[] charsDetected;
    private static int[] charEncountered = new int[56000];

    public static void main(String[] args) 
    {


        String input = " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42";

        charsDetected = new int[65535]; // because we have 65535 in plane  The Basic Multilingual Plane (BMP) 
        int[] count = new int[65535];


        int i = 0;

        while (i < input.length()) {
            
            char c = input.charAt(i);
            if (charsDetected[c] == 0) 
            {
                charsDetected[c] = input.charAt(i);
            }
            if(count[c] != 999)
                count[c]++; // increment value of the index c
            i++;
        }

        

        int[] sortedCount = sortCountValues(count , i , charsDetected);
         
        displayResults(sortedCount);
        
    }

    private static void displayResults(int[] sortedCount) {
        
        int j = 10;
        for (int i = 0; i < 10; i++) {
            System.out.println(sortedCount[i]);
            
            for (int k = 10 - i; k > 0; k--) {
                System.out.println(" #");
            }
        }
    
    }

    private static int[] sortCountValues(int[] count , int len , int[] charsDetected) {


        int temp;
        int t;
        int z = 0;
        for (int x = 0; x < count.length; x++)   
        { 
            for (int j = x + 1; j < count.length; j++)   
            {  
                if (count[x] < count[j])   
                {  
                    temp = count[x]; 
                    t = charsDetected[x];  
                    count[x] = count[j]; 
                    charsDetected[x] = charsDetected[j]; 
                    count[j] = temp;   
                    charsDetected[j] = t;   
                }
                // if (charsDetected[x] < charsDetected[j])   
                // {  
                //     t = charsDetected[x];  
                //     charsDetected[x] = charsDetected[j]; 
                //     charsDetected[j] = t;   
                // }

    
            }  
        }  
        return count;

    }
}