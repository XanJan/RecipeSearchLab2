package recipesearch;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.lab2.Recipe;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RecipeListItem extends AnchorPane {
    private RecipeSearchController parentController;
    private RecipeBackendController rbc;
    private Recipe recipe;
    @FXML
    private ImageView recipeImageId;
    @FXML
    private Text recipeLableId;
    @FXML
    private ImageView mainIngredient_listItem;
    @FXML
    private ImageView flag_listItem;
    @FXML
    private ImageView difficulty_listItem;
    @FXML
    private Text time_listItem;
    @FXML
    private Text cost_listItem;

    public RecipeListItem(Recipe recipe, RecipeSearchController recipeSearchController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recipe_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = recipeSearchController;
        this.recipe = recipe;
        this.recipeImageId.setImage(parentController.getSquareImage(recipe.getFXImage()));
        this.recipeLableId.setText(recipe.getName());


        this.flag_listItem.setImage(parentController.getCuisineImage(this.recipe.getCuisine()));
        this.mainIngredient_listItem.setImage(parentController.getMainIngreidentImage(this.recipe.getMainIngredient()));
        this.difficulty_listItem.setImage(parentController.getDifficulty(this.recipe.getDifficulty()));
        this.time_listItem.setText(String.valueOf(this.recipe.getTime()) + " minuter");
        this.cost_listItem.setText((String.valueOf(this.recipe.getPrice())) + " kr");



    }


    @FXML
    protected void onClick(Event event) {
        parentController.openRecipeView(recipe);
    }



}
