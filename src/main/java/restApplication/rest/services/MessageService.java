package restApplication.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import restApplication.rest.dto.MessageCreationRequest;
import restApplication.rest.dto.MessageDTO;
import restApplication.rest.exceptions.NotFoundException;
import restApplication.rest.message.MessageDTOMapper;
import restApplication.rest.models.Message;
import restApplication.rest.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageDTOMapper messageDTOMapper;

    public List<MessageDTO> findAll(){
        return messageRepository.findAll()
                .stream()
                .map(messageDTOMapper)
                .collect(Collectors.toList());
    }

    public MessageDTO findOne(Long id){
        return messageRepository.findById(id)
                .map(messageDTOMapper)
                .orElseThrow(NotFoundException::new);
    }

    public void createMessage(MessageCreationRequest messageRequest){

        Message message = new Message(
                messageRequest.text()
        );

        message.setCreationDate(LocalDateTime.now());

        messageRepository.save(message);
    }

    public void updateMessage(Long id, MessageCreationRequest messageRequest){
        Message messageFromDb =
                messageRepository
                        .findById(id)
                        .orElseThrow(
                                NotFoundException::new
                        );

        Message message = new Message(
                messageRequest.text()
        );

        BeanUtils.copyProperties(message, messageFromDb,"id");

        messageRepository.save(messageFromDb);
    }

    public void deleteMessage(Long id){
        messageRepository.deleteById(id);
    }

}
