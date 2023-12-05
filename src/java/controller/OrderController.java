/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Employee;
import entity.Order;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import model.DAOOrder;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

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
            HttpSession session = request.getSession();
            DAOOrder dao = new DAOOrder();
            String service = request.getParameter("service");
            String submit = request.getParameter("submit");
            if(service==null)service="listAll";
            if(service.equals("listAll")){
                ResultSet rs=null;
                String role=(String)session.getAttribute("role");
                if(role.equals("emp")){
                    Employee e=(Employee) session.getAttribute("account");
                    rs=dao.searchOrderByEmp(e.getEmployeeID());
                    request.setAttribute("name", e.getLastName()+" "+e.getFirstName());
                }
                else{
                    Customer c=(Customer) session.getAttribute("account");
                    rs=dao.searchOrderByCus(c.getCustomerID());
                    request.setAttribute("name", c.getContactName());
                }
                request.setAttribute("rsOrder", rs);
                request.getRequestDispatcher("/jsp/showOrder.jsp").forward(request, response);
            }
            if (service.equals("insert")) {
                if (submit == null) {
                    ResultSet rsEmp = dao.getData("Select * from Employees");
                    ResultSet rsVia = dao.getData("Select * from Shippers");
                    request.setAttribute("rsEmp", rsEmp);
                    request.setAttribute("rsVia", rsVia);
                    request.getRequestDispatcher("/jsp/insertOrder.jsp").forward(request, response);
                } else {
                    String customerID = request.getParameter("customerID");
                    int employeeID = Integer.parseInt(request.getParameter("employeeID"));
                    String orderDate = request.getParameter("orderDate"),
                            requiredDate = request.getParameter("requiredDate"),
                            shippedDate = request.getParameter("shippedDate");
                    int shipVia = Integer.parseInt(request.getParameter("shipVia"));
                    double freight = Double.parseDouble(request.getParameter("freight"));
                    String shipName = request.getParameter("shipName"),
                            shipAddress = request.getParameter("shipAddress"),
                            shipCity = request.getParameter("shipCity"),
                            shipRegion = request.getParameter("shipRegion"),
                            shipPostalCode = request.getParameter("shipPostalCode"),
                            shipCountry = request.getParameter("shipCountry");
                    Order ord = new Order(customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry);
                    int oID = dao.addOrder(ord);
                    //check n
                    response.sendRedirect("OrderDetailController?id=" + oID + "&service=insert");
                }
            }
        }
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
