package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor @Data @NoArgsConstructor
@DiscriminatorValue("Medecin")
public class Medecin extends Personne {
    private String specialite;
    @JsonBackReference
    @ManyToOne
    private Departement dep;
    @JsonBackReference
    @ManyToMany
   @JoinTable(name="MedPatient")
    private List<Patient> patients;
    @JsonManagedReference

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> RDV;
    @JsonManagedReference

    @OneToMany(mappedBy ="medecin")
   private List<Reclamation> reclamations;
    private  Integer durationRDV;
    @Enumerated(EnumType.STRING)
    OnOff onOff;
    private Integer limitNumRDV;
    private LocalTime debutRDV;

}
