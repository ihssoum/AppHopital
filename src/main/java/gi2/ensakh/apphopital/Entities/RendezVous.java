package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date_RDV;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date_demande;
    @Temporal(TemporalType.TIME)
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;
    @JsonBackReference
    @ManyToOne
    private Medecin medecin;
    @Enumerated(EnumType.STRING)
    private StatusRDV statusRDV;
    @ManyToOne
    Compte compte;


}
