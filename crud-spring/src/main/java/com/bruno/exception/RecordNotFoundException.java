package com.bruno.exception;

public class RecordNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id){
        super("Registro nao encontrado para o id: " + id);
    }
}
