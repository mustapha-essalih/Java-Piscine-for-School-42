import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menu
 */
public class Menu {

    private TransactionsService transactionsService;
    int mod; 
    private static Scanner scanner ;
    public Menu(int mod)
    {
        this.mod = mod;
        transactionsService = new TransactionsService();
    }

    public void callApp(){

        this.getInput();
        
        this.displayMenu();
        
         
        
            
        

    }
    

    private void getInput() 
    {
        boolean isErrorInput = true;

        this.displayMenu();
        while (isErrorInput) {
  
            try {
                scanner = new Scanner(System.in);
                Integer input = scanner.nextInt();
                if (input == 1) 
                {
                    while (this.addUser() == false) {}
                    displayMenu();
                }
                else if (input == 2) {
                    while (this.viewUserBalances() == false) {}
                    displayMenu();
                }
            } 
            catch (Exception e) 
            {
                System.out.println("ERROR");
                this.displayMenu();              
            }
        }
    }

    
    public boolean viewUserBalances() throws UserNotFoundException
    {
        System.out.println("Enter a user ID");
        
        try {
             scanner = new Scanner(System.in);
            Integer userId = scanner.nextInt();

            try {
                User user = transactionsService.getUsersList().retrieveUserByID(userId);
                Integer userBalance = transactionsService.getUserBalance(userId);
                System.out.println(user.getName() + " - " + userBalance);
                System.out.println("---------------------------------------------------------");

            } catch (UserNotFoundException e) {
                System.out.println(e);
                return false;
            }

        } 
        catch (Exception e) 
        {
            System.out.println("ERROR");
            return false;              
        }
        return true;
        
        
        // System.out.println(this.transactionsService.getUserBalance(userId ));

        // System.out.println("---------------------------------------------------------");
    } 

    public boolean addUser()
    {
        System.out.println("Enter a user name and a balance");
        
        try {
            scanner = new Scanner(System.in);
            String name = scanner.next();
            Integer balance = scanner.nextInt();
            User newUser;
             
            newUser = new User(name, balance);
            if (newUser.getBalance() == 0) {
                return false;
            }
            this.transactionsService.addUser(newUser);
            System.out.println("User with id = " + newUser.getId() + " is added");
            System.out.println("---------------------------------------------------------");
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR");
            return false;              
        }
        return true;
    }

    private void displayMenu() {
        

        System.out.println("1. Add a user\n" +
            "2. View user balances\n" +
            "3. Perform a transfer\n" +
            "4. View all transactions for a specific user" );
        if(this.mod == 1)
        {
            System.out.println("5. DEV - remove a transfer by ID\n" +
            "6. DEV - check transfer validity");
            System.out.println("7. Finish execution");
        }
        else{
            System.out.println("5. Finish execution");
        }
        
        
    }

    public TransactionsService getTransactionsService() {
        return transactionsService;
    }
    
}