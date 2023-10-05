package restApplication.rest.message;

import org.springframework.stereotype.Component;
import restApplication.rest.dto.MessageDTO;
import restApplication.rest.models.Message;
import java.util.function.Function;


@Component
public class MessageDTOMapper implements Function<Message, MessageDTO> {
    @Override
    public MessageDTO apply(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getText(),
                message.getCreationDate()
        );
    }
}
