package com.musiccl1v2.data;

import java.sql.*;

import com.musiccl1v2.ui.ConsoleRenderer;

public class History implements AutoCloseable {

    // ! The credential are exposed for testing
    // credentials
    private final String url = "JDBC:mysql://localhost:3306/musiccli";
    private final String user = "root";
    private final String password = "";
    private final Connection connection;

    public History() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void record(String input) {
        // sentence SQL
        // ? I can do this like a generic method in where you receive a query directly?
        String sql = "INSERT INTO history (command, execution_date) VALUES (?, ?)";

        // try that close the connection automatically :D
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {

            stmt.setString(1, input); // command exec
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // formated time

            // its different cuzz this is for insert/update/delete and the other only for
            // select
            stmt.executeUpdate();

        } catch (Exception e) {
            ConsoleRenderer.printError(e.getMessage());
        }
    }

    public void read() {
        String sql = "SELECT command, execution_date FROM history";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             // only for select
             java.sql.ResultSet rs = stmt.executeQuery()) {

            // while still exist data...
            while (rs.next()) {
                // get string for column
                String command = rs.getString("command");
                java.sql.Timestamp date = rs.getTimestamp("execution_date");

                // ? this should be in the CLController
                // format
                System.out.println("[Command: " + command + " | Date: " + date + "]");
            }

        } catch (Exception e) {
            ConsoleRenderer.printError(e.getMessage());
        }
    }

    @Override
    public void close() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }
}