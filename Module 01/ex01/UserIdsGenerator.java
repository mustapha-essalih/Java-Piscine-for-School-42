/**
 * UserIdsGenerator
 */
public class UserIdsGenerator {

    private static UserIdsGenerator instance; 
    private Integer id = 0;

    // Private constructor to prevent instantiation from outside
    private UserIdsGenerator(){}

    public static UserIdsGenerator getInstance(){

        if (instance == null) // for first time
            instance = new UserIdsGenerator();
        return instance;
    }

    public Integer generateId(){
        id++;
        return id;
    }
}