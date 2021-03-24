package cat.tecnocampus.users.deleteUserNotesAdapter;

import cat.tecnocampus.users.application.portsOut.DeleteUserNotes;
import cat.tecnocampus.users.configuration.DeleteUserNotesChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(DeleteUserNotesChannel.class)
public class DeleteUserNotesAdapter implements DeleteUserNotes {
    private MessageChannel deleteUserNotesChannel;

    public DeleteUserNotesAdapter(DeleteUserNotesChannel channels) {
        this.deleteUserNotesChannel = channels.senderDeleteNotesChannel();
    }

    @Override
    public void deleteUserNotes(String userName) {
        CommandNotes command = new CommandNotes(userName);
        deleteUserNotesChannel.send(MessageBuilder.withPayload(command).build());
    }
}
