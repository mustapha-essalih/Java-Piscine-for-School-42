/**
 * TransactionsList
 */
public interface TransactionsList {

    void addTransaction(Transaction data);
    void removeTransactionByID(String transactionId); 
    Transaction[] transformIntoArray();
    void displayTransaction();
}