package recipesearch;

import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;
import se.chalmers.ait.dat215.lab2.SearchFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeBackendController {
    String cuisine;
    String difficulty;
    String mainIngridient;
    int maxPrice;
    int maxTime;

    List cuisineList = Arrays.asList("Sverige", "Grekland", "Indien", "Asien", "Afrika", "Frankrike");

    RecipeDatabase db = RecipeDatabase.getSharedInstance();
    public List<Recipe>getRecipes(){
        List<Recipe> recipes = db.search(new SearchFilter(difficulty, maxTime, cuisine, maxPrice, mainIngridient));
        return recipes;
    }

    public void setCuisine(String cuisine){
        if(cuisineList.contains(cuisine)){
            this.cuisine = cuisine;
        }
        else{
            this.cuisine = null;
        }

    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public void setMainIngridient(String mainIngridient){
        this.mainIngridient = mainIngridient;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
}
