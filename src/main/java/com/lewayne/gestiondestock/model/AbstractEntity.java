package com.lewayne.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
//import java.util.Date;


/*@Getter
@Setter
@Builder
@AllArgsConstructor*/
@Data //Lombok : pour gèrer les logs, les getters et les setters
@MappedSuperclass // Classe Abstraite: Toutes les classes vont hériter de cette classe
@EntityListeners(AuditingEntityListener.class) // fourni par spring
// cet annotation va automatiquement écouter la classe AbstractEntity
// et chaque fois qu'elle trouve les attributs @CreatedDate, @LastModifiedDate elle va automatiqument mettre à jour ces champs
// dans la base de données lors de l'enregistrement. elle va donc assigné une valeur à CreatedDate et à LastModifiedDate
public class AbstractEntity implements Serializable {
    //public AbstractEntity(){}

    @Id
    @GeneratedValue //avec auto on délèque à Hibernate la stratégie de création ou de génération des ID
    private Integer id;

    @CreatedDate //dire à Hibernate que c'est une date de création
    @Column(name = "creationDate", nullable = false)  // donner un nom à cette date, et dire aussi qu'elle ne doit pas être null
    @JsonIgnore // On n'a pas besoin de cet attribut quand t'invoque notre API. ce sont des propriètés techniques
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "LastModifiedDate")
    @JsonIgnore
    private Instant lastUpdateDate;
}
