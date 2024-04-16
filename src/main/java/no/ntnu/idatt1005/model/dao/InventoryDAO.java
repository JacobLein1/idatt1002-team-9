package no.ntnu.idatt1005.model.dao;
import java.sql.*;
import java.util.HashMap;
import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.model.inventory.Inventory;

import static no.ntnu.idatt1005.model.dao.DBConnectionProvider.close;

/** Class for accessing data related to the table Inventory. This class contains methods for
 * accessing all groceries in the inventory, adding, updating or deleting specific groceries, as
 * well as getting the amount of a specific grocery in the inventory. It also creates an object of
 * the GroceryDAO-class, letting this class gain access to relevant methods necessary for the
 * methods of this class. The code in this class was inspired by Surya Kathayat and modified to fit
 * the needs of the application. GitHub Copilot assisted with writing the code more quickly.
 *
 * @author Sigrid Hoel, Therese Synnøve Rondeel
 * @see GroceryDAO
 */
public class InventoryDAO {

    /**
     * Object for help with creating a connection to the database.
     */
    private final DBConnectionProvider connectionProvider;

    /**
     * Object for creating an instance of GroceryDAO.
     */
    private final GroceryDAO groceryDAO;

    public InventoryDAO(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.groceryDAO = new GroceryDAO(connectionProvider);
    }

    public Inventory getInventory() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Inventory inventory = new Inventory();

        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Inventory");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int groceryID = resultSet.getInt("groceryId");
                Grocery grocery = groceryDAO.getGroceryById(groceryID);
                double amount = resultSet.getDouble("groceryAmount");

                if (grocery != null) {
                    Ingredient invGrocery = new Ingredient(grocery, amount);
                    inventory.addGroceryToInventory(invGrocery);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return inventory;
    }

    public void addGrocery(int groceryID, double groceryAmount) throws IllegalArgumentException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Inventory (groceryId, groceryAmount) VALUES (?, ?)");
            preparedStatement.setInt(1, groceryID);
            preparedStatement.setDouble(2, groceryAmount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    public void updateGrocery(int groceryID, double groceryAmount) throws IllegalArgumentException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Inventory SET groceryAmount = ? WHERE groceryId = ?");
            preparedStatement.setDouble(1, groceryAmount);
            preparedStatement.setInt(2, groceryID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    public void removeGrocery(int groceryID) throws IllegalArgumentException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Inventory WHERE groceryId = ?");
            preparedStatement.setInt(1, groceryID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    public double getGroceryAmount(int groceryID) throws IllegalArgumentException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Inventory WHERE groceryId = ?");
            preparedStatement.setInt(1, groceryID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("groceryAmount");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }
}


