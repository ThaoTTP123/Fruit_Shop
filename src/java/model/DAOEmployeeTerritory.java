/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.EmployeeTerritory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOEmployeeTerritory extends DBconnect {

    public void display() {
        String sql = "SELECT * FROM EmployeeTerritories";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int employeeID = rs.getInt(1);
                String territoryID = rs.getString(2);
                EmployeeTerritory empTerritory = new EmployeeTerritory(employeeID, territoryID);
                
                System.out.println(empTerritory);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        DAOEmployeeTerritory dao = new DAOEmployeeTerritory();
        dao.display();
    }
}
