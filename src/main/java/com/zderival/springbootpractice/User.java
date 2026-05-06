package com.zderival.springbootpractice;
import jakarta.persistence.*;

// Entity - Declares a Java class as your table for your database
@Entity
/*Table - changes the name of your table. User is a built-in command in SQL so we need to
specify the table name as "users", so it doesn't mix with the command "User" */
@Table(name = "users")
public class User{

    /* @ID - An identifier for your database to go based on when tracking data values
    (Ex: Users in this case) */
    @Id
    // @GeneratedValue  - Automatically inputs the values to you ID
    // (strategy = GenerationType.Identity) - Is the strategy of how you ID numbers are to be set
    // In this case its in numerical order
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    public User(){}

    public User(Integer id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
