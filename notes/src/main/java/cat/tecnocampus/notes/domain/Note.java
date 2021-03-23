package cat.tecnocampus.notes.domain;

import java.time.LocalDateTime;

public class Note {

    private String title;
    private String content;

    private LocalDateTime dateCreation;
    private LocalDateTime dateEdit;

    private String userName;
    private boolean checked;

    public Note() {
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString(){
        return "NoteLab: "+this.title+", Content: "+ this.content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
