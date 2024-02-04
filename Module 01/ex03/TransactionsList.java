/**
 * TransactionsList
 */
public interface TransactionsList {

    void addTransaction(Transaction data);
    void removeTransactionByID(String transactionId); 
    void displayTransaction();
    Transaction[] transformIntoArray ();
}