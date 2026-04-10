package com.musiccl1v2.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class History {

    // credentials
    private final String url = "jdbc:mysql://localhost:3306/musiccli";
    private final String user = "root";
    private final String password = "";

    public void record(String input) {
        // sentence sql
        String sql = "INSERT INTO history (command, execution_date) VALUES (?, ?)";

        // try that close the connection automatically :D
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, input); // command exec
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // formated time

            // its differen cuzz this is for insert/update/delete and the other only for
            // select
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println("Record failed: " + e.getMessage());
        }
    }

    public void read() {
        String sql = "SELECT command, execution_date FROM history";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                // only for select
                java.sql.ResultSet rs = pstmt.executeQuery()) {

            // while still exist data...
            while (rs.next()) {
                // get string for column
                String command = rs.getString("command");
                java.sql.Timestamp date = rs.getTimestamp("execution_date");

                // format
                System.out.println("[Command: " + command + " | Date: " + date + "]");
            }

        } catch (Exception e) {
            System.err.println("Read failed: " + e.getMessage());
        }
    }
}