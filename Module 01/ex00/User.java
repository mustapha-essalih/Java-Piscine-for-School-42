/**
 * User
 */
public class User {

    private static Integer lastID = 0;
    private  Integer identifier;
    private String   name;
    private Integer balance;

    public User( String name, Integer balance)
    {
        identifier = lastID++;
        if (name == null || name.isEmpty()) 
            System.out.println("name cannot be null or empty");    
        else
            this.name = name;
        if (balance < 0) 
            System.out.println("balance should be greter then 0");    
        else
            this.balance = balance;
    }

    public  Integer getIdentifier() {
        return identifier;
    }
 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0) 
            this.balance = 0;
        else
            this.balance = balance;
    }

    @Override
    public String toString() {
        return "User [ id=" + identifier + " name=" + name + ", balance=" + balance + "]";
    }
}