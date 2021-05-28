package com.lewayne.gestiondestock.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    private String login;
    private String password;

    /*public AuthenticationRequest (String login, String password){
        login = login;
        password = password;
    }*/
}
