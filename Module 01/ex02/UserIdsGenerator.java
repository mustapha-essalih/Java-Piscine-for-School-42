/**
 * UserIdsGenerator
 */
public class UserIdsGenerator {

    // The single instance of the class
    private static UserIdsGenerator instance; 
    private Integer id = 0;

    // Private constructor to prevent instantiation from outside
    private UserIdsGenerator(){}

    // Method to get the instance of the Singleton class
    public static UserIdsGenerator getInstance(){

        if (instance == null) 
            instance = new UserIdsGenerator();

        return instance;
    }

    public Integer generateId(){
        id++;
        return id;
    }
    
}