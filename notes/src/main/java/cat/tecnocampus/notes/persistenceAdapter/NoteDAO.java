package cat.tecnocampus.notes.persistenceAdapter;

import cat.tecnocampus.notes.domain.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class NoteDAO implements cat.tecnocampus.notes.application.portsOut.NoteDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_NOTE = "insert into note (title, content, date_creation, date_edit, owner, checked) values(?, ?, ?, ?, ?, ?)";
    private final String FIND_ALL = "select * from note";
    private final String FIND_BY_USERNAME = "select * from note where owner = ? order by date_edit desc";
    private final String FIND_BY_ID = "select * from note where id = ?";
    private final String UPDATE_NOTE = "update note set title = ?, content = ?, date_edit = ? where date_creation = ? and title = ? and owner = ?";
    private final String DELETE_NOTE = "delete note where title = ? and date_creation = ?";
    private final String EXISTS_NOTE = "select count(*) from note where title = ? and date_creation = ?";
    private final String DELETE_USER_NOTES = "delete note where owner = ?";
    private final String UPDATE_NOTE_EXISTS = "update note set checked = true where owner = ?";

    private RowMapper<Note> mapper = (resultSet, i) -> {
        Note note = new Note();
        note.setTitle(resultSet.getString("title"));
        note.setContent(resultSet.getString("content"));
        note.setUserName(resultSet.getNString("owner"));
        note.setDateCreation(resultSet.getTimestamp("date_creation").toLocalDateTime());
        note.setDateEdit(resultSet.getTimestamp("date_edit").toLocalDateTime());
        note.setChecked(resultSet.getBoolean("checked"));
        return note;
    };

    public NoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Note> findAll() {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public List<Note> findByUsername(String username) {
        return jdbcTemplate.query(FIND_BY_USERNAME, mapper, username);
    }

    @Override
    public Note findById(int id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, mapper, id);
    }


    @Override
    public int insert(Note note) {
        return jdbcTemplate.update(INSERT_NOTE, note.getTitle(), note.getContent(), Timestamp.valueOf(note.getDateCreation()),
                Timestamp.valueOf(note.getDateEdit()), note.getUserName(), note.isChecked());
    }

    @Override
    public int updateNote(String oldTitle, Note note) {
        return jdbcTemplate.update(UPDATE_NOTE,
                note.getTitle(), note.getContent(), note.getDateEdit(), note.getDateCreation(), oldTitle, note.getUserName());
    }

    @Override
    public int updateNoteExists(String userName) {
        return jdbcTemplate.update(UPDATE_NOTE_EXISTS, userName);
    }

    @Override
    public int deleteNote(Note note) {
        return jdbcTemplate.update(DELETE_NOTE, note.getTitle(), note.getDateCreation());
    }

    @Override
    public int deleteUserNotes(String userName) {
        return jdbcTemplate.update(DELETE_USER_NOTES, userName);
    }

    @Override
    public boolean existsNote(Note note) {
        int countOfNotes = jdbcTemplate.queryForObject(
                EXISTS_NOTE, Integer.class, note.getTitle(), Timestamp.valueOf(note.getDateCreation()));
        return countOfNotes > 0;
    }

}
