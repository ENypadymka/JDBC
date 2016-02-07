package com.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPreparedStatementInsertExample extends ConnectSettings {


    public static void main(String[] argv) {

        try {

            insertRecordIntoTable();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void insertRecordIntoTable() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO DBUSER1"
                + "(USER_ID, USERNAME, CREATED_BY) VALUES"
                + "(?,?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, 11);
            preparedStatement.setString(2, "vasya");
            preparedStatement.setString(3, "system");


            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into DBUSER1 table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }
}