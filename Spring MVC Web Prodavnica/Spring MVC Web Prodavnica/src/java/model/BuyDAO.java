/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Nermin
 */
public class BuyDAO {

    public static void insertBuy(Buy buy) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO BUY (buyId,productId,customerId,buyNumber,buyQty) VALUES(?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "")) {
            // Connection conn=dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, buy.getBuyId());
                ps.setInt(2, buy.getProductId());
                ps.setInt(3, buy.getCustomerId());
                ps.setString(4, buy.getBuyNumber());
                ps.setInt(5, buy.getBuyQty());
                ps.executeUpdate();
            }

        } catch (SQLException e) {

        }
    }

}
