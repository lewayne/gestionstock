package com.lewayne.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="article")
public class Article extends  AbstractEntity{

    @Column(name = "codearticle")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxtva")
    private BigDecimal tauxTva;

    @Column(name = "prixunitairettc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne()
    @JoinColumn(name = "idcategory")
    private Categorie category;
    /**category
     * @JoinColumn(name = "idcategory")   : Permet de spécifier la colonne que nous utiliserons pour rejoindre une association d'entitéou une collection d’éléments.
     *
     * l'entité Article aura une colonne clé étrangère "idcategory" se référant à l'id d'attribut principal de notre entité Categorie.
     *
     * Une fois que nous avons défini le côté propriétaire de la relation, Hibernate a déjà toutes les informations dont il a besoin
     * pour cartographier cette relation dans notre base de données.
     *
     * Pour rendre cette association bidirectionnelle, tout ce que nous aurons à faire est de définir le côté référencement.
     * L’inverse ou le côté référent ne fait que cartographier le côté propriétaire.
     * Nous pouvons facilement utiliser l’attribut cartographié par @OneToMany annotation pour le faire. :@OneToMany(mappedBy = "category")
     */


    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

    /*@ManyToOne()
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;*/

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "article")
    private List<MvtStock> mvtStocks;

    @OneToMany(mappedBy = "article")
    private List<LigneVente> ligneVentes;




}
