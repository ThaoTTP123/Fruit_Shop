/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOOrderDetail extends DBconnect {

    public int addOrderDetail(OrderDetail od) {
        int n = 0;
        String sql = "INSERT INTO [Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Discount])\n"
                + "     VALUES\n"
                + "           ("+od.getOrderID()+"\n"
                + "           ,"+od.getProductID()+"\n"
                + "           ,"+od.getUnitPrice()+"\n"
                + "           ,"+od.getQuantity()+"\n"
                + "           ,"+od.getDiscount()+")";
        try {
            Statement state=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public ResultSet getOrderDetailByID(int OrderID) {
        String sql = "With t1 as(\n"
                + "		Select OrderID,(UnitPrice*Quantity)-(UnitPrice*Quantity*Discount) subTotal,ProductID,UnitPrice,Quantity,Discount\n"
                + "		from [Order Details]\n"
                + "	),\n"
                + "	t2 as(\n"
                + "		Select o.OrderID,p.ProductName,p.ProductID\n"
                + "		from [Order Details] o join Products p on o.ProductID=p.ProductID\n"
                + "	)\n"
                + "Select t1.OrderID,t2.ProductName,t1.Quantity,t1.UnitPrice,t1.Discount,t1.subTotal\n"
                + "from t1 join t2 on t1.OrderID=t2.OrderID and t1.ProductID=t2.ProductID where t1.OrderID="+OrderID;
        ResultSet rs=getData(sql);
        return rs;
    }
    public void display() {
        String sql = "SELECT * FROM [Order Details]";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int orderID = rs.getInt(1),
                        productID = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getDouble(5);

                OrderDetail ordDetail = new OrderDetail(orderID, productID, unitPrice, quantity, discount);

                System.out.println(ordDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOOrderDetail dao = new DAOOrderDetail();
        dao.display();
    }
}
