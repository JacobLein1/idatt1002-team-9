package no.ntnu.idatt1005.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.unit.UnitsE;

import static no.ntnu.idatt1005.model.dao.DBConnectionProvider.close;

public class GroceryDAO {
  private DBConnectionProvider connectionProvider;

  public GroceryDAO(DBConnectionProvider connectionProvider) {
    this.connectionProvider = connectionProvider;
  }


  public Grocery getGroceryById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Grocery grocery = null;

    try {
      connection = connectionProvider.getConnection();
      preparedStatement = connection.prepareStatement("SELECT * FROM GroceryRegister WHERE groceryId=?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      grocery = getGroceryFromResultSet(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      close(connection, preparedStatement, resultSet);
    }
    return grocery;
  }

  private Grocery getGroceryFromResultSet(ResultSet resultSet){
    Grocery grocery = null;
    try{
          String name = resultSet.getString("name");
          String image = resultSet.getString("image");
          String groceryId = String.valueOf(resultSet.getInt("groceryId"));
          String unitString = resultSet.getString("unit");

          try{
            UnitsE unit = UnitsE.getValue(unitString);
            grocery = new Grocery (name, image, groceryId, unit);
          } catch (Exception e){
            e.printStackTrace();
          }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return grocery;
  }

  public List<Grocery> getAllGroceries() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Grocery> groceryList = new ArrayList<>();

    try {
      connection = connectionProvider.getConnection();
      preparedStatement = connection.prepareStatement("SELECT * FROM GroceryRegister");
      resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        Grocery grocery = getGroceryFromResultSet(resultSet);
        groceryList.add(grocery);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      close(connection, preparedStatement, resultSet);
    }
    return groceryList;
  }

  public void addGrocery(String name, String image, UnitsE unit) throws IllegalArgumentException {

    if (name == null || image == null || unit == null) {
      throw new IllegalArgumentException("Name, image and unit cannot be null");
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("INSERT INTO GroceryRegister (name, image, unit) VALUES (?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, image);
        preparedStatement.setString(3, unit.getUnit());
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
    }finally{
      close(connection, preparedStatement, resultSet);
    }
  }

}
