/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Controller.cartlist;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import model.Product;
import model.User;

/**
 *
 * @author Admin
 */
public class AdminServlet extends HttpServlet {

    ArrayList<Product> list = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
    HttpSession session;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String page = request.getParameter("page");

        if (page == null) {
            request.getRequestDispatcher("admin/login.jsp").forward(request, response);;
        } else if (page.equals("index")) {
            DAO db = new DAO();
            try {
                list = db.fetch();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            session = request.getSession();
            session.setAttribute("list", list);
            request.getRequestDispatcher("admin/index.jsp").forward(request, response);;
        } else {
            doPost(request, response);
        }
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
        String page = request.getParameter("page");
        if (page.equals("admin-login-form")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username.equals("admin") && password.equals("123")) {
                DAO db = new DAO();
                try {
                    list = db.fetch();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                session = request.getSession();
                session.setAttribute("list", list);
                request.getRequestDispatcher("admin/index.jsp").forward(request, response);;

            } else {
                request.setAttribute("msg", "Invalid Crediantials");
                request.setAttribute("username", username);
                request.getRequestDispatcher("admin/login.jsp").forward(request, response);

            }
        }

        if (page.equals("delete")) {
            String id = request.getParameter("id");
            DAO db = new DAO();
            try {
                db.deleteProduct(id);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //JOptionPane.showMessageDialog(null, "Product deleted successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
            request.getRequestDispatcher("admin/index.jsp").forward(request, response);

        }

        if (page.equals("index")) {
            request.getRequestDispatcher("admin/index.jsp").forward(request, response);
        }

        if (page.equals("addproduct")) {
            request.getRequestDispatcher("admin/addProduct.jsp").forward(request, response);
        }

        if (page.equals("edit")) {
            String id = request.getParameter("id");
            DAO account = new DAO();
            Product p = null;
            try {
                p = account.fetchProduct(id);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            request.setAttribute("p", p);
            request.getRequestDispatcher("admin/editProduct.jsp").forward(request, response);
        }

        if (page.equals("edit_product")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String category = request.getParameter("category");
            String featured = request.getParameter("featured");
            String quantity = request.getParameter("quantity");
            Product p = new Product();
            p.setId(Integer.parseInt(id));
            p.setName(name);
            p.setPrice(price);
            p.setCategory(category);
            p.setFeatured(featured);
            p.setQuantity(Integer.parseInt(quantity));
            DAO account = new DAO();
            try {
                account.updateProduct(p);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // JOptionPane.showMessageDialog(null, "Product details updated successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
            request.getRequestDispatcher("admin/index.jsp").forward(request, response);
        }

        if (page.equals("add_product")) {
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String category = request.getParameter("category");
            String featured = request.getParameter("featured");
            String image = request.getParameter("image");
            String quantity = request.getParameter("quantity");
            Product p = new Product();
            p.setName(name);
            p.setPrice(price);
            p.setCategory(category);
            p.setFeatured(featured);
            p.setImage("img/" + image);
            p.setQuantity(Integer.parseInt(quantity));

            DAO account = new DAO();
            try {
                account.addProduct(p);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //JOptionPane.showMessageDialog(null, "Product added Successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
            request.getRequestDispatcher("admin/index.jsp").forward(request, response);
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
