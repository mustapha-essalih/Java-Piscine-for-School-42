import java.util.Scanner;

/**
 * Program
 */
public class Program {

    private static short len;
    private static short[] days;
    private static short[] times;
    private static short[] indexOfStudent;
    private static int[][] attenceTable;
    public static void main(String[] args) {
        
        String[] listOfStudents = getListOfStudents();
         
        short numberOfStudents = len;

        populatingTimetable();
 
        short numberOfClasses = len;
        
        implAttendance(numberOfStudents , numberOfClasses ,listOfStudents);
        
      
        
        
    }

    // -> Mike 2 1 NOT_HERE MO = 1
    // -> John 4 5 HERE    FR = 5
    // -> Mike 4 5 HERE     FR = 5

    // cehck if user is exist
    // check if time and date exist
    // check status if writing correct
    // register the student attence

    private static void implAttendance(short numberOfStudents, short numberOfClasses , String[] listOfStudents) {
        
        String input;
        int i;
        
        attenceTable = new int[numberOfStudents][packNumbers(6 , 31)];

        i = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        while (!(input = scanner.nextLine()).equals(".") && i < 10)  
        {
            String[] splitedInput = splitString(input , 4);
            String name = splitedInput[0];
            int time = toDigit(splitedInput[1]);
            int day = toDigit(splitedInput[2]);
            
            if(checkIfExists(name , listOfStudents , (short)time , day , numberOfClasses , numberOfStudents) == false)
                System.exit(-1);
            
            int status = getAttendanceStatus(splitedInput[3]);
            
            int studentIndex = getStudentIndex(numberOfStudents ,listOfStudents , name);
            int dayAndTime = packNumbers(time , day);
            attenceTable[studentIndex][dayAndTime] = status;
            System.out.println(attenceTable[studentIndex][dayAndTime]);
            System.out.print("-> ");
            i++;
        }


        

    }



    private static int getStudentIndex(short numberOfStudents , String[] listOfStudents , String name ) {
        
        for (int i = 0; i < numberOfStudents; i++) {
            if (listOfStudents[i] == name) {
                return i;
            }
        }
        return 0;
    }

    private static void populatingTimetable() {
        
        short indexOfDay; 
        len = 0;
        days = new short[10];
        times = new short[10];
        
        String input;
        int i;
        
        i = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        while (!(input = scanner.nextLine()).equals(".") && i < 10)  
        {
            String[] splitedStr = splitString(input , 2);
            
            int time = toDigit(splitedStr[0]);
            
            if (time < 1 || time > 6 ) 
                System.exit(-1);
            
            String day = splitedStr[1];
           
            indexOfDay = checkDays(day);
            if(indexOfDay == 0)
            {
                System.exit(-1);
            }
            
            if (isDuplicateClass(indexOfDay, time , i))
            {
                System.exit(-1);
            }

            days[i] = indexOfDay;
            times[i] = (short)time;

            System.out.print("-> ");
            i++;
            len++;
        }

     }
 


    private static short checkDays(String day) {
        switch (day) 
        {
            case "MO":
                return 1;
            case "TU":
                return 2;
            case "WE":
                return 3;
            case "TH":
                return 4;
            case "FR":
                return 5;
            case "SA":
                return 6;
            case "SU":
                return 7;
            default:
                return 0;
        }

    }

    private static boolean isDuplicateClass(short indexOfDay, int time , int size) {
        for (int i = 0; i < size; i++) {
            if (days[i] == indexOfDay && times[i] == time)
                return true;
        }
        return false;
    }

    private static String[] getListOfStudents() 
    {
        Scanner scanner = new Scanner(System.in);
        String input;
        int i;
        String[] listOfStudents;

        i = 0;
        listOfStudents = new String[10];
        indexOfStudent = new short[10];
        len = 0;

        System.out.print("-> ");
        while (!(input = scanner.nextLine()).equals(".") && i < 10)  
        {
            if(input.length() > 10 || includeEspace(input))
            {
                scanner.close();
                System.exit(-1);
            }
            listOfStudents[i] = input;
            indexOfStudent[i] = (short)i;
            i++;
            len++;
            System.out.print("-> ");
        }
        return listOfStudents;
    }

    
    private static boolean includeEspace(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 32 || input.charAt(i) == 9) {
                return true;
            }
        }

        return false;
    }
 
     
    private static String[] splitString(String input , int flag) {
         
        int wordCount = 1; // at least one word
       for (int i = 0; i < input.length(); i++) {
           if (input.charAt(i) == ' ') 
           {
               while (input.charAt(i) == ' ') 
                   i++;
               wordCount++;
           }
       }

        if (flag == 2 && wordCount > 2) 
        {
         System.exit(-1);
        }
        if (flag == 4 && wordCount > 4) 
        {
            System.exit(-1);
        }
       String[] wordsArray = new String[wordCount];

       int wordStartIndex = 0;
       int arrayIndex = 0;

       for (int i = 0; i < input.length(); i++) {
           if (input.charAt(i) == ' '  ) {
               while (input.charAt(i) == ' ') 
                   i++;
               i--;
               int wordLength = i - wordStartIndex;

               char[] currentWordArray = new char[wordLength];
               
               for (int j = 0; j < wordLength; j++) {
                   currentWordArray[j] = input.charAt(wordStartIndex + j);
                }

               wordsArray[arrayIndex] = new String(currentWordArray);
               arrayIndex++;

               wordStartIndex = i + 1;
               while (input.charAt(i) == ' ' || input.charAt(i) == 9)
                   i++; 
           }
       }

       int lastWordLength = input.length() - wordStartIndex;
       char[] lastWordArray = new char[lastWordLength];
       
       for (int j = 0; j < lastWordLength; j++) {
           lastWordArray[j] = input.charAt(wordStartIndex + j);
       }
       wordsArray[arrayIndex] = new String(lastWordArray);
       return wordsArray;
    }

    private static int toDigit(String str) {
         
        int startIndex = 0;
        int result = 0;

        for (int i = startIndex; i < str.length(); i++) {
            char digitChar = str.charAt(i);
            if (Character.isDigit(digitChar)) {
                int digitValue = digitChar - '0'; // Convert char to int value
                result = result * 10 + digitValue;
            } 
            else {
                System.exit(-1);
            }
        }

        return result;
    }

    private static boolean checkIfExists(String name, String[] listOfStudents, short time , int day , short numberOfClasses , short numberOfStudents ) {

        int i;

        i = 0;
        while ( i < numberOfStudents) {
            if (listOfStudents[i].equals(name)) {
                break;
            }
            i++;
        }
        
        if (i == numberOfStudents) // if student is exist or not 
            System.exit(-1);
        
        if (time < 1 || time > 6 ) 
            System.exit(-1);

        if (day < 1 || day > 31) 
            System.exit(-1);
       

        while (day > 7) {
            day -= 7;
        }
        i = 0;
        while ( i < numberOfClasses) 
        {
            if (days[i] == day && times[i] == time) 
                return true;
            i++;
        }


        return false;
    }

    private static int packNumbers(int firstNumber, int secondNumber) {
        
        // Use bit manipulation to pack the two numbers
        return (firstNumber << 5) | secondNumber;
    }

    private static int getAttendanceStatus(String status) {
        
        if (!status.equals("NOT_HERE") && !status.equals("HERE")) 
            System.exit(-1);
        if (status.equals("HERE"))
            return 1;
        return -1;
    }

    
}