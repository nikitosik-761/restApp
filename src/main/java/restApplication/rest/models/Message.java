package restApplication.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import restApplication.rest.views.Views;

import java.time.LocalDateTime;

@Entity
@Table(name = "Message")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //@JsonView(Views.Id.class)
    private Long id;

    @Column(name = "text")
    //@JsonView(Views.IdName.class)
    private String text;

    @Column(name = "date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;

    public Message(String text) {
        this.text = text;
    }
}
