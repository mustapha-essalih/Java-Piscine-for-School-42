import java.util.Scanner;

public class Program {
    
    private static int numberOfStudents;
    private static String[] listOfStudents;
    private static boolean[][] classes;
    private static int[][][] attendance;
    private static   String[] daysOfWeek = {"MO" , "TU" , "WE" , "TH" , "FR" , "SA" , "SU"};
    private static String strTime ; 
    private static String strDate ; 
    private static String strDay ;
    private static String nameOfStudent ;
    private static String strStatus ;


    
    public static void main(String[] args) 
    {
        getListOfStudents();    
        getClasses();
        createAttendance();
        printTable();
    }



    private static void getListOfStudents() 
    {
        Scanner scanner = new Scanner(System.in);
        listOfStudents = new String[10];
        numberOfStudents = 0;
         
        while (numberOfStudents < 10) 
        {
            String studentName = getStudentName(numberOfStudents , scanner );
            if(studentName == null)
                break;
            while (studntNameExist(studentName)) {
                System.out.println("ERROR");
                studentName = getStudentName(numberOfStudents , scanner );
                if (studentName == null) {
                    return;
                }
            }    
            listOfStudents[numberOfStudents] = studentName;
            numberOfStudents++;             
        }
        
        if (numberOfStudents == 10) 
            System.out.println(".");
    }


    private static void getClasses()
    {
        Scanner scanner = new Scanner(System.in);
        String input;
        int numberOfClasses;
        int indexOfDay;
        int time;
        
                    //     30days 6h
        classes = new boolean[30][6];
         
        numberOfClasses = 0;
        while (!(input = scanner.nextLine()).equals("."))  
        {
            
            while (input.length() == 0) // if just enter enter
            {
                System.out.println("ERROR");
                input = scanner.nextLine();
                if (input.equals(".")) 
                    return ;    
            }
            
            Scanner splitedInput = new Scanner(input).useDelimiter("\\s+");
            
            inputClassesFromUser(splitedInput , scanner , 2);
            
            time = getTime(strTime);
            indexOfDay = getIndexOfDay(strDay);
            while (time == -1 || indexOfDay == -1) 
            {
                inputClassesFromUser(splitedInput , scanner , 2);
                time = getTime(strTime);
                indexOfDay = getIndexOfDay(strDay);
            }
            int rtn = fillClasses(time , indexOfDay , 0);
            
            if (rtn != 0) 
                numberOfClasses++;   

            if(numberOfClasses == 10)
            {
                System.out.println(".");
                return;
            }
        }
    }

    private static void createAttendance()
    {
        String input;
        int time;
        int status;
        int date;
        int indexOfDay;
        int indexOfStudnet;

        attendance = new int[30][6][numberOfStudents];
        
        Scanner scanner = new Scanner(System.in);  
        
        //  inifinite loop because can user want to edit status of attendace
        while (!(input = scanner.nextLine()).equals(".")) 
        {
            while (input.length() == 0) // if just enter enter
            {
                System.out.println("ERROR");
                input = scanner.nextLine();
                if (input.equals(".")) 
                    return ;    
            }
            
            Scanner splitedInput = new Scanner(input).useDelimiter("\\s+");
            
            inputClassesFromUser(splitedInput , scanner , 4);
            time = getTime(strTime);
            date = checkIfDayIsExistInWeek(toDigit(strDate) , time);
            indexOfStudnet = getIndexOfStudent(nameOfStudent);
            status = getStatus(strStatus);
             
            while (indexOfStudnet == -1 || time == -1 || date == -1 || status == 0 ) 
            {
                inputClassesFromUser(splitedInput , scanner , 4);
                time = getTime(strTime);
                date = checkIfDayIsExistInWeek(toDigit(strDate) , time);
                indexOfStudnet = getIndexOfStudent(nameOfStudent);
                status = getStatus(strStatus);
            }
            attendance[date][time - 1][indexOfStudnet] = status;
        }
    }

    private static int fillClasses(int time , int indexOfDay , int numberOfClasses)
    {
        int startDayOfMonth = 1; // TU it is the start day in septembere 2020
        int firstDayOfMonth;
        
        if (indexOfDay >= startDayOfMonth) 
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
            {
                classes[i][time - 1] = true;   
                numberOfClasses++; // because increment 4 times
            }
        }
        
