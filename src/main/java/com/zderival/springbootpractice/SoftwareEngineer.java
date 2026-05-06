package com.zderival.springbootpractice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

// Entity - Declares a Java class as your table for your database
@Entity
public class SoftwareEngineer {
    /* @ID - An identifier for your database to go based on when tracking data values
    (Ex: Software Engineers in this case) */
    @Id
    // @Generateed Value - Automatically inputs the values to you ID
    // (strategy = GenerationType.Identity) - Is the strategy of how you ID numbers are to be set
    // In this case its in numerical order
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String techStack;


    public SoftwareEngineer(Integer id,String name, String techStack){
        this.id = id;
        this.name = name;
        this.techStack = techStack;
    }

    public SoftwareEngineer() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTechStack(String techStack){
        this.techStack = techStack;
    }

    public String getTechStack(){
        return techStack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(techStack, that.techStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techStack);
    }

}
