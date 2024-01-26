import java.util.Scanner;

/**
 * Program
 */
public class Program {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int count = 0;
        
        int[] counts = new int[input.length()];
        
        int j  = 0 ;
        int z  = 0;
        for (int i = 0; i < input.length(); i++) {
            
            char c = input.charAt(i);
            int x = 0;
            while (x < input.length()) {
                if (c == input.charAt(x)) {
                    count++;            
                }
                x++;
            }
            counts[z] = count;
            z++;
            break;
        }
        
        System.out.println(counts[0]);

    }
}


