package backEnd.Dto;

import lombok.Data;

@Data
public class RecipeResponseDTO {
    private long id;

    public RecipeResponseDTO(long id) {
        this.id = id;
    }

}
