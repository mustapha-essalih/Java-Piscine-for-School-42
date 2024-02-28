/**
 * User
 */
public class User {

    private final Integer ID;    
    private String   name;
    private int balance;
    private TransactionsList transactions;  

     
    
    public User( String name, int balance)
    {
        ID = UserIdsGenerator.getInstance().generateId();;
        if (name == null || name.isEmpty()) 
            System.out.println("name cannot be null or empty");    
        else
            this.name = name;
        if (balance < 0) 
            System.out.println("balance should be greter then 0");    
        else
            this.balance = balance;
        transactions = new TransactionsLinkedList();
    }


    public TransactionsList getTransactions() {
        return transactions;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) 
            this.balance = 0;
        else
            this.balance = balance;
    }

    public Integer getId() {
        return ID;
    }


    @Override
    public String toString() {
        return "User [ id=" + this.ID + " name=" + name + ", balance=" + balance + "]";
    }

}

class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String error){
        super(error);
    }
    
}