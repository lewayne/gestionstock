package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.dto.auth.AuthenticationRequest;
import com.lewayne.gestiondestock.dto.auth.AuthenticationResponse;
import com.lewayne.gestiondestock.model.auth.ExtendedUser;
import com.lewayne.gestiondestock.services.auth.ApplicationUserDetailsService;
import com.lewayne.gestiondestock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.lewayne.gestiondestock.utils.Constantes.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)  // parcequ'in ne crée pas une interface
public class AuthenticationController{


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")   //il faut crée un postMapping pour renvoyer mon token
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        /*authenticationManager.authenticate( // vérifi que l'utilisateur existe dans la base de données avec ce login et ce mot de passe
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                ) // si l'utilisateur n'existe pas on lève une exception
                  // badCREDENCIAL if .....
        );*/
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin()); // on cherche l'utilisateur

        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails); // on génère le token

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
        /*authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLogin(),
                        authenticationRequest.getPassword()
                )
        );

        //Verifi si l'utilisateur existe dans la base de donnée
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

        //return ResponseEntity.ok(AuthenticationResponse.builder().accessToken("lewayne_access_token").build());

        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }*/
}
