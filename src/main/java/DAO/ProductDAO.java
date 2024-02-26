package DAO;

import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    Connection conn;
    public void Product(Connection conn){
        this.conn = conn;
    }

    public ProductDAO(Connection conn) {
    }

    public void insertProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "PRODUCT (product_id, product_name, price, seller_name) values (?, ?, ?, ?)");

            ps.setInt(1, p.getProductId());
            ps.setString(2, p.getProductName());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getSellerName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts(){
        List<Product> resultProducts = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from PRODUCT");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int productId = rs.getInt("product_id");
                String productName = rs.getString("Product_name");
                double price = rs.getDouble("price");
                String sellerName = rs.getString("seller_name");
                Product p = new Product(productId, productName, price, sellerName);
                resultProducts.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultProducts;
    }
    public Product getProductById(int productId){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from PRODUCT where product_id = ?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                productId = rs.getInt("product_id");
                String productName  = rs.getString("product_name");
                double price = rs.getDouble("price");
                String sellerName = rs.getString("seller_name");
                Product p = new Product(productId, productName, price, sellerName);
                return p;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
