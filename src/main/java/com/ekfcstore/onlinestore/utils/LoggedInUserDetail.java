package com.ekfcstore.onlinestore.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticatedPrincipal;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInUserDetail implements AuthenticatedPrincipal {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private Boolean isEnable;
    private String deviceId;
    private String authToken;
    private Long time;
    private String userType;
    private  String site;
    private byte[] authorities;

    @Override
    public String getName() {
        return Stream.of(firstName, lastName).filter(Objects::nonNull).collect(Collectors.joining(" "));
    }
}
