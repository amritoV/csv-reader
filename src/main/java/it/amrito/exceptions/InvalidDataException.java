package it.amrito.exceptions;

import java.io.Serializable;

public class InvalidDataException extends RuntimeException implements Serializable {


    public InvalidDataException(String message, int line, int column){
        super(message +" line: "+ line + " column: "+ column);
    }

}
