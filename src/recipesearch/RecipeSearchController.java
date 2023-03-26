
package recipesearch;

import java.net.URL;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;


public class RecipeSearchController implements Initializable {
    @FXML
    private FlowPane recipeFlowPane;
    @FXML
    private ComboBox<String> mainIngredientId;

    RecipeDatabase db = RecipeDatabase.getSharedInstance();
    List<RecipeListItem> recipeListItem = new ArrayList<>();
    RecipeBackendController RBC = new RecipeBackendController();
    private Map<String, RecipeListItem> recipeListItemMap = new HashMap<String, RecipeListItem>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRecipesinHashMap();
        updateRecipeList();

        initilizeComboboxMainIng();
    }
    private void updateRecipeList(){
        recipeFlowPane.getChildren().clear();
        List<Recipe> recipesList = RBC.getRecipes();

        for(Recipe recipe: recipesList){
            RecipeListItem recipeListItem = recipeListItemMap.get(recipe.getName());
            recipeFlowPane.getChildren().add(recipeListItem);
        }
    }
    private void setRecipesinHashMap() {
        for (Recipe recipe : RBC.getRecipes()) {
            RecipeListItem recipeListItem = new RecipeListItem(recipe, this);
            recipeListItemMap.put(recipe.getName(), recipeListItem);
        }
    }

        private void initilizeComboboxMainIng () {
            mainIngredientId.getItems().addAll("Visa alla", "Kött", "Fisk", "Kyckling", "Vegetarisk");
            mainIngredientId.getSelectionModel().select("Visa alla");

            mainIngredientId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    RBC.setMainIngridient(newValue);
                    updateRecipeList();
                }
            });
        }


}

