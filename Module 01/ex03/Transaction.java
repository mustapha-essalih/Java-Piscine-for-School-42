import java.util.UUID;

enum TransferCategory{ // debit (outgoing) or credit (incoming).
    debits,
    credits
}

public class Transaction {
    
    private String identifier;
    private User recipient; 
    private User sender;
    private Integer transferAmount; // A numeric value representing the amount of money being transferred. It should be positive for incoming transactions and negative for outgoing transactions.
    private TransferCategory transferCategory;


    public Transaction(User sender , User recipient , TransferCategory tCategory, Integer transferAmount )
    {
        UUID uuid = UUID.randomUUID(); 
        if (recipient != null && sender != null && sender != recipient)
        {
            if (transferAmount <= sender.getBalance() && transferAmount > 0) 
            {
                if (tCategory == TransferCategory.debits) 
                {
                    this.identifier = uuid.toString();
                    this.sender = sender;
                    this.recipient = recipient;
                    sender.setBalance(sender.getBalance() - transferAmount);
                    recipient.setBalance(recipient.getBalance() + transferAmount);
                    this.transferAmount = transferAmount;
                    this.transferCategory = tCategory;
                }
            }
        } 
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    
    @Override
    public String toString() 
    {
        if (sender == null ) 
            return "transaction is fail";
        
        return sender.getName() + " -> " + recipient.getName() + ", -" + this.transferAmount + ", OUTCOME, " + this.identifier + "\n" +
            recipient.getName() + " -> " + sender.getName() + ", +" + this.transferAmount + ", INCOME, " + this.identifier ;
    }
}






 
class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String error){
        super(error);
    } 
}

class TransactionFailException extends RuntimeException{
    
    public TransactionFailException(String message){
        super(message);
    }
}
