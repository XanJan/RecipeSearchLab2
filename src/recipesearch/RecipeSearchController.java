
package recipesearch;

import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;


public class RecipeSearchController implements Initializable {
    @FXML
    private FlowPane recipeFlowPane;

    RecipeDatabase db = RecipeDatabase.getSharedInstance();
    List<RecipeListItem> recipeListItem = new ArrayList<>();
    RecipeBackendController RBC = new RecipeBackendController();
    private Map<String, RecipeListItem> recipeListItemMap = new HashMap<String, RecipeListItem>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRecipesinHashMap();
        updateRecipeList();
    }
    private void updateRecipeList(){
        recipeFlowPane.getChildren().clear();
        List<Recipe> recipesList = RBC.getRecipes();

        for(Recipe recipe: recipesList){
            RecipeListItem recipeListItem = recipeListItemMap.get(recipe.getName());
            recipeFlowPane.getChildren().add(recipeListItem);
        }
    }
    private void setRecipesinHashMap(){
        for (Recipe recipe : RBC.getRecipes()) {
            RecipeListItem recipeListItem = new RecipeListItem(recipe, this);
            recipeListItemMap.put(recipe.getName(), recipeListItem);
    }

}

}