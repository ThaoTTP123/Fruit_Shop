/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOSupplier;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "ControllerSupplier", urlPatterns = {"/SupplierURL"})
public class ControllerSupplier extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOSupplier dao = new DAOSupplier();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerSupplier</title>");
            out.println("</head>");
            out.println("<body>");
            String service=request.getParameter("service");
            if(service==null){
                service="listAll";
            }
            if(service.equals("listAll")){
                ResultSet rs=dao.getAll();
                display(rs, out);
            }
            if(service.equals("insertsupplier")){
                String companyName = request.getParameter("CompanyName"),
                        contactName = request.getParameter("Contactname"),
                        contactTitle = request.getParameter("ContactTitle"),
                        address = request.getParameter("Address"),
                        city = request.getParameter("City"),
                        region = request.getParameter("Region"),
                        postalCode = request.getParameter("PostalCode"),
                        country = request.getParameter("Country"),
                        phone = request.getParameter("Phone"),
                        fax = request.getParameter("Fax"),
                        homePage = request.getParameter("HomePage");
                Supplier s=new Supplier(companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, homePage);
                int n=dao.addSupplier(s);
                response.sendRedirect("SupplierURL");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger(ControllerSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void display(ResultSet rs,PrintWriter out){
        out.print("<table>\n"
                    + "    <caption>Employee list</caption>\n"
                    + "    <tr>\n"
                    + "        <th>SupplierID</th>\n"
                    + "        <th>CompanyName</th>\n"
                    + "        <th>Contactname</th>\n"
                    + "        <th>ContactTitle</th>\n"
                    + "        <th>Address</th>\n"
                    + "        <th>City</th>\n"
                    + "        <th>Region</th>\n"
                    + "        <th>PostalCode</th>\n"
                    + "        <th>Country</th>\n"
                    + "        <th>Phone</th>\n"
                    + "        <th>Fax</th>\n"
                    + "        <th>HomePage</th>\n"
                    + "        <th>update</th>\n"
                    + "        <th>delete</th>\n"
                    + "    </tr>");
        try{
            while (rs.next()) {
                out.print("<tr>\n"
                        + "        <td>"+rs.getString(1)+"</td>\n"
                        + "        <td>"+rs.getString(2)+"</td>\n"
                        + "        <td>"+rs.getString(3)+"</td>\n"
                        + "        <td>"+rs.getString(4)+"</td>\n"
                        + "        <td>"+rs.getString(5)+"</td>\n"
                        + "        <td>"+rs.getString(6)+"</td>\n"
                        + "        <td>"+rs.getString(7)+"</td>\n"
                        + "        <td>"+rs.getString(8)+"</td>\n"
                        + "        <td>"+rs.getString(9)+"</td>\n"
                        + "        <td>"+rs.getString(10)+"</td>\n"
                        + "        <td>"+rs.getString(11)+"</td>\n"
                        + "        <td>"+rs.getString(12)+"</td>\n"
                        + "    </tr>");
            }
        } catch (Exception ex) {
            Logger.getLogger(ControllerSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.print("</table>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
