/**
 * TransactionNotFoundException
 */
public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String error){
        super(error);
    }
    
}