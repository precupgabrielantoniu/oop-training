package org.example;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @Test
    void givenMsg_whenVerifyUsingAnyMatcher_thenOk() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("me");
        messageDTO.setTo("you");
        messageDTO.setText("Hello, you!");

        messageController.createMessage(messageDTO);

        verify(messageService, times(1)).deliverMessage(any(Message.class));
    }

    @Test
    void givenMsg_whenVerifyUsingMessageMatcher_thenOk() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("me");
        messageDTO.setTo("you");
        messageDTO.setText("Hello, you!");

        messageController.createMessage(messageDTO);

        Message message = new Message();
        message.setFrom("me");
        message.setTo("you");
        message.setText("Hello, you!");

        verify(messageService, times(1)).deliverMessage(argThat(new MessageMatcher(message)));
    }
}