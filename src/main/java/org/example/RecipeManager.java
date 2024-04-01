package org.example;

public class RecipeManager {
        private String[] recipes;
        private int count; // Tracks the number of actual recipes in the array

        //constructor for initializing
        public RecipeManager(int size) {
            this.recipes = new String[size];
            this.count = 0;
        }

        public boolean addRecipe(String recipe) {
            // Check if the array is full
            if (count >= recipes.length) {
                return false; // Array is full, not possible to  add more recipes
            }

            // making sure that the recipe does not already exist
            for (int i = 0; i < count; i++) {
                if (recipes[i].startsWith(recipe.split(":")[0])) {
                    return false; // Recipe already exists
                }
            }

            recipes[count] = recipe;
            count++;
            return true;
        }

        public boolean removeRecipe(String recipeName) {
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (recipes[i].startsWith(recipeName)) {
                    // Shift all elements to the left to fill the gap
                    for (int j = i; j < count - 1; j++) {
                        recipes[j] = recipes[j + 1];
                    }
                    count--;
                    recipes[count] = null; // Optional: clear the last element
                    found = true;
                    break;
                }
            }
            return found;
        }

        public String[] findRecipesByIngredients(String ingredient) {
            String[] foundRecipes = new String[count]; // Temporary array to store matches
            int foundCount = 0;

            for (int i = 0; i < count; i++) {
                String[] parts = recipes[i].split(":");
                if (parts.length > 1 && parts[1].contains(ingredient)) {
                    foundRecipes[foundCount] = recipes[i];
                    foundCount++;
                }
            }

            // Copy the found recipes to a new array of exact size
            String[] result = new String[foundCount];
            System.arraycopy(foundRecipes, 0, result, 0, foundCount);
            return result;
        }

        public boolean updateRecipe(String originalName, String updatedRecipe) {
            for (int i = 0; i < count; i++) {
                if (recipes[i].startsWith(originalName)) {
                    recipes[i] = updatedRecipe;
                    return true;
                }
            }
            return false; // Original recipe not found
        }
}
