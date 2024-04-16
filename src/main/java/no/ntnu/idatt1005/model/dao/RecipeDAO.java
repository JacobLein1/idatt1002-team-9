package no.ntnu.idatt1005.model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import no.ntnu.idatt1005.model.grocery.Grocery;

import static no.ntnu.idatt1005.model.dao.DBConnectionProvider.close;

/** Class for accessing data related to the table RecipeList. This class contains methods for
 * accessing specific recipes by its id, getting the ingredients for a specific recipe, as well as a
 * method for accessing all recipes. It also creates an object of the GroceryDAO-class, letting
 * this class gain access to relevant methods necessary for the methods of this class.
 * The code in this class was inspired by Surya Kathayat and modified to fit the needs of the
 * application. GitHub Copilot assisted with writing the code more quickly as well as suggestions.
 *
 * @author Sigrid Hoel, Therese Synnøve Rondeel
 * @see GroceryDAO
 */
public class RecipeDAO {
    /**
     * Object for help with creating a connection to the database.
     */
    private DBConnectionProvider connectionProvider;

    /**
     * Field for creating an instance of a GroceryDAO-object.
     */
    private GroceryDAO groceryDAO;

    /**
     * Constructor for creating a RecipeDAO object by taking a DBConnectionProvider as a parameter,
     * and initializing both the connection provider and the groceryDAO.
     *
     * @param connectionProvider the connection provider to the database
     */
    public RecipeDAO(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.groceryDAO = new GroceryDAO(connectionProvider);
    }

    /**
     * Method for getting a specific Recipe by its id from the database. It firstly gets a connection
     * to the database, then prepares a sqlite query and executes this. From the result set the
     * requested Recipe-object is created. GitHub Copilot helped with more quickly writing code, and
     * sometimes coming with suggestions that were controlled and modified. The method has also
     * been modified later to fit changes in relevant classes.
     *
     * @param id the id of the specific grocery
     * @return the requested Recipe-object, or null if it could not be found
     */
    public Recipe getRecipeById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM RecipeList WHERE recipeId=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            int recipeID = resultSet.getInt("recipeId");
            String recipeName = resultSet.getString("recipeName");
            String instructions = resultSet.getString("instructions");
            int numberOfPeople = resultSet.getInt("numberOfPeople");
            List<Ingredient> ingredients = getIngredientsForRecipe(recipeID);

            try {
                return new Recipe(
                    String.valueOf(recipeID), recipeName, ingredients, instructions, numberOfPeople);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * Method for getting the ingredients for a specific recipe by the recipe id. It firstly gets a
     * connection to the database, then prepares a sqlite query and executes this. After this,
     * it creates an ingredient, as long as the grocery retrieved from the GroceryDAO method
     * 'getGroceryById' is not null. GitHub Copilot helped with more quickly writing code, and
     * sometimes coming with suggestions that were controlled and modified. The method has also
     * been modified later to fit changes in relevant classes.
     *
     * @param id the id of the recipe
     * @return a list of Ingredient-objects, or an empty list if errors occur
     * @see GroceryDAO#getGroceryById(int) 
     */
    private List<Ingredient> getIngredientsForRecipe(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM RecipeGrocery WHERE recipeId=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int groceryId = resultSet.getInt("groceryId");
                Grocery grocery = groceryDAO.getGroceryById(groceryId);
                double amount = resultSet.getDouble("amount");

                if (grocery != null) {
                    Ingredient ingredient = new Ingredient(grocery, amount);
                    ingredients.add(ingredient);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return ingredients;
    }

    /**
     * Method for getting all recipes in the database. It firstly gets connection to the database, 
     * then prepares a sqlite query and executes this. After this, as long as there is another
     * row retrieved from the database, a new recipe is created. The getIngredientsForRecipe is
     * also used to get the specific ingredients needed.
     * 
     * @return a list of Recipe-objects, or an empty list if errors occur
     * @see #getIngredientsForRecipe(int) 
     */
    public List<Recipe> getAllRecipes() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Recipe> recipes = new ArrayList<>();
        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM RecipeList");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int recipeID = resultSet.getInt("recipeId");
                String recipeName = resultSet.getString("recipeName");
                String instructions = resultSet.getString("instructions");
                int numberOfPeople = resultSet.getInt("numberOfPeople");
                List<Ingredient> ingredients = getIngredientsForRecipe(recipeID);

                try {
                    Recipe recipe = new Recipe(String.valueOf(recipeID),
                        recipeName, ingredients, instructions, numberOfPeople);
                    recipes.add(recipe);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return recipes;
    }
}
