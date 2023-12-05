/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOCategory extends DBconnect {
    public ResultSet getAll() {
        String sql = "Select * from Categories";
        ResultSet rs=getData(sql);
        return rs;
    }
    public int addCategory(Category c) {
        int n=0;
        String sql = "INSERT INTO [Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES("
                + "'" + c.getCategoryName() + "'"
                + ",'" + c.getDescription() + "'"
                + ",'" + c.getPicture() + "')";
        try{
            Statement state=conn.createStatement();
            n=state.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return n;
    }   
    public Vector<Category> searchName(String cName){
        Vector<Category> vector=new Vector<Category>();
        String sql="Select * from Categories where CategoryName = "+cName;
        ResultSet rs=getData(sql);
        try {
            while(rs.next()){
                int CategoryID=rs.getInt(1);
                String CategoryName=rs.getString(2);
                String Description=rs.getString(3);
                String Picture=rs.getString(4);
                Category c=new Category(CategoryID, CategoryName, Description, Picture);
                vector.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public void display() {
        String sql = "SELECT * FROM Categories";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int categoryID = rs.getInt(1);
                String categoryName = rs.getString(2);
                String description = rs.getString(3);
                String picture = rs.getString(4);

                Category cate = new Category(categoryID, categoryName, description, picture);

                System.out.println(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        dao.display();
    }
}
