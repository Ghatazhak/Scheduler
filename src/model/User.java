package model;

public class User {
    private int userId;
    private String username;
    private String password;

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
    /** This method gets user id.
     * @return  user id*/
    public int getUserId() {
        return userId;
    }

    /** This method gets username.
     * @return  username.*/
    public String getUsername() {
        return username;
    }
    /** This method gets password.
     * @return  password.*/
    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return String.valueOf(userId);
    }
}
