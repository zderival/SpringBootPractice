package com.zderival.springbootpractice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repositories are your connection to databases. They are only needed to be an interface everytime
// JPARepository - They are what connects to your Table (Or @Entity) that you wish to use
// Takes two Generics. First one takes the entity (Ex: User).
// Second one takes the value of your @ID (Ex: Integer)
public interface UserRepository extends JpaRepository<User, Integer> {
    //Optional<User> - Specify that the object with "User" data type may or may not exist
    // Throw an exception if that's the case

    // findByUsername(String username) - By writing this method, JPA goes into SQL for you and writes:
    //"SELECT * FROM users WHERE username = username"
    Optional<User> findByUsername(String username);
}
