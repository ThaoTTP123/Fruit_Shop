/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import model.DAOProduct;

/**
 *
 * @author lenovo
 */
public class MainController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            DAOProduct dao = new DAOProduct();
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("listAll")) {
                ResultSet rsCate = dao.getData("Select CategoryID,CategoryName from Categories");
                String col = request.getParameter("col");
                String order = request.getParameter("order");
                String cate = request.getParameter("cate");
                if (cate == null) {
                    cate = "a.CategoryID";
                }
                if (order == null) {
                    order = "asc";
                }
                if (col == null) {
                    col = "ProductID";
                }
                ResultSet rsPro = dao.getAllByOrder(col, order, cate);
                request.setAttribute("cate", cate);
                request.setAttribute("col", col);
                request.setAttribute("order", order);
                // select view: jsp
                RequestDispatcher disp = request.getRequestDispatcher("MainController");
                request.setAttribute("mRs", rsCate);
                request.setAttribute("cRs", rsPro);
                String role=(String)session.getAttribute("role");
                if(role==null)role="";
                if(role.equals("emp")){
                    request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);
                    return;
                }
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/index.jsp");
                dis.forward(request, response);
            }
            if (service.equals("listMenu")) {
                ResultSet rsCate = dao.getData("Select CategoryID,CategoryName from Categories");
                int cateID = Integer.parseInt(request.getParameter("id"));
                ResultSet rsPro = dao.searchByCateID(cateID);
                request.setAttribute("mRs", rsCate);
                request.setAttribute("cRs", rsPro);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/index.jsp");
                String role=(String)session.getAttribute("role");
                if(role.equals("emp")){
                    request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);
                    return;
                }
                dis.forward(request, response);
                return;
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
