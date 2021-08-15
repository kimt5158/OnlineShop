/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Controller extends HttpServlet {

    ArrayList<Product> list = new ArrayList<>();
    static ArrayList<String> cartlist = new ArrayList<>();
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
            out.println("<title>Servlet Controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        if ((page == null || page.equals("index")) && action == null) {

            DAO db = new DAO();
            try {
                list = db.fetch();
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            int numPros = list.size();
            int numPerPage = 6;
            int numberPage = numPros / numPerPage
                    + (numPros % numPerPage == 0 ? 0 : 1);
            int start, end;
            int numPage;
            String tpage = request.getParameter("numPage");
            try {
                numPage = Integer.parseInt(tpage);
            } catch (NumberFormatException e) {
                numPage = 1;
            }
            start = (numPage - 1) * numPerPage;
            if (numPage * numPerPage > numPros) {
                end = numPros;
            } else {
                end = numPage * numPerPage;
            }
            List<Product> arr = db.getProductByPage(list, start, end);
            request.setAttribute("num", numberPage);

            request.setAttribute("listPage", arr);

            session = request.getSession();
            session.setAttribute("cartlist", cartlist);
            session.setAttribute("list", list);

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (page.equals("index") && action.equals("back")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
        if (page.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        if (page.equals("sign-up")) {
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }

        if (page.equals("sign-up-form")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String address = request.getParameter("address");
            String password_1 = request.getParameter("password_1");
            String password_2 = request.getParameter("password_2");

            if (password_1.equals(password_2)) {

                User user = new User();
                user.setAddress(address);
                user.setEmail(email);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password_1);

                DAO db = new DAO();
                try {
                    db.addUser(user);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                request.setAttribute("username", username);
                request.setAttribute("msg", "Account created successfully, Please Login!");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } else {
                request.setAttribute("msg", "The two passwords do not match");
                request.setAttribute("name", name);
                request.setAttribute("address", address);
                request.setAttribute("email", email);
                request.setAttribute("username", username);
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }

        }

        if (page.equals("login-form")) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            DAO db = new DAO();
            User user = new User();
            boolean status = false;
            try {
                status = db.checkUser(username, password);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (status) {
                session = request.getSession();
                session.setAttribute("session", session);

                try {
                    userList = db.fetchUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("address", user.fetchadd(userList, username));
                session.setAttribute("email", user.fetchemail(userList, username));
                session.setAttribute("name", user.fetchname(userList, username));
                session.setAttribute("username", username);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Invalid Crediantials");
                request.setAttribute("username", username);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        }

        if (page.equals("logout")) {
            session = request.getSession();
            session.invalidate();

            session = request.getSession();
            cartlist.clear();
            session.setAttribute("cartlist", cartlist);
            session.setAttribute("list", list);

            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        if (page.equals("mobiles") || page.equals("laptops") || page.equals("clothing") || page.equals("home-decor") || page.equals("all-products")) {
            DAO db = new DAO();
            try {
                list = db.fetch();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            request.setAttribute("list", list);

            if (page.equals("mobiles")) {
                request.getRequestDispatcher("mobiles.jsp").forward(request, response);
            }
            if (page.equals("laptops")) {
                request.getRequestDispatcher("laptops.jsp").forward(request, response);
            }
            if (page.equals("clothing")) {
                request.getRequestDispatcher("clothing.jsp").forward(request, response);
            }
            if (page.equals("home-decor")) {
                request.getRequestDispatcher("home-decor.jsp").forward(request, response);
            }
            if (page.equals("all-products")) {
                request.getRequestDispatcher("all-products.jsp").forward(request, response);
            }
        }

        if (page.equals("showcart")) {
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        if (page.equals("addtocart")) {
            String id = request.getParameter("id");
            String action = request.getParameter("action");
            Product p = new Product();
            int id2 = Integer.parseInt(request.getParameter("id"));
            boolean check = p.check(cartlist, id);

            PrintWriter out = response.getWriter();
            if (check) {

                // JOptionPane.showMessageDialog(null, "Product is already added to Cart", "Info", JOptionPane.INFORMATION_MESSAGE);
                for (Product product : list) {
                    if (product.getId() == id2) {
                        product.setQuantity(product.getQuantity() + 1);
                    }
                }
            } else {
                for (Product product : list) {
                    if (product.getId() == id2) {
                        if (product.getQuantity() < 1) {
                            product.setQuantity(1);
                        }
                    }
                }
                cartlist.add(id);
                //JOptionPane.showMessageDialog(null, "Product successfully added to Cart", "Info", JOptionPane.INFORMATION_MESSAGE);

            }

            if (action.equals("index")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (action.equals("allproducts")) {
                request.getRequestDispatcher("all-products.jsp").forward(request, response);
            }
            if (action.equals("clothing")) {
                request.getRequestDispatcher("clothing.jsp").forward(request, response);
            }
            if (action.equals("home-decor")) {
                request.getRequestDispatcher("home-decor.jsp").forward(request, response);
            }
            if (action.equals("laptops")) {
                request.getRequestDispatcher("laptops.jsp").forward(request, response);
            }
            if (action.equals("mobiles")) {
                request.getRequestDispatcher("mobiles.jsp").forward(request, response);
            }
        }

        if (page.equals("success")) {

            request.getRequestDispatcher("success.jsp").forward(request, response);

            /*session = request.getSession();
			 cartlist.clear();
			 session.setAttribute("cartlist", cartlist);*/
        }

        if (page.equals("remove")) {
            String id = request.getParameter("id");
            Product p = new Product();
            cartlist = p.remove(cartlist, id);
            for (Product product : list) {
                if (product.getId() == Integer.parseInt(id)) {
                    product.setQuantity(product.getQuantity() + 1);
                }
            }

            session = request.getSession();
            session.setAttribute("cartlist", cartlist);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        if (page.equals("price-sort")) {
            String price = request.getParameter("sort");
            String action = request.getParameter("action");
            Product p = new Product();
            if (price.equals("low-to-high")) {
                list = p.lowtohigh(list);
            } else {
                list = p.hightolow(list);
            }

            session.setAttribute("list", list);

            if (action.equals("index")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (action.equals("all-products")) {
                request.getRequestDispatcher("all-products.jsp").forward(request, response);
            }
            if (action.equals("mobiles")) {
                request.getRequestDispatcher("mobiles.jsp").forward(request, response);
            }
            if (action.equals("laptops")) {
                request.getRequestDispatcher("laptops.jsp").forward(request, response);
            }
            if (action.equals("clothing")) {
                request.getRequestDispatcher("clothing.jsp").forward(request, response);
            }
            if (action.equals("home-decor")) {
                request.getRequestDispatcher("home-decor.jsp").forward(request, response);
            }
        }

        if (page.equals("product-search")) {
            String product = request.getParameter("search");
            String action = request.getParameter("action");
            Product p = new Product();
            DAO dal = new DAO();
            try {
                list = dal.searchProduct(product);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.setAttribute("list", list);

            if (action.equals("index")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (action.equals("all-products")) {
                request.getRequestDispatcher("all-products.jsp").forward(request, response);
            }
            if (action.equals("mobiles")) {
                request.getRequestDispatcher("mobiles.jsp").forward(request, response);
            }
            if (action.equals("laptops")) {
                request.getRequestDispatcher("laptops.jsp").forward(request, response);
            }
            if (action.equals("clothing")) {
                request.getRequestDispatcher("clothing.jsp").forward(request, response);
            }
            if (action.equals("home-decor")) {
                request.getRequestDispatcher("home-decor.jsp").forward(request, response);
            }
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
