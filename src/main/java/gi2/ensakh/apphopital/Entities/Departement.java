package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "num")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num ;
    private String nom_dep ;
    @JsonManagedReference
    @OneToMany(mappedBy = "dep")
    private List <Medecin> listMedecin;
    @JsonBackReference
    @ManyToOne
    private Secretaire secretaire;

}
