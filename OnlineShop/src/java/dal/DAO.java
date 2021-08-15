/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import model.Product;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DAO extends DBContext {

    ArrayList<Product> list = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();

    public void addUser(User user) throws SQLException {

        String sql = "Insert into [user](name,address,email,username,password) values(?,?,?,?,?)";
        PreparedStatement st = connection.prepareStatement(sql);

        st.setString(1, user.getName());
        st.setString(2, user.getAddress());
        st.setString(3, user.getEmail());
        st.setString(4, user.getUsername());
        st.setString(5, user.getPassword());

        st.executeUpdate();

    }

    public boolean checkUser(String username, String password) throws SQLException {

        int count = 0;
        String sql = "Select * from [user] where username = ? and password = ?";
        PreparedStatement st = connection.prepareStatement(sql);

        st.setString(1, username);
        st.setString(2, password);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            count = 1;
        }

        if (count == 0) {
            return false;
        }

        return true;
    }

    public ArrayList<Product> fetch() throws SQLException {

        String sql = "Select * from product";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            String price = rs.getString("price");
            String featured = rs.getString("featured");
            String image = rs.getString("image");

            Product p = new Product();
            p.setCategory(category);
            p.setFeatured(featured);
            p.setId(id);
            p.setImage(image);
            p.setName(name);
            p.setPrice(price);
            list.add(p);
            p = null;

        }

        return list;
    }

    public ArrayList<Product> getProductByPage(ArrayList<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }

        return arr;
    }

    public ArrayList<User> fetchUser() throws SQLException {

        String sql = " Select * from [user] ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String address = rs.getString("address");
            String user = rs.getString("username");
            String email = rs.getString("email");
            String name = rs.getString("name");
            int id = rs.getInt("id");
            String password = rs.getString("password");

            User u = new User();
            u.setAddress(address);
            u.setEmail(email);
            u.setId(id);
            u.setName(name);
            u.setUsername(user);
            u.setPassword(password);
            userList.add(u);
            u = null;

        }

        return userList;
    }

    public void deleteProduct(String id) throws SQLException {

        String sql = "Delete from product where id=?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, id);
        st.executeUpdate();

    }

    public Product fetchProduct(String id) throws SQLException {

        String sql = "select * from product where id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rst = pstmt.executeQuery();
        Product p = new Product();
        while (rst.next()) {

            p.setId(rst.getInt("id"));
            p.setName(rst.getString("name"));
            p.setPrice(rst.getString("price"));
            p.setCategory(rst.getString("category"));
            p.setFeatured(rst.getString("featured"));
            p.setImage(rst.getString("image"));
            p.setQuantity(rst.getInt("quantity"));
        }

        return p;
    }

    public void updateProduct(Product p) throws SQLException {

        String sql = "update product set name=?,price=?,category=?,featured=?,quantity=? where id=?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, p.getName());
        st.setString(2, p.getPrice());
        st.setString(3, p.getCategory());
        st.setString(4, p.getFeatured());
        st.setInt(5, p.getQuantity());
        st.setInt(6, p.getId());
        st.executeUpdate();

    }

    public void addProduct(Product p) throws SQLException {
        
        String sql = "Insert into product(name,price,category,featured,image,quantity) values(?,?,?,?,?,?)";
        PreparedStatement st = connection.prepareStatement(sql);

        st.setString(1, p.getName());
        st.setString(2, p.getPrice());
        st.setString(3, p.getCategory());
        st.setString(4, p.getFeatured());
        st.setString(5, p.getImage());
        st.setInt(6, p.getQuantity());

        st.executeUpdate();

    }
    
    public ArrayList<Product> searchProduct(String name) throws SQLException{
        String sql = "SELECT * FROM product WHERE name LIKE ?";
        ArrayList<Product> product = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + name + "%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            product.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("price"), rs.getString("category"), 
                    rs.getString("featured"), rs.getString("image")));
        }
        
        
        return product;
    }

}
