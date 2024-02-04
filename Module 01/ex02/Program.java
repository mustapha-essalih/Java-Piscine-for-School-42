/*** InnerProgram
 */

 


public class Program {

    public static void main(String[] args) {
        
        UsersArrayList usersArrayList = new UsersArrayList();

        for (int i = 0; i < 9; i++) {
            usersArrayList.addUser(new User("mm" , 200));
        }
        System.out.println(usersArrayList.getSize());

        for (int i = 0; i < 10; i++) {
            usersArrayList.addUser(new User("mm" , 200));
        }

        System.out.println(usersArrayList.getSize());

        try{
            User user1 = usersArrayList.retrieveUserByIndex(7);
            User user2 = usersArrayList.retrieveUserByID(2);
            System.out.println(usersArrayList.retrieveNumberOfUsers());
            System.out.println(user1);
            System.out.println(user2);
        }
        catch(UserNotFoundException e) {
            System.out.println(e);
        }


        try{
            User user1 = usersArrayList.retrieveUserByIndex(70);
            User user2 = usersArrayList.retrieveUserByID(2);
            System.out.println(usersArrayList.retrieveNumberOfUsers());
            System.out.println(user1);
            System.out.println(user2);
        }
        catch(UserNotFoundException e) {
            System.out.println(e);
        }
    }
}




 