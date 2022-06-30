package com.acme.mylawyerbe.shared.domain.service.communication;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public abstract class BaseResponse<T> {

    private boolean success;
    private String message;
    private T resource;

    //cuando la respuesta es que ha fallado
    public BaseResponse(String message){
        this.success = false;
        this.message = message;
        this.resource = null;
    }

    //cuando la respuesta es que ha sido satisfactoria
    public BaseResponse(T resource){
        this.success = true;
        this.message = Strings.EMPTY;
        this.resource = resource;
    }

}
