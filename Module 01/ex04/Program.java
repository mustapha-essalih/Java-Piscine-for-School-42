/*** InnerProgram
 */

 


public class Program {

    public static void main(String[] args) {
        
        User user1 = new User("mustapha", 5000);
        User user2 = new User("nasser", 5000);
        
        Transaction transaction1 = new Transaction(user1, user2, TransferCategory.debits, 20);
        Transaction transaction2 = new Transaction(user1, user2, TransferCategory.debits, 20);

        try {
            user1.getTransactionsList().addTransaction(transaction1);
            user1.getTransactionsList().addTransaction(transaction2);
            
            Transaction[] transactions = user1.getTransactionsList().transformIntoArray();
            
            for (int i = 0; i < transactions.length; i++) {
                System.out.println(transactions[i].getIdentifier());
            }
            // user1.getTransactionsList().displayTransaction();
            
            System.out.println();
            // user1.getTransactionsList().removeTransactionByID(transaction1.getIdentifier());
    
            // user1.getTransactionsList().displayTransaction();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
           
            // user1.getTransactionsList().removeTransactionByID("test");
    
            // user1.getTransactionsList().displayTransaction();
            
        } catch (Exception e) {
            System.out.println(e);
        }
         
        // transactionsLinkedList.addTransaction(transaction1);

        // transactionsLinkedList.displayTransaction();
        
    }
}




 