import java.util.ArrayList;
import java.util.Objects;

/**
 * UsersArrayList
 */
public class UsersArrayList  implements UsersList {

    private User[] users;
    private Integer size;


    public UsersArrayList(){
        users = new User[10];
        size = 0;
    }

    @Override
    public void addUser(User user) {
       
        if (user != null) {

            if (size == users.length) // capacity - 1
            {
                int newSize = size + (size / 2);
                
                User[] tempUsers = new User[newSize]; 
                
                for (int i = 0; i < size; i++) {
                    tempUsers[i] = users[i];
                }

                users = new User[newSize];
                
                for (int i = 0; i < size; i++) {
                    users[i] = tempUsers[i];
                }
            }
            users[size] =  user;
            size++;
        }
    }
 

    @Override
    public User retrieveUserByID(Integer userId) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (users[i].getId() == userId) {
                return users[i];
            }
        }
        throw new UserNotFoundException("user id not found");
    }

    
    @Override
    public User retrieveUserByIndex(int index) {
        
        for (int i = 0; i < size; i++) 
        {
            if (i == index) {
                return users[i];
            }
        }
        throw new UserNotFoundException("user index not found");
    }
    
    @Override
    public Integer getUsersCount() {
        return size;
    }
    
    public Integer getCapacity() {
        return users.length;
    }
}