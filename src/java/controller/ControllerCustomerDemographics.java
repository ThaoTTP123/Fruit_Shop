/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CustomerDemographics;
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
import model.DAOCustomerDemographics;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "ControllerCustomerDemographics", urlPatterns = {"/CustomerDemographicsURL"})
public class ControllerCustomerDemographics extends HttpServlet {

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
        DAOCustomerDemographics dao=new DAOCustomerDemographics();
        try ( PrintWriter out = response.getWriter()) {
            String service=request.getParameter("service");
            if(service==null)service="listAll";
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerCustomerDemographics</title>");
            out.println("</head>");
            out.println("<body>");
            if (service.equals("listAll")) {
                ResultSet rs = dao.getAll();
                display(rs, out);
            }
            if(service.equals("insertCustomerDemographics")){
                String customerTypeID = request.getParameter("CustomerTypeID");
                String CustomerDesc = request.getParameter("CustomerDesc");
                if(customerTypeID==null||customerTypeID.equals("")){
                    out.print("pls input CustomerTypeID");
                }
                CustomerDemographics c=new CustomerDemographics(customerTypeID, CustomerDesc);
                int n=dao.addCustomerDemographics(c);
                response.sendRedirect("CustomerDemographicsURL");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void display(ResultSet rs, PrintWriter out) {
        out.print("<table>\n"
                + "    <tr>\n"
                + "        <th>CustomerTypeID</th>\n"
                + "        <th>CustomerDesc</th>\n"
                + "        <th>update</th>\n"
                + "        <th>delete</th>\n"
                + "    </tr>");
        try {
            while (rs.next()) {
                out.print("<tr>\n"
                        + "        <td>"+rs.getString(1)+"</td>\n"
                        + "        <td>"+rs.getString(2)+"</td>\n"
                        + "        <td></td>\n"
                        + "        <td></td>\n"
                        + "    </tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
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
