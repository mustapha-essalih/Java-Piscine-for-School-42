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

    
    public Transaction(User sender , User recipient , TransferCategory tCategory, Integer transferAmount , String transactionId)
    {
        this.identifier = transactionId;
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.transferCategory = tCategory;
    }
    
    public String getIdentifier() {
        return identifier;
    }




    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }




    public User getRecipient() {
        return recipient;
    }




    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }




    public User getSender() {
        return sender;
    }




    public void setSender(User sender) {
        this.sender = sender;
    }




    public Integer getTransferAmount() {
        return transferAmount;
    }




    public void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }




    public TransferCategory getTransferCategory() {
        return transferCategory;
    }


    public void setTransferCategory(TransferCategory transferCategory) {
        this.transferCategory = transferCategory;
    }

    @Override
    public String toString() 
    {
        if (sender == null || recipient == null) 
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
