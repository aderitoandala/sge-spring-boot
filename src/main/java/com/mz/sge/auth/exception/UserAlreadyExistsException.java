package com.mz.sge.auth.exception;

public class UserAlreadyExistsException extends RuntimeException{

public UserAlreadyExistsException(){

	super("Registration failed");
}

public UserAlreadyExistsException(String message){

	super(message);
}


}
