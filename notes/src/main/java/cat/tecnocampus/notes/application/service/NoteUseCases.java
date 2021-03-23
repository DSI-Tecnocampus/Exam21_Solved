package cat.tecnocampus.notes.application.service;

import cat.tecnocampus.notes.application.portsOut.NoteDAO;
import cat.tecnocampus.notes.domain.Note;
import cat.tecnocampus.notes.webAdapter.UserDoesNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteUseCases implements cat.tecnocampus.notes.application.portsIn.NoteUseCases {

    private final NoteDAO noteDAO;
    private RestTemplate restTemplate;

    public NoteUseCases(NoteDAO noteDAO, RestTemplate restTemplate) {
        this.noteDAO = noteDAO;
        this.restTemplate = restTemplate;
    }

    @Override
    public Note addNote(Note note) {
        noteDAO.insert(note);

        return note;
    }

    @Override
    public Note createNote(Note note) {
        //TODO: hexagonal port
        boolean userExists = restTemplate.getForObject("http://localhost:8080/users/exists/" + note.getUserName(), boolean.class);

        if (userExists) {
            note.setChecked(true);
            note.setDateCreation(LocalDateTime.now());
            note.setDateEdit(LocalDateTime.now());
            return addNote(note);
        }

        throw new UserDoesNotExistException();
    }

    @Override
    public Note deleteNote(Note note) {
        noteDAO.deleteNote(note);
        return note;
    }

    @Override
    public int deleteUserNotes(String userName) {
        return noteDAO.deleteUserNotes(userName);
    }

    @Override
    public List<Note> getUserNotes(String userName) {
        return noteDAO.findByUsername(userName);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteDAO.findAll();
    }

    @Override
    public int updateNoteExists(String userName) {
        return noteDAO.updateNoteExists(userName);
    }
}
