/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

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
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("listAll")) {
                request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);
            }
            if (service.equals("addCart")) {
                String id = request.getParameter("id");
                //     id - key
                //        get infor of product: name, price, images
                //         create product 
//                Object value = session.getAttribute(id); //getKey(id)
                //	the first time the product is selected
                if (session.getAttribute(id) == null) {
                    session.setAttribute(id, 1);
                } //the second/third... time the product is selected
                else {
                    int quan = (Integer) session.getAttribute(id);
                    int count = quan + 1;
                    //                pro.setQuantitty(pro.getQuantity()+1);
                    //put name-value pair into session object (see HttpSession)
                    session.setAttribute(id, count);
                    //                session.setAttribute(id,pro);
                }
                response.sendRedirect("CartController");
                return;
            }
            if (service.equals("update")) {
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String raw_id = em.nextElement().toString();
                    if (!raw_id.equals("account")&& !raw_id.equals("role")) {
                        String raw_quantity = request.getParameter("pro" + raw_id);
                        int quantity = Integer.parseInt(raw_quantity);
                        session.setAttribute(raw_id, quantity);
                    }
                }
                response.sendRedirect("CartController");
            }
            if (service.equals("remove")) {
                String pid = request.getParameter("id");
                java.util.Enumeration em = session.getAttributeNames();
                if (pid == null) {
                    while (em.hasMoreElements()) {
                        String raw_id = em.nextElement().toString();
                        if (!raw_id.equals("account") && !raw_id.equals("role")) {
                            session.removeAttribute(raw_id);
                        }
                    }
                }
                session.removeAttribute(pid);
                response.sendRedirect("CartController");
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
