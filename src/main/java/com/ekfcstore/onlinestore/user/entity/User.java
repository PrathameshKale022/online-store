package com.ekfcstore.onlinestore.user.entity;


import com.ekfcstore.onlinestore.utils.AppConstants;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username;
    private String password;
    private String email;
    private String authorities;
    private boolean active;
    private String type= AppConstants.ROLE_USER;

}
