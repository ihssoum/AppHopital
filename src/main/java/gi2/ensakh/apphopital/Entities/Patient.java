package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("Patient")
public class Patient extends Personne {
    @JsonManagedReference

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> RDV;
    @ManyToMany(mappedBy = "patients")
    private List<Medecin> medecins;
    @JsonManagedReference

    @OneToMany(mappedBy = "patient")
    private List<Reclamation> reclamations;

}
