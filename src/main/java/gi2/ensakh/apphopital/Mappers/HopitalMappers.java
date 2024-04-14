package gi2.ensakh.apphopital.Mappers;

import gi2.ensakh.apphopital.Dtos.PatientRdvDto;
import gi2.ensakh.apphopital.Dtos.RdvDto;
import gi2.ensakh.apphopital.Entities.Compte;
import gi2.ensakh.apphopital.Entities.Patient;
import gi2.ensakh.apphopital.Entities.RendezVous;
import gi2.ensakh.apphopital.Repositories.CompteRepository;
import gi2.ensakh.apphopital.Repositories.MedecinRepository;
import gi2.ensakh.apphopital.Repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class HopitalMappers {
    PatientRepository patientRepository;
    MedecinRepository medecinRepository;
    CompteRepository compteRepository;
    public PatientRdvDto fromPatientRdv(RendezVous Rendezvous){
        PatientRdvDto patientRdvDto=new PatientRdvDto();
        patientRdvDto.setCin(Rendezvous.getPatient().getCin());
        patientRdvDto.setNom(Rendezvous.getPatient().getNom());
        patientRdvDto.setPrenom(Rendezvous.getPatient().getPrenom());
        patientRdvDto.setTel(Rendezvous.getPatient().getTel());
        patientRdvDto.setEmail(Rendezvous.getPatient().getEmail());
       patientRdvDto.setStatusRDV(Rendezvous.getStatusRDV());
       patientRdvDto.setDate(Rendezvous.getDate_RDV());
       return patientRdvDto;
    }
        public RendezVous fromRdvDTO(RdvDto rdvDto){
        RendezVous rendezVous=new RendezVous();
        Compte compte = compteRepository.findCompteByUsername(rdvDto.getUsername());
        Patient patient=new Patient();
        rendezVous.setStatusRDV(rdvDto.getStatusRDV());
        patient.setNom(rdvDto.getNom());
        patient.setPrenom(rdvDto.getPrenom());
        patient.setTel(rdvDto.getTel());
        patient.setEmail(rdvDto.getEmail());
        patient.setCin(rdvDto.getPatientCin());
        patientRepository.save(patient);
        rendezVous.setPatient(patient);
        rendezVous.setStatusRDV(rdvDto.getStatusRDV());
        rendezVous.setMedecin(medecinRepository.findByCin(rdvDto.getDRcin()));
        rendezVous.setDate_demande(rdvDto.getDate_demande());
        rendezVous.setCompte(compte);
        return rendezVous;
   }

}
