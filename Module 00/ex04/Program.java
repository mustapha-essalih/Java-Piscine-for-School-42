import java.util.Scanner;

/**
 * AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

 
 
counts[i] * 10 / maxCount

36 * 10 / 36
35 * 10 / 36
27 * 10 / 36

```java

int maxCount = sortedCount[0];
for (int i = 0; i < 10; i++) {
    
    System.out.println(sortedCount[i]);
    int len = (sortedCount[i] * 10) / maxCount;
    for (int j = 0; j < len; j++) {
        System.out.println(" #");
    }
    System.out.print(" ");
    break;
}```

 */
public class Program {
    

    // this program cannot handle emoji, because emoji in Supplementary Planes (Plane 1 to Plane 16)

    private static int[] charsDetected;

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        charsDetected = new int[65535]; // because we have 65535 in plane  The Basic Multilingual Plane (BMP) 
        short[] count = new short[65535];


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

        short[] sortedCount = sortCountValues(count , i , charsDetected);
         
        displayResults(sortedCount);
        scanner.close();
    }

    private static void displayResults(short[] sortedCount) {
        
        // we calc the modulasse of the max count
        int maxCount = sortedCount[0];

        for (int i = 10; i >= 0; i--) { // loop on 11 for print also the sortedCount[i]
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
    }

    private static short[] sortCountValues(short[] count , int len , int[] charsDetected) {

        short temp; // i dont use integer because allocate 4 bytes
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