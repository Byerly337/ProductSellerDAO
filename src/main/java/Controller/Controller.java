package Controller;

import Exceptions.ProductAlreadyExistsException;
import Exceptions.SellerNotFoundException;
import Model.Product;
import Model.Seller;
import Service.ProductService;
import Service.SellerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class Controller {

    SellerService sellerService;
    ProductService productService;
    public Controller(SellerService sellerService, ProductService productService){
        this.sellerService = sellerService;
        this.productService = productService;
    }

    public Javalin getAPI() {
        Javalin app = Javalin.create();
        app.get("seller", context -> {
            List<Seller> sellerList = sellerService.getAllSellers();
            context.json(sellerList);
        });
        app.post("seller", context -> {
            ObjectMapper om = new ObjectMapper();
            Seller s = om.readValue(context.body(), Seller.class);
            sellerService.saveSeller(s);
            context.status(201);
        });
        app.get("seller/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            try{
                Seller s = sellerService.getSellerById(id);
                context.json(s);
            }catch (SellerNotFoundException e){
                context.status(404);
            }
        });
        app.post("product", context -> {
            ObjectMapper om = new ObjectMapper();
            Product p = om.readValue(context.body(), Product.class);
//            try{
                productService.saveProduct(p);
                context.status(201);
//            }catch(ProductAlreadyExistsException e){
                context.status(400);
//                context.result(e.getMessage());
//            }

        });

        app.get("product", context -> {
            List<Product> products = productService.getAllProducts();
                context.json(products);
        });

        return app;
    }

}
