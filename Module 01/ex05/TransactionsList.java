/**
 * TransactionsList
 */
public interface TransactionsList {

    void addTransaction(Transaction data);
    Transaction removeTransactionById(String transactionId);
    Transaction[] transformTransactionToArray(); 
    public void displayTransaction();
}