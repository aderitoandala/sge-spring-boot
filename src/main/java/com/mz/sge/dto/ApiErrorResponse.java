package com.mz.sge.dto;
import java.time.LocalDateTime;
//import org.springframework.http.HttpStatus;

public class ApiErrorResponse{
private int status;
private String title;
private String message;
private String path;
private LocalDateTime timestamp;


public ApiErrorResponse(int status, String title,String message,String path){
this.status=status;
this.title=title;
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

public String getTitle(){
return title;
}


public String getPath(){
return path;
}

public LocalDateTime getTimestamp(){
return timestamp;
}






}
