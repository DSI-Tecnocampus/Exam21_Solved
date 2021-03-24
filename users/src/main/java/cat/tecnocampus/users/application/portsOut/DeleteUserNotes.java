package cat.tecnocampus.users.application.portsOut;

public interface DeleteUserNotes {
    public void deleteUserNotes(String userName);

    public class CommandNotes {
        private final String command;
        private final String userName;

        public CommandNotes(String userName) {
            command = "Delete";
            this.userName = userName;
        }

        public String getCommand() {
            return command;
        }

        public String getUserName() {
            return userName;
        }
    }
}
