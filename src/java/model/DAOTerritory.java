/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Territory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOTerritory extends DBconnect {

    public void display() {
        String sql = "SELECT * FROM Territories";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                String territoryID = rs.getString(1),
                        territoryDescription = rs.getString(2);
                int regionID = rs.getInt(3);

                Territory terr = new Territory(territoryID, territoryDescription, regionID);
                System.out.println(terr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOTerritory dao = new DAOTerritory();
        dao.display();
    }
}
