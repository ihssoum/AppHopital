package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface NoteRepository extends JpaRepository<Note,Integer> {
    Note findNotesByDateEventAndMedecin_Cin(LocalDate dateEvent, String cin );
}
