package gi2.ensakh.apphopital.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import gi2.ensakh.apphopital.Entities.StatusRDV;
import lombok.Data;

import java.util.Date;

@Data
public class RdvDto {
    private StatusRDV statusRDV;
    @JsonProperty("Patientcin")
    private String patientCin;
    @JsonProperty("DRcin")
    private String DRcin;
    @JsonProperty("username")
    private String username;
    private Date date_demande;
    private String nom ;
    private String prenom ;
    private Long tel ;
    private String email ;
    private String adresse;

}

