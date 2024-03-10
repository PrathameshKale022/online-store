package com.ekfcstore.onlinestore.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Boolean status = true;

    private String code;

    private String message;

    private T data;
}
