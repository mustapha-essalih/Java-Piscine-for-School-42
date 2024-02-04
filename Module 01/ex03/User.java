/**
 * User
 */
public class User {

    private final Integer id;
    private String  name;
    private int balance;
    private TransactionsList transactionsList =  new TransactionsLinkedList();

    public User( String name, int balance)
    {
        id = UserIdsGenerator.getInstance().generateId();;
        if (name == null || name.isEmpty()) 
            System.out.println("name cannot be null or empty");    
        else
            this.name = name;
        if (balance < 0) 
            System.out.println("balance should be greter then 0");    
        else
            this.balance = balance;
    }

    public TransactionsList getTransactionsList() { // because TransactionsLinkedList inherit from TransactionsList
        return transactionsList;
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
        return id;
    }


    @Override
    public String toString() {
        return "User [ id=" + this.id + " name=" + name + ", balance=" + balance + "]";
    }


    

}