package com.zderival.springbootpractice;

import org.springframework.data.jpa.repository.JpaRepository;


// Repositories are your connection to databases. They are only needed to be an interface everytime
// JPARepository - They are what connects to your Table (Or @Entity) that you wish to use
// Takes two Generics. First one takes the entity (Ex: SoftwareEngineer).
// Second one takes the value of your @ID (Ex: Integer)
public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {

}
