package backEnd.Dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for a recipe response.
 * This DTO is used to encapsulate data concerning a recipe's identity
 */
@Data
public class RecipeResponseDTO {
    // Identifier for the recipe
    private long id;

    /**
     * Constructor for creating a RecipeResponseDTO with a specific recipe ID.
     *
     * @param id the unique identifier of the recipe
     */
    public RecipeResponseDTO(long id) {
        this.id = id;
    }
}
