/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huytu
 */
public class DAOProduct extends DBconnect {

    public ResultSet getAll() {
        String sql = "Select b.ProductID,b.ProductName,b.QuantityPerUnit,b.UnitPrice,b.UnitsInStock,b.UnitsOnOrder,a.CategoryID,a.CategoryName,s.SupplierID,s.CompanyName \n"
                + "from Categories a join Products b on a.CategoryID=b.CategoryID\n"
                + "join Suppliers s on s.SupplierID=b.SupplierID ";
        ResultSet rs = getData(sql);
        return rs;
    }
    public ResultSet getAllByOrder(String type,String order,String cate) {
        String sql = "Select b.ProductID,b.ProductName,b.QuantityPerUnit,b.UnitPrice,b.UnitsInStock,b.UnitsOnOrder,a.CategoryID,a.CategoryName,s.SupplierID,s.CompanyName \n"
                + "from Categories a join Products b on a.CategoryID=b.CategoryID\n"
                + "join Suppliers s on s.SupplierID=b.SupplierID where a.CategoryID="+cate+" order by "+type+" "+order;
        ResultSet rs = getData(sql);
        return rs;
    }
    public ResultSet searchByName(String name) {
        String sql = "Select b.ProductID,b.ProductName,b.QuantityPerUnit,b.UnitPrice,b.UnitsInStock,b.UnitsOnOrder,a.CategoryID,a.CategoryName,s.SupplierID,s.CompanyName \n"
                + "from Categories a join Products b on a.CategoryID=b.CategoryID\n"
                + "join Suppliers s on s.SupplierID=b.SupplierID WHERE ProductName like '%" + name + "%'";
        ResultSet rs = getData(sql);
        return rs;
    }
     public ResultSet searchByCateID(int cateID) {
        String sql = "Select b.ProductID,b.ProductName,b.QuantityPerUnit,b.UnitPrice,b.UnitsInStock,b.UnitsOnOrder,a.CategoryID,a.CategoryName,s.SupplierID,s.CompanyName \n"
                + "from Categories a join Products b on a.CategoryID=b.CategoryID\n"
                + "join Suppliers s on s.SupplierID=b.SupplierID WHERE a.CategoryID="+cateID;
        ResultSet rs = getData(sql);
        return rs;
    }
    //remove
    public int removeProduct(int id) {
        int n = 0;
        ResultSet rsOrdDetail = getData("select * from [Order details]" + " where productid=" + id);
        try {
            if (!rsOrdDetail.next()) {
                Statement state = conn.createStatement();
                n = state.executeUpdate("delete From Products where productid=" + id);
            }
        } catch (SQLException ex) {
            n = -1;
            ex.printStackTrace();
        }
        return n;
    }

    //add product
    public int addProduct(Product pro) {
        int n = 0;
        String sqlQuery = "INSERT INTO [Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES\n"
                + "           ('" + pro.getProductName() + "', " + pro.getSupplierID() + ", "
                + "" + pro.getCategoryID() + ",'" + pro.getQuantityPerUnit() + "', " + pro.getUnitPrice() + ", "
                + "" + pro.getUnitsInStock() + ", " + pro.getUnitsOnOrder() + ", " + pro.getReorderLevel() + ", "
                + (pro.isDiscontinued() == true ? 1 : 0) + ")";

        //debug
        System.out.println(sqlQuery);
        //
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //add product use prepare statement
    public int addProductUsePrepareStatement(Product pro) {
        int n = 0;
        String sqlQuery = "INSERT INTO [Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sqlQuery);
            //setData: pre.setDataType(index, parameter);
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.isDiscontinued() == true ? 1 : 0);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    //update product
    public int updatePrice(int pId, double newPrice) {
        int n = 0;
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [Products]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[SupplierID] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[QuantityPerUnit] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[UnitsInStock] =	?\n"
                + "      ,[UnitsOnOrder] = ?\n"
                + "      ,[ReorderLevel] = ?\n"
                + "      ,[Discontinued] = ?\n"
                + " WHERE ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.isDiscontinued() == true ? 1 : 0);
            pre.setInt(10, pro.getProductID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    //search
    public Product searchByID(int id) {
        Product pro = null;
        String sqlQuery = "SELECT * FROM Products WHERE productID=" + id;
        ResultSet rs = getData(sqlQuery);
        try {
            while (rs.next()) {
                if (rs.getInt(1) == id) {
                    int pid = rs.getInt(1); // === int pid = rs.getInt("productID");
                    String pname = rs.getString("productName"); // ===  String pname = rs.getString(2);
                    int supplierID = rs.getInt(3), categoryID = rs.getInt(4);
                    String quantityPerUnit = rs.getString(5);
                    double unitPrice = rs.getDouble(6);
                    int unitsInStock = rs.getInt(7), unitsOnOrder = rs.getInt(8), reorderLevel = rs.getInt(9);
                    boolean discontinued = rs.getInt(10) == 1 ? true : false;
                    pro = new Product(pid, pname, supplierID, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    public void display() {
        String sql = "SELECT * FROM Products";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int pid = rs.getInt(1); // === int pid = rs.getInt("productID");
                String pname = rs.getString("productName"); // ===  String pname = rs.getString(2);
                int supplierID = rs.getInt(3), categoryID = rs.getInt(4);
                String quantityPerUnit = rs.getString(5);
                double unitPrice = rs.getDouble(6);
                int unitsInStock = rs.getInt(7), unitsOnOrder = rs.getInt(8), reorderLevel = rs.getInt(9);
                boolean discontinued = rs.getInt(10) == 1 ? true : false;
                Product pro = new Product(pid, pname, supplierID, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);

                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int delete(int id) {
        int n = 0;
        String sql = "DELETE FROM [Order Details] WHERE ProductID="+id+"\n"
                + "DELETE FROM Products WHERE ProductID=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        String cate="CategoryID",order="asc",type="ProductID";
        String sql = "Select b.ProductID,b.ProductName,b.QuantityPerUnit,b.UnitPrice,b.UnitsInStock,b.UnitsOnOrder,a.CategoryID,a.CategoryName,s.SupplierID,s.CompanyName \n"
                + "from Categories a join Products b on a.CategoryID=b.CategoryID\n"
                + "join Suppliers s on s.SupplierID=b.SupplierID where CategoryID="+cate+" order by "+type+" "+order;
        System.out.println(sql);
    }
}
