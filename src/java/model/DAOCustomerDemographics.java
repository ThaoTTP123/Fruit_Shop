/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerDemographics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOCustomerDemographics extends DBconnect {

    public ResultSet getAll() {
        String sql = "Select * from CustomerDemographics";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int addCustomerDemographics(CustomerDemographics c) {
        int n = 0;
        String sqlQuery = "INSERT INTO [dbo].[CustomerDemographics]\n"
                + "           ([CustomerTypeID]\n"
                + "           ,[CustomerDesc])\n"
                + "     VALUES\n"
                + "           ('"+c.getCustomerTypeID()+"'\n"
                + "           ,'"+c.getCustomerDesc()+"')";
        try {
            Statement state=conn.createStatement();
            n=state.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void display() {
        String sql = "SELECT * FROM CustomerDemographics";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                String customerTypeID = rs.getString(1);
                String CustomerDesc = rs.getString(2);

                CustomerDemographics cusDemographics = new CustomerDemographics(customerTypeID, CustomerDesc);
                System.out.println(cusDemographics);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOCustomerDemographics dao = new DAOCustomerDemographics();
        dao.display();
    }
}
