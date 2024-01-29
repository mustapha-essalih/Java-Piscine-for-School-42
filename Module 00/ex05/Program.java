import java.util.Scanner;

/**
 * Program
 */
public class Program {

    private static int numberOfStudents;
    private static String[] listOfStudents;
    private static boolean[][] classes;
    private static int[][][] attendance;

    public static void main(String[] args) {
        
        getListOfStudents();
        
        getClasses();

        createAttendance();
        
    }

    private static void createAttendance()
    {
        String input;

        attendance = new int[30][6][numberOfStudents];
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("-> ");
        while (!(input = scanner.nextLine()).equals(".")) 
        {
            String[] splitedInput = splitString(input, 4);

            while (splitedInput[0] == null) {
                System.out.println("enter 4 args.");
                System.out.print("-> ");
                input = scanner.nextLine();
                splitedInput = splitString(input, 4);
            }

            String studentName = splitedInput[0];
            int indexOfStudnet = getIndexOfStudent(studentName);
            int time = getTime(splitedInput[1]);
            int date = checkIfDayIsExistInWeek(toDigit(splitedInput[2]) , time);
            int status = getStatus(splitedInput[3]);
            
            while (indexOfStudnet == -1 || time == -1 || date == -1 || status == 0 ) 
            {
                System.out.println("attence input not correct");
                
                System.out.print("-> ");
                input = scanner.nextLine();
                if (input.equals(".")) 
                    return ;
                splitedInput = splitString(input, 4);
                indexOfStudnet = getIndexOfStudent(splitedInput[0]);
                time = getTime(splitedInput[1]);
                date = checkIfDayIsExistInWeek(toDigit(splitedInput[2]) , time);
                status = getStatus(splitedInput[3]);
            }
            System.out.print("-> ");
            attendance[date - 1][time - 1][indexOfStudnet] = status;
        }

    }

     
    private static void getClasses()
    {
        Scanner scanner = new Scanner(System.in);
        String input;
                    //     30days 6h
        classes = new boolean[30][6];
        // loop on 4 weeks

        for (int i = 0; i < 4; i++) 
        {
            for (int j = 0; j < 10; j++) // 10 class peer week
            {
                System.out.print("-> ");
                input = scanner.nextLine();    
                if (input.equals(".")) 
                    return ;

                String[] splitedStr = splitString(input , 2);

                while (splitedStr == null) {
                    System.out.println("enter 2 args.");
                    System.out.print("-> ");
                    input = scanner.nextLine();
                    splitedStr = splitString(input, 2);
                }
                int time = getTime(splitedStr[0]);
                
                int indexOfDay = getIndexOfDay(splitedStr[1]);

                while (time == -1 || indexOfDay == -1) 
                {
                    System.out.println("error in time or day");
                    System.out.print("-> ");
                    input = scanner.nextLine();
                    if (input.equals(".")) 
                        return ;
                    splitedStr = splitString(input , 2);
                    time = getTime(splitedStr[0]);
                    indexOfDay = getIndexOfDay(splitedStr[1]);
                }
                
                fillClasses(time , indexOfDay);
            }    
        }

    }

    private static void fillClasses(int time , int indexOfDay)
    {
        // start of the month is TU is 1
        // get first day in moth entered by user : MO , so monday her calue is 7 
        // index of monday in week is 0
        
        // we search for index of day entered by user in table to fill all days in  month 

        int startDayOfMonth = 1; // TU it is the start day in septembere 2020
        int firstDayOfMonth;
        
        if (indexOfDay > startDayOfMonth) 
            firstDayOfMonth = indexOfDay - startDayOfMonth; 
        else
            firstDayOfMonth = (indexOfDay + 7) - startDayOfMonth;
        
        for (int i = firstDayOfMonth; i < 30; i+=7) 
        {
            if(classes[i][time - 1] == true)
            {
                System.out.println("class alered created in day and this time.");
                break ;
            }
            else    
                classes[i][time - 1] = true;   
        }

    }

    private static void getListOfStudents() 
    {
        Scanner scanner = new Scanner(System.in);
        String input;
         
        listOfStudents = new String[10];
        numberOfStudents = 0;
        System.out.print("-> ");
        while (!(input = scanner.nextLine()).equals(".") && numberOfStudents < 10)  
        {
             
            while(input.length() > 10 || input.length() == 0  || includeEspace(input))
            {
                System.out.print("-> ");
                input = scanner.nextLine();
                if (input.equals(".")) {
                    return ;
                }
            }
            listOfStudents[numberOfStudents] = input;
             
            numberOfStudents++;
            System.out.print("-> ");
        }
        if (numberOfStudents == 10) {
            System.out.println(".");
         }
    }

    private static boolean includeEspace(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 32) {
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
        if (wordCount == 1) {
            return null;
        }        
        if (flag == 2 && wordCount > 2) 
        {
            return null;
        }
        if (flag == 4 && wordCount > 4) 
        {
            return null;
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

    private static int checkIfDayIsExistInWeek(int date , int time)
    {
        if (classes[date - 1][time - 1] == true) 
        {
            return date - 1;     
        }
        return -1;
    }

    private static int toDigit(String str) {
         
        int startIndex = 0;
        int result = 0;

        
        for (int i = startIndex; i < str.length(); i++) {
            char digitChar = str.charAt(i);
            if (isDigit(digitChar))  
            {
                int digitValue = digitChar - '0'; 
                result = result * 10 + digitValue;
            } 
            else {
                return -1;
            }
        }
        return result;
    }

    private static int getIndexOfStudent(String name)
    {
        for (int i = 0; i < numberOfStudents; i++) {
            if (listOfStudents[i].equals(name)) 
                return i;    
        }
        return -1;
    }

    private static int getTime(String time)
    {
        int t;

        t = toDigit(time);
        if (t < 1 || t > 6 ) 
            return -1;
        return t;
    }

    private static int getIndexOfDay(String day)
    {
        switch (day) 
        {
            case "MO":
                return 0;
            case "TU":
                return 1;
            case "WE":
                return 2;
            case "TH":
                return 3;
            case "FR":
                return 4;
            case "SA":
                return 5;
            case "SU":
                return 6;
            default:
                return -1;
        }
    }
    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static int  getStatus(String status)
    {
        if (status.equals("HERE")) {
            return 1;
        }
        else if (status.equals("NOT HERE")) {
            return -1;
        }
        return 0;
    }
}