package gi2.ensakh.apphopital.Web;

import gi2.ensakh.apphopital.Dtos.PatientRdvDto;
import gi2.ensakh.apphopital.Dtos.RdvDto;
import gi2.ensakh.apphopital.Dtos.checkTime;
import gi2.ensakh.apphopital.Entities.*;
import gi2.ensakh.apphopital.Mappers.HopitalMappers;
import gi2.ensakh.apphopital.Repositories.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class AppRestController {
    private static final Logger logger = LoggerFactory.getLogger(AppRestController.class);

    public RendezVousRepository rendezVousRepository;
    private PatientRepository patientRepository;
    private HopitalMappers dtoMapper;
    private MedecinRepository medecinRepository;
    private NoteRepository noteRepository;

    @GetMapping("/listeRDVID/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Patient ListeRDVID(@PathVariable(name = "id") Long Id) {
        String cinMed = rendezVousRepository.findCinPatientByIdRDV(Id);
        return patientRepository.findPatientByCin(cinMed);
    }


    @GetMapping("/SecByRDV/{idRDV}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Secretaire SecByRDV(@PathVariable(name = "idRDV") Long idRDV) {
        return rendezVousRepository.findSecretaireByRdvId(idRDV);
    }

    @GetMapping("/SEC/RdvBySec/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public List<PatientRdvDto> listRdvBySec(@PathVariable(name = "username") String username) {
        List<RendezVous> listepatientRdv = rendezVousRepository.listRdvBySec(username);
        List<PatientRdvDto> listpatientRdvDtos = listepatientRdv.stream()
                .map(rdvPatient -> dtoMapper.fromPatientRdv(rdvPatient))
                .collect(Collectors.toList());
        return listpatientRdvDtos;
    }

    @GetMapping("/ListDoctors")
    public List<Medecin> medecinList() {
        return medecinRepository.findAll();
    }

    @PostMapping("/priseRdvNewPatient")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public ResponseEntity<Map<String, String>> priseRDV(@RequestBody RdvDto request) {
        RendezVous rendezVous = dtoMapper.fromRdvDTO(request);
        String patientCin = rendezVous.getPatient().getCin();
        String username = rendezVous.getCompte().getUsername();
        String doctorCin = rendezVous.getMedecin().getCin();

        List<StatusRDV> statuses = Arrays.asList(StatusRDV.EnAttente, StatusRDV.valide);
        int NumexistingRdv = rendezVousRepository.RendezVousTaken(doctorCin, patientCin, username, statuses);
        if (NumexistingRdv != 0) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Appointment is already taken.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            rendezVousRepository.save(rendezVous);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Appointment created successfully.");
            return ResponseEntity.ok(response);
        }
    }


    @PostMapping("/patient")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public void patient(@RequestBody Patient patient1) {
        patientRepository.save(patient1);

    }

    @GetMapping("/listpatient")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public List<Medecin> listpatient() {
        return medecinRepository.findAll();

    }

    @PostMapping("/nbrRDV")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public int numRDV(@RequestBody checkTime request) {
        String cinMed = request.getCin();
        String username = request.getUsername();
        Date dateDemande = request.getDate_demande();
        return rendezVousRepository.numRdvByDay(cinMed, username, dateDemande);
    }

    @GetMapping("/listRDV")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public List<RendezVous> listRDV() {
        return rendezVousRepository.findAll();

    }

    @GetMapping("/appointments/latest-time/{date}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public LocalTime getLatestAppointmentTimeForDate(@PathVariable("date") LocalDate date) {
        String timeString = rendezVousRepository.findFirstAppointmentTimeForDate(date);
        return LocalTime.parse(timeString);
    }

    @GetMapping("/appointments/number/{date}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public int getNumberRDVByDay(@PathVariable("date") LocalDate date) {
        return rendezVousRepository.countNumberRDVByDay(date);
    }

    @GetMapping("/appointments/proposed-time/{cin}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public Map<String, Object> getProposedAppointmentDateTime(@PathVariable("cin") String cin) {

        LocalDate currentDate = LocalDate.now();
        LocalDate nextDay = currentDate.plusDays(1);
        LocalDate proposedDate = null;
        Medecin medecin = medecinRepository.findByCin(cin);
        int numberRDVNextDay = 0;

        while (proposedDate == null && nextDay.isBefore(currentDate.plusDays(200))) {
            if (nextDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
                numberRDVNextDay = rendezVousRepository.countNumberRDVByDay(nextDay);
                if (numberRDVNextDay < medecin.getLimitNumRDV()) {
                    proposedDate = nextDay;
                }
            }
            nextDay = nextDay.plusDays(1);
        }

        if (medecin != null && proposedDate != null) {
            LocalTime proposedTime;
            if (numberRDVNextDay != 0) {
                LocalTime lastAppointmentTime = getLatestAppointmentTimeForDate(proposedDate);
                if (lastAppointmentTime != null) {
                    Integer durationRDV = medecin.getDurationRDV();
                    proposedTime = lastAppointmentTime.plusMinutes(durationRDV);
                } else {
                    return null;
                }
            } else {
                proposedTime = medecin.getDebutRDV();
            }

            Note note = noteRepository.findNotesByDateEventAndMedecin_Cin(proposedDate, cin);
            Map<String, Object> response = new HashMap<>();
            response.put("proposedDateTime", LocalDateTime.of(proposedDate, proposedTime));
            if (note != null) {
                response.put("message", "Note: There is a note for this day. Note Title: " + note.getTitle());
            }
            return response;
        }
        return null;
    }

    @GetMapping("/list")
    public String TESTGIT() {
        return "this is a test ";

    }

    @GetMapping("/list")
    public String TESTGIT22git() {
        return "this is a test ";
    }

    public void GitMethode() {
        System.out.println("Hello!");
    }

    public void GitMethode2() {
        System.out.println("Hello its imane!");
    }


    public void GitMethode1() {
        System.out.println("Hello 1!");
    }
    public void GitMethode4() {
        System.out.println("Hello worldddddddddddd!");
    }


}

