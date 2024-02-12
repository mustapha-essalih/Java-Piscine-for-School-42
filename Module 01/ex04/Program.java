/*** InnerProgram
 */

 

// so it is an abstraction that hide complisity in controller and implement this complicity in service

public class Program {

    public static void main(String[] args) {
        
        User user1 = new User("mike", 500);
        User user2 = new User("john", 500);

        TransactionsService transactionsService = new TransactionsService();

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);

        transactionsService.performTransfer(user1.getId(), user2.getId(), 50);
        user1.getTransactions().displayTransaction(); // same transacation id
        System.out.println();
        user2.getTransactions().displayTransaction(); // same transacation id
    
        System.out.println();

        try {
            transactionsService.performTransfer(user1.getId(), user2.getId(), 22220);
        } catch (TransactionFailException e) {
            System.out.println(e);
        }
    

        String t1 = transactionsService.performTransfer(user1.getId(), user2.getId(), 50);
        String t2 = transactionsService.performTransfer(user1.getId(), user2.getId(), 10);
        String t3 = transactionsService.performTransfer(user1.getId(), user2.getId(), 20);
        String t4 = transactionsService.performTransfer(user1.getId(), user2.getId(), 30);
    
        System.out.println();

        {
            
            Transaction[] transfersOfuser = transactionsService.getTransfersForUser(user1.getId());
            
            for (int i = 0; i < transfersOfuser.length; i++) {
                System.out.println(transfersOfuser[i]);
            }
            
        }

        System.out.println();
        transactionsService.deleteTransactionByID(user1.getId(), t1);
        
        {
            Transaction[] transfersOfuser = transactionsService.getTransfersForUser(user1.getId());

            System.out.println();

            for (int i = 0; i < transfersOfuser.length; i++) {
                System.out.println(transfersOfuser[i]);
            }
        }
        
        System.out.println();
        
        transactionsService.deleteTransactionByID(user1.getId(), t4);
        transactionsService.deleteTransactionByID(user2.getId(), t3);

        Transaction[] unpaired = transactionsService.checkValidityOfTransactions();         
        for (int i = 0; i < unpaired.length; i++) {
            System.out.println(unpaired[i]);
        }
    }
}
 