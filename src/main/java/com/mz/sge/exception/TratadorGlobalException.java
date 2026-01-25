package com.mz.sge.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import com.mz.sge.dto.ApiErrorResponse;
import jakarta. servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.mz.sge.dto.ApiValidationErrorResponse;

@RestControllerAdvice
public class TratadorGlobalException{

//Not Found
@ExceptionHandler(RecursoNaoEncontradoException.class)
public ResponseEntity<ApiErrorResponse> recursoNaoEncontradoHandler(RecursoNaoEncontradoException e , HttpServletRequest request){
ApiErrorResponse erro= new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),"Not Found",e.getMessage(),request.getRequestURI());
return 
ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
}

//Internal server error
@ExceptionHandler(Exception.class)
public ResponseEntity<ApiErrorResponse>erroInternoHandler(Exception e, HttpServletRequest request){
return
ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorResponse(500,"Internal Server Error","Ocorreu um erro no servidor. Tente mais tarde",request.getRequestURI()));
}

//Conflict
@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<ApiErrorResponse>violacaoIntegridadeHandler(DataIntegrityViolationException e,HttpServletRequest request){
ApiErrorResponse erro=new ApiErrorResponse(409,"Conflict","Não podem existir dados duplicados",request.getRequestURI());
return
ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
}

//Bad request - erro de validacao
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiValidationErrorResponse> erroValidacaoHandler(MethodArgumentNotValidException e , HttpServletRequest r){
Map<String,String> erros=new HashMap<>();
e.getBindingResult().getFieldErrors().forEach(erro -> erros.put(erro.getField(),erro.getDefaultMessage()));

return
ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiValidationErrorResponse(erros,r.getRequestURI()));


}


}
