/*** InnerProgram
 */

 


public class Program {

    public static void main(String[] args) {
        
        User user1 = new User("mike", 500);
        User user2 = new User("john", 500);

        TransactionsService transactionsService = new TransactionsService();

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);

        transactionsService.performTransfer(user1.getId(), user2.getId(), 50);
        user1.getTransactions().displayTransaction(); // same transacation id
        user2.getTransactions().displayTransaction(); // same transacation id
    
    

        try {
            transactionsService.performTransfer(user1.getId(), user2.getId(), 22220);
        } catch (TransactionFailException e) {
            System.out.println(e);
        }
    

        String t1 = transactionsService.performTransfer(user1.getId(), user2.getId(), 50);
        String t2 = transactionsService.performTransfer(user1.getId(), user2.getId(), 10);
        String t3 = transactionsService.performTransfer(user1.getId(), user2.getId(), 20);
        String t4 = transactionsService.performTransfer(user1.getId(), user2.getId(), 30);
    
        {
            
            Transaction[] transfersOfuser = transactionsService.getTransfersForUser(user1.getId());
            
            for (int i = 0; i < transfersOfuser.length; i++) {
                System.out.println(transfersOfuser[i]);
            }
            
        }

        transactionsService.deleteTransactionByID(user1.getId(), t1);
        
        {
            Transaction[] transfersOfuser = transactionsService.getTransfersForUser(user1.getId());

            System.out.println();

            for (int i = 0; i < transfersOfuser.length; i++) {
                System.out.println(transfersOfuser[i]);
            }
        }
        
        transactionsService.deleteTransactionByID(user1.getId(), t1);
        transactionsService.deleteTransactionByID(user2.getId(), t3);

        transactionsService.checkValidityOfTransactions();         
    }
}

// / should return all unacknowledged transactions

// get all users
// get all transactions of user
// each transactions has sender and recipent
// loop on transactions of sender and recipent

// evry user has transactions
// evry transaction haas sender and reciver
// transaction can have sender and can reciver missing or via vesca