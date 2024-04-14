package gi2.ensakh.apphopital.Dtos;

import gi2.ensakh.apphopital.Entities.StatusRDV;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PatientRdvDto {
    private String cin;
    private String nom;
    private String prenom;
    private Long tel;
    private String email;
    private LocalDateTime date;
    private StatusRDV statusRDV;
}