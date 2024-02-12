import java.util.Scanner;

class Program {

    private static int getSumOfDigits(int number)
    {
        int result = 0;
        
        while (number > 0) 
        {
            result += number % 10;
            number = number / 10;    
        }
        return result;
    }

    private static boolean isPrime(int number)
    {
        boolean flag = true;

        if(number <= 1)
        {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        for (int i = 2; i * i <= number; i++) 
        {
            if (number % i == 0) 
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
     
        Scanner input = new Scanner(System.in);
        
        int count = 0;
        int num;

        while(true)
        {
            if (!input.hasNextInt()) 
            {
                System.err.println("IllegalArgument");
                input.close();
                System.exit(-1);
            }

            if (( num = input.nextInt()) == 42) 
                break;
            int sumOfDigits = getSumOfDigits(num);
            boolean ifIsPrime = isPrime(sumOfDigits);
            if(ifIsPrime)
                count++;
        }
        System.out.println("Count of coffee-request : " + count);
        input.close();
    }
}