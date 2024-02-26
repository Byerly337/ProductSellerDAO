package Service;

import DAO.ProductDAO;
import Exceptions.ProductAlreadyExistsException;
import Exceptions.ProductException;
import Model.Product;
import org.example.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ProductService {

    ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void saveProduct(Product p) throws ProductException {
        Main.log.info("log, product info added");
        if (p.getProductName() == null || p.getSellerName() == null || p.getPrice() <= 0) {
            throw new ProductException("The product and seller names are required. Price must be more than 0.");
        }
            productDAO.insertProduct(p);

            //    public void saveProduct(Product p) throws ProductAlreadyExistsException {

//        int productId = p.getProductId();

//        if(productDAO.getProductById(productId) == null){
//            productDAO.insertProduct(p);
//        }else{
//            throw new ProductAlreadyExistsException("product with id "+productId+" already exists");
//        }
        }


    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}

