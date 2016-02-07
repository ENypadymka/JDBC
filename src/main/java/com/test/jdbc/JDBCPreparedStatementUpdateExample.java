package com.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPreparedStatementUpdateExample extends ConnectSettings {


    public static void main(String[] argv) {

        try {

            updateRecordToTable();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void updateRecordToTable() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE DBUSER SET USERNAME = ? "
                + " WHERE USER_ID = ?";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, "vasya_new_value");
            preparedStatement.setInt(2, 1001);

            // execute update SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is updated to DBUSER1 table!");

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