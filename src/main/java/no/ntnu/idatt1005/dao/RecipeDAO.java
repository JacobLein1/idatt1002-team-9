package no.ntnu.idatt1005.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.Grocery.GroceryList;
import no.ntnu.idatt1005.Recipe.Recipe;
import no.ntnu.idatt1005.Unit.UnitsE;

import static no.ntnu.idatt1005.dao.DBConnectionProvider.close;

public class RecipeDAO {

    private DBConnectionProvider connectionProvider;
    public RecipeDAO(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Recipe> getRecipes(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Recipe> recipes = new ArrayList<>();
        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Recipe join RecipeGrocery on Recipe.recipeId = RecipeGrocery.recipeId");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String recipeID = String.valueOf(resultSet.getInt("recipeId"));
                String recipeName = resultSet.getString("recipeName");
                String instructions = resultSet.getString("instructions");
                int numberOfPeople = resultSet.getInt("numberOfPeople");
                String groceryID = String.valueOf(resultSet.getInt("groceryId"));
                double amount = resultSet.getDouble("amount");
                Map<String, Double> ingredients = new HashMap<>();
                ingredients.put(groceryID, amount);
                Recipe recipe = new Recipe(recipeID, recipeName, instructions, numberOfPeople, ingredients);
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
