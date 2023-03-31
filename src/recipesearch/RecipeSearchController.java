
package recipesearch;

import java.net.URL;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;


public class RecipeSearchController implements Initializable {
    @FXML
    private FlowPane recipeFlowPane;
    @FXML
    private ComboBox<String> mainIngredientId;
    @FXML
    private ComboBox<String> cuisineId;
    @FXML
    private RadioButton easyId;
    @FXML
    private RadioButton mediumId;
    @FXML
    private RadioButton hardId;
    @FXML
    private RadioButton allId;
    @FXML
    private Spinner<Integer> maxPriceId;
    @FXML
    private Slider maxTimeId;
    @FXML
    private Label maxTimeTextId;
    @FXML
    private Label recipeNameDetailId;
    @FXML
    private ImageView recipeImageDetailId;
    @FXML
    private SplitPane searchPaneId;
    @FXML
    private AnchorPane recipeDetailPaneId;




    RecipeDatabase db = RecipeDatabase.getSharedInstance();
    List<RecipeListItem> recipeListItem = new ArrayList<>();
    RecipeBackendController RBC = new RecipeBackendController();
    private Map<String, RecipeListItem> recipeListItemMap = new HashMap<String, RecipeListItem>();
    private ToggleGroup difficultyToggleGroup;
    private int minValue = 0, maxValue = 1000, initialValue = 0, amountToStepBy = 100;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRecipesinHashMap();
        updateRecipeList();

        initilizeComboboxMainIng();
        initilizeComboboxCuisine();
        initilizeRadioButtons();
        initilizeSpinnerMaxPrice();
        initilizeSliderMaxTime();
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
        private void initilizeComboboxCuisine(){
            cuisineId.getItems().addAll("Visa alla","Sverige", "Grekland", "Indien", "Asien", "Afrika", "Frankrike");
            cuisineId.getSelectionModel().select("Visa alla");

            cuisineId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    RBC.setCuisine(newValue);
                    updateRecipeList();
                }
            });

        }

        private void initilizeRadioButtons(){
        difficultyToggleGroup = new ToggleGroup();

        easyId.setToggleGroup(difficultyToggleGroup);
        mediumId.setToggleGroup(difficultyToggleGroup);
        hardId.setToggleGroup(difficultyToggleGroup);
        allId.setToggleGroup(difficultyToggleGroup);

        allId.setSelected(true);

            difficultyToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                    if (difficultyToggleGroup.getSelectedToggle() != null) {
                        RadioButton selected = (RadioButton) difficultyToggleGroup.getSelectedToggle();
                        RBC.setDifficulty(selected.getText());
                        updateRecipeList();
                    }
                }
            });
        }

        private void initilizeSpinnerMaxPrice(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, maxValue, initialValue, amountToStepBy);
        maxPriceId.setValueFactory(valueFactory);

            maxPriceId.valueProperty().addListener(new ChangeListener<Integer>() {

                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    RBC.setMaxPrice(newValue);
                    updateRecipeList();
                }
            });

            maxPriceId.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if(newValue){
                        //focusgained - do nothing
                    }
                    else{
                        Integer value = Integer.valueOf(maxPriceId.getEditor().getText());
                        RBC.setMaxPrice(value);
                        updateRecipeList();
                    }

                }
            });

        }

        private void initilizeSliderMaxTime(){

            maxTimeId.valueProperty().addListener(new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    maxTimeTextId.setText(newValue.intValue() + " minuter");
                    if(newValue != null && !newValue.equals(oldValue) && !maxTimeId.isValueChanging()) {
                        RBC.setMaxTime(newValue.intValue());

                        updateRecipeList();
                    }


                }
            });


        }

        private void populateRecipeDetailView(Recipe recipe){
            recipeNameDetailId.setText(recipe.getName());
            recipeImageDetailId.setImage(recipe.getFXImage());
        }

        @FXML
        public void closeRecipeView(){
            searchPaneId.toFront();
        }
        public void openRecipeView(Recipe recipe){
            populateRecipeDetailView(recipe);
            recipeDetailPaneId.toFront();
        }


}

