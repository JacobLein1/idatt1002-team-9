package no.ntnu.idatt1005.controller;

import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {
    RecipeController recipeController = new RecipeController();

    @Test
    void getAllRecipes() {
        assertEquals(2, recipeController.getAllRecipes().size());
    }

    @Test
    void getRecipeById(){
        assertEquals("French toast", recipeController.getRecipeById("1").getRecipeName());
    }
}