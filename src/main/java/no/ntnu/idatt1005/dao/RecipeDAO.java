package no.ntnu.idatt1005.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.Grocery.GroceryList;
import no.ntnu.idatt1005.Recipe.Ingredient;
import no.ntnu.idatt1005.Recipe.Recipe;
import no.ntnu.idatt1005.Unit.UnitsE;

import static no.ntnu.idatt1005.dao.DBConnectionProvider.close;

public class RecipeDAO {

    private DBConnectionProvider connectionProvider;
    public RecipeDAO(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Ingredient> getIngredientsForRecipe(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM RecipeGrocery WHERE recipeId=id");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String groceryId = String.valueOf(resultSet.getInt("groceryId"));
                double amount = resultSet.getDouble("amount");

                Ingredient ingredient = new Ingredient(groceryId, amount);
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
                String recipeID = String.valueOf(resultSet.getInt("recipeId"));
                String recipeName = resultSet.getString("recipeName");
                String instructions = resultSet.getString("instructions");
                int numberOfPeople = resultSet.getInt("numberOfPeople");
                Recipe recipe = new Recipe(recipeID, recipeName, instructions, numberOfPeople);
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
