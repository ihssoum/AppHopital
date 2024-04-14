package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type" ,length=10)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cin")

public abstract class Personne {

    @Id
    private String cin;
    private String nom ;
    private String prenom ;
    private Long tel ;
    private String email ;
    private Date dateN;
    private String adresse;
    @OneToOne
  private Compte compte;



}