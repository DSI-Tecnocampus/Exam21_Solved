package cat.tecnocampus.notes.webAdapter;

import cat.tecnocampus.notes.application.portsIn.NoteUseCases;
import cat.tecnocampus.notes.domain.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotesRESTController {
    private NoteUseCases noteUseCases;
    public NotesRESTController(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }


    @GetMapping(value = "/notes/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> getUserNotes(@PathVariable String username) {
        List<Note> noteLabs = noteUseCases.getUserNotes(username);
        return noteLabs;
    }

    @GetMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> getAllNotes() {
        List<Note> noteLabs = noteUseCases.getAllNotes();
        return noteLabs;
    }

    @PostMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Note createNote(@RequestBody Note note) {
        noteUseCases.createNote(note);
        return note;
    }

    public Note saveUncheckedNote(Note note) {
        
        note.setChecked(false);
        noteUseCases.createNote(note);

        return note;
    }

    @DeleteMapping(value = "/notes/{username}")
    public int deleteUserNotes(@PathVariable String username) {
        return noteUseCases.deleteUserNotes(username);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity handleUserDoesNotExist() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
