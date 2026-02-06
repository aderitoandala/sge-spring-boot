package com.mz.sge.dto;
import java.time.LocalDateTime;
//import org.springframework.http.HttpStatus;

public class ApiErrorResponse{
private int status;
private String error;
private String message;
private String path;
private LocalDateTime timestamp;


public ApiErrorResponse(int status, String error,String message,String path){
this.status=status;
this.error=error;
this.message=message;
this.path=path;
this.timestamp=LocalDateTime.now();
}

//public ApiErrorResponse(int status,){}

//getters

public int getStatus(){
return status;
}

public String getMessage(){
return message;
}

public String getError(){
return error;
}


public String getPath(){
return path;
}

public LocalDateTime getTimestamp(){
return timestamp;
}






}
