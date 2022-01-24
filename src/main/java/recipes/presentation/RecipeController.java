package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import recipes.businesslayer.Recipe;
import recipes.businesslayer.RecipeService;
import recipes.businesslayer.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    public Map<String, Long> postRecipe(@Valid @RequestBody Recipe recipe, @AuthenticationPrincipal User user) {
        return this.recipeService.save(recipe, user);
    }

    @GetMapping("/api/recipe/{id}")
    public Optional<Recipe> getRecipe(@PathVariable long id) {
        return this.recipeService.get(id);
    }

    @GetMapping("/api/recipe/search")
    @ResponseBody
    public List<Recipe> searchRecipe(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (category == null && name == null
                || category != null && name != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (category != null) {
            return this.recipeService.searchCategory(category);
        } else {
            return this.recipeService.searchName(name);
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id, @AuthenticationPrincipal User user) {
        this.recipeService.delete(id, user);
    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe, @AuthenticationPrincipal User user) {
        this.recipeService.update(id, recipe, user);
    }

}