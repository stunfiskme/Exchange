package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.repository.RecipeRepository;

@Component("recipeSecurity")
public class RecipeSecurity {

    @Autowired
    private RecipeRepository recipeRepository;
    
    public boolean isOwner(Long recipeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal() instanceof CustomUserDetails)) return false;

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        Long ownerId = recipeRepository.findOwnerId(recipeId).orElse(null);

        return ownerId.equals(user.getId());
    }
}
