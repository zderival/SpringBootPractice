package com.zderival.springbootpractice;
// This is a custom runtime exception that represents missing Software Engineer
// Runtime Exception is used so the exception doesn't have to be thrown, but rather sent to
// GlobalExceptionHandler

public class SoftwareEngineerNotFoundException extends RuntimeException{
    public SoftwareEngineerNotFoundException(Integer id){
        super("Engineer with id " + id + "not found");
    }
}