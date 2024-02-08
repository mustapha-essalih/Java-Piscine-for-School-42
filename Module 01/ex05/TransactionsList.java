/**
 * TransactionsList
 */
public interface TransactionsList {

    void addTransaction(Transaction data);
    void removeTransactionById(String transactionId);
    Transaction[] transformTransactionToArray(); 
    public void displayTransaction();
}