package cat.tecnocampus.notes.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReceiverDeleteUserNotesChannel {

    @Input("receiverDeleteNotesChannel")
    SubscribableChannel receiverDeleteNotesChannel();
}
