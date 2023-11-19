package restApplication.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;



@Entity
@Table(name = "Usr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column
    private String id;
    @Column(nullable = false)
    private String name;
    @Column
    private String userpic;
    @Column
    private String email;
    @Column
    private String gender;
    @Column
    private String locale;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;

}
