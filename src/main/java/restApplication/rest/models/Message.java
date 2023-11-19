package restApplication.rest.models;

import jakarta.persistence.*;
import lombok.*;

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
    //@JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;

    public Message(String text) {
        this.text = text;
    }
}
