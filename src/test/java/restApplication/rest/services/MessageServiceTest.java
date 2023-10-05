package restApplication.rest.services;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import restApplication.rest.dto.MessageDTO;
import restApplication.rest.exceptions.NotFoundException;
import restApplication.rest.message.MessageDTOMapper;
import restApplication.rest.models.Message;
import restApplication.rest.repositories.MessageRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    private MessageService messageService;
    private final MessageDTOMapper messageDTOMapper = new MessageDTOMapper();

    @BeforeEach
    void setUp() {
        messageService = new MessageService(messageRepository, messageDTOMapper);
    }

    @Test
    void findAll() {
        messageService.findAll();
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void findOne() {
        Long id = 10L;
        Message message = new Message(
                "dff"
        );

        when(messageRepository.findById(id)).thenReturn(Optional.of(message));

        MessageDTO expected = messageDTOMapper.apply(message);

        MessageDTO actual = messageService.findOne(id);

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void willTrowWhenGetMessageReturnEmptyOptional(){
        Long id = 10L;
        when(messageRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> messageService.findOne(id))
                .isInstanceOf(NotFoundException.class);
    }

}