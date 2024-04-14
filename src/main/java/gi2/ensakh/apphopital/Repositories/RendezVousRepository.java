package gi2.ensakh.apphopital.Repositories;
import gi2.ensakh.apphopital.Entities.RendezVous;
import gi2.ensakh.apphopital.Entities.StatusRDV;
import gi2.ensakh.apphopital.Entities.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Integer> {
    @Query("select R.patient.cin from RendezVous R where R.id=:id")
    String findCinPatientByIdRDV(@Param("id")Long id);
    @Query("select R.medecin.cin from RendezVous R where R.id=:id")
    String findCinDrByIdRDV(@Param("id")Long id);
    @Query("SELECT r.medecin.dep.secretaire FROM RendezVous r WHERE r.id = :idRdv")
    Secretaire findSecretaireByRdvId(@Param("idRdv") Long idRdv);
    @Query("select r from RendezVous r where r.medecin.dep.secretaire.compte.username=:username")
    List<RendezVous> listRdvBySec (@Param("username")String username);
    @Query("select count (r) from RendezVous r where r.medecin.cin=:cinMed and r.compte.username=:username and r.date_demande=:dateDemande")
    int numRdvByDay(@Param("cinMed")String cinMed,@Param("username") String username,@Param("dateDemande") Date dateDemande);
    @Query("SELECT TIME(a.date_RDV) FROM RendezVous a WHERE DATE(a.date_RDV) = DATE(:date) ORDER BY a.date_RDV DESC LIMIT 1")
    String findFirstAppointmentTimeForDate(@Param("date") LocalDate date);

    @Query("SELECT count(a) FROM RendezVous a WHERE DATE(a.date_RDV) = DATE(:date) ")
    int countNumberRDVByDay(@Param("date") LocalDate date);
    @Query("SELECT count (a) FROM RendezVous a WHERE a.statusRDV IN (:statuses) AND a.compte.username = :username AND a.patient.cin = :cinpatient AND a.medecin.cin = :cinMedecin")
    int RendezVousTaken(@Param("cinMedecin") String cinMedecin,@Param("cinpatient") String cinpatient, @Param("username") String username, @Param("statuses") List<StatusRDV> statuses);



}

