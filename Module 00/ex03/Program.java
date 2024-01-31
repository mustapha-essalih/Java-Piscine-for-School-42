import java.util.Scanner;
 

class Program{



    public static void main(String[] args) {
         
        Scanner scanner = new Scanner(System.in);

        System.out.print("-> ");

        String week;
        int weekNumber;
        
        if (scanner.hasNext()) 
        {
            week = scanner.next();
        
            int i = 0;
            int total = 0;
    
            while(!week.equals("42") && i < 18)
            {
                if(week.equals("Week") )
                {   
                    weekNumber = scanner.nextInt();
    
                    if(weekNumber == i + 1)
                    {
                        System.out.print("-> ");
                        
                        int  j = 0;
    
                        int smmalar = 9;
    
                        while(j < 5)
                        {
                            int grade = scanner.nextInt();
    
                            if(grade >= 1 && grade <= 9)
                            {
                                if(grade <= smmalar)
                                {
                                    smmalar = grade;
                                }
                            }
                            else // if pass grade less then 1 or greter then 9 is error
                            {
                                System.err.println("IllegalArgument");
                                System.exit(-1);
                            }
                            j++;
                        }
     
                        total *= 10;
                        total += smmalar;
                    }
                    else
                    {
                        System.err.println("IllegalArgument");
                        System.exit(-1);
                    }
                    
                }
                else
                {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                i++;
                System.out.print("-> ");
                week = scanner.next();
            }
            
            

            int  reverse = 0;  
            
            while(total > 0)   
            {  
                int remainder = total % 10;  
                
                reverse = reverse * 10 + remainder;  
                
                total = total/10;  
            }  
    
            int result = 0;
            i = 1;
            while (reverse > 0) 
            {
                result = reverse % 10;
                
                System.out.print(String.format("Week %d ", i));
                
                i++;
                
                while (result-- > 0) 
                {
                    System.out.print("=");
                }
                reverse = reverse / 10;    
                System.out.println(">");
            }
            
            
        }

       
        scanner.close();

    }



}