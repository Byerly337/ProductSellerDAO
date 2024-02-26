package Util;

import DAO.ProductDAO;
import DAO.SellerDAO;
import Service.ProductService;
import Service.SellerService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.sql.Connection;
import Controller.Controller;
public class Application {
    Connection conn = ConnectionSingleton.getConnection();
    SellerDAO sellerDAO = new SellerDAO(conn);
    ProductDAO productDAO = new ProductDAO(conn);
    SellerService sellerService = new SellerService(sellerDAO);
    ProductService productService = new ProductService(productDAO);
    Controller controller = new Controller(sellerService, productService);
    Javalin api = controller.getAPI();

 //        api.start(9000);
}
