package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Entity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;





/**
 *
 * @author Nermin
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @NotNull
    @Column (name="productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @NotEmpty
    @Column(name = "productName")
    private String productName;
    @NotNull
    @Column(name = "productPrice")
    private float productPrice;
    @NotEmpty
    @Column(name = "productDescription")
    private String productDescription;
    @NotNull
    @Column(name = "productQty")
    private int productQty;

    Product(int id,String name, int price,String description, int qty){
        this.productId=id;
        this.productName=name;
        this.productPrice=price;
        this.productDescription=description;
        this.productQty=qty;
                
    
    }

    public Product() {
    }
    
public int getProductId(){
    
    return productId;
}

public String getProductName(){
    return productName;
}

public float getProductPrice(){
    return productPrice;
}

public String getProductDescription(){
    return productDescription;
}
public int getProductQty()
{
    return productQty;
}

public void setProductId(int productId){
    this.productId=productId;    
}

public void setProductName(String productName){
    this.productName=productName;
}

public void setProductPrice(float productPrice){
    this.productPrice=productPrice;
}

public void setProductDescription(String productDescriptio){
    this.productDescription=productDescriptio;
}

public void setProductQty(int qty){
    this.productQty=qty;
}


}