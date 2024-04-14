package gi2.ensakh.apphopital.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateEvent;
    private String Title;
    @ManyToOne
    private Medecin medecin;


}