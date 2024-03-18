package no.ntnu.idatt1005.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.Grocery.GroceryList;
import no.ntnu.idatt1005.Unit.UnitsE;

import static no.ntnu.idatt1005.dao.DBConnectionProvider.close;

public class InventoryDAO {

    private DBConnectionProvider connectionProvider;

    public InventoryDAO(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public HashMap<String, Double> getInventory() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<String, Double> inventory = new HashMap<>();

        try {
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Inventory");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String groceryID = String.valueOf(resultSet.getInt("groceryId"));
                double amount = resultSet.getDouble("groceryAmount");
                inventory.put(groceryID, amount);
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
            preparedStatement = connection.prepareStatement("SELECT FROM Inventory WHERE groceryId = ?");
            preparedStatement.setInt(1, groceryID);
            resultSet = preparedStatement.executeQuery();

            return resultSet.getDouble("groceryAmount");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }
}


