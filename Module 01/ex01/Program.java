
public class Program {

    public static void main(String[] args) {
        
        for (int i = 1; i < 5; i++) {
            User user1 = new User("user: " + i, 500);
            System.out.println(user1);
        }

    }
}






// John -> Mike, -500, OUTCOME, transaction ID
// Mike -> John, +500, INCOME, transaction ID