package restApplication.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import restApplication.rest.views.Views;

import java.time.LocalDateTime;

public record MessageDTO(
        @JsonView(Views.Id.class)
        Long id,
        @JsonView(Views.IdName.class)
        String text,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonView(Views.FullMessage.class)
        LocalDateTime localDateTime
){
}
