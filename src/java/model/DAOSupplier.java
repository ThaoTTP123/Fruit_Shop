/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOSupplier extends DBconnect {
    public ResultSet getAll() {
        String sql = "Select * from Suppliers";
        ResultSet rs=getData(sql);
        return rs;
    }
    public int addSupplier(Supplier s){
        int n=0;
        String sqlQuery="INSERT INTO [Suppliers]\n" +
"           ([CompanyName]\n" +
"           ,[ContactName]\n" +
"           ,[ContactTitle]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[Phone]\n" +
"           ,[Fax]\n" +
"           ,[HomePage])\n" +
"     VALUES\n" +
"           ('"+s.getCompanyName()+"'\n" +
"           ,'"+s.getContactName()+"'\n" +
"           ,'"+s.getContactTitle()+"'\n" +
"           ,'"+s.getAddress()+"'\n" +
"           ,'"+s.getCity()+"'\n" +
"           ,'"+s.getRegion()+"'\n" +
"           ,'"+s.getPostalCode()+"'\n" +
"           ,'"+s.getCountry()+"'\n" +
"           ,'"+s.getPhone()+"'\n" +
"           ,'"+s.getFax()+"'\n" +
"           ,'"+s.getHomePage()+"')";
        try {
            Statement state=conn.createStatement();
            n=state.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public void display() {
        String sql = "SELECT * FROM Suppliers";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int supplierID = rs.getInt(1);
                String companyName = rs.getString(2),
                        contactName = rs.getString(3),
                        contactTitle = rs.getString(4),
                        address = rs.getString(5),
                        city = rs.getString(6),
                        region = rs.getString(7),
                        postalCode = rs.getString(8),
                        country = rs.getString(9),
                        phone = rs.getString(10),
                        fax = rs.getString(11),
                        homePage = rs.getString(12);
                Supplier sup = new Supplier(supplierID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, homePage);
                System.out.println(sup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOSupplier dao = new DAOSupplier();
        dao.display();
    }
}
