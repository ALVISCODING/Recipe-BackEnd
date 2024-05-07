package backEnd.Controller;

import backEnd.Entity.Recipe;
import backEnd.Service.RecipeServiceIml;
import backEnd.Service.UserDetailsServiceIml;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    @Autowired
    private RecipeServiceIml recipeService;

    @Autowired
    private UserDetailsServiceIml userDetailsServiceIml;


    /**
     * Create a new recipe
     * //@Valid annotation enables automatic validation
     * of Java objects against constraints (e.g., @NotNull, @Size)
     *
     * @param newRecipe
     * @return
     */
    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> createRecipe(@Valid @RequestBody Recipe newRecipe) {
        return ResponseEntity.ok().body(recipeService.saveRecipe(newRecipe));
    }

    /**
     *
     * @param id
     * @return the recipe dto by its id
     */
    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable("id") long id) {
        Optional<Recipe> recipe = recipeService.getSaveRecipe(id);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe);
        } else {
            //make sure to use .build in order to return a responseEnitiy object
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity<?> getRecipeByCategory(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name
    ) {
        // check if the parameter exist
        boolean categoryProvided = category != null && !category.isBlank();
        boolean nameProvided = name != null && !name.isBlank();

        //check if the useres provide more than 1 parameter or none
        if (categoryProvided && nameProvided)  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if(!categoryProvided && !nameProvided){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

//      //empty list
        List<Recipe> searchResult = new ArrayList<>();

        //fill in the list according to the request parameter
        // by category or name
        if (category != null && name == null) {
            searchResult = recipeService.getAllRecipeByCategory(category);
        } else if (category == null && name != null) {
            searchResult = recipeService.getAllRecipeByName(name);
        }

        //to this stage return the list
        return ResponseEntity.ok(searchResult);
    }


    /**
     * This method is to update the existing recipe
     * only the author can update the recipe
     * @param id
     * @param updatedRecipe
     * @return
     */
    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable("id") long id,@Valid  @RequestBody Recipe updatedRecipe){
        // Attempt to fetch the recipe to update
        Optional<Recipe> tempRecipeOpt = recipeService.getSaveRecipe(id);
        if (tempRecipeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Check if the current user is the author of the recipe
        // This check depends on how you've set up your User and Recipe entities. Adjust accordingly.
        if (!recipeService.isUserRecipeAuthor(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // If the current user is the author, proceed with update
        recipeService.updateRecipe(id, updatedRecipe);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") long id){
        // Check if the recipe exist
        Optional<Recipe> tempRecipeOpt = recipeService.getSaveRecipe(id);
        if (tempRecipeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // if the recipe exist check the author
        boolean isRecipeExist = recipeService.deleteRecipe(id);

        // return not content if the author is the same else return forbidden
        if(isRecipeExist){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

}
