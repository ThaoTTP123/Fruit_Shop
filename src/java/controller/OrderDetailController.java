/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.OrderDetail;
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
import model.DAOCustomer;
import model.DAOOrderDetail;
import model.DAOProduct;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetailController"})
public class OrderDetailController extends HttpServlet {

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
            HttpSession session = request.getSession();
            String service = request.getParameter("service");
            DAOOrderDetail daoOrd = new DAOOrderDetail();
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("listAll")) {
                String id=request.getParameter("id");
                String cusid=request.getParameter("cus");
                DAOCustomer daoCus=new DAOCustomer();
                Customer c=daoCus.getCus(cusid);
                String name=c.getContactName();
                ResultSet rs=daoOrd.getOrderDetailByID(Integer.parseInt(id));
                request.setAttribute("name", name);
                request.setAttribute("rsOrderDetail", rs);
                request.getRequestDispatcher("/jsp/showOrderDetail.jsp").forward(request, response);
            }
            if(service.equals("listNew")){
                String id=request.getParameter("id");
                ResultSet rs=daoOrd.getOrderDetailByID(Integer.parseInt(id));
                request.setAttribute("rsOrderDetail", rs);
                request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
            }
            if (service.equals("insert")) {
                int oID = Integer.parseInt(request.getParameter("id"));
                java.util.Enumeration em = session.getAttributeNames();
                DAOProduct daoPro = new DAOProduct();

                while (em.hasMoreElements()) {
                    String raw_id = em.nextElement().toString();
                    if (!raw_id.equals("account")&&!raw_id.equals("role")) {
                        int id = Integer.parseInt(raw_id);
                        Product pro = daoPro.searchByID(id);
                        double unitprice = pro.getUnitPrice();
                        int quantity = (Integer) session.getAttribute(raw_id);
                        OrderDetail ord = new OrderDetail(oID, id, unitprice, quantity, 0);
                        int n = daoOrd.addOrderDetail(ord);
                        session.removeAttribute(raw_id);
                    }
                }
                response.sendRedirect("OrderDetailController?service=listNew&id="+oID);
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
