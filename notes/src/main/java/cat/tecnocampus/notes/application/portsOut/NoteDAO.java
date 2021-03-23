package cat.tecnocampus.notes.application.portsOut;

import cat.tecnocampus.notes.domain.Note;

import java.util.List;

public interface NoteDAO {
    List<Note> findAll();

    List<Note> findByUsername(String username);

    Note findById(int id);

    int insert(Note note);

    int updateNote(String oldTitle, Note note);

    int updateNoteExists(String userName);

    int deleteNote(Note note);

    int deleteUserNotes(String userName);

    boolean existsNote(Note note);
}
