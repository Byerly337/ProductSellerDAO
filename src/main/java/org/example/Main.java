package org.example;

import Controller.Controller;
import DAO.ProductDAO;
import DAO.SellerDAO;
import Service.ProductService;
import Service.SellerService;
import Util.ConnectionSingleton;
import io.javalin.Javalin;
import org.slf4j.Logger;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Logger log;

    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductDAO productDAO = new ProductDAO(conn);
        SellerService sellerService = new SellerService(sellerDAO);
        ProductService productService = new ProductService(productDAO);
        Controller controller = new Controller(sellerService, productService);
        Javalin api = controller.getAPI();

        api.start(9002);
    }
}