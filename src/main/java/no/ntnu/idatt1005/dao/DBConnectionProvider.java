package no.ntnu.idatt1005.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
public class DBConnectionProvider {

  private final String url;

  private static DBConnectionProvider databaseConnectionProvider;

  public DBConnectionProvider() {
    String filePath = "src/main/resources/applicationDB.db";
    Path path = Paths.get(filePath);

    if (!Files.exists(path)) {
      //createDatabase();
    }
    this.url = "jdbc:sqlite:" + filePath;
  }

  public Connection getConnection() throws RuntimeException{
    try {
      return DriverManager.getConnection(url);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static DBConnectionProvider instance() {
    if (databaseConnectionProvider == null) {
      databaseConnectionProvider = new DBConnectionProvider();
    }
    return databaseConnectionProvider;
  }

  /**
   * Closes connections to database, makes sure that resultSets, and statements gets closed properly
   * @param connection the connection to be closed
   * @param preparedStatement the preparedStatement to be closed
   * @param resultSet the resultSet to be closed
   */
  public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  private void createDatabase() {
  }
}

