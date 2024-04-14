package gi2.ensakh.apphopital.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
@Data
public class checkTime {
    String cin;
    String username;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date date_demande;
}
