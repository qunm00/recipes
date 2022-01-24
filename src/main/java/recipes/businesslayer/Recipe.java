package recipes.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UpdateTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JsonIgnore
    private User owner;

    @NotBlank(message="Recipe must have name")
    private String name;

    @NotBlank(message="Recipe must have description")
    private String description;

    @NotBlank(message="Recipe must have category")
    private String category;

    @ElementCollection
    @NotNull(message="Recipe must have ingredients")
    @Size(min = 1, message="A recipe must have at least 1 ingredient")
    private List<String> ingredients;

    @ElementCollection
    @NotNull(message="Recipe must have directions")
    @Size(min = 1, message="A recipe must have at least 1 direction")
    private List<String> directions;

    public Recipe() { }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    public User getOwner() { return this.owner; }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwner(User user) { this.owner = user; }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", date=" + date +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", ingredients=" + ingredients +
                ", directions=" + directions +
                '}';
    }
}