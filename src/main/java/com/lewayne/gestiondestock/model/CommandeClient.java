package com.lewayne.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="commandeClient")
public class CommandeClient  extends AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant dateCommande;

    /*@Column(name = "etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;*/

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne()  // propriètaire de la relation
    @JoinColumn(name = "idclient") // on crée cet attribut(clé étrangère) dans l'entité CommandeClient
    private Client client;

    @OneToMany(mappedBy = "commandeClient")
    private List<LigneCommandeClient> ligneCommandeClients;
}
