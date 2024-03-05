package com.ekfcstore.onlinestore.user.dto;

import com.ekfcstore.onlinestore.utils.AppConstants;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {

    private Long user_id;
    private String username;
    private String password;
    private String email;
    private String authorities;
    private boolean active;
    private String type= AppConstants.ROLE_USER;


}
