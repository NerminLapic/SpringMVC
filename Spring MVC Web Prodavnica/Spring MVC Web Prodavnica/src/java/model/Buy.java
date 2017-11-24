package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Nermin
 */
public class Buy {

    private int buyId;
    @NotNull
    private int customerId;
    @NotNull
    private int productId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate buyDate;
    @NotNull
    private int buyQty;
    private String buyNumber;

    /**
     * @return the buyId
     */
    public int getBuyId() {
        return buyId;
    }

    /**
     * @param buyId the buyId to set
     */
    public void setBuyId(int buyId) {
        this.buyId = buyId;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the buyDate
     */
    public LocalDate getBuyDate() {
        return buyDate;
    }

    /**
     * @param buyDate the buyDate to set
     */
    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    /**
     * @return the buyQty
     */
    public int getBuyQty() {
        return buyQty;
    }

    /**
     * @param buyQty the buyQty to set
     */
    public void setBuyQty(int buyQty) {
        this.buyQty = buyQty;
    }

    /**
     * @return the buynumber
     */
    public String getBuyNumber() {
        return buyNumber;
    }

    /**
     * @param buynumber the buynumber to set
     */
    public void setBuynumber(String buynumber) {
        this.buyNumber = buyNumber;
    }

}
