/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOCustomerCustomerDemo extends DBconnect {

    public void display() {
        String sql = "SELECT * FROM CustomerCustomerDemo";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                String customerID = rs.getString(1);
                String customerTypeID = rs.getString(2);
                
                CustomerCustomerDemo cusDemo = new CustomerCustomerDemo(customerID, customerTypeID);
                
                System.out.println(cusDemo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        DAOCustomerCustomerDemo dao = new DAOCustomerCustomerDemo();
        dao.display();
    }
}
