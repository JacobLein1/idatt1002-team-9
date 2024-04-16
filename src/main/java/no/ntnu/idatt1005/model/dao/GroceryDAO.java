package no.ntnu.idatt1005.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.Unit.UnitsE;

import static no.ntnu.idatt1005.model.dao.DBConnectionProvider.close;

/** Class for accessing data related to the table GroceryRegister. This class contains methods for
 * accessing specific groceries by its id, all groceries or adding a grocery to the GroceryRegister.
 * As per this version of the application, the addGrocery-method is not used.
 * The code in this class was inspired by Surya Kathayat and modified to fit the needs of the
 * application. GitHub Copilot assisted with writing the code more quickly.
 *
 * @author Sigrid Hoel, Therese Synnøve Rondeel
 */
public class GroceryDAO {
  /**
   * Object for help with creating a connection to the database.
   */
  private final DBConnectionProvider connectionProvider;

  /**
   * Constructor for creating a GroceryDAO object by taking a DBConnectionProvider as a parameter.
   *
   * @param connectionProvider the connection provider to the database
   */
  public GroceryDAO(DBConnectionProvider connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  /**
   * Method for getting a specific Grocery by its id from the database. It firstly gets a connection
   * to the database, then prepares a sqlite query and executes this. From the result set the
   * requested Grocery-object is created through the GetGroceryFromResultSet-method.
   *
   * @param id the id of the specific grocery
   * @return the requested Grocery-object, or null if it could not be found
   * @see #getGroceryFromResultSet(ResultSet)
   */
  public Grocery getGroceryById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Grocery grocery = null;

    try {
      connection = connectionProvider.getConnection();
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM GroceryRegister WHERE groceryId=?");
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

  /**
   * Method for getting all groceries that exist in the GroceryRegister table in the database. It
   * firstly gets a connection to the database, then prepares a sqlite query and executes it. After
   * this, the method iterates through all the result-sets and creates a grocery-object from each
   * one through the getGroceryFromResultSet-method.
   *
   * @return a list of all Groceries in the database, or an empty list if an error occurs
   * @see #getGroceryFromResultSet(ResultSet)
   */
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
        if (grocery != null) {
          groceryList.add(grocery);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      close(connection, preparedStatement, resultSet);
    }
    return groceryList;
  }

  /**
   * Method for adding a grocery to the database, through parameters representing a name, image and
   * unit. When the application supports pictures, the 'String image' parameter should be changed
   * accordingly. The method firstly checks that the parameters are valid. Then it gets a
   * connection to the database, prepares a sqlite query and executes it, adding the grocery to
   * the database. GitHub Copilot assisted with quickly writing the code for this method, but its
   * suggestions were quality-checked.
   *
   * @param name the name of the grocery
   * @param image the image for the grocery
   * @param unit the unit of the grocery
   * @throws IllegalArgumentException if either of the parameters are null
   */
  public void addGrocery(String name, String image, UnitsE unit) throws IllegalArgumentException {

    if (name == null || image == null || unit == null) {
      throw new IllegalArgumentException("Name, image and unit cannot be null");
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
            "INSERT INTO GroceryRegister (name, image, unit) VALUES (?, ?, ?)");
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

  /**
   * Method for creating a Grocery-object from a result set.
   *
   * @param resultSet the result set containing the information for the creation of the Grocery-object
   * @return the requested Grocery-object, or null if any problems occurs when creating it
   */
  private Grocery getGroceryFromResultSet(ResultSet resultSet) {
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
}