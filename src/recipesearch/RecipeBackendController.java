package recipesearch;

import javafx.scene.image.Image;
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
    List difficultyList = Arrays.asList("Lätt", "Medel", "Svår");
    List mainIndirientList = Arrays.asList("Kött", "Fisk", "Kyckling", "Vegetarisk");
    List maxTimeList = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150);

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
        if(difficultyList.contains(difficulty)){
            this.difficulty = difficulty;
        }
        else{
            this.difficulty = null;
        }
    }
    public void setMainIngridient(String mainIngridient){
        if(mainIndirientList.contains(mainIngridient)){
            this.mainIngridient = mainIngridient;
        }
        else{
            this.mainIngridient = null;
        }
    }

    public void setMaxPrice(int maxPrice) {
        if(maxPrice <= 0){
            this.maxPrice = maxPrice;
        }
        else{
            this.maxPrice = 0;
        }
    }

    public void setMaxTime(int maxTime) {
        if(maxTimeList.contains(maxTime)){
            this.maxTime = maxTime;
        }
        else{
            this.maxTime = 0;
        }
    }




}
