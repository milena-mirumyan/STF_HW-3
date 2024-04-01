package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeManagerTest {

    private RecipeManager manager;

    @BeforeEach
    void setUp() {
        // Initialize RecipeManager with a capacity of 5 for testing
        manager = new RecipeManager(5);
    }

    @Test
    void testAddRecipeSuccessfully() {
        assertTrue(manager.addRecipe("Pancakes:Flour,Eggs,Milk"));
        // Test adding multiple recipes
        assertTrue(manager.addRecipe("Salad:Tomato,Cucumber,Lettuce"));
    }

    @Test
    void testAddRecipeFailureDueToDuplication() {
        manager.addRecipe("Pancakes:Flour,Eggs,Milk");
        assertFalse(manager.addRecipe("Pancakes:Sugar,Butter"));
    }

    @Test
    void testAddRecipeFailureDueToFullCapacity() {
        manager.addRecipe("Recipe1:Ingredient1");
        manager.addRecipe("Recipe2:Ingredient2");
        manager.addRecipe("Recipe3:Ingredient3");
        manager.addRecipe("Recipe4:Ingredient4");
        manager.addRecipe("Recipe5:Ingredient5");
        // Attempt to add a recipe when the array is full
        assertFalse(manager.addRecipe("Recipe6:Ingredient6"));
    }

    @Test
    void testRemoveRecipeSuccessfully() {
        manager.addRecipe("Pancakes:Flour,Eggs,Milk");
        assertTrue(manager.removeRecipe("Pancakes"));
    }

    @Test
    void testRemoveRecipeFailure() {
        manager.addRecipe("Pancakes:Flour,Eggs,Milk");
        // Attempt to remove a recipe that does not exist
        assertFalse(manager.removeRecipe("Pizza"));
    }

    @Test
    void testFindRecipesByIngredient() {
        manager.addRecipe("Pancakes:Flour,Eggs,Milk");
        manager.addRecipe("Omelette:Eggs,Milk,Cheese");
        manager.addRecipe("Cake:Flour,Sugar,Eggs");
        // Test finding recipes by a specific ingredient
        String[] foundRecipes = manager.findRecipesByIngredients("Eggs");
        assertEquals(3, foundRecipes.length);
        assertArrayEquals(new String[] {"Pancakes:Flour,Eggs,Milk", "Omelette:Eggs,Milk,Cheese", "Cake:Flour,Sugar,Eggs"}, foundRecipes);
    }

    @Test
    void testFindRecipesByIngredientNoneFound() {
        manager.addRecipe("Pancakes:Flour,Eggs,Milk");
        // Test when no recipes contain the specified ingredient
        String[] foundRecipes = manager.findRecipesByIngredients("Banana");
        assertEquals(0, foundRecipes.length);
    }

    @Test
    void testUpdateRecipeSuccessfully() {
        manager.addRecipe("Pancakes:Flour,Eggs,Milk");
        assertTrue(manager.updateRecipe("Pancakes", "Pancakes:Flour,Eggs,Milk,Sugar"));
    }

    @Test
    void testUpdateRecipeFailure() {
        manager.addRecipe("Pancakes:Flour,Eggs,Milk");
        // Attempt to update a recipe that does not exist
        assertFalse(manager.updateRecipe("Pizza", "Pizza:Tomato,Cheese"));
    }
}
