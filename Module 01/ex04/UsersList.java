public interface UsersList {
    void addUser(User user);
    User retrieveUserByID(Integer userId);
    User retrieveUserByIndex(int index);
    Integer retrieveNumberOfUsers();
}