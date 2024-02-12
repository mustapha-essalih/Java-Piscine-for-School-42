import java.util.Scanner;

class Program{

    public static void main(String[] args) {
        
        boolean flag = true;
        
        int steps = 1;
        
        Scanner input = new Scanner(System.in);

        if (input.hasNextInt()) 
        {    
            int number = input.nextInt();
    
            if(number <= 1)
            {
                System.err.println("IllegalArgument");
                input.close();
                System.exit(-1);
            }
             
            for (int i = 2; i * i <= number; i++) 
            {
                if (number % i == 0) 
                {
                    flag = false;
                    break;
                }
                steps++;
            }
    
            if (!flag)
                System.out.println(flag + " " + steps);
            else
                System.out.println(flag + " " + steps);
        }
        input.close();
    }
}