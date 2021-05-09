package com.lewayne.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="categorie")
public class Categorie extends AbstractEntity{
    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy = "category") //on crée ce champs dans Article -- bidirectionnelle
    private List<Article> articles;
    /*
     * Pour rendre cette association bidirectionnelle, tout ce que nous aurons à faire est de définir le côté référencement.
     * L’inverse ou le côté référent ne fait que cartographier le côté propriétaire.
     * Nous pouvons facilement utiliser l’attribut cartographié par @OneToMany annotation pour le faire. :@OneToMany(mappedBy = "category")
     */
}
