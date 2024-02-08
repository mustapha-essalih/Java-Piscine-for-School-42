/**
 * UserNotFoundException
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String error){
        super(error);
    }
    
}