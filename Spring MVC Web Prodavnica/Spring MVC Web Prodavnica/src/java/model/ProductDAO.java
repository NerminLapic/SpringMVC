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
public class ProductDAO { 

    /** Metod za unos novog proizvoda u bazu*/
    public static void insertProduct(Product product) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO PRODUCTS (productId,productName,productPrice,productDescription,productQty) VALUES(?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "")) {

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, product.getProductId());
                ps.setString(2, product.getProductName());
                ps.setFloat(3, product.getProductPrice());
                ps.setString(4, product.getProductDescription());
                ps.setInt(5, product.getProductQty());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
        }
    }
      /** Metod za editovanje  proizvoda u bazi*/
   public static void editProduct(Product product) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE  PRODUCTS SET productName=?,productPrice=?,productDescription=?,productQty=? WHERE productId=?";

        Class.forName("com.mysql.jdbc.Driver");

        try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "")) {
            //Connection conn=dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, product.getProductName());
                ps.setFloat(2, product.getProductPrice());
                ps.setString(3, product.getProductDescription());
                ps.setInt(4, product.getProductQty());
                ps.setInt(5, product.getProductId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {

        }
    }
     /** Metod koji vraca listu proizvod aiz baze*/
 public static ArrayList<String[]> productList() throws SQLException{
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "");
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select * from products");
         ArrayList<String[]> proizvodi=new ArrayList<String[]>();
               while(rs.next()){
               proizvodi.add(new String[]{rs.getString("productId"),rs.getString("productName"),rs.getString("productPrice"),rs.getString("productQty")});
               
               }   
     return proizvodi;
 }
   /** Metod koji selectuje odgovarajuci proizvod u bazi i vraća taj proizvod kao objekat tipa Product*/
    public static Product selectProduct(int id) throws SQLException {

        Product product = new Product();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from products where productID=" + id);
        if (rs.next()) {
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setProductPrice(rs.getFloat("productPrice"));
            product.setProductQty(rs.getInt("productQty"));
        }

        return product;

    }
    
    public static void deleteProduct(int Id) throws SQLException{
          Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "");
         Statement st=conn.createStatement();
         st.executeUpdate("delete  from products where productId ="+Id);
    }

}



