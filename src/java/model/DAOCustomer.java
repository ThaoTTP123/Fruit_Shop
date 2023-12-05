/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOCustomer extends DBconnect{
    public ResultSet getAll(){
        String sql="Select * from Customers";
        ResultSet rs=getData(sql);
        return rs;
    }
    public Customer getCus(String cusid) {
        ResultSet rs=getAll();
        try {
            while (rs.next()) {
                if (cusid.equals(rs.getString(1))) {
                    Customer c = new Customer(rs.getString(1), rs.getString(3));
                    return c;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
     public void display() {
        String sql = "SELECT * FROM Customers";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
     String CustomerID = rs.getString(1),
            companyName = rs.getString(2),
            contactName = rs.getString(3),
            contactTitle = rs.getString(4),
            address = rs.getString(5),
            city = rs.getString(6),
            region = rs.getString(7),
            postalCode = rs.getString(8),
            country = rs.getString(9),
            phone = rs.getString(10),
            fax = rs.getString(11);
                
                Customer cus = new Customer(CustomerID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax);
                
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        dao.display();
    }
}
