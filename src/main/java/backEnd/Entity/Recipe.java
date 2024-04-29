package backEnd.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

/**
 * This is a Recipe class entity
 * This class represent the recipe
 */
@Data
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with one argument for each field
@Entity // This annotation marks the class as a JPA entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    private LocalDateTime date = LocalDateTime.now();

    @NotNull
    @NotEmpty
    @Size(min = 1)
    @ElementCollection // using ElementCollection as list is not support by default in JPA
    private List<String> ingredients;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    @ElementCollection // using ElementCollection as list is not support by default in JPA
    private List<String> directions;

    /**
     * Each recipe can only belong to one user
     * and the details of the user shall remain in the backend only
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
