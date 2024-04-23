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
 * the needs of the application. GitHub Copilot assisted with writing the code more quickly, as
 * well as suggestions.
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
     * Field for creating an instance of a GroceryDAO-object.
     */
    private final GroceryDAO groceryDAO;

    /**
     * Constructor for creating an InventoryDAO object by taking a DBConnectionProvider as a
     * parameter, and initializing both the connection provider and the GroceryDAO.
     *
     * @param connectionProvider the connection provider to the database
     */
    public InventoryDAO(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.groceryDAO = new GroceryDAO(connectionProvider);
    }

    /**
     * Method for accessing all groceries in the inventory. It firstly gets a connection to the
     * database, then prepares a sqlite query and executes this. After this, for each row retrieved,
     * an Ingredient-object, representing the grocery and the associated value, is created and
     * added to the Inventory-object. The initial method was created with the help of GitHub
     * Copilot, and was quality assured. The method has later been modified to fit changes in
     * the architecture of the codebase as well as relevant classes.
     *
     * @return an Inventory-object containing the groceries with the associated value, represented
     *         by using the Ingredient-class
     */
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

    /**
     * Method for adding a grocery to the Inventory-table by the id of the grocery, as well as the
     * amount of the grocery to be added. It firstly gets a connection to the database, then
     * prepares a sqlite query and executes the insertion to the database. The initial method was
     * created with the help of GitHub Copilot, and was quality assured.
     *
     * @param groceryID the id of the grocery to be added
     * @param groceryAmount the amount of the grocery to be added
     */
    public void addGrocery(int groceryID, double groceryAmount) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(
                "INSERT INTO Inventory (groceryId, groceryAmount) VALUES (?, ?)");
            preparedStatement.setInt(1, groceryID);
            preparedStatement.setDouble(2, groceryAmount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Method for updating the amount of a grocery that already exists in the Inventory-table by
     * its id and the new amount to be set. It firstly gets a connection to the database, then
     * prepares a sqlite query and executes the update to the database. The initial method was
     * created with the help of GitHub Copilot, and was quality assured.
     *
     * @param groceryID the id of the grocery to be updated
     * @param groceryAmount the new amount of the grocery to be updated
     */
    public void updateGrocery(int groceryID, double groceryAmount) {

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

    /**
     * Method for deleting a grocery from the Inventory table by its id. It firstly gets a
     * connection to the database, then prepares a sqlite query and executes the deletion. The
     * initial method was created with the help of GitHub Copilot, and was quality assured.
     *
     * @param groceryID the id of the grocery to be updated
     */
    public void removeGrocery(int groceryID) {


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

    /**
     * Method for getting the amount of a grocery in the inventory. It firstly gets a connection to
     * the database, then prepares a sqlite query and executes it. From the resultSet, the amount
     * of the grocery is retrieved. If an error occurs 0 is returned. The initial method was
     * created with the help of GitHub Copilot, and was quality assured.
     *
     * @param groceryID the id of the grocery
     * @return the amount of the grocery in the inventory, or 0 if an error occurs
     */
    public double getGroceryAmount(int groceryID) {
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


