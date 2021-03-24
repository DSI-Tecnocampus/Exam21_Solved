package cat.tecnocampus.notes.application.portsIn;

public interface DeleteUserNotes {
    public void deleteUserNotes(CommandNotes command);

    public class CommandNotes {
        private final String command;
        private final String userName;

        public CommandNotes(String userName, String command) {
            this.command = command;
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
        }

        public String getCommand() {
            return command;
        }
    }
}
