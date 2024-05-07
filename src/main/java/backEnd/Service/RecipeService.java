package backEnd.Service;

import backEnd.Dto.RecipeResponseDTO;
import backEnd.Entity.Recipe;

import java.util.List;
import java.util.Optional;

/**
 * This is a service interface
 * It provides all the necessary methods for recipe
 */
public interface RecipeService {

    //store the recipe
    RecipeResponseDTO saveRecipe(Recipe importRecipe);

    //return the current recipe
    Optional<Recipe> getSaveRecipe(long id);

    boolean deleteRecipe(long id);

    void updateRecipe(long id, Recipe importRecipe);

    List<Recipe> getAllRecipeByCategory(String category);

    List<Recipe> getAllRecipeByName(String name);

    boolean isUserRecipeAuthor(long id);

}
