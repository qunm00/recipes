package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import recipes.persistence.RecipeRepository;
import recipes.persistence.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public RecipeService(RecipeRepository recipeRepository,
//                         UserRepository userRepository) {
//        this.recipeRepository = recipeRepository;
//        this.userRepository = userRepository;
//    }

    public boolean isOwner(User user, Recipe recipe) {
        if (recipe.getOwner() == null) {
            return true;
        }
        return recipe.getOwner().equals(user);
    }

    public Map<String, Long> save(Recipe recipe, User authentication) {
        User owner = this.userRepository.findById(authentication.getUsername()).get();
        recipe.setOwner(owner);
        Recipe newRecipe = this.recipeRepository.save(recipe);
        return Map.of("id", newRecipe.getId());
    }

    public Optional<Recipe> get(long id) {
        if (this.recipeRepository.existsById(id)) {
            return this.recipeRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void delete(long id, User user) {
        Recipe recipe = get(id).get();
        if (isOwner(user, recipe)) {
            this.recipeRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public void update(long id, Recipe newRecipe, User user) {
        Recipe recipe = get(id).get();
        if (isOwner(user, recipe)) {
            newRecipe.setId(id);
            this.recipeRepository.save(newRecipe);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public List<Recipe> searchCategory(String category) {
        return this.recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }
    public List<Recipe> searchName(String name) {
        return this.recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }
}