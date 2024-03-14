package no.ntnu.idatt1005.dao;

import java.sql.*;
import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.Grocery.GroceryList;

public class GroceryDAO {
  private DBConnectionProvider connectionProvider;

  public GroceryDAO(DBConnectionProvider connectionProvider) {
    this.connectionProvider = connectionProvider;
  }


  /*public Grocery getGroceryById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Grocery grocery = null;

    try {
      connection = connectionProvider.getConnection();
      preparedStatement = connection.prepareStatement("SELECT * FROM GroceryRegister WHERE groceryId=?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      user = getUserFromResultSet(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      close(connection, preparedStatement, resultSet);
    }
    return user;

    return null;
  }*/

  public GroceryList getAllGroceries() {
    return null;
  }

  public void addGrocery() {}

  public void removeGrocery() {
    //kan være denne ikke er nødvendig
  }

}
