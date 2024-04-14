package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    public Patient findPatientByCin(String cin);
}
