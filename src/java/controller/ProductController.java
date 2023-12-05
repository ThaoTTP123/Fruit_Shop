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
import java.sql.ResultSet;
import model.DAOProduct;

/**
 *
 * @author lenovo
 */
public class ProductController extends HttpServlet {

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
            DAOProduct dao = new DAOProduct();
            String service = request.getParameter("service");
            if (service.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsCate = dao.getData("Select * from Categories");
                    ResultSet rsSupp = dao.getData("Select * from Suppliers");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("rsSupp", rsSupp);
                    request.getRequestDispatcher("/jsp/insertProduct.jsp").forward(request, response);
                } else {
                    String productName = request.getParameter("pname");
                    String supplierID = request.getParameter("supplier"),
                            categoryID = request.getParameter("Category");
                    String quantityPerUnit = request.getParameter("quantityPerUnit");
                    String unitPrice = request.getParameter("unitPrice");
                    String unitsInStock = request.getParameter("unitsInStock"),
                            unitsOnOrder = request.getParameter("unitsOnOrder"),
                            reorderLevel = request.getParameter("reorderLevel");
                    String discontinued = request.getParameter("discontinued");
                    //check data
                    // convert
                    int supplierIDInt = Integer.parseInt(supplierID),
                            categoryIDInt = Integer.parseInt(categoryID);
                    double unitPriceDouble = Double.parseDouble(unitPrice);
                    int unitsInStockInt = Integer.parseInt(unitsInStock),
                            unitsOnOrderInt = Integer.parseInt(unitsOnOrder),
                            reorderLevelInt = Integer.parseInt(reorderLevel);
                    boolean discontinuedBool = Integer.parseInt(discontinued) == 1 ? true : false;
                    Product pro = new Product(productName, supplierIDInt,
                            categoryIDInt, quantityPerUnit, unitPriceDouble,
                            unitsInStockInt, unitsOnOrderInt,
                            reorderLevelInt, discontinuedBool);
                    int n = dao.addProduct(pro);
                    //check n
                    response.sendRedirect("MainController");
                }
            }
            if (service.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Product pro = dao.searchByID(id);
                    //view form: call jsp
                    //set data
                    ResultSet rsCate = dao.getData("Select * from Categories");
                    ResultSet rsSupp = dao.getData("Select * from Suppliers");
                    request.setAttribute("pro", pro);
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("rsSupp", rsSupp);
                    request.getRequestDispatcher("/jsp/updateProduct.jsp").forward(request, response);
                } else {
                    String productName = request.getParameter("pname");
                    String supplierID = request.getParameter("supplier"),
                            categoryID = request.getParameter("Category");
                    String quantityPerUnit = request.getParameter("quantityPerUnit");
                    String unitPrice = request.getParameter("unitPrice");
                    String unitsInStock = request.getParameter("unitsInStock"),
                            unitsOnOrder = request.getParameter("unitsOnOrder"),
                            reorderLevel = request.getParameter("reorderLevel");
                    String discontinued = request.getParameter("discontinued");
                    // check data
                    // convert
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    int supplierIDInt = Integer.parseInt(supplierID),
                            categoryIDInt = Integer.parseInt(categoryID);
                    double unitPriceDouble = Double.parseDouble(unitPrice);
                    int unitsInStockInt = Integer.parseInt(unitsInStock),
                            unitsOnOrderInt = Integer.parseInt(unitsOnOrder),
                            reorderLevelInt = Integer.parseInt(reorderLevel);
                    boolean discontinuedBool = Integer.parseInt(discontinued) == 1 ? true : false;
                    Product pro = new Product(pid, productName, supplierIDInt,
                            categoryIDInt, quantityPerUnit, unitPriceDouble,
                            unitsInStockInt, unitsOnOrderInt,
                            reorderLevelInt, discontinuedBool);
                    int n = dao.updateProduct(pro);
                    //check n
                    response.sendRedirect("MainController");
                }
            }
            if (service.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int n = dao.removeProduct(id);
                response.sendRedirect("MainController");
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ProductController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ProductController at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
