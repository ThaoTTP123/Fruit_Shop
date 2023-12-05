/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Region;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAORegion extends DBconnect {

    public ResultSet getAll() {
        String sql = "Select * from Region";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int addRegion(Region r) {
        int n = 0;
        String sqlQuery = "INSERT INTO [Region]\n"
                + "           ([RegionID]\n"
                + "           ,[RegionDescription])\n"
                + "     VALUES\n"
                + "           (" + r.getRegionID() + "\n"
                + "           ,'" + r.getRegionDescription() + "')";
        try {
            Statement state = conn.createStatement();
            n=state.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void display() {
        String sql = "SELECT * FROM Region";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int regionID = rs.getInt(1);
                String RegionDescription = rs.getString(2);

                Region region = new Region(regionID, RegionDescription);
                System.out.println(region);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAORegion dao = new DAORegion();
        System.out.println(Integer.parseInt(""));
    }
}
