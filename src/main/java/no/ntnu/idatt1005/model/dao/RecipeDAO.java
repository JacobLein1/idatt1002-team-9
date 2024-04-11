package no.ntnu.idatt1005.model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import no.ntnu.idatt1005.model.grocery.Grocery;

import static no.ntnu.idatt1005.model.dao.DBConnectionProvider.close;

public class RecipeDAO {

    private DBConnectionProvider connectionProvider;
    private GroceryDAO groceryDAO;
    public RecipeDAO(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.groceryDAO = new GroceryDAO(connectionProvider);
    }

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

            return new Recipe(
                String.valueOf(recipeID), recipeName, ingredients, instructions, numberOfPeople);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

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

                Ingredient ingredient = new Ingredient(grocery, amount);
                ingredients.add(ingredient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return ingredients;
    }

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
                //String recipeID = String.valueOf(resultSet.getInt("recipeId"));
                String recipeName = resultSet.getString("recipeName");
                String instructions = resultSet.getString("instructions");
                int numberOfPeople = resultSet.getInt("numberOfPeople");
                List<Ingredient> ingredients = getIngredientsForRecipe(recipeID);
                Recipe recipe = new Recipe(String.valueOf(recipeID),
                    recipeName, ingredients, instructions, numberOfPeople);
                recipes.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return recipes;
    }
}
