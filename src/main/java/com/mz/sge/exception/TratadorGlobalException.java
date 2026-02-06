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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class TratadorGlobalException{

//Not Found
@ExceptionHandler(RecursoNaoEncontradoException.class)
public ResponseEntity<ApiErrorResponse> recursoNaoEncontradoHandler(RecursoNaoEncontradoException e , HttpServletRequest request){
log.warn("Recurso não encontrado.caminho:{}",request.getRequestURI(),e);
ApiErrorResponse erro= new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),"Not Found",e.getMessage(),request.getRequestURI());
return 
ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
}

//Internal server error
@ExceptionHandler(RuntimeException.class)
public ResponseEntity<ApiErrorResponse>erroInternoHandler(RuntimeException e, HttpServletRequest request){
log.error("erro interno",e);

return
ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error","Ocorreu um erro no servidor. Tente mais tarde",request.getRequestURI()));
}

//Conflict
@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<ApiErrorResponse>violacaoIntegridadeHandler(DataIntegrityViolationException e,HttpServletRequest request){
log.warn("erro de integridade no caminho:{}",request.getRequestURI(),e);

ApiErrorResponse erro=new ApiErrorResponse(HttpStatus.CONFLICT.value(),"Conflict","Violação de integridade dos dados",request.getRequestURI());
return
ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
}

//Bad request - erro de validacao de campos
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiValidationErrorResponse> erroValidacaoHandler(MethodArgumentNotValidException e , HttpServletRequest r){

log.warn("erro de validação no caminho:{}",r.getRequestURI(),e);
Map<String,String> fields=new HashMap<>();
e.getBindingResult().getFieldErrors().forEach(field -> fields.put(field.getField(),field.getDefaultMessage()));

return
ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiValidationErrorResponse(HttpStatus.BAD_REQUEST.value(),"Bad Request","Houve falha na validação de um ou  mais campos",fields,r.getRequestURI()));


}


}
