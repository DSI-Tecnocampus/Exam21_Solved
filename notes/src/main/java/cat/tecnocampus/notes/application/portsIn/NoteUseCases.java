package cat.tecnocampus.notes.application.portsIn;

import cat.tecnocampus.notes.domain.Note;

import java.util.List;

public interface NoteUseCases {
    Note addNote(Note noteLab);

    Note createNote(Note noteLab);

    Note deleteNote(Note note);

    int deleteUserNotes(String userName);

    List<Note> getUserNotes(String userName);

    List<Note> getAllNotes();

    int updateNoteExists(String userName);
}
