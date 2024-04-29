package backEnd.Repository;

import backEnd.Entity.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // No need to add any method here for basic CRUD operations,
    // JpaRepository provides them out of the box.

    //this method is to find all the recipe based on category
    // we also included Sort so we can sort by the date
    List<Recipe> findAllByCategoryIgnoreCase(String category, Sort sort);

    /**
     *
     * @param name
     * @param sort
     * @return all the recipe that contain the name
     */
    List<Recipe> findAllByNameContainingIgnoreCase(String name, Sort sort);

}
