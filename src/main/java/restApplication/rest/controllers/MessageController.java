package restApplication.rest.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restApplication.rest.dto.MessageCreationRequest;
import restApplication.rest.dto.MessageDTO;
import restApplication.rest.services.MessageService;
import restApplication.rest.views.Views;


import java.util.List;


@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

private final MessageService messageService;


    @GetMapping
    @JsonView(Views.IdName.class)
    public List<MessageDTO> list(){
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullMessage.class)
    public MessageDTO getOne(@PathVariable("id") Long id){

        return messageService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody MessageCreationRequest message){
        messageService.createMessage(message);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(
            @PathVariable("id") Long id,
            @RequestBody MessageCreationRequest message
    ){
       messageService.updateMessage(id, message);

       return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        messageService.deleteMessage(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
