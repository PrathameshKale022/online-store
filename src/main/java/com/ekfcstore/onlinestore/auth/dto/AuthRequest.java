package com.ekfcstore.onlinestore.auth.dto;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ToString
public class AuthRequest {

    @Pattern(regexp= "^[0-9]*$", message = "Username should be only in digits")
    @Length(min = 9, max = 9, message = "Username should be 9 digits")
    @NotNull(message = "Please enter user name")
    @NotEmpty(message = "Please enter user name")
    private String username;
    @NotNull(message = "Please enter password")
    @NotEmpty(message = "Please enter password")
    @Size(min = 6, max = 6, message = "Length should be 6 digits")
    private String password;


}
