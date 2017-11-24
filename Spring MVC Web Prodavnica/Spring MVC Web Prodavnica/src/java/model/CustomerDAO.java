/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nermin
 */
public class CustomerDAO {
    
    public static void insertCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO CUSTOMERS (customerId,firstName,lastName,email) VALUES(?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "")) {
            // Connection conn=dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customer.getCustomerId());
                ps.setString(2, customer.getFirstName());
                ps.setString(3, customer.getLastName());
                ps.setString(4, customer.getEmail());

                ps.executeUpdate();
            }

        } catch (SQLException e) {

        }

        
    
    
}
    public static void editCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE  CUSTOMERS SET firstName=?,lastName=?,email=? WHERE customerId=?";

        Class.forName("com.mysql.jdbc.Driver");

        try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "")) {
            //Connection conn=dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, customer.getFirstName());
                ps.setString(2, customer.getLastName());
                ps.setString(3, customer.getEmail());
                ps.setInt(4, customer.getCustomerId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {

        }
    }


    public static ArrayList<String[]> customerList() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from Customers");
        ArrayList<String[]> kupci = new ArrayList<String[]>();
        while (rs.next()) {
            kupci.add(new String[]{rs.getString("customerId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email")});

        }

        return kupci;
    }
    
    public static void deleteCustomer(int id) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "");
        Statement st = conn.createStatement();
        st.executeUpdate("delete  from Customers where customerId =" + id);
    }

public static Customer selectCusomer(int id) throws SQLException{
     Customer cust = new Customer();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select *  from Customers where customerId =" + id);
        if (rs.next()) {

            cust.setCustomerId(rs.getInt("customerId"));
            cust.setFirstName(rs.getString("firstName"));
            cust.setLastName(rs.getString("lastName"));
            cust.setEmail(rs.getString("email"));
        }
        
        return cust;
}
    
}
