package backEnd.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.*;


@Entity
@Data
@Table(name = "users")  // Renamed to avoid conflict with SQL reserved keyword "USER"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @NotBlank
    //@Email(message = "Email should be valid")
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;



}
