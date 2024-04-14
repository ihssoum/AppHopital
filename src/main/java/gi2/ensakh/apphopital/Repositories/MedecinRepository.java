package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedecinRepository extends JpaRepository<Medecin,Integer> {
    @Query("select R.patient.cin from RendezVous R where R.id=:id")
    public String findDrCinByIdRDV(@Param("id")Long id);
   @Query("select m from Medecin m where m.cin=:cin")
    Medecin findByCin(@Param("cin")String cin);


}
