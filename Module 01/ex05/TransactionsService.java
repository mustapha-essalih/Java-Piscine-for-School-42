import java.util.UUID;

/**
 * TransactionsService
 */
public class TransactionsService {

    private UsersList usersList;
    
    public TransactionsService(){
        usersList = new UsersArrayList();
    }
    
    public void addUser(User user)
    {
        usersList.addUser(user);
    }
    
    public Integer getUserBalance(Integer userId){

        User user = usersList.retrieveUserByID(userId);
        return user.getBalance();
    }
    
    public String performTransfer(int senderID, int recipientID, int transferAmount) {
        
        User sender = usersList.retrieveUserByID(senderID);
        User recipient = usersList.retrieveUserByID(recipientID);

        UUID uuid = UUID.randomUUID(); 
        String transactionId = uuid.toString();

        Transaction transactionOfSender = new Transaction(sender, recipient, TransferCategory.debits, transferAmount, transactionId);        
        Transaction transactionOfRecipent = new Transaction(sender, recipient, TransferCategory.credits, transferAmount, transactionId);        

        sender.getTransactions().addTransaction(transactionOfSender);
        recipient.getTransactions().addTransaction(transactionOfRecipent);
        return transactionId;
    }

    // A "transfer" here refers to a financial transaction, which could be a debit or credit transaction.
    public Transaction[] getTransfersForUser(Integer userId) {

        User user = usersList.retrieveUserByID(userId);

        Transaction[] trnsfersOfUser = user.getTransactions().transformTransactionToArray();

        return trnsfersOfUser;
    }

    boolean isHavePair(Transaction  transactionsOfCurrentUser, Transaction[] transactions)
    {
         for (int i = 0; i < transactions.length; i++) {
            if (transactions[i].getIdentifier().equals(transactionsOfCurrentUser.getIdentifier())) {
                return false;
            }
        }
        return true;
    }
    
    public Transaction[] checkValidityOfTransactions() {
        
        TransactionsLinkedList unpairTransaction = new TransactionsLinkedList();
         
        Integer numberOfUser = usersList.retrieveNumberOfUsers();
        for (int i = 0; i < numberOfUser; i++) 
        {
            User user = usersList.retrieveUserByIndex(i);
            Transaction[] allTransactionsOfuser = user.getTransactions().transformTransactionToArray();

            for (int j = 0; j < allTransactionsOfuser.length; j++) {
                if (user == allTransactionsOfuser[j].getSender())// if this user is a sender 
                {
                    if(isHavePair(allTransactionsOfuser[j] ,allTransactionsOfuser[j].getRecipient().getTransactions().transformTransactionToArray())){
                        unpairTransaction.addTransaction(allTransactionsOfuser[j]);
                    }
                }
                else if(user == allTransactionsOfuser[j].getRecipient()){
                    if(isHavePair(allTransactionsOfuser[j] ,allTransactionsOfuser[j].getSender().getTransactions().transformTransactionToArray())){
                        unpairTransaction.addTransaction(allTransactionsOfuser[j]);
                    }
                }
            }

        }
        unpairTransaction.displayTransaction();
        return unpairTransaction.transformTransactionToArray();
    }

    public void deleteTransactionByID(Integer userId , String transactionId)
    {
        User user = usersList.retrieveUserByID(userId);
        user.getTransactions().removeTransactionById(transactionId);
    }

    public UsersList getUsersList() {
        return usersList;
    }
}