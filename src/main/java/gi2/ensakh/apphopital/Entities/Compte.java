package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")

public class Compte {

    @Id
    private String username;
    private String mdp;

    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store enum values as strings
    private Role role; // Use the Role enum type instead of defining the enum values here

   @OneToOne(mappedBy = "compte")
   private Personne personne;
   @OneToMany(mappedBy = "compte")
    List<RendezVous> rendezVous;
}

