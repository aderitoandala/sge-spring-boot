package com.mz.sge.dto;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

public class ApiValidationErrorResponse{

private LocalDateTime timestamp;
private Map<String,String> erros;
private String path;

public ApiValidationErrorResponse(Map<String,String> erros,String path){
this.timestamp=LocalDateTime.now();
this.erros=erros;
this.path=path;
}


public Map<String,String> getErros(){
return erros;
}

public String getPath(){
return path;
}

public LocalDateTime getTimestamp(){
return timestamp;
}







}
