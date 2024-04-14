package gi2.ensakh.apphopital;

import gi2.ensakh.apphopital.Entities.*;
import gi2.ensakh.apphopital.Repositories.MedecinRepository;
import gi2.ensakh.apphopital.Repositories.PatientRepository;
import gi2.ensakh.apphopital.Repositories.RendezVousRepository;
import gi2.ensakh.apphopital.Repositories.SecretaireRepository;
import gi2.ensakh.apphopital.Services.CompteServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class AppHopitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppHopitalApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(RendezVousRepository rendezVousRepository, CompteServiceImpl compteService, SecretaireRepository secretaireRepository, PatientRepository patientRepository, MedecinRepository medecinRepository) {
		return args -> {
		/*	compteService.addNewUser("SEC1", "123", Role.SECRETAIRE, "123");
			compteService.addNewUser("SEC2", "123", Role.SECRETAIRE, "123");*/

		Medecin medecin = new Medecin();
		medecin.setCin("sahbek");
		medecin.setNom("Jonnyy");
		medecin.setPrenom("Doe");
		medecin.setTel(1234567890L);
		medecin.setEmail("john.doe@example.com");
		medecin.setDateN(new Date());
		medecin.setAdresse("123 Main St");
		medecin.setSpecialite("tbibi");
		medecinRepository.save(medecin);
		/*	Medecin medecin1 = new Medecin();
			medecin1.setCin("bo3o");
			medecin1.setNom("John");
			medecin1.setPrenom("Doe");
			medecin1.setTel(1234567890L);
			medecin1.setEmail("john.doe@example.com");
			medecin1.setDateN(new Date());
			medecin1.setAdresse("123 Main St");
			medecin1.setSpecialite("Cardiologue");
			medecinRepository.save(medecin);
		Patient patient = new Patient();
		patient.setCin("soso");
		patient.setNom("soum");
		patient.setPrenom("kiro");
		patient.setTel(1234567890L);
		patient.setEmail("john.doe@example.com");
		patient.setDateN(new Date());
		patient.setAdresse("123 Main St");
		patientRepository.save(patient);

			Secretaire secretaire = new Secretaire();
			secretaire.setCin("111");
			secretaire.setNom("ihss");
			secretaire.setPrenom("kiro");
			secretaire.setTel(1234567890L);
			secretaire.setEmail("john.doe@example.com");
			secretaire.setDateN(new Date());
			secretaire.setAdresse("123 Main St");

			secretaireRepository.save(secretaire);*/
			Patient patient = new Patient();
			patient.setCin("klbounaaa");
			patient.setNom("soum");
			patient.setPrenom("kiro");
			patient.setTel(1234567890L);
			patient.setEmail("john.doe@example.com");
			patient.setDateN(new Date());
			patient.setAdresse("123 Main St");
			RendezVous rdv = new RendezVous();
			patientRepository.save(patient);
			rdv.setPatient(patient);
			rendezVousRepository.save(rdv);

		};
	}
}
