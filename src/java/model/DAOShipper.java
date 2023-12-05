/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Shipper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOShipper extends DBconnect {

    public ResultSet getAll() {
        String sql = "Select * from Shippers";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int addShipper(Shipper s) {
        int n = 0;
        String sqlQuery = "INSERT INTO [Shippers]\n"
                + "           ([CompanyName]\n"
                + "           ,[Phone])\n"
                + "     VALUES\n"
                + "           ('" + s.getCompanyName() + "'\n"
                + "           ,'" + s.getPhone() + "')";
        try {
            Statement state = conn.createStatement();
            n=state.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void display() {
        String sql = "SELECT * FROM Shippers";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int shipperID = rs.getInt(1);
                String companyName = rs.getString(2);
                String phone = rs.getString(3);

                Shipper shipper = new Shipper(shipperID, companyName, phone);
                System.out.println(shipper);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOShipper dao = new DAOShipper();
        dao.display();
    }
}