        return numberOfClasses == 4 ? 1 : 0;
    }

    private static void printTable()
    {
        System.out.printf("%10s", " ");
        for (int i = 0; i < classes.length; i++) 
        {
            for (int j = 0; j < classes[j].length; j++) 
            {
                if (classes[i][j] == true) 
                {
                    System.out.printf("%1d:00%3s%3d|", j + 1, getDayFromIndex((i + 1) % 7), i + 1);
                }    
            }    
        }
        System.out.println();
        
        for (int i = 0; i < numberOfStudents; i++) 
        {
            System.out.printf("%10s" , listOfStudents[i]);
            for (int j = 0; j < classes.length; j++) 
            {
                for (int x = 0; x < classes[j].length; x++) 
                {
                    if (classes[j][x] == true) 
                    {
                        if (attendance[j][x][i] == 1) 
                            System.out.printf("%10s|", "1");
                        else if (attendance[j][x][i] == -1) 
                            System.out.printf("%10s|", "-1");
                        else 
                            System.out.printf("%10s|", " ");
                    }    
                }    
            }
            System.out.println();
        }
    }

    private static void inputClassesFromUser(Scanner splitedInput, Scanner scanner , int size) 
    {
        String input;
        String[] timeAndDay = new String[size];
        int i ;
        i = 0;
        while (i < size + 1) 
        {
            boolean hasNext = splitedInput.hasNext();
            if (hasNext && i < size)
                timeAndDay[i] = splitedInput.next();
           
            else if (hasNext && i == size) 
            {
                System.out.println("ERROR");    
                input = scanner.nextLine();
                if (input.equals(".")) 
                    return ;
                while (input.length() == 0) // if just enter enter
                {
                    System.out.println("ERROR");
                    input = scanner.nextLine();
                    if (input.equals(".")) 
                        return ;    
                }
                splitedInput = new Scanner(input).useDelimiter("\\s+");
                i = -1;
            }
            else if (!hasNext && i < size) 
            {
                System.out.println("ERROR");    

                input = scanner.nextLine();
                if (input.equals(".")) 
                    return ;
                while (input.length() == 0) // if just enter enter
                {
                    System.out.println("ERROR");
                    input = scanner.nextLine();
                    if (input.equals(".")) 
                        return ;    
                }
                splitedInput = new Scanner(input).useDelimiter("\\s+");
                i = -1;
            }

            i++;
        }
        if (size == 2) 
        {
            strTime = timeAndDay[0];
            strDay = timeAndDay[1];
        }
        else
        {
            nameOfStudent =  timeAndDay[0];
            strTime = timeAndDay[1];
            strDate =  timeAndDay[2];
            strStatus = timeAndDay[3];
        }
    }
    

    private static String getStudentName(int numberOfStudents ,Scanner scanner) 
    {
        String input;
        String studentName;

        
        studentName = null;
        if (!(input = scanner.nextLine()).equals(".") && numberOfStudents < 10)  
        {
            while (input.length() == 0) // if just enter enter
            {
                System.out.println("ERROR");
                input = scanner.nextLine();
                if (input.equals(".")) 
                    return null;    
            }
            
            Scanner splitedInput = new Scanner(input).useDelimiter("\\s+"); // split by one or more then one space ( space and tab)
            studentName = splitedInput.next();
            while (splitedInput.hasNext() || studentName.length() > 10 ) // if enter > string or studnet name len > 10
            {
                System.out.println("ERROR");
                input = scanner.nextLine();
                if (input.equals(".")) 
                    return null; 
                while (input.length() == 0) 
                {
                    System.out.println("ERROR");
                    input = scanner.nextLine();
                    if (input.equals(".")) 
                        return null; 
                }
                splitedInput = new Scanner(input).useDelimiter("\\s+");
                studentName = splitedInput.next();
            }            
        }
         
        return studentName;
    }
 
    private static int checkIfDayIsExistInWeek(int date , int time)
    {
        if (date < 1 || date > 30) 
        {
            System.out.println("ERROR");    
            return -1;
        }
        
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
        for (int i = 0; i < daysOfWeek.length; i++) {
            if (day.equals(daysOfWeek[i])) {
                return i;
            }
        }
        return -1;
    }
    private static String getDayFromIndex(int indexOfDay)
    {
        return daysOfWeek[indexOfDay];
    }
    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static int  getStatus(String status)
    {
        if (status.equals("HERE")) {
            return 1;
        }
        else if (status.equals("NOT_HERE")) {
            return -1;
        }
        return 0;
    }

    private static boolean studntNameExist(String name){
        for (int i = 0; i < numberOfStudents; i++) {
            if (listOfStudents[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

}
