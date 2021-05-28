package com.lewayne.gestiondestock.services.auth;

import com.lewayne.gestiondestock.dto.UtilisateurDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.model.Utilisateur;
import com.lewayne.gestiondestock.model.auth.ExtendedUser;
import com.lewayne.gestiondestock.repository.UtilisateurRepository;
import com.lewayne.gestiondestock.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService utilisateurService;

    //private UtilisateurRepository utilisateurRepository;

// on veut chercher un utilisateur par son userName : service pour  . . .
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       UtilisateurDTO utilisateurDTO = utilisateurService.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateurDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        //return new ExtendedUser(utilisateurDTO.getEmail(), utilisateurDTO.getMoteDePasse(), utilisateurDTO.getEntreprise().getId(), authorities); /**/
        /*Utilisateur utilisateur = utilisateurRepository.findUtilisateurByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Aucun user avec ", ErrorCodes.UTILISATEUR_NOT_FOUND)
        );*/

        //return new User("lewayne@gmail.com", "lewayne", Collections.emptyList());
        //return new User(utilisateurDTO.getEmail(), utilisateurDTO.getMoteDePasse(), Collections.emptyList());

        // si l'utilisateur existe alors on renvoi un objet de type User
        //  return new User(utilisateur.getEmail(), utilisateur.getMoteDePasse(), Collections.emptyList());

        //return new User("lewayne", "lewayne", Collections.emptyList());
        //return new ExtendedUser(utilisateurDTO.getEmail(), utilisateurDTO.getMoteDePasse(), utilisateurDTO.getEntreprise().getId(), Collections.emptyList());

        return new ExtendedUser(utilisateurDTO.getEmail(), utilisateurDTO.getMoteDePasse(), utilisateurDTO.getEntreprise().getId(), authorities);
    }
}
