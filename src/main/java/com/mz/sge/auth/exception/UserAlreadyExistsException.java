package com.mz.sge.auth.exception;

public class UserAlreadyExistsException extends RuntimeException{

public UserAlreadyExistsException(){

	super("Registro sem sucesso");
}

public UserAlreadyExistsException(String message){

	super(message);
}


}
