package cat.tecnocampus.users.configuration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DeleteUserNotesChannel {

    @Output("senderDeleteNotesChannel")
    MessageChannel senderDeleteNotesChannel();
}
