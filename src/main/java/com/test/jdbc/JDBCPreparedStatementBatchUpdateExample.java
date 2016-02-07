package com.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPreparedStatementBatchUpdateExample extends ConnectSettings {

    public static void main(String[] argv) {

        try {

            batchInsertRecordsIntoTable();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void batchInsertRecordsIntoTable() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO DBUSER1"
                + "(USER_ID, USERNAME, CREATED_BY) VALUES"
                + "(?,?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            dbConnection.setAutoCommit(false);

            preparedStatement.setInt(1, 101);
            preparedStatement.setString(2, "vova");
            preparedStatement.setString(3, "system");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 102);
            preparedStatement.setString(2, "vanya");
            preparedStatement.setString(3, "system");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 103);
            preparedStatement.setString(2, "sergey");
            preparedStatement.setString(3, "system");
            preparedStatement.addBatch();

            preparedStatement.executeBatch();

            dbConnection.commit();

            System.out.println("Record is inserted into DBUSER1 table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            dbConnection.rollback();

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