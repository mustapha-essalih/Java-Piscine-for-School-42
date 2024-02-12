
public class Program {

    public static void main(String[] args) {
        
        User user1 = new User("mustapha" , 500);
        User user2 = new User("stof", 5000);
        
        Transaction transaction1 = new Transaction(user1 , user2 , TransferCategory.debits , 500 );
        System.out.println(transaction1);
        
        Transaction transaction2 = new Transaction(user1 , user2 , TransferCategory.debits , 600);
        System.out.println(transaction2);
    }
}






// John -> Mike, -500, OUTCOME, transaction ID
// Mike -> John, +500, INCOME, transaction ID