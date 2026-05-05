package com.zderival.springbootpractice;

public class SoftwareEngineerNotFoundException extends RuntimeException{
    public SoftwareEngineerNotFoundException(Integer id){
        super("Engineer with id " + id + "not found");
    }
}