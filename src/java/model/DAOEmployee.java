/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Minh Tuan
 */
public class DAOEmployee extends DBconnect {

    public ResultSet getAll() {
        String sql = "Select * from Employees";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int addEmployee(Employee e) {
        int n = 0;
        String sqlQuery = "INSERT INTO Employees\n"
                + "           ([LastName]\n"
                + "           ,[FirstName]\n"
                + "           ,[Title]\n"
                + "           ,[TitleOfCourtesy]\n"
                + "           ,[BirthDate]\n"
                + "           ,[HireDate]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[HomePhone]\n"
                + "           ,[Extension]\n"
                + "           ,[Photo]\n"
                + "           ,[Notes]\n"
                + "           ,[ReportsTo]\n"
                + "           ,[PhotoPath])\n"
                + "     VALUES\n"
                + "           ('" + e.getLastName() + "'\n"
                + "           ,'" + e.getFirstName() + "'\n"
                + "           ,'" + e.getTitle() + "'\n"
                + "           ,'" + e.getTitleOfCourtesy() + "'\n"
                + "           ,'" + e.getBirthDate() + "'\n"
                + "           ,'" + e.getHireDate() + "'\n"
                + "           ,'" + e.getAddress() + "'\n"
                + "           ,'" + e.getCity() + "'\n"
                + "           ,'" + e.getRegion() + "'\n"
                + "           ,'" + e.getPostalCode() + "'\n"
                + "           ,'" + e.getCountry() + "'\n"
                + "           ,'" + e.getHomePhone() + "'\n"
                + "           ,'" + e.getExtension() + "'\n"
                + "           ,'" + e.getPhoto() + "'\n"
                + "           ,'" + e.getNotes() + "'\n"
                + "           ," + e.getReportsTo() + "\n"
                + "           ,'" + e.getPhotoPath() + "')";
        try{
            Statement state=conn.createStatement();
            n=state.executeUpdate(sqlQuery);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return n;
    }

    public void display() {
        String sql = "SELECT * FROM Employees";
        try {
            //create statement
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //run and get result
            ResultSet rs = statement.executeQuery(sql);
            //
            while (rs.next()) {
                int employeeID = rs.getInt(1);
                String lastName = rs.getString(2),
                        firstName = rs.getString(3),
                        title = rs.getString(4),
                        titleOfCourtesy = rs.getString(5);
                String birthDate = rs.getString(6),
                        hireDate = rs.getString(7);
                String address = rs.getString(8),
                        city = rs.getString(9),
                        region = rs.getString(10),
                        postalCode = rs.getString(11),
                        country = rs.getString(12),
                        homePhone = rs.getString(13),
                        extension = rs.getString(14),
                        photo = rs.getString(15),
                        notes = rs.getString(16);
                int reportsTo = rs.getInt(17);
                String photoPath = rs.getString(18);
                Employee emp = new Employee(employeeID, lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode, country, homePhone, extension, photo, notes, reportsTo, photoPath);

                System.out.println(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOEmployee dao = new DAOEmployee();
        
    }
}
