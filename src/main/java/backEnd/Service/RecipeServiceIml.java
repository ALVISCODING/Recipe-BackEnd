package backEnd.Service;

import backEnd.Dto.RecipeResponseDTO;
import backEnd.Entity.Recipe;
import backEnd.Entity.User;
import backEnd.Repository.RecipeRepository;
import backEnd.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceIml implements RecipeService {
    // in the 1 stage we just store it here

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public RecipeServiceIml(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }


    /**
     * Saves a given recipe to the repository and returns a response DTO containing the ID of the saved recipe.
     * This method takes a Recipe object as input, persists it using the recipe repository, and then creates
     * a RecipeResponseDTO with the ID of the newly saved recipe. This ID can be used for further operations
     * or as a reference in the response to the client.
     *
     * @param importRecipe The Recipe object to be saved.
     * @return RecipeResponseDTO containing the ID of the saved recipe.
     */
    @Override
    public RecipeResponseDTO saveRecipe(Recipe importRecipe) {
        // check if the user is register
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

//        // Get the username (email) of the currently authenticated user
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//
//        // Find the user entity by email
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("User not found"));

        // Associate the user with the recipe

        importRecipe.setUser(user);
        //save the new recipe
        Recipe savedRecipe = recipeRepository.save(importRecipe);
        // then we return the json object of the recipe id
        return new RecipeResponseDTO(savedRecipe.getId());
    }

    @Override
    public Optional<Recipe> getSaveRecipe(long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public boolean deleteRecipe(long id) {
        Optional<Recipe> recipeToBeDelete = recipeRepository.findById(id);

        if(recipeToBeDelete.isPresent() && isUserRecipeAuthor(id)){
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateRecipe(long id,Recipe importRecipe) {
//        // Obtain the currently authenticated user's username or ID
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName(); // Or use a custom method to get the user ID depending on your setup
//
        // Fetch the recipe to be updated
        Recipe recipeToBeUpdate = recipeRepository.getReferenceById(id);
//
//        // Assuming you have a method in your recipe or user repository to get the owner's username/ID
//        // This part depends on how your entities and relationships are set up
//        String ownerUsername = recipeToBeUpdate.getUser().getEmail(); // This is an illustrative method
//
//        // Check if the current user is the owner of the recipe
//        if (currentUsername.equals(ownerUsername)) {
//            //Recipe recipeToBeUpdate = recipeRepository.getById(id);
//            recipeToBeUpdate.setName(importRecipe.getName());
//            recipeToBeUpdate.setDescription(importRecipe.getDescription());
//            recipeToBeUpdate.setCategory(importRecipe.getCategory());
//            recipeToBeUpdate.setDirections(importRecipe.getDirections());
//            recipeToBeUpdate.setIngredients(importRecipe.getIngredients());
//            recipeToBeUpdate.setDate(LocalDateTime.now());
//
//            //at the end it has to be saved in the repository in order to be updated
//            recipeRepository.save(recipeToBeUpdate);
//        }

        recipeToBeUpdate.setName(importRecipe.getName());
        recipeToBeUpdate.setDescription(importRecipe.getDescription());
        recipeToBeUpdate.setCategory(importRecipe.getCategory());
        recipeToBeUpdate.setDirections(importRecipe.getDirections());
        recipeToBeUpdate.setIngredients(importRecipe.getIngredients());
        recipeToBeUpdate.setDate(LocalDateTime.now());

        //at the end it has to be saved in the repository in order to be updated
        recipeRepository.save(recipeToBeUpdate);
    }

    @Override
    public List<Recipe> getAllRecipeByCategory(String category) {
        // this sort instance is for sorting the recipe by date in descending order
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        return recipeRepository.findAllByCategoryIgnoreCase(category,sort);
    }

    @Override
    public List<Recipe> getAllRecipeByName(String name) {
        // this sort instance is for sorting the recipe by date in descending order
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        return recipeRepository.findAllByNameContainingIgnoreCase(name,sort);
    }

    /**
     * Check if the current user is the recipe author
     * @param id
     * @return
     */
    @Override
    public boolean isUserRecipeAuthor(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // Or use a custom method to get the user ID depending on your setup

        Optional<Recipe> recipe = getSaveRecipe(id);

        return recipe.isPresent() && recipe.get().getUser().getEmail().equalsIgnoreCase(currentUsername);
    }




}
