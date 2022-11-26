package BusinessLayer;

import java.io.Serializable;

public class Client implements Serializable {

    private int userId;
    private String username;
    private String password;
    private Role role;
    private static int count=1;

    public Client(){}

    public Client(String username, String password,Role role) {
        this.username = username;
        this.password = password;
        this.role=role;

        if (this.role == Role.Admin) {
            this.userId = 1;
        } else {
            count++;
            this.userId = count;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString()
    {
        String s= "Name: "+ getUsername()+" Role: "+ getRole();
        return s;
    }
}