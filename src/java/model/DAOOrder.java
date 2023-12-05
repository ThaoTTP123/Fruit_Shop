/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOOrder extends DBconnect {

    public int addOrder(Order o) {
        int n = 0;
        int orderID = -1;
        String sql = "INSERT INTO [Orders]\n"
                + "           ([CustomerID]\n"
                + "           ,[EmployeeID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[RequiredDate]\n"
                + "           ,[ShippedDate]\n"
                + "           ,[ShipVia]\n"
                + "           ,[Freight]\n"
                + "           ,[ShipName]\n"
                + "           ,[ShipAddress]\n"
                + "           ,[ShipCity]\n"
                + "           ,[ShipRegion]\n"
                + "           ,[ShipPostalCode]\n"
                + "           ,[ShipCountry])\n"
                + "     VALUES\n"
                + "           ('"+o.getCustomerID()+"'\n"
                + "           ,"+o.getEmployeeID()+"\n"
                + "           ,'"+o.getOrderDate()+"'\n"
                + "           ,'"+o.getRequiredDate()+"'\n"
                + "           ,'"+o.getShippedDate()+"'\n"
                + "           ,"+o.getShipVia()+"\n"
                + "           ,"+o.getFreight()+"\n"
                + "           ,'"+o.getShipName()+"'\n"
                + "           ,'"+o.getShipAddress()+"'\n"
                + "           ,'"+o.getShipCity()+"'\n"
                + "           ,'"+o.getShipRegion()+"'\n"
                + "           ,'"+o.getShipPostalCode()+"'\n"
                + "           ,'"+o.getShipCountry()+"')";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            n = state.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = state.getGeneratedKeys();
            while (rs.next()) {
                orderID = rs.getInt(1);
                return orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void display() {
        String sql = "SELECT * FROM Orders";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String customerID = rs.getString(2);
                int employeeID = rs.getInt(3);
                String orderDate = rs.getString(4),
                        requiredDate = rs.getString(5),
                        shippedDate = rs.getString(6);
                int shipVia = rs.getInt(7);
                double freight = rs.getDouble(8);
                String shipName = rs.getString(9),
                        shipAddress = rs.getString(10),
                        shipCity = rs.getString(11),
                        shipRegion = rs.getString(12),
                        shipPostalCode = rs.getString(13),
                        shipCountry = rs.getString(14);

                Order ord = new Order(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry);
                System.out.println(ord);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet searchOrderByCus(String cID){
        String sql="Select OrderID,RequiredDate,EmployeeID from Orders where CustomerID = '"+cID+"'";
        ResultSet rs=getData(sql);
        return rs;
    }
    public ResultSet searchOrderByEmp(int eID){
        String sql="Select OrderID,RequiredDate,CustomerID from Orders where EmployeeID = "+eID;
        ResultSet rs=getData(sql);
        return rs;
    }
    public static void main(String[] args) {
        Date d=new Date();
        System.out.println(new SimpleDateFormat("yyyyMMdd").format(d));
    }
}
