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
                else if(input == 3){
                    while (this.PerformTransfer() == false) {}
                    displayMenu();
                }
                else if(input == 4){
                    while (this.displayAllTransactionsForUser() == false) {}
                    displayMenu();
                }
                else if(input == 5){
                    while (this.removeTransferByID() == false) {}
                    displayMenu();
                }
            } 
            catch (Exception e) 
            {
                System.out.println(e.getMessage());
                System.out.println("ERROR");
                this.displayMenu();              
            }
        }
    }

    public boolean removeTransferByID() 
    {
        System.out.println("Enter a user ID and a transfer ID");
        
        try {
            scanner = new Scanner(System.in);
            Integer userId = scanner.nextInt();
            String transferId = scanner.next();
            
            try {
                 
                Transaction removedTransaction = transactionsService.deleteTransactionByID(userId, transferId);
                
                System.out.println("Transfer To " + removedTransaction.getRecipient().getName() + "(id = " + removedTransaction.getRecipient().getId() + ") " + removedTransaction.getTransferAmount() + " removed");
                System.out.println("---------------------------------------------------------");
                return true;
            } catch (UserNotFoundException e) {
                System.out.println(e);
                return false;
            }
            catch (TransactionNotFoundException e) {
                System.out.println(e);
                return false;
            }
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR");
            return false;              
        }

    } 

    public boolean displayAllTransactionsForUser()  
    {
        System.out.println("Enter a user ID");
         
        try {
            scanner = new Scanner(System.in);
            Integer userId = scanner.nextInt();

            try {
                Transaction[] allTransactionsForUser = this.transactionsService.getTransfersForUser(userId);
                for(int i = 0; i < allTransactionsForUser.length ; i++)
                {
                    System.out.println("To " + allTransactionsForUser[i].getRecipient().getName() + 
                    "(id = " +   allTransactionsForUser[i].getRecipient().getId() + ") -" + 
                    allTransactionsForUser[i].getTransferAmount() + " with id = " +
                       allTransactionsForUser[i].getIdentifier());
                }        
                System.out.println("-----------------------------------");
               
                return true;
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
 

    }


    public boolean  PerformTransfer()
    {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        
        try {
            scanner = new Scanner(System.in);
            Integer senderId = scanner.nextInt();
            Integer recipiId = scanner.nextInt();
            Integer transferAmount = scanner.nextInt();

            try {
                
                this.transactionsService.performTransfer(senderId, recipiId, transferAmount);
                System.out.println("The transfer is completed");
                System.out.println("---------------------------------------------------------");
                return true;
            } catch (UserNotFoundException e) {
                System.out.println(e);
                return false;
            }
            catch (TransactionFailException e) {
                System.out.println(e);
                return false;
            }

        } 
        catch (Exception e) 
        {
            System.out.println("ERROR");
            return false;              
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