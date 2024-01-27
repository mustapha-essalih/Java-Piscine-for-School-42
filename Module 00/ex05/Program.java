import java.util.Scanner;

/**
 * Program
 */
public class Program {

    public static void main(String[] args) {
        
        //  create list of students


        Scanner scanner = new Scanner(System.in);
        

        String[] listOfStudents = getListOfStudents(scanner);
        String[][]  timetable = populatingTimetable(scanner);

        // int j = 0;
        // while (j < listOfStudents.length) {
        //     System.out.println(listOfStudents[j]);
        //     j++;
        // }

    }

    private static String[][] populatingTimetable(Scanner scanner) {
        
        String[][][]  timetable = new String[10][10][10];

        // -> 2 MO have class in monday start at 2
        // -> 4 WE have class in wensday start at 4

        String input;
        int i;

        i = 1;


        System.out.print("-> ");
        int time = scanner.nextInt();
        String day = scanner.next();
        timetable[0][time][day];


        // listOfStudents[0] = input;
        // while (i < 10) // handle scann when input mustapha essalih 
        // {
        //     System.out.print("-> ");
        //     input = scanner.nextLine();
        //     if(input.equals("."))
        //         break;
        //     while(input.length() > 10)
        //     {
        //         System.out.print("-> ");
        //         input = scanner.nextLine();
        //     }

        //     // listOfStudents[i] = input;
        //     i++;
        // }


        throw new UnsupportedOperationException("Unimplemented method 'populatingTimetable'");
    }

    private static String[] getListOfStudents(Scanner scanner) {
        String input;
        int i;
        String[] listOfStudents;

        i = 1;
        listOfStudents = new String[10];


        System.out.print("-> ");
        input = scanner.next();
        if(input.equals("."))
        {
            listOfStudents[0] = input;
            return listOfStudents;
        }
        listOfStudents[0] = input;
        while (i < 10) // handle scann when input mustapha essalih 
        {
            System.out.print("-> ");
            input = scanner.next();
            if(input.equals("."))
                break;
            while(input.length() > 10)
            {
                System.out.print("-> ");
                input = scanner.next();
            }

            listOfStudents[i] = input;
            i++;
        }
        return listOfStudents;
    }
}