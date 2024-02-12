import java.util.Scanner;


public class Program {

    private static int[] charsDetected;

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        charsDetected = new int[65535];  
        short[] count = new short[65535];

        int i = 0;

        while (i < input.length()) {
            
            char c = input.charAt(i);
            if (charsDetected[c] == 0) 
            {
                charsDetected[c] = input.charAt(i);
            }
            if(count[c] != 999)
                count[c]++;  
            i++;
        }

        short[] sortedCount = sortCountValues(count , i , charsDetected);
         
        displayResults(sortedCount);
        scanner.close();
    }

    private static void displayResults(short[] sortedCount) {
        
        int maxCount = sortedCount[0];

        for (int i = 10; i >= 0; i--) {  
            for (int j = 0; j < 10; j++) 
            {
                if (sortedCount[j] * 10 / maxCount == i && sortedCount[j] > 0) 
                {
                    System.out.printf("%3d ", sortedCount[j]);
                } 
                else if (sortedCount[j] * 10 / maxCount > i) 
                {
                    System.out.print("  # ");
                } 
                else 
                {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
    
        for (int i = 0; i < 10; i++) {
            if (sortedCount[i] == sortedCount[i + 1] && charsDetected[i] > charsDetected[i + 1]) {
                int temp =  charsDetected[i];
                charsDetected[i] = charsDetected[i + 1];
                charsDetected[i + 1] = temp;
            }
            System.out.printf("%3c ", charsDetected[i]);
        } 
        System.out.println(); 
    }

    private static short[] sortCountValues(short[] count , int len , int[] charsDetected) {

        short temp;  
        int t;
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
            }  
        }  
        return count;
    }
}