/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOCustomer;
import model.DAOEmployee;

/**
 *
 * @author lenovo
 */
public class ControllerAccount extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.print("<h1 style=\"color: red;\">Login Fail!</h1>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private Customer getCus(ResultSet rs, String username, String password) {
        try {
            while (rs.next()) {
                if (username.equals(rs.getString(1)) && password.equals(rs.getString(10))) {
                    Customer c = new Customer(rs.getString(1), rs.getString(3));
                    return c;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Employee getEmp(ResultSet rs, String username, String password) {
        try {
            while (rs.next()) {
                if (username.equals(rs.getString(1)) && password.equals(rs.getString(13))) {
                    int employeeID = rs.getInt(1);
                    String lastName = rs.getString(2);
                    String firstName = rs.getString(3);
                    Employee e = new Employee(employeeID, lastName, firstName);
                    return e;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        doPost(request, response);
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
        HttpSession session = request.getSession();
        String service = request.getParameter("service");
        if (service == null) {
            service = "login";
        }
        if (service.equals("login")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                return;
            } else {
                DAOCustomer daoCus = new DAOCustomer();
                DAOEmployee daoEmp = new DAOEmployee();
                ResultSet rs = null;
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                rs = daoCus.getAll();
                Employee e = null;
                Customer c = getCus(rs, username, password);
                if (c == null) {
                    rs = daoEmp.getAll();
                    e = getEmp(rs, username, password);
                    session.setAttribute("role", "emp");
                } else {
                    session.setAttribute("account", c);
                    session.setAttribute("role", "cus");
                    response.sendRedirect("MainController");
                    return;
                }
                if (e != null) {
                    session.setAttribute("account", e);
                    response.sendRedirect("MainController");
                    return;
                } else {
                    request.setAttribute("error", "Login Fail!");
                    request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                    return;
                }
            }
        }
        if (service.equals("logout")) {
            session.removeAttribute("account");
            session.removeAttribute("role");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            return;
        }
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
